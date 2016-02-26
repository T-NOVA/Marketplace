# -*- coding: utf-8 -*-

from unittest import TestCase
from pprint import pprint
import json

from slaclient import wsag_model
from slaclient import xmlconverter


class ConvertersTestCase(TestCase):

    def setUp(self):
        self.violation = """
            <violation>
                <uuid>ce0e148f-dfac-4492-bb26-ad2e9a6965ec</uuid>
                <contract_uuid>agreement04</contract_uuid>
                <service_scope></service_scope>
                <metric_name>Performance</metric_name>
                <datetime>2014-01-14T11:28:22Z</datetime>
                <actual_value>0.09555700123360344</actual_value>
            </violation>"""

        self.provider = """
            <provider>
                <uuid>1ad9acb9-8dbc-4fe6-9a0b-4244ab6455da</uuid>
                <name>Provider2</name>
            </provider> """

        self.list = """
            <providers>
                <provider>
                    <uuid>1ad9acb9-8dbc-4fe6-9a0b-4244ab6455da</uuid>
                    <name>Provider1</name>
                </provider>
                <provider>
                    <uuid>2ad9acb9-8dbc-4fe6-9a0b-4244ab6455da</uuid>
                    <name>Provider2</name>
                </provider>
            </providers>"""

        self.agreement_status = """
            {
                "AgreementId":"agreement03",
                "guaranteestatus":"VIOLATED",
                "guaranteeterms":
                    [
                        {"name":"GT_ResponseTime","status":"FULFILLED"},
                        {"name":"GT_Performance","status":"VIOLATED"}
                    ]
            }"""

        self.ejob1 = """
          <enforcement_job>
            <agreement_id>$id</agreement_id>
            <enabled>true</enabled>
          </enforcement_job>
        """

        self.ejob2 = """
          <enforcement_job>
            <agreement_id>$id</agreement_id>
            <enabled>false</enabled>
          </enforcement_job>
        """

        self.penalty1 = """
            <penalty>
                <uuid>f112b1de-eb1b-44e4-aa7c-19fe81e72412</uuid>
                <agreement_id>agreement-id</agreement_id>
                <datetime>2015-02-11T00:44:32GMT</datetime>
                <definition type="discount" expression="100"
                    unit="euro" validity="P1M"/>
            </penalty>
        """

        self.penalty2 = """
            <penalty>
                <uuid>f112b1de-eb1b-44e4-aa7c-19fe81e72412</uuid>
                <agreement_id>agreement-id</agreement_id>
                <datetime>2015-02-11T00:44:32GMT</datetime>
                <definition type="migration"/>
            </penalty>
        """

        self.penalties = """
            <penalties>
                <penalty xmlns:sla="http://sla.atos.eu" xmlns:wsag="http://www.ggf.org/namespaces/ws-agreement"><uuid>daa8c54c-5c52-4efe-8e4c-a5b7f31cf3fb</uuid><agreement_id>agreement01</agreement_id><datetime>2015-01-26T13:21:51CET</datetime><definition type="discount" expression="" unit="euro" validity=""/></penalty>
            </penalties>
        """

    def test_agreement(self):
        conv = xmlconverter.AgreementConverter()

        out = xmlconverter.convertfile(conv, "slaclient/tests/agreement.xml")
        """:type : Agreement"""

        self.assertEquals("agreement02", out.agreement_id)
        self.assertEquals("RandomClient", out.context.consumer)
        self.assertEquals("provider-prueba", out.context.provider)
        self.assertEquals("template-2007-12-04", out.context.template_id)
        self.assertEquals("ExampleService", out.context.service)
        csl = \
            out.guaranteeterms["login"].servicelevelobjective.customservicelevel
        self.assertEquals(10000, csl.maxvalue)
        self.assertEquals(float("-inf"), csl.minvalue)

        pprint(out)

    def test_template(self):
        conv = xmlconverter.AgreementConverter()

        out = xmlconverter.convertfile(conv, "slaclient/tests/template.xml")
        """:type : Template"""

        self.assertEquals("template02", out.template_id)
        self.assertEquals("RandomClient", out.context.consumer)
        self.assertEquals("provider-prueba", out.context.provider)
        self.assertEquals("ExampleService", out.context.service)
        #pprint(out)

    def test_provider(self):
        conv = xmlconverter.ProviderConverter()
        out = xmlconverter.convertstring(conv, self.provider)
        #pprint(out)

    def test_violation(self):
        conv = xmlconverter.ViolationConverter()
        out = xmlconverter.convertstring(conv, self.violation)
        #pprint(out)

    def test_enforcementjob(self):
        conv = xmlconverter.EnforcementConverter()

        current = xmlconverter.convertstring(conv, self.ejob1)
        expected = wsag_model.EnforcementJob("$id", True)
        self.assertEquals(current, expected)

        current = xmlconverter.convertstring(conv, self.ejob2)
        expected = wsag_model.EnforcementJob("$id", False)
        self.assertEquals(current, expected)

    def test_penalty(self):
        conv = xmlconverter.PenaltyConverter()

        current = xmlconverter.convertstring(conv, self.penalty1)
        pprint(current)

    def test_penalty_with_empty_fields(self):
        conv = xmlconverter.PenaltyConverter()

        current = xmlconverter.convertstring(conv, self.penalty2)
        pprint(current)

    def test_penalties(self):
        conv = xmlconverter.ListConverter(xmlconverter.PenaltyConverter())
        out = xmlconverter.convertstring(conv, self.penalties)
        pprint(out)

    def test_list(self):
        conv = xmlconverter.ListConverter(xmlconverter.ProviderConverter())
        out = xmlconverter.convertstring(conv, self.list)
        pprint(out)

    def test_agreement_status_decode(self):
        json_obj = json.loads(self.agreement_status)
        out = wsag_model.AgreementStatus.from_dict(json_obj)
        #pprint(out)

    def test_parse_servicelevel(self):

        csl = self._test_parse_servicelevel(
            constraint="v1 NOT EXISTS",
            maxvalue=10000,
            minvalue=0
        )
        csl = self._test_parse_servicelevel(
            constraint="v1 NOT EXISTS",
            maxvalue=10000,
        )
        csl = self._test_parse_servicelevel(
            constraint="v1 NOT EXISTS",
            minvalue=10000,
        )
        csl = self._test_parse_servicelevel(
            constraint="v1 NOT EXISTS",
        )
        csl = self._test_parse_servicelevel(
            constraint="v1 NOT EXISTS",
            level='{"constraint":"v1 NOT EXISTS"}'
        )


    def _build_servicelevel(self, constraint="", maxvalue=float("inf"),
                            minvalue=float("-inf")):

        o = dict()
        o["constraint"] = constraint
        o["qos"] = dict()
        if maxvalue != float("inf"):
            o["qos"]["hasMaxValue"] = maxvalue
        if minvalue != float("-inf"):
            o["qos"]["hasMinValue"] = minvalue
        o["aggregation"] = dict(parameters=[], aggregateFuntion="Average")

        result = json.dumps(o)
        return result

    def _test_parse_servicelevel(self, constraint="", maxvalue=float("inf"),
                                 minvalue=float("-inf"), level=""):

        if level == "":
            level = self._build_servicelevel(constraint, maxvalue, minvalue)

        csl = wsag_model.Agreement.GuaranteeTerm.\
            ServiceLevelObjective.CustomServiceLevel(level)

        self.assertEquals(constraint, csl.constraint)
        self.assertEquals(maxvalue, csl.maxvalue)
        self.assertEquals(minvalue, csl.minvalue)

        return csl
