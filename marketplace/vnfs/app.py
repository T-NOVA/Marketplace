__author__ = "George Alexiou (TEIC)"
__email__ = "g.alexiou@pasiphae.eu"

from bson.json_util import dumps
from flask import Flask, request, jsonify, Response
from flask.ext.pymongo import PyMongo
from functools import wraps

from vnfdj2j import createVNFDSLATemplate

import datetime
import requests
import jwt
import yaml
import os

import nfsimages
import nfsapi


BASE_DIR = os.path.dirname(__file__)
UPLOAD_FOLDER = os.path.join(BASE_DIR, 'images')

#UMAA_URL = '127.0.0.1:9000'
UMAA_URL = 'umaa.docker:8000'

with open('/keys/.shared_key', 'r') as content_file:
    SECRET_KEY = content_file.read()

app = Flask(__name__)
app.debug = True

app.config['MONGO_DBNAME'] = 'vnfd_db'
app.config['MONGO_HOST'] = 'mongodb'
#app.config['MONGO_HOST'] = '127.0.0.1'
app.config['MONGO_PORT'] = 27017

mongo = PyMongo(app)

def jwt_required():
    """View decorator that requires a valid JWT token to be present in the request"""
    def wrapper(fn):
        @wraps(fn)
        def decorator(*args, **kwargs):

            auth = request.headers.get('Authorization', None)

            if auth is None:
                response = jsonify(detail="Authentication credentials were not provided.")
                response.status_code = 401
                return response

            parts = auth.split()

            if parts[0] != 'JWT':
                response = jsonify(detail="Unsupported authorization type.")
                response.status_code = 401
                return response
            elif len(parts) == 1:
                response = jsonify(detail="Token is missing.")
                response.status_code = 401
                return response
            elif len(parts) > 2:
                response = jsonify(detail="Token contains spaces.")
                response.status_code = 401
                return response

            try:
                payload = jwt.decode(parts[1], SECRET_KEY, algorithms=['HS256'], verify=False)
                request.user = payload
                request.jwt_token = parts[1]

                # get user permissions
                try:
                    response = requests.get(
                        'http://%s/user-management/profile/permissions/' % UMAA_URL,
                        headers={
                            'Content-Type': 'application/json',
                            'Authorization': 'JWT %s' % request.jwt_token
                        }
                    )

                    if response.status_code != 200:
                        response = jsonify(detail="Required permissions error.")
                        response.status_code = 401
                        return response

                    request.permissions = response.json()
                except:
                    response = jsonify(detail="Permission service (umaa) communication error.")
                    response.status_code = 401
                    return response
            except:
                response = jsonify(detail="Token is undecipherable.")
                response.status_code = 401
                return response

            return fn(*args, **kwargs)
        return decorator
    return wrapper


def has_perm(role):
    if role not in request.permissions:
        return False
    return True


def has_perm_decorator(role):
    """View decorator that requires a valid JWT token to be present in the request"""
    def wrapper(fn):
        @wraps(fn)
        def decorator(*args, **kwargs):

            if role not in request.permissions:
                response = jsonify(detail="You do not have permission to perform this action.")
                response.status_code = 401
                return response

            return fn(*args, **kwargs)
        return decorator
    return wrapper


@app.route('/vnfs/', methods=['GET'])
@jwt_required()
def vnfs():
    vnfds = []
    if has_perm('vnfs.view_all_vnfs'):
        vnfds = mongo.db.vnfds.find()
    elif has_perm('vnfs.view_own_vnfs'):
        vnfds = mongo.db.vnfds.find({'provider_id': request.user['user_id']})
    return Response(dumps(vnfds), mimetype='application/json', status=200)


# is an internal interface for module-to-module communication (for the broker)
@app.route('/internal/vnfs/', methods=['GET'])
def vnfs_internal():
    vnfds = mongo.db.vnfds.find()
    return Response(dumps(vnfds), mimetype='application/json', status=200)


# is an internal interface for module-to-module communication (for service selection)
@app.route('/internal/vnfs/<int:vnfd_id>/', methods=['GET'])
def get_vnf_internal(vnfd_id):
    vnfd = mongo.db.vnfds.find_one({'_id': vnfd_id})
    if vnfd != None:
        del vnfd['_id']
        return Response(dumps(vnfd), mimetype='application/json', status=200)
    else:
        response = jsonify(detail="VNFD not found.")
        response.status_code = 404
        return response


@app.route('/vnfs/', methods=['POST'])
@jwt_required()
@has_perm_decorator('vnfs.create_own_vnfs')
def create_vnf():
    vnfd = request.get_json()

    if vnfd is None:
        response = jsonify(detail="Empty Request body.")
        response.status_code = 404
        return response

    # append some extra fields
    vnfd['provider_id'] = request.user['user_id']
    vnfd['provider'] = request.user['company_name']
    vnfd['created_at'] = datetime.datetime.now().strftime('%Y-%m-%dT%H:%M:%SZ')
    vnfd['modified_at'] = datetime.datetime.now().strftime('%Y-%m-%dT%H:%M:%SZ')

    #rename vdus to vdu
    vnfd['vdu'] = vnfd['vdus']
    del vnfd['vdus']

    code, nfs_response = nfsapi.upload_vnfd(vnfd)
    print code, nfs_response
    if code != 201:
        response = jsonify(detail="NFS POST request failed, code:%s" % code, nfs_error=nfs_response)
        response.status_code = 404
        return response

    code, nfs_vnfd = nfsapi.get_vnfd(nfs_response['vnfd_id'])
    nfs_vnfd['_id'] = nfs_response['vnfd_id']

    # SLA TEMPLATE GENERATION
    try:
        createVNFDSLATemplate(nfs_vnfd)
    except:
        print("VNF SLA template exception error")

    # insert submitted vnfd to your db
    vnfd_id = mongo.db.vnfds.insert(nfs_vnfd)

    if vnfd_id:
        return Response(dumps(vnfd), mimetype='application/json', status=200)
    else:
        response = jsonify(detail="VNFD insert failed.")
        response.status_code = 404
        return response

@app.route('/vnfs/<int:vnfd_id>/', methods=['GET'])
@jwt_required()
def get_vnf(vnfd_id):
    vnfd = mongo.db.vnfds.find_one({'_id': vnfd_id})

    if vnfd != None:
        if has_perm('vnfs.view_all_vnfs') or (has_perm('vnfs.view_own_vnfs') and vnfd['provider_id'] == request.user['user_id']):
            del vnfd['_id']
            return Response(dumps(vnfd), mimetype='application/json', status=200)
        else:
            response = jsonify(detail="You do not have permission to perform this action.")
            response.status_code = 401
            return response
    else:
        response = jsonify(detail="VNFD not found.")
        response.status_code = 404
        return response

@app.route('/vnfs/<int:vnfd_id>/vnfd', methods=['GET'])
#@jwt_required()
def get_vnfd_file(vnfd_id):
    vnfd = mongo.db.vnfds.find_one({'_id': vnfd_id})

    if vnfd != None:
        if True:
        #if has_perm('vnfs.view_all_vnfs') or (has_perm('vnfs.view_own_vnfs') and vnfd['provider_id'] == request.user['user_id']):
            del vnfd['_id']
            return Response(dumps(vnfd), mimetype='application/json', status=200)
        else:
            response = jsonify(detail="You do not have permission to perform this action.")
            response.status_code = 401
            return response
    else:
        response = jsonify(detail="VNFD not found.")
        response.status_code = 404
        return response


@app.route('/vnfs/<int:vnfd_id>/yaml', methods=['GET'])
#@jwt_required()
def get_vnfd_yaml_file(vnfd_id):
    vnfd = mongo.db.vnfds.find_one({'_id': vnfd_id})

    if vnfd != None:
        if True:
        #if has_perm('vnfs.view_all_vnfs') or (has_perm('vnfs.view_own_vnfs') and vnfd['provider_id'] == request.user['user_id']):
            del vnfd['_id']
            return Response(yaml.safe_dump(vnfd, default_flow_style=False), mimetype='text/yaml', status=200)
        else:
            response = jsonify(detail="You do not have permission to perform this action.")
            response.status_code = 401
            return response
    else:
        response = jsonify(detail="VNFD not found.")
        response.status_code = 404
        return response

# @app.route('/vnfs/<int:vnfd_id>/', methods=['PUT'])
# @jwt_required()
# def get_vnf(vnfd_id):
#     vnfd = request.get_json()
#
#     if vnfd is None:
#         response = jsonify(detail="Empty Request body.")
#         response.status_code = 404
#         return response
#
#     # remove _id if exists
#     if '_id' in vnfd:
#         del vnfd['_id']
#
#     vnfd = mongo.db.vnfds.find_one_and_update({'_id': vnfd_id}, {'$inc': vnfd})
#
#     if vnfd != None:
#         return Response(dumps(vnfd), mimetype='application/json', status=200)
#     else:
#         response = jsonify(detail="VNFD not found.")
#         response.status_code = 404
#         return response

@app.route('/vnfs/<int:vnfd_id>/', methods=['DELETE'])
@jwt_required()
def delete_vnf(vnfd_id):

    vnfd = mongo.db.vnfds.find_one({'_id': vnfd_id})

    if vnfd != None:
        if not has_perm('vnfs.delete_all_vnfs') and not (has_perm('vnfs.delete_own_vnfs') and vnfd['provider_id'] == request.user['user_id']):
            response = jsonify(detail="You do not have permission to perform this action.")
            response.status_code = 401
            return response
    else:
        response = jsonify(detail="VNFD not found.")
        response.status_code = 404
        return response

    code, nfs_response = nfsapi.delete_vnfd(vnfd_id)

    if code != 204:
        response = jsonify(detail="NFS DELETE request failed, code:%s" % code)
        response.status_code = 404
        return response

    success = mongo.db.vnfds.remove({'_id': vnfd_id})

    if success:
        return Response(mimetype='application/json', status=204)
    else:
        response = jsonify(detail="VNFD not found.")
        response.status_code = 404
        return response


@app.route('/vnfs/images/', methods=['GET'])
@jwt_required()
def images():
    try:
        image_list = []
        images = nfsimages.get_images()

        for i in images:
            if True:
            #if request.user['user_id'] == i.provider_id:
                image = {'url': i.nfs_image_url, 'provider_id': i.provider_id, 'image_name': i.image_name, 'nfs_image_name': i.nfs_image_name, 'assigned_vnfds': i.nfs_vnfd_id}
                image_list.append(image)
        return Response(dumps(image_list), mimetype='application/json', status=200)

    except nfsimages.NFSImagesListError as e:
        print e.error
        response = jsonify(detail=e.error)
        response.status_code = 404
        return response


@app.route('/vnfs/images/<image_name>/', methods=['DELETE'])
@jwt_required()
def images_list(image_name):
    try:
        image = nfsimages.get_image(image_name)
        image.delete()
        return Response(mimetype='application/json', status=204)
    except nfsimages.NFSImageError as e:
        print e.error
        response = jsonify(detail=e.error)
        response.status_code = 404
        return response


@app.route('/vnfs/images/upload/', methods=['POST'])
@jwt_required()
def upload_image():
    file = request.files['file']
    filename = file.filename
    temp_file_path = os.path.join(UPLOAD_FOLDER, filename)
    file.save(temp_file_path)

    md5sum = request.form.get('md5sum')
    image_type = request.form.get('image_type')

    nfs_image = nfsimages.NFSImage(int(request.user['user_id']), file.filename)
    try:
        nfs_image.upload(temp_file_path, md5sum, image_type)
    except nfsimages.NFSImageUploadError as e:
        os.remove(temp_file_path)
        print e.error
        response = jsonify(detail=e.error)
        response.status_code = 404
        return response

    os.remove(temp_file_path)

    return Response(dumps({'detail': 'Image Uploaded.'}), mimetype='application/json', status=200)


@app.route('/vnfs/enforce', methods=['GET'])
def init_sample_data():
    with app.app_context():
        # remove all vnfds from the nfstore
        code, v = nfsapi.get_vnfd_list()
        for vnfd in v['vnfds']:
            print nfsapi.delete_vnfd(vnfd['id'])

        # remove all images from the nfstore
        # code, v = nfsapi.get_images_list()
        # for i in v['files']:
        #     print nfsapi.delete_image(i['name'])

        temp_file_path = os.path.join(UPLOAD_FOLDER, 'sample-pfsense.img')
        nfs_image = nfsimages.NFSImage(4, 'pfsense.img')
        nfs_image.upload(temp_file_path, '2345094b3b92832abb35162a61f6aa03', 'raw')

        json_data = open(os.path.join(os.path.join(BASE_DIR, 'templates'), 'vnfd0.json')).read()
        code, nfs_response = nfsapi.upload_vnfd(json_data)

        code, nfs_vnfd = nfsapi.get_vnfd(nfs_response['vnfd_id'])
        nfs_vnfd['_id'] = nfs_response['vnfd_id']
        vnfd_id = mongo.db.vnfds.insert(nfs_vnfd)

        # SLA TEMPLATE GENERATION
        try:
            createVNFDSLATemplate(nfs_vnfd)
        except:
            print("VNF SLA template exception error")

        nfs_image = nfsimages.NFSImage(5, 'pfsense.img')
        nfs_image.upload(temp_file_path, '2345094b3b92832abb35162a61f6aa03', 'raw')

        json_data = open(os.path.join(os.path.join(BASE_DIR, 'templates'), 'vnfd1.json')).read()
        code, nfs_response = nfsapi.upload_vnfd(json_data)

        code, nfs_vnfd = nfsapi.get_vnfd(nfs_response['vnfd_id'])
        nfs_vnfd['_id'] = nfs_response['vnfd_id']
        vnfd_id = mongo.db.vnfds.insert(nfs_vnfd)

        # SLA TEMPLATE GENERATION
        try:
            createVNFDSLATemplate(nfs_vnfd)
        except:
            print("VNF SLA template exception error")

        return Response("OK", mimetype='application/json', status=200)


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000, debug=True)