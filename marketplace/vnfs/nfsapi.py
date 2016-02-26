from requests_toolbelt import MultipartEncoder
import requests
import json
import uuid
import re

#NFS_HOST = '83.212.108.105'

NFS_HOST = '172.16.6.85'
NFS_PORT = '8080'
USE_HTTPS = False


def _get_call_url(endpoint):
    protocol = 'https' if USE_HTTPS else 'http'
    return '%s://%s:%s/%s' % (protocol, NFS_HOST, NFS_PORT, endpoint.strip('/'))


def upload_image(image_name, image_path, provider_id, md5sum, image_type, json_response=True):
    endpoint = 'NFS/files'

    m = MultipartEncoder(
        fields={
            'file': (image_name, open(image_path), 'application/octet-stream', {'Content-Transfer-Encoding': 'binary', 'Provider-ID': provider_id, 'MD5SUM':md5sum, 'Image-Type':image_type})
        }
    )

    r = requests.post(_get_call_url(endpoint), data=m, headers={'Content-Type': m.content_type})
    if json_response:
        try:
            return r.status_code, r.json()
        except ValueError:
            return r.status_code, r.text
    return r.status_code, r.text


def delete_image(image_name):
    endpoint = 'NFS/files/%s' % image_name

    r = requests.delete(_get_call_url(endpoint))
    return r.status_code, r.text


def get_images_list(json_response=True):
    endpoint = 'NFS/files'

    r = requests.get(_get_call_url(endpoint))
    if json_response:
        try:
            return r.status_code, r.json()
        except ValueError:
            return r.status_code, r.text
    return r.status_code, r.text


def get_image_url(image_name):
    endpoint = 'NFS/files/%s' % image_name
    return _get_call_url(endpoint)


# code, response = upload_image('ubuntu.img', '/home/goten002/Desktop/file.img')
# print code, response
#
# code, response = get_images_list()
# print code, response
#
# print get_image_download_url('ubuntu.img')
#
# code, response = delete_image('ubuntu.img')
# print code, response


def upload_vnfd(vnfd, json_response=True):
    endpoint = 'NFS/vnfds'

    data = json.dumps(vnfd) if isinstance(vnfd, dict) else vnfd

    r = requests.post(_get_call_url(endpoint), data=data, headers={'Content-Type': 'application/json'})
    if json_response:
        try:
            return r.status_code, r.json()
        except ValueError:
            return r.status_code, r.text
    return r.status_code, r.text


def get_vnfd_list(json_response=True):
    endpoint = 'NFS/vnfds'

    r = requests.get(_get_call_url(endpoint))
    if json_response:
        try:
            return r.status_code, r.json()
        except ValueError:
            return r.status_code, r.text
    return r.status_code, r.text


def get_vnfd(vnfd_id, json_response=True):
    endpoint = 'NFS/vnfds/%s' % vnfd_id

    r = requests.get(_get_call_url(endpoint))
    if json_response:
        try:
            return r.status_code, r.json()
        except ValueError:
            return r.status_code, r.text
    return r.status_code, r.text


def delete_vnfd(vnfd_id):
    endpoint = 'NFS/vnfds/%s' % vnfd_id

    r = requests.delete(_get_call_url(endpoint))
    return r.status_code, r.text

#delete vnfd
# code, v = get_vnfd_list()
# print v
# for vnfd in v['vnfds']:
#     print delete_vnfd(vnfd['id'])
# #
# code, v = get_images_list()
# for i in v['files']:
#     print delete_image(i['name'])

# code, response = delete_image('23-ubuntu2.img')
# print code, response

# vnfd = {
#     'vdu':[
#         {
#             'id': 'vdu0',
#             'vm_image': '23-ubuntu2.img'
#         }
#     ]
# }

# code, response = upload_vnfd(vnfd)
# print code, response
# code, response = get_vnfd_list()
# print code, response
# code, response = delete_vnfd(305)
# print code, response
# code, response = get_vnfd(305)
# print code, response


# code, response = get_images_list()
# print code, response

# uuid_regex = '^(?P<uuid>[0-9A-F]{8}-[0-9A-F]{4}-4[0-9A-F]{3}-[89AB][0-9A-F]{3}-[0-9A-F]{12})-(?P<provider_id>[0-9]+)-(?P<image_name>.*)$'
#
# if re.match(uuid_regex, '1b5bdf98-17d7-4c63-bdd9-5056f9bf312d-23-ubuntu.img', re.IGNORECASE):
#     print 'gogo'
#
#
# results = re.search(uuid_regex, '1b5bdf98-17d7-4c63-bdd9-5056f9bf312d-23-dsfdsfdsf-sdaf-dsa -f sad -f-sdf-as-f-asfubuntu.img', re.IGNORECASE)
#
# if results:
#     uuid = results.group('uuid')
#     provider_id = int(results.group('provider_id'))
#     image_name = results.group('image_name')
#     print uuid
#     print provider_id
#     print image_name

# >>> import re
# >>> match = re.search('(?P<name>.*) (?P<phone>.*)', 'John 123456')
# >>> match.group('name')
# 'John'

#print re.search(uuid_regex, uuid.uuid4())


# image_name = '%s-%s-%s' % (uuid.uuid4(), 23, 'ubuntu.img')
#
# print image_name

# >>> import re
# >>> m = re.search('(?<=abc)def', 'abcdef')
# >>> m.group(0)
# 'def'


# c, r = get_vnfd_list()
#
# print c, r

# for v in r['vnfd_id']:
#     delete_vnfd(v)
#
#
# code, resp = upload_image('image8g.img', image_path='/home/goten002/image8g.img', provider_id=4, md5sum='b770351fadae5a96bbaf9702ed97d28d', image_type='qcow2')
# if code is not 201:
#             print 'Image Problemoooo!!'
# else:
#             print 'Image successfully uploaded!'

# code, v = get_images_list()
# for i in v['files']:
#     print i
#     print delete_image(i['name'])