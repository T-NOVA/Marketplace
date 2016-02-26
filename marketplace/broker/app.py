__author__ = "George Alexiou (TEIC)"
__email__ = "g.alexiou@pasiphae.eu"

from bson.json_util import dumps
from bson.objectid import ObjectId
from flask import Flask, request, jsonify, Response
from flask.ext.pymongo import PyMongo
from functools import wraps

import datetime
import requests
import jwt

with open('/keys/.shared_key', 'r') as content_file:
    SECRET_KEY = content_file.read()

app = Flask(__name__)
app.debug = True

#VNFS_URL = 'http://127.0.0.1:9000'
#UMAA_URL = 'http://127.0.0.1:9000'

VNFS_URL = 'http://vnfs.docker:5000' #vnfs.docker:5000
UMAA_URL = 'http://umaa.docker:8000' #umaa.docker:8000


app.config['MONGO_DBNAME'] = 'broker_db'
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
                        '%s/user-management/profile/permissions/' % UMAA_URL,
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


@app.route('/broker/vnfs/', methods=['GET'])
@jwt_required()
def vnfs():
    if has_perm('broker.list_all_vnfs'):

        response = requests.get('%s/internal/vnfs/' % VNFS_URL)

        if response.status_code != 200:
            response = jsonify(detail="Internal request to vnfs.docker failed, code:%s" % response.status_code, error=response.text)
            response.status_code = 404
            return response

        return Response(response.text, mimetype='application/json', status=200)

    else:
        response = jsonify(detail="You do not have permission to perform this action.")
        response.status_code = 401
        return response


@app.route('/broker/vnfs/trade/', methods=['GET'])
@jwt_required()
def get_trades():
    trades = []
    trades_list = mongo.db.trades.find({'provider_id': request.user['user_id']})
    for trade in trades_list:
        t = trade
        t['id'] = str(t['_id'])
        del t['_id']
        trades.append(t)
    return Response(dumps(trades), mimetype='application/json', status=200)


@app.route('/broker/vnfs/trade/', methods=['POST'])
@jwt_required()
def create_trade():
    trade_request = request.get_json()

    if trade_request is None:
        response = jsonify(detail="Empty Request body.")
        response.status_code = 404
        return response

    # append some extra fields
    trade_request['created_at'] = datetime.datetime.now().strftime('%Y-%m-%dT%H:%M:%SZ')
    trade_request['modified_at'] = datetime.datetime.now().strftime('%Y-%m-%dT%H:%M:%SZ')
    trade_request['status'] = 'pending'

    # insert submitted vnfd to your db
    trade = mongo.db.trades.insert(trade_request)
    trade_request['id'] = str(trade)
    if trade:
        return Response(dumps(trade_request), mimetype='application/json', status=200)
    else:
        response = jsonify(detail="trade insert failed.")
        response.status_code = 404
        return response


@app.route('/broker/vnfs/trade/<ObjectId:trade_id>/', methods=['GET'])
@jwt_required()
def get_trade(trade_id):
    trade = mongo.db.trades.find_one_or_404(trade_id)

    if True:
        trade['id'] = str(trade['_id'])
        del trade['_id']
        return Response(dumps(trade), mimetype='application/json', status=200)
    else:
        response = jsonify(detail="You do not have permission to perform this action.")
        response.status_code = 401
        return response


@app.route('/broker/vnfs/trade/<ObjectId:trade_id>/accept/', methods=['GET'])
@jwt_required()
def trade_accept(trade_id):
    mongo.db.trades.find_one_or_404(trade_id)

    mongo.db.trades.update_one({
        '_id': trade_id
    }, {
        '$set': {
            'status': 'accepted'
        }
    }, upsert=False)

    return Response('', mimetype='application/json', status=200)


@app.route('/broker/vnfs/trade/<ObjectId:trade_id>/reject/', methods=['GET'])
@jwt_required()
def trade_reject(trade_id):
    mongo.db.trades.find_one_or_404(trade_id)

    mongo.db.trades.update_one({
        '_id': trade_id
    }, {
        '$set': {
            'status': 'rejected'
        }
    }, upsert=False)

    return Response('', mimetype='application/json', status=200)


def test_database():
    with app.app_context():

        # insert
        test_request = {"test_value": 1, "test_value_2": 2}
        new_rec = mongo.db.trades.insert(test_request)

        rec_id = str(new_rec)
        print 'New Record:' + rec_id

        # find one
        rec_object_id = ObjectId(rec_id)
        rec = mongo.db.trades.find_one_or_404(rec_object_id)
        print rec

        # update one
        mongo.db.trades.update_one({
            '_id': rec_object_id
        }, {
            '$set': {
                'test_value_2': 10
            }
        }, upsert=False)


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000, debug=True)





