import argparse
import ConfigParser
import requests
import os

config = ConfigParser.ConfigParser()
config.read('marketplace-cli.conf')

USE_HTTPS = False
MARKETPLACE_IP = config.get("marketplace", "ip")
MARKETPLACE_PORT = int(config.get("marketplace", "port"))

parser = argparse.ArgumentParser(description='Marketplace-CLI v0.1')
parser.add_argument('--upload', type=str, choices=["nsd", "vnfd"])
args = parser.parse_args()


def _get_call_url(endpoint):
    protocol = 'https' if USE_HTTPS else 'http'
    return '%s://%s:%s/%s' % (protocol, MARKETPLACE_IP, MARKETPLACE_PORT, endpoint)


def _get_auth_token(username, password):
    r = requests.post(_get_call_url('auth/'), data={'username': username, 'password': password})
    if r.status_code == 200:
        return r.json()['token']

if args.upload == "vnfd":
    FP_USERNAME = config.get("fp", "username")
    FP_PASSWORD = config.get("fp", "password")
    FP_AUTH_TOKEN = _get_auth_token(FP_USERNAME, FP_PASSWORD)

    if not FP_AUTH_TOKEN:
        print "FP authentication failed."
        exit(1)

    VNFD_FILE = config.get("templates", "vnfd")
    if os.path.isfile(VNFD_FILE):
        VNFD_TEMPLATE = open(VNFD_FILE).read()
        r = requests.post(_get_call_url('vnfs/'), data=VNFD_TEMPLATE, headers={"Authorization": "JWT " + FP_AUTH_TOKEN, 'Content-Type': 'application/json'})
        if r.status_code != 201:
            print "VNFD uploading failed."
            print "STATUS_CODE: %s" % r.status_code
            print "---ERROR---\n" + r.text
            exit(1)
        else:
            print "VNFD succesfully uploaded."
    else:
        print "VNFD template not found."
        exit(1)

if args.upload == "nsd":

    SP_USERNAME = config.get("sp", "username")
    SP_PASSWORD = config.get("sp", "password")
    SP_AUTH_TOKEN = _get_auth_token(SP_USERNAME, SP_PASSWORD)

    if not SP_AUTH_TOKEN:
        print "SP provider authentication failed."
        exit(1)

    NSD_FILE = config.get("templates", "nsd")
    if os.path.isfile(NSD_FILE):
        NSD_TEMPLATE = open(NSD_FILE).read()
        r = requests.post(_get_call_url('service-catalog/service/catalog'), data=NSD_TEMPLATE, headers={'Content-Type': 'application/json'})
        if r.status_code != 201:
            print "NSD uploading failed."
            print "STATUS_CODE:%s" % r.status_code
            print "---ERROR---\n" + r.text
            exit(1)
        else:
            nsd = r.json()
            print "NSD succesfully uploaded."
            print "NSD_ID:%s" % nsd['nsd']['id']

    else:
        print "NSD template not found."
        exit(1)


