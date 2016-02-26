import json
import requests


class SlaTemplateEncoder:
    def __init__(self, objFlavour, objVDUs, vnfName, vnfId, vnfType, vnfProvider):
        self.name = vnfName + "_" + str(objFlavour['flavour_key'])
        self.templateId = "vnf" + str(vnfId) + str(objFlavour['flavour_key'])
        self.context = {
            "agreementInitiator": None,
            "agreementResponder": str(vnfProvider),
            "serviceProvider": "AgreementResponder",
            "templateId": "vnf" + str(vnfId) + str(objFlavour['flavour_key']),
            "service": vnfType
        }
        self.terms = {
            "allTerms": {
                "serviceDescriptionTerm": {
                    "name": "requirements",
                    "serviceName": str(objFlavour['flavour_key']),
                    "requirements": getRequirements(objVDUs, objFlavour['vdu_reference'])
                    # [ { "name": "RAM", "value": 512, "unit": "MB" } ]
                },
                "serviceProperties": [
                    {
                        "name": "MonitoredMetrics",
                        "serviceName": "default",
                        "variableSet": {
                            "variables": getVariables(objFlavour)
                        }
                    }
                ],
                "guaranteeTerms": getGT(objFlavour)
            }
        }


def getVariables(obj):
    result = []
    for var in obj['assurance_parameters']:
        item = {}
        item['name'] = var['id']
        item['metric'] = "xs:double"
        item['location'] = "/monitor/" + str(var['id'])
        result.append(item)
    return result


def getPenalties(obj):
    result = []
    item = {}
    item['count'] = 1,
    item['penalties'] = obj['penalty']
    result.append(item)
    return result


def getViolations(violations):
    result = []
    for violation in violations:
        item = {}
        item['count'] = violation['breaches_count']
        item['interval'] = violation['interval']
        result.append(item)
    return str.replace(str(result), "'", "\"")


def getGT(obj):
    result = []
    for gt in obj['assurance_parameters']:
        item = {}
        item['name'] = gt['id']
        item['qualifyingCondition'] = None
        item['serviceScope'] = None
        item['businessValueList'] = {
            "customBusinessValue": [{
                "count": 1,
                "penalties": [gt['penalty']]
            }]
        }
        item['serviceLevelObjetive'] = {
            "kpitarget": {
                "kpiName": gt['id'],
                "customServiceLevel": '{\"policies\": %s, \"constraint\" : \"%s\"}' % (
                getViolations(gt['violation']), gt['formula'])
            }
        }
        result.append(item)
    return result


def getRequirements(objVDUs, vduList):
    result = []
    for vduId in vduList:
        for vdu in objVDUs:
            if vdu['id'] == vduId:
                item = {}
                item['name'] = vduId
                item['value'] = json.dumps(vdu['resource_requirements'])
                item['unit'] = "-"
                result.append(item)
    return result


def createVNFDSLATemplate(obj):

    for template in obj['deployment_flavours']:
        dataObj = SlaTemplateEncoder(template, obj['vdu'], obj['name'], obj['id'], obj['type'], obj['provider_id'])
        json_data = json.dumps(vars(dataObj), sort_keys=True, indent=4)
        print 'VNF_SLA'
        print json_data
        sla_response = requests.post('http://sla.docker:9040/templates', auth=('user', 'password'), data=json_data, headers={'content-type': 'application/json'})
        if sla_response.status_code == 201:
            print 'SLA template created'
        else:
            print 'SLA template creation failed'
            print 'Error Code: %s ' % sla_response.status_code
            print 'Error Resp: %s ' % sla_response.text


# json_data = open('vnfd.json').read()
# obj = json.loads(json_data)
# postVNFDTemplate(obj)