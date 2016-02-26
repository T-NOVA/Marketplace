-- MySQL dump 10.13  Distrib 5.6.27, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: atossla
-- ------------------------------------------------------
-- Server version	5.6.27-0ubuntu0.15.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `agreement`
--

DROP TABLE IF EXISTS `agreement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agreement` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `agreement_id` varchar(255) DEFAULT NULL,
  `consumer` varchar(255) DEFAULT NULL,
  `expiration_time` datetime DEFAULT NULL,
  `metrics_eval_end` tinyint(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `service_id` varchar(255) DEFAULT NULL,
  `text` longtext,
  `provider_id` bigint(20) NOT NULL,
  `template_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_b3upn8jbq7ryrj26mpc5hccf3` (`agreement_id`),
  KEY `FK_hlyajkn6gfqd9vd9u2ne74dbf` (`provider_id`),
  KEY `FK_ob6aljvohdv1g1nckphj69974` (`template_id`),
  CONSTRAINT `FK_hlyajkn6gfqd9vd9u2ne74dbf` FOREIGN KEY (`provider_id`) REFERENCES `provider` (`id`),
  CONSTRAINT `FK_ob6aljvohdv1g1nckphj69974` FOREIGN KEY (`template_id`) REFERENCES `template` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agreement`
--

LOCK TABLES `agreement` WRITE;
/*!40000 ALTER TABLE `agreement` DISABLE KEYS */;
INSERT INTO `agreement` VALUES (1,'vnf3a2971d0-2eae-11e5-a2cb-0800200c9a66calls5k','providerajax',NULL,NULL,'nombre','TC / should an ontology be defined or this is free text input?','<wsag:Agreement wsag:AgreementId=\"vnf3a2971d0-2eae-11e5-a2cb-0800200c9a66calls5k\" xmlns:wsag=\"http://www.ggf.org/namespaces/ws-agreement\" xmlns:sla=\"http://sla.atos.eu\"><wsag:Name>nombre</wsag:Name><wsag:Context><wsag:AgreementInitiator>providerajax</wsag:AgreementInitiator><wsag:AgreementResponder>fp01</wsag:AgreementResponder><wsag:ServiceProvider>AgreementResponder</wsag:ServiceProvider><wsag:TemplateId>vnf3a2971d0-2eae-11e5-a2cb-0800200c9a66calls5k</wsag:TemplateId><sla:Service>TC / should an ontology be defined or this is free text input?</sla:Service></wsag:Context><wsag:Terms><wsag:All><wsag:ServiceDescriptionTerm wsag:Name=\"requirements\" wsag:ServiceName=\"calls5k\"><wsag:Requirements wsag:Name=\"virt_mem_res_element\" wsag:Value=\"6\" wsag:Unit=\"GB\"/><wsag:Requirements wsag:Name=\"CPU\" wsag:Value=\"6\" wsag:Unit=\"cores\"/><wsag:Requirements wsag:Name=\"TLB size\" wsag:Value=\"1024\" wsag:Unit=\"\"/><wsag:Requirements wsag:Name=\"storage\" wsag:Value=\"20\" wsag:Unit=\"GB\"/></wsag:ServiceDescriptionTerm><wsag:ServiceProperties wsag:Name=\"MonitoredMetrics\" wsag:ServiceName=\"default\"><wsag:VariableSet><wsag:Variable wsag:Name=\"pepito\" wsag:Metric=\"xs:double\"><wsag:Location>/monitor/pepito</wsag:Location></wsag:Variable><wsag:Variable wsag:Name=\"juanito\" wsag:Metric=\"xs:double\"><wsag:Location>/monitor/juanito</wsag:Location></wsag:Variable></wsag:VariableSet></wsag:ServiceProperties><wsag:GuaranteeTerm wsag:Name=\"pepito\"><wsag:ServiceLevelObjective><wsag:KPITarget><wsag:KPIName>pepito</wsag:KPIName><wsag:CustomServiceLevel> { \"policies\": [ { \"count\" : 2, \"interval\": 30 } ], \"constraint\" : \"pepito GT 0.5\" }</wsag:CustomServiceLevel></wsag:KPITarget></wsag:ServiceLevelObjective><wsag:BusinessValueList><wsag:CustomBusinessValue count=\"1\"><wsag:Penalty type=\"discount\" expression=\"5\" unit=\"%\" validity=\"P1D\"/></wsag:CustomBusinessValue></wsag:BusinessValueList></wsag:GuaranteeTerm><wsag:GuaranteeTerm wsag:Name=\"juanito\"><wsag:ServiceLevelObjective><wsag:KPITarget><wsag:KPIName>juanito</wsag:KPIName><wsag:CustomServiceLevel> { \"policies\": [ { \"count\" : 2, \"interval\": 30 } ], \"constraint\" : \"juanito GT 0.7\" }</wsag:CustomServiceLevel></wsag:KPITarget></wsag:ServiceLevelObjective><wsag:BusinessValueList><wsag:CustomBusinessValue count=\"1\"><wsag:Penalty type=\"discount\" expression=\"5\" unit=\"%\" validity=\"P1D\"/></wsag:CustomBusinessValue></wsag:BusinessValueList></wsag:GuaranteeTerm></wsag:All></wsag:Terms></wsag:Agreement>',2,1),(2,'vnfidf51','p6',NULL,NULL,'vnf5gold','TC / should an ontology be defined or this is free text input?','<wsag:Agreement wsag:AgreementId=\"vnfidf51\" xmlns:wsag=\"http://www.ggf.org/namespaces/ws-agreement\" xmlns:sla=\"http://sla.atos.eu\"><wsag:Name>vnf5gold</wsag:Name><wsag:Context><wsag:AgreementInitiator>p6</wsag:AgreementInitiator><wsag:AgreementResponder>f5</wsag:AgreementResponder><wsag:ServiceProvider>AgreementResponder</wsag:ServiceProvider><wsag:TemplateId>vnfvnf5gold</wsag:TemplateId><sla:Service>TC / should an ontology be defined or this is free text input?</sla:Service></wsag:Context><wsag:Terms><wsag:All><wsag:ServiceDescriptionTerm wsag:Name=\"requirements\" wsag:ServiceName=\"calls5k\"><wsag:Requirements wsag:Name=\"virt_mem_res_element\" wsag:Value=\"6\" wsag:Unit=\"GB\"/><wsag:Requirements wsag:Name=\"CPU\" wsag:Value=\"6\" wsag:Unit=\"cores\"/><wsag:Requirements wsag:Name=\"TLB size\" wsag:Value=\"1024\" wsag:Unit=\"\"/><wsag:Requirements wsag:Name=\"storage\" wsag:Value=\"20\" wsag:Unit=\"GB\"/></wsag:ServiceDescriptionTerm><wsag:ServiceProperties wsag:Name=\"MonitoredMetrics\" wsag:ServiceName=\"default\"><wsag:VariableSet><wsag:Variable wsag:Name=\"pepitovnf5\" wsag:Metric=\"xs:double\"><wsag:Location>/monitor/pepitovnf5</wsag:Location></wsag:Variable><wsag:Variable wsag:Name=\"juanitovnf5\" wsag:Metric=\"xs:double\"><wsag:Location>/monitor/juanitovnf5</wsag:Location></wsag:Variable></wsag:VariableSet></wsag:ServiceProperties><wsag:GuaranteeTerm wsag:Name=\"pepitovnf5\"><wsag:ServiceLevelObjective><wsag:KPITarget><wsag:KPIName>pepitovnf5</wsag:KPIName><wsag:CustomServiceLevel> { \"policies\": [ { \"count\" : 2, \"interval\": 30 } ], \"constraint\" : \"pepitovnf5 GT 0.5\" }</wsag:CustomServiceLevel></wsag:KPITarget></wsag:ServiceLevelObjective><wsag:BusinessValueList><wsag:CustomBusinessValue count=\"1\"><wsag:Penalty type=\"discount\" expression=\"5\" unit=\"%\" validity=\"P1D\"/></wsag:CustomBusinessValue></wsag:BusinessValueList></wsag:GuaranteeTerm><wsag:GuaranteeTerm wsag:Name=\"juanitovnf5\"><wsag:ServiceLevelObjective><wsag:KPITarget><wsag:KPIName>juanitovnf5</wsag:KPIName><wsag:CustomServiceLevel> { \"policies\": [ { \"count\" : 2, \"interval\": 30 } ], \"constraint\" : \"juanitovnf5 GT 0.7\" }</wsag:CustomServiceLevel></wsag:KPITarget></wsag:ServiceLevelObjective><wsag:BusinessValueList><wsag:CustomBusinessValue count=\"1\"><wsag:Penalty type=\"discount\" expression=\"5\" unit=\"%\" validity=\"P1D\"/></wsag:CustomBusinessValue></wsag:BusinessValueList></wsag:GuaranteeTerm></wsag:All></wsag:Terms></wsag:Agreement>',4,2),(3,'vnfidf52','p6',NULL,NULL,'vnf6gold','TC / should an ontology be defined or this is free text input?','<wsag:Agreement wsag:AgreementId=\"vnfidf52\" xmlns:wsag=\"http://www.ggf.org/namespaces/ws-agreement\" xmlns:sla=\"http://sla.atos.eu\"><wsag:Name>vnf6gold</wsag:Name><wsag:Context><wsag:AgreementInitiator>p6</wsag:AgreementInitiator><wsag:AgreementResponder>f6</wsag:AgreementResponder><wsag:ServiceProvider>AgreementResponder</wsag:ServiceProvider><wsag:TemplateId>vnfvnf6gold</wsag:TemplateId><sla:Service>TC / should an ontology be defined or this is free text input?</sla:Service></wsag:Context><wsag:Terms><wsag:All><wsag:ServiceDescriptionTerm wsag:Name=\"requirements\" wsag:ServiceName=\"calls5k\"><wsag:Requirements wsag:Name=\"virt_mem_res_element\" wsag:Value=\"6\" wsag:Unit=\"GB\"/><wsag:Requirements wsag:Name=\"CPU\" wsag:Value=\"6\" wsag:Unit=\"cores\"/><wsag:Requirements wsag:Name=\"TLB size\" wsag:Value=\"1024\" wsag:Unit=\"\"/><wsag:Requirements wsag:Name=\"storage\" wsag:Value=\"20\" wsag:Unit=\"GB\"/></wsag:ServiceDescriptionTerm><wsag:ServiceProperties wsag:Name=\"MonitoredMetrics\" wsag:ServiceName=\"default\"><wsag:VariableSet><wsag:Variable wsag:Name=\"pepitovnf6\" wsag:Metric=\"xs:double\"><wsag:Location>/monitor/pepitovnf6</wsag:Location></wsag:Variable><wsag:Variable wsag:Name=\"juanitovnf6\" wsag:Metric=\"xs:double\"><wsag:Location>/monitor/juanitovnf6</wsag:Location></wsag:Variable></wsag:VariableSet></wsag:ServiceProperties><wsag:GuaranteeTerm wsag:Name=\"pepitovnf6\"><wsag:ServiceLevelObjective><wsag:KPITarget><wsag:KPIName>pepitovnf6</wsag:KPIName><wsag:CustomServiceLevel> { \"policies\": [ { \"count\" : 2, \"interval\": 30 } ], \"constraint\" : \"pepitovnf6 GT 0.5\" }</wsag:CustomServiceLevel></wsag:KPITarget></wsag:ServiceLevelObjective><wsag:BusinessValueList><wsag:CustomBusinessValue count=\"1\"><wsag:Penalty type=\"discount\" expression=\"5\" unit=\"%\" validity=\"P1D\"/></wsag:CustomBusinessValue></wsag:BusinessValueList></wsag:GuaranteeTerm><wsag:GuaranteeTerm wsag:Name=\"juanitovnf6\"><wsag:ServiceLevelObjective><wsag:KPITarget><wsag:KPIName>juanitovnf6</wsag:KPIName><wsag:CustomServiceLevel> { \"policies\": [ { \"count\" : 2, \"interval\": 30 } ], \"constraint\" : \"juanitovnf6 GT 0.7\" }</wsag:CustomServiceLevel></wsag:KPITarget></wsag:ServiceLevelObjective><wsag:BusinessValueList><wsag:CustomBusinessValue count=\"1\"><wsag:Penalty type=\"discount\" expression=\"5\" unit=\"%\" validity=\"P1D\"/></wsag:CustomBusinessValue></wsag:BusinessValueList></wsag:GuaranteeTerm></wsag:All></wsag:Terms></wsag:Agreement>',5,3),(4,'serviceids101','c1',NULL,NULL,'service6gold','TC / should an ontology be defined or this is free text input?','<wsag:Agreement wsag:AgreementId=\"serviceids101\" xmlns:wsag=\"http://www.ggf.org/namespaces/ws-agreement\" xmlns:sla=\"http://sla.atos.eu\"><wsag:Name>service6gold</wsag:Name><wsag:Context><wsag:AgreementInitiator>c1</wsag:AgreementInitiator><wsag:AgreementResponder>p6</wsag:AgreementResponder><wsag:ServiceProvider>AgreementResponder</wsag:ServiceProvider><wsag:TemplateId>serviceservice6gold</wsag:TemplateId><sla:Service>TC / should an ontology be defined or this is free text input?</sla:Service></wsag:Context><wsag:Terms><wsag:All><wsag:ServiceDescriptionTerm wsag:Name=\"requirements\" wsag:ServiceName=\"calls5k\"><wsag:Requirements wsag:Name=\"virt_mem_res_element\" wsag:Value=\"6\" wsag:Unit=\"GB\"/><wsag:Requirements wsag:Name=\"CPU\" wsag:Value=\"6\" wsag:Unit=\"cores\"/><wsag:Requirements wsag:Name=\"TLB size\" wsag:Value=\"1024\" wsag:Unit=\"\"/><wsag:Requirements wsag:Name=\"storage\" wsag:Value=\"20\" wsag:Unit=\"GB\"/></wsag:ServiceDescriptionTerm><wsag:ServiceProperties wsag:Name=\"MonitoredMetrics\" wsag:ServiceName=\"default\"><wsag:VariableSet><wsag:Variable wsag:Name=\"pepitoservice6\" wsag:Metric=\"xs:double\"><wsag:Location>/monitor/pepitoservice6</wsag:Location></wsag:Variable><wsag:Variable wsag:Name=\"juanitoservice6\" wsag:Metric=\"xs:double\"><wsag:Location>/monitor/juanitoservice6</wsag:Location></wsag:Variable></wsag:VariableSet></wsag:ServiceProperties><wsag:GuaranteeTerm wsag:Name=\"pepitoservice6\"><wsag:ServiceLevelObjective><wsag:KPITarget><wsag:KPIName>pepitoservice6</wsag:KPIName><wsag:CustomServiceLevel> { \"policies\": [ { \"count\" : 2, \"interval\": 30 } ], \"constraint\" : \"pepitoservice6 GT 0.5\" }</wsag:CustomServiceLevel></wsag:KPITarget></wsag:ServiceLevelObjective><wsag:BusinessValueList><wsag:CustomBusinessValue count=\"1\"><wsag:Penalty type=\"discount\" expression=\"5\" unit=\"%\" validity=\"P1D\"/></wsag:CustomBusinessValue></wsag:BusinessValueList></wsag:GuaranteeTerm><wsag:GuaranteeTerm wsag:Name=\"juanitoservice6\"><wsag:ServiceLevelObjective><wsag:KPITarget><wsag:KPIName>juanitoservice6</wsag:KPIName><wsag:CustomServiceLevel> { \"policies\": [ { \"count\" : 2, \"interval\": 30 } ], \"constraint\" : \"juanitoservice6 GT 0.7\" }</wsag:CustomServiceLevel></wsag:KPITarget></wsag:ServiceLevelObjective><wsag:BusinessValueList><wsag:CustomBusinessValue count=\"1\"><wsag:Penalty type=\"discount\" expression=\"5\" unit=\"%\" validity=\"P1D\"/></wsag:CustomBusinessValue></wsag:BusinessValueList></wsag:GuaranteeTerm></wsag:All></wsag:Terms></wsag:Agreement>',6,4),(5,'vnfidf61','providerajax',NULL,NULL,'vnf5gold','TC / should an ontology be defined or this is free text input?','<wsag:Agreement wsag:AgreementId=\"vnfidf61\" xmlns:wsag=\"http://www.ggf.org/namespaces/ws-agreement\" xmlns:sla=\"http://sla.atos.eu\"><wsag:Name>vnf5gold</wsag:Name><wsag:Context><wsag:AgreementInitiator>providerajax</wsag:AgreementInitiator><wsag:AgreementResponder>f5</wsag:AgreementResponder><wsag:ServiceProvider>AgreementResponder</wsag:ServiceProvider><wsag:TemplateId>vnfvnf5gold</wsag:TemplateId><sla:Service>TC / should an ontology be defined or this is free text input?</sla:Service></wsag:Context><wsag:Terms><wsag:All><wsag:ServiceDescriptionTerm wsag:Name=\"requirements\" wsag:ServiceName=\"calls5k\"><wsag:Requirements wsag:Name=\"virt_mem_res_element\" wsag:Value=\"6\" wsag:Unit=\"GB\"/><wsag:Requirements wsag:Name=\"CPU\" wsag:Value=\"6\" wsag:Unit=\"cores\"/><wsag:Requirements wsag:Name=\"TLB size\" wsag:Value=\"1024\" wsag:Unit=\"\"/><wsag:Requirements wsag:Name=\"storage\" wsag:Value=\"20\" wsag:Unit=\"GB\"/></wsag:ServiceDescriptionTerm><wsag:ServiceProperties wsag:Name=\"MonitoredMetrics\" wsag:ServiceName=\"default\"><wsag:VariableSet><wsag:Variable wsag:Name=\"pepitovnf5\" wsag:Metric=\"xs:double\"><wsag:Location>/monitor/pepitovnf5</wsag:Location></wsag:Variable><wsag:Variable wsag:Name=\"juanitovnf5\" wsag:Metric=\"xs:double\"><wsag:Location>/monitor/juanitovnf5</wsag:Location></wsag:Variable></wsag:VariableSet></wsag:ServiceProperties><wsag:GuaranteeTerm wsag:Name=\"pepitovnf5\"><wsag:ServiceLevelObjective><wsag:KPITarget><wsag:KPIName>pepitovnf5</wsag:KPIName><wsag:CustomServiceLevel> { \"policies\": [ { \"count\" : 2, \"interval\": 30 } ], \"constraint\" : \"pepitovnf5 GT 0.5\" }</wsag:CustomServiceLevel></wsag:KPITarget></wsag:ServiceLevelObjective><wsag:BusinessValueList><wsag:CustomBusinessValue count=\"1\"><wsag:Penalty type=\"discount\" expression=\"5\" unit=\"%\" validity=\"P1D\"/></wsag:CustomBusinessValue></wsag:BusinessValueList></wsag:GuaranteeTerm><wsag:GuaranteeTerm wsag:Name=\"juanitovnf5\"><wsag:ServiceLevelObjective><wsag:KPITarget><wsag:KPIName>juanitovnf5</wsag:KPIName><wsag:CustomServiceLevel> { \"policies\": [ { \"count\" : 2, \"interval\": 30 } ], \"constraint\" : \"juanitovnf5 GT 0.7\" }</wsag:CustomServiceLevel></wsag:KPITarget></wsag:ServiceLevelObjective><wsag:BusinessValueList><wsag:CustomBusinessValue count=\"1\"><wsag:Penalty type=\"discount\" expression=\"5\" unit=\"%\" validity=\"P1D\"/></wsag:CustomBusinessValue></wsag:BusinessValueList></wsag:GuaranteeTerm></wsag:All></wsag:Terms></wsag:Agreement>',4,2);
/*!40000 ALTER TABLE `agreement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `breach`
--

DROP TABLE IF EXISTS `breach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `breach` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `contract_uuid` varchar(255) DEFAULT NULL,
  `datetime` datetime DEFAULT NULL,
  `kpi_name` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `violation_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_l215s831vj7ar4rpecpggg2xp` (`violation_id`),
  CONSTRAINT `FK_l215s831vj7ar4rpecpggg2xp` FOREIGN KEY (`violation_id`) REFERENCES `violation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `breach`
--

LOCK TABLES `breach` WRITE;
/*!40000 ALTER TABLE `breach` DISABLE KEYS */;
/*!40000 ALTER TABLE `breach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `business_value_list`
--

DROP TABLE IF EXISTS `business_value_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `business_value_list` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `importance` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `business_value_list`
--

LOCK TABLES `business_value_list` WRITE;
/*!40000 ALTER TABLE `business_value_list` DISABLE KEYS */;
INSERT INTO `business_value_list` VALUES (1,0),(2,0),(3,0),(4,0),(5,0),(6,0),(7,0),(8,0),(9,0),(10,0);
/*!40000 ALTER TABLE `business_value_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enforcement_job`
--

DROP TABLE IF EXISTS `enforcement_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enforcement_job` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `enabled` bit(1) DEFAULT NULL,
  `first_executed` datetime DEFAULT NULL,
  `last_executed` datetime DEFAULT NULL,
  `agreement_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_e63hbo5jhctm96xdwu93cls0v` (`agreement_id`),
  CONSTRAINT `FK_e63hbo5jhctm96xdwu93cls0v` FOREIGN KEY (`agreement_id`) REFERENCES `agreement` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enforcement_job`
--

LOCK TABLES `enforcement_job` WRITE;
/*!40000 ALTER TABLE `enforcement_job` DISABLE KEYS */;
INSERT INTO `enforcement_job` VALUES (1,'\0',NULL,NULL,1),(2,'\0','2015-11-03 12:52:31','2015-11-03 15:10:30',2),(3,'\0','2015-11-03 12:52:31','2015-11-03 15:10:30',3),(4,'\0','2015-11-03 12:52:31','2015-11-03 15:10:30',4),(5,'\0',NULL,NULL,5);
/*!40000 ALTER TABLE `enforcement_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guarantee_term`
--

DROP TABLE IF EXISTS `guarantee_term`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guarantee_term` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `kpi_name` varchar(255) DEFAULT NULL,
  `lastSampledDate` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `samplingPeriodFactor` int(11) DEFAULT NULL,
  `service_level` varchar(255) DEFAULT NULL,
  `service_name` varchar(255) DEFAULT NULL,
  `service_scope` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `business_value_id` bigint(20) DEFAULT NULL,
  `agreement_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7vtc6h6w1len3s3xrol2rma8o` (`business_value_id`),
  KEY `FK_pufedjn479knv894hyqrlfpgx` (`agreement_id`),
  CONSTRAINT `FK_7vtc6h6w1len3s3xrol2rma8o` FOREIGN KEY (`business_value_id`) REFERENCES `business_value_list` (`id`),
  CONSTRAINT `FK_pufedjn479knv894hyqrlfpgx` FOREIGN KEY (`agreement_id`) REFERENCES `agreement` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guarantee_term`
--

LOCK TABLES `guarantee_term` WRITE;
/*!40000 ALTER TABLE `guarantee_term` DISABLE KEYS */;
INSERT INTO `guarantee_term` VALUES (1,'pepito',NULL,'pepito',NULL,'pepito GT 0.5',NULL,NULL,2,1,1),(2,'juanito',NULL,'juanito',NULL,'juanito GT 0.7',NULL,NULL,2,2,1),(3,'pepitovnf5','2015-11-03 15:10:30','pepitovnf5',NULL,'pepitovnf5 GT 0.5',NULL,NULL,1,3,2),(4,'juanitovnf5','2015-11-03 15:10:30','juanitovnf5',NULL,'juanitovnf5 GT 0.7',NULL,NULL,1,4,2),(5,'pepitovnf6','2015-11-03 15:10:30','pepitovnf6',NULL,'pepitovnf6 GT 0.5',NULL,NULL,1,5,3),(6,'juanitovnf6','2015-11-03 15:10:30','juanitovnf6',NULL,'juanitovnf6 GT 0.7',NULL,NULL,1,6,3),(7,'pepitoservice6','2015-11-03 15:10:30','pepitoservice6',NULL,'pepitoservice6 GT 0.5',NULL,NULL,1,7,4),(8,'juanitoservice6','2015-11-03 15:10:30','juanitoservice6',NULL,'juanitoservice6 GT 0.7',NULL,NULL,1,8,4),(9,'pepitovnf5',NULL,'pepitovnf5',NULL,'pepitovnf5 GT 0.5',NULL,NULL,2,9,5),(10,'juanitovnf5',NULL,'juanitovnf5',NULL,'juanitovnf5 GT 0.7',NULL,NULL,2,10,5);
/*!40000 ALTER TABLE `guarantee_term` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `penalty`
--

DROP TABLE IF EXISTS `penalty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `penalty` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `agreement_id` varchar(255) DEFAULT NULL,
  `datetime` datetime DEFAULT NULL,
  `kpi_name` varchar(255) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `definition_id` bigint(20) NOT NULL,
  `violation_id` bigint(20) NOT NULL,
  `guarantee_term_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_s4japyqgwxvss8e63ejryo4js` (`definition_id`),
  KEY `FK_ecbrf8mo6b45v7s2ilcpb1qb6` (`violation_id`),
  KEY `FK_v8in0arhs3ff1i109d9ftxbm` (`guarantee_term_id`),
  CONSTRAINT `FK_ecbrf8mo6b45v7s2ilcpb1qb6` FOREIGN KEY (`violation_id`) REFERENCES `violation` (`id`),
  CONSTRAINT `FK_s4japyqgwxvss8e63ejryo4js` FOREIGN KEY (`definition_id`) REFERENCES `penaltydefinition` (`id`),
  CONSTRAINT `FK_v8in0arhs3ff1i109d9ftxbm` FOREIGN KEY (`guarantee_term_id`) REFERENCES `guarantee_term` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `penalty`
--

LOCK TABLES `penalty` WRITE;
/*!40000 ALTER TABLE `penalty` DISABLE KEYS */;
INSERT INTO `penalty` VALUES (1,'serviceids101','2015-11-03 12:52:31','pepitoservice6','fcd94909-62dd-46ae-b01f-7f85fe22a519',7,4,7),(2,'vnfidf51','2015-11-03 12:52:31','juanitovnf5','ebf55488-8f3c-424b-95a4-ae9ab9b29fdf',4,5,4),(3,'vnfidf52','2015-11-03 12:52:31','juanitovnf6','b917e5b6-3070-4ec1-acf8-82bc69a2e71e',6,6,6),(4,'serviceids101','2015-11-03 12:52:31','juanitoservice6','07ee7ee3-d435-4b3b-bf1c-193b146e3a0b',8,7,8),(5,'vnfidf52','2015-11-03 12:52:31','pepitovnf6','ff04679f-3130-4308-8f9b-578723fda323',5,8,5),(6,'vnfidf51','2015-11-03 12:53:30','pepitovnf5','d79e225d-bd0b-4768-a75c-177e333a0d9b',3,11,3),(7,'serviceids101','2015-11-03 12:53:30','juanitoservice6','34d66675-02fd-4add-9bda-5c779314b68d',8,12,8),(8,'vnfidf52','2015-11-03 12:53:30','pepitovnf6','89a6340c-a5b9-4b6a-a2b6-37c26778a29d',5,14,5),(9,'vnfidf51','2015-11-03 12:53:30','juanitovnf5','447390e4-83f2-48c9-9947-679771962569',4,15,4),(10,'vnfidf52','2015-11-03 12:53:30','juanitovnf6','1a329f7e-c808-4680-9a5e-bfca59bfacef',6,17,6),(11,'serviceids101','2015-11-03 14:58:30','pepitoservice6','08a72ce2-ffa0-4f81-8f7b-5c517eea4e06',7,18,7),(12,'serviceids101','2015-11-03 14:58:30','juanitoservice6','b2706fc4-a9f9-4c35-821c-b3b59144119d',8,19,8),(13,'serviceids101','2015-11-03 14:59:30','juanitoservice6','2e2a06ef-596a-45a7-a0bb-7f26d5d83fe6',8,21,8),(14,'serviceids101','2015-11-03 15:00:30','pepitoservice6','d81c69f5-f04a-4e1d-87ad-11cc9ff3bf56',7,22,7),(15,'serviceids101','2015-11-03 15:00:30','juanitoservice6','a9ae99c5-0d26-48eb-8dd1-0fb34d490ee3',8,23,8),(16,'serviceids101','2015-11-03 15:01:30','pepitoservice6','90488f9a-e7d4-46cb-a2ca-d42c8065880f',7,24,7),(17,'serviceids101','2015-11-03 15:01:30','juanitoservice6','29d400bc-43dc-4dcd-aa63-478ab8bf5403',8,26,8),(18,'serviceids101','2015-11-03 15:02:30','pepitoservice6','b1d32330-5619-4ee0-9391-156755de7484',7,27,7),(19,'serviceids101','2015-11-03 15:02:30','juanitoservice6','548fbb26-8451-4caa-baa1-d7a1450cabcd',8,29,8),(20,'serviceids101','2015-11-03 15:03:30','pepitoservice6','89cf8bcc-a94e-4190-8db9-a9b95866dcc0',7,31,7),(21,'serviceids101','2015-11-03 15:03:30','juanitoservice6','d9cbe6bf-7465-476b-a523-00da26e43f70',8,32,8),(22,'serviceids101','2015-11-03 15:04:30','juanitoservice6','44e67205-5041-4b60-b870-7c7af5215e2e',8,33,8),(23,'serviceids101','2015-11-03 15:06:30','pepitoservice6','1fbfe24b-472e-44cf-98e1-21931bbb4ab8',7,35,7),(24,'serviceids101','2015-11-03 15:06:30','juanitoservice6','85a99d8c-acec-4795-938c-b1bffcf8a41c',8,36,8),(25,'serviceids101','2015-11-03 15:07:30','pepitoservice6','0b8da1e2-f0d8-45db-87ec-4e52d25b8228',7,38,7),(26,'serviceids101','2015-11-03 15:08:30','pepitoservice6','648e27fb-c177-4d4d-8fce-62cc44906750',7,39,7),(27,'serviceids101','2015-11-03 15:08:30','juanitoservice6','fbc3cc81-7d12-4317-80e6-f60c941fc5d0',8,40,8),(28,'serviceids101','2015-11-03 15:09:30','juanitoservice6','81fb2a0a-798d-4ec1-afc4-1e98b5f2eea8',8,42,8),(29,'serviceids101','2015-11-03 15:09:30','pepitoservice6','e428a8b1-6789-4971-b046-fa5f7cd282c4',7,44,7),(30,'vnfidf52','2015-11-03 15:10:30','pepitovnf6','bee35f5b-9a1f-4531-b939-90845fecc6fc',5,48,5),(31,'vnfidf51','2015-11-03 15:10:30','pepitovnf5','5eaf2fa8-e533-4f76-8e78-cf7c3cce6b27',3,47,3),(32,'serviceids101','2015-11-03 15:10:30','juanitoservice6','8e440f6b-b5e0-4231-acf8-7ebc9d1d5e66',8,49,8),(33,'vnfidf51','2015-11-03 15:10:30','juanitovnf5','6fc69cd8-2948-4f55-98d2-fb069837377c',4,50,4),(34,'vnfidf52','2015-11-03 15:10:30','juanitovnf6','6932884e-45c4-4672-8f15-27e01815db2e',6,52,6),(35,'serviceids101','2015-11-03 15:10:30','pepitoservice6','fc3c87ed-f42d-4196-85e1-de96767d1b45',7,53,7);
/*!40000 ALTER TABLE `penalty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `penaltydefinition`
--

DROP TABLE IF EXISTS `penaltydefinition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `penaltydefinition` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `action` varchar(255) NOT NULL,
  `number` int(11) NOT NULL,
  `kind` varchar(255) NOT NULL,
  `time_interval` datetime NOT NULL,
  `validity` varchar(255) NOT NULL,
  `value_expression` varchar(255) NOT NULL,
  `value_unit` varchar(255) NOT NULL,
  `business_value_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_5xxk7uh9k3v0uqt0pd5cwhi6j` (`business_value_id`),
  CONSTRAINT `FK_5xxk7uh9k3v0uqt0pd5cwhi6j` FOREIGN KEY (`business_value_id`) REFERENCES `business_value_list` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `penaltydefinition`
--

LOCK TABLES `penaltydefinition` WRITE;
/*!40000 ALTER TABLE `penaltydefinition` DISABLE KEYS */;
INSERT INTO `penaltydefinition` VALUES (1,'discount',1,'CUSTOM_PENALTY','1970-01-01 00:00:00','P1D','5','%',1),(2,'discount',1,'CUSTOM_PENALTY','1970-01-01 00:00:00','P1D','5','%',2),(3,'discount',1,'CUSTOM_PENALTY','1970-01-01 00:00:00','P1D','5','%',3),(4,'discount',1,'CUSTOM_PENALTY','1970-01-01 00:00:00','P1D','5','%',4),(5,'discount',1,'CUSTOM_PENALTY','1970-01-01 00:00:00','P1D','5','%',5),(6,'discount',1,'CUSTOM_PENALTY','1970-01-01 00:00:00','P1D','5','%',6),(7,'discount',1,'CUSTOM_PENALTY','1970-01-01 00:00:00','P1D','5','%',7),(8,'discount',1,'CUSTOM_PENALTY','1970-01-01 00:00:00','P1D','5','%',8),(9,'discount',1,'CUSTOM_PENALTY','1970-01-01 00:00:00','P1D','5','%',9),(10,'discount',1,'CUSTOM_PENALTY','1970-01-01 00:00:00','P1D','5','%',10);
/*!40000 ALTER TABLE `penaltydefinition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `policy`
--

DROP TABLE IF EXISTS `policy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `policy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` int(11) DEFAULT NULL,
  `time_interval` datetime DEFAULT NULL,
  `variable` varchar(255) DEFAULT NULL,
  `guarantee_term_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_p4wup2luxq7ido6gqris3mmd8` (`guarantee_term_id`),
  CONSTRAINT `FK_p4wup2luxq7ido6gqris3mmd8` FOREIGN KEY (`guarantee_term_id`) REFERENCES `guarantee_term` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `policy`
--

LOCK TABLES `policy` WRITE;
/*!40000 ALTER TABLE `policy` DISABLE KEYS */;
/*!40000 ALTER TABLE `policy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provider`
--

DROP TABLE IF EXISTS `provider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `provider` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_cfgo93bl0v243co72ay26bs94` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provider`
--

LOCK TABLES `provider` WRITE;
/*!40000 ALTER TABLE `provider` DISABLE KEYS */;
INSERT INTO `provider` VALUES (1,'provider03name','provider03'),(2,'fp01','fp01'),(3,'ajaxprovider','ajaxprovider'),(4,'f5','f5'),(5,'f6','f6'),(6,'p6','p6'),(7,'c1','c1');
/*!40000 ALTER TABLE `provider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_properties`
--

DROP TABLE IF EXISTS `service_properties`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `service_properties` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `service_name` varchar(255) DEFAULT NULL,
  `agreement_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_q19s0rkjhma344krrm6rbfpdt` (`agreement_id`),
  CONSTRAINT `FK_q19s0rkjhma344krrm6rbfpdt` FOREIGN KEY (`agreement_id`) REFERENCES `agreement` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_properties`
--

LOCK TABLES `service_properties` WRITE;
/*!40000 ALTER TABLE `service_properties` DISABLE KEYS */;
INSERT INTO `service_properties` VALUES (1,'MonitoredMetrics','default',1),(2,'MonitoredMetrics','default',2),(3,'MonitoredMetrics','default',3),(4,'MonitoredMetrics','default',4),(5,'MonitoredMetrics','default',5);
/*!40000 ALTER TABLE `service_properties` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `template`
--

DROP TABLE IF EXISTS `template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `service_id` varchar(255) DEFAULT NULL,
  `text` longtext NOT NULL,
  `uuid` varchar(255) NOT NULL,
  `provider_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_m0a0t99pnntf75psmk1lptr3n` (`uuid`),
  KEY `FK_5p28ldeg0v7loq063g2s9gykx` (`provider_id`),
  CONSTRAINT `FK_5p28ldeg0v7loq063g2s9gykx` FOREIGN KEY (`provider_id`) REFERENCES `provider` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `template`
--

LOCK TABLES `template` WRITE;
/*!40000 ALTER TABLE `template` DISABLE KEYS */;
INSERT INTO `template` VALUES (1,'nombre','TC / should an ontology be defined or this is free text input?','<wsag:Template wsag:TemplateId=\"vnf3a2971d0-2eae-11e5-a2cb-0800200c9a66calls5k\" xmlns:wsag=\"http://www.ggf.org/namespaces/ws-agreement\" xmlns:sla=\"http://sla.atos.eu\"><wsag:Name>nombre</wsag:Name><wsag:Context><wsag:AgreementResponder>fp01</wsag:AgreementResponder><wsag:ServiceProvider>AgreementResponder</wsag:ServiceProvider><wsag:TemplateId>vnf3a2971d0-2eae-11e5-a2cb-0800200c9a66calls5k</wsag:TemplateId><sla:Service>TC / should an ontology be defined or this is free text input?</sla:Service></wsag:Context><wsag:Terms><wsag:All><wsag:ServiceDescriptionTerm wsag:Name=\"requirements\" wsag:ServiceName=\"calls5k\"><wsag:Requirements wsag:Name=\"virt_mem_res_element\" wsag:Value=\"6\" wsag:Unit=\"GB\"/><wsag:Requirements wsag:Name=\"CPU\" wsag:Value=\"6\" wsag:Unit=\"cores\"/><wsag:Requirements wsag:Name=\"TLB size\" wsag:Value=\"1024\" wsag:Unit=\"\"/><wsag:Requirements wsag:Name=\"storage\" wsag:Value=\"20\" wsag:Unit=\"GB\"/></wsag:ServiceDescriptionTerm><wsag:ServiceProperties wsag:Name=\"MonitoredMetrics\" wsag:ServiceName=\"default\"><wsag:VariableSet><wsag:Variable wsag:Name=\"pepito\" wsag:Metric=\"xs:double\"><wsag:Location>/monitor/pepito</wsag:Location></wsag:Variable><wsag:Variable wsag:Name=\"juanito\" wsag:Metric=\"xs:double\"><wsag:Location>/monitor/juanito</wsag:Location></wsag:Variable></wsag:VariableSet></wsag:ServiceProperties><wsag:GuaranteeTerm wsag:Name=\"pepito\"><wsag:ServiceLevelObjective><wsag:KPITarget><wsag:KPIName>pepito</wsag:KPIName><wsag:CustomServiceLevel> { \"policies\": [ { \"count\" : 2, \"interval\": 30 } ], \"constraint\" : \"pepito GT 0.5\" }</wsag:CustomServiceLevel></wsag:KPITarget></wsag:ServiceLevelObjective><wsag:BusinessValueList><wsag:CustomBusinessValue count=\"1\"><wsag:Penalty type=\"discount\" expression=\"5\" unit=\"%\" validity=\"P1D\"/></wsag:CustomBusinessValue></wsag:BusinessValueList></wsag:GuaranteeTerm><wsag:GuaranteeTerm wsag:Name=\"juanito\"><wsag:ServiceLevelObjective><wsag:KPITarget><wsag:KPIName>juanito</wsag:KPIName><wsag:CustomServiceLevel> { \"policies\": [ { \"count\" : 2, \"interval\": 30 } ], \"constraint\" : \"juanito GT 0.7\" }</wsag:CustomServiceLevel></wsag:KPITarget></wsag:ServiceLevelObjective><wsag:BusinessValueList><wsag:CustomBusinessValue count=\"1\"><wsag:Penalty type=\"discount\" expression=\"5\" unit=\"%\" validity=\"P1D\"/></wsag:CustomBusinessValue></wsag:BusinessValueList></wsag:GuaranteeTerm></wsag:All></wsag:Terms></wsag:Template>','vnf3a2971d0-2eae-11e5-a2cb-0800200c9a66calls5k',2),(2,'vnf5gold','TC / should an ontology be defined or this is free text input?','<wsag:Template wsag:TemplateId=\"vnfvnf5gold\" xmlns:wsag=\"http://www.ggf.org/namespaces/ws-agreement\" xmlns:sla=\"http://sla.atos.eu\"><wsag:Name>vnf5gold</wsag:Name><wsag:Context><wsag:AgreementResponder>f5</wsag:AgreementResponder><wsag:ServiceProvider>AgreementResponder</wsag:ServiceProvider><wsag:TemplateId>vnfvnf5gold</wsag:TemplateId><sla:Service>TC / should an ontology be defined or this is free text input?</sla:Service></wsag:Context><wsag:Terms><wsag:All><wsag:ServiceDescriptionTerm wsag:Name=\"requirements\" wsag:ServiceName=\"calls5k\"><wsag:Requirements wsag:Name=\"virt_mem_res_element\" wsag:Value=\"6\" wsag:Unit=\"GB\"/><wsag:Requirements wsag:Name=\"CPU\" wsag:Value=\"6\" wsag:Unit=\"cores\"/><wsag:Requirements wsag:Name=\"TLB size\" wsag:Value=\"1024\" wsag:Unit=\"\"/><wsag:Requirements wsag:Name=\"storage\" wsag:Value=\"20\" wsag:Unit=\"GB\"/></wsag:ServiceDescriptionTerm><wsag:ServiceProperties wsag:Name=\"MonitoredMetrics\" wsag:ServiceName=\"default\"><wsag:VariableSet><wsag:Variable wsag:Name=\"pepitovnf5\" wsag:Metric=\"xs:double\"><wsag:Location>/monitor/pepitovnf5</wsag:Location></wsag:Variable><wsag:Variable wsag:Name=\"juanitovnf5\" wsag:Metric=\"xs:double\"><wsag:Location>/monitor/juanitovnf5</wsag:Location></wsag:Variable></wsag:VariableSet></wsag:ServiceProperties><wsag:GuaranteeTerm wsag:Name=\"pepitovnf5\"><wsag:ServiceLevelObjective><wsag:KPITarget><wsag:KPIName>pepitovnf5</wsag:KPIName><wsag:CustomServiceLevel> { \"policies\": [ { \"count\" : 2, \"interval\": 30 } ], \"constraint\" : \"pepitovnf5 GT 0.5\" }</wsag:CustomServiceLevel></wsag:KPITarget></wsag:ServiceLevelObjective><wsag:BusinessValueList><wsag:CustomBusinessValue count=\"1\"><wsag:Penalty type=\"discount\" expression=\"5\" unit=\"%\" validity=\"P1D\"/></wsag:CustomBusinessValue></wsag:BusinessValueList></wsag:GuaranteeTerm><wsag:GuaranteeTerm wsag:Name=\"juanitovnf5\"><wsag:ServiceLevelObjective><wsag:KPITarget><wsag:KPIName>juanitovnf5</wsag:KPIName><wsag:CustomServiceLevel> { \"policies\": [ { \"count\" : 2, \"interval\": 30 } ], \"constraint\" : \"juanitovnf5 GT 0.7\" }</wsag:CustomServiceLevel></wsag:KPITarget></wsag:ServiceLevelObjective><wsag:BusinessValueList><wsag:CustomBusinessValue count=\"1\"><wsag:Penalty type=\"discount\" expression=\"5\" unit=\"%\" validity=\"P1D\"/></wsag:CustomBusinessValue></wsag:BusinessValueList></wsag:GuaranteeTerm></wsag:All></wsag:Terms></wsag:Template>','vnfvnf5gold',4),(3,'vnf6gold','TC / should an ontology be defined or this is free text input?','<wsag:Template wsag:TemplateId=\"vnfvnf6gold\" xmlns:wsag=\"http://www.ggf.org/namespaces/ws-agreement\" xmlns:sla=\"http://sla.atos.eu\"><wsag:Name>vnf6gold</wsag:Name><wsag:Context><wsag:AgreementResponder>f6</wsag:AgreementResponder><wsag:ServiceProvider>AgreementResponder</wsag:ServiceProvider><wsag:TemplateId>vnfvnf6gold</wsag:TemplateId><sla:Service>TC / should an ontology be defined or this is free text input?</sla:Service></wsag:Context><wsag:Terms><wsag:All><wsag:ServiceDescriptionTerm wsag:Name=\"requirements\" wsag:ServiceName=\"calls5k\"><wsag:Requirements wsag:Name=\"virt_mem_res_element\" wsag:Value=\"6\" wsag:Unit=\"GB\"/><wsag:Requirements wsag:Name=\"CPU\" wsag:Value=\"6\" wsag:Unit=\"cores\"/><wsag:Requirements wsag:Name=\"TLB size\" wsag:Value=\"1024\" wsag:Unit=\"\"/><wsag:Requirements wsag:Name=\"storage\" wsag:Value=\"20\" wsag:Unit=\"GB\"/></wsag:ServiceDescriptionTerm><wsag:ServiceProperties wsag:Name=\"MonitoredMetrics\" wsag:ServiceName=\"default\"><wsag:VariableSet><wsag:Variable wsag:Name=\"pepitovnf6\" wsag:Metric=\"xs:double\"><wsag:Location>/monitor/pepitovnf6</wsag:Location></wsag:Variable><wsag:Variable wsag:Name=\"juanitovnf6\" wsag:Metric=\"xs:double\"><wsag:Location>/monitor/juanitovnf6</wsag:Location></wsag:Variable></wsag:VariableSet></wsag:ServiceProperties><wsag:GuaranteeTerm wsag:Name=\"pepitovnf6\"><wsag:ServiceLevelObjective><wsag:KPITarget><wsag:KPIName>pepitovnf6</wsag:KPIName><wsag:CustomServiceLevel> { \"policies\": [ { \"count\" : 2, \"interval\": 30 } ], \"constraint\" : \"pepitovnf6 GT 0.5\" }</wsag:CustomServiceLevel></wsag:KPITarget></wsag:ServiceLevelObjective><wsag:BusinessValueList><wsag:CustomBusinessValue count=\"1\"><wsag:Penalty type=\"discount\" expression=\"5\" unit=\"%\" validity=\"P1D\"/></wsag:CustomBusinessValue></wsag:BusinessValueList></wsag:GuaranteeTerm><wsag:GuaranteeTerm wsag:Name=\"juanitovnf6\"><wsag:ServiceLevelObjective><wsag:KPITarget><wsag:KPIName>juanitovnf6</wsag:KPIName><wsag:CustomServiceLevel> { \"policies\": [ { \"count\" : 2, \"interval\": 30 } ], \"constraint\" : \"juanitovnf6 GT 0.7\" }</wsag:CustomServiceLevel></wsag:KPITarget></wsag:ServiceLevelObjective><wsag:BusinessValueList><wsag:CustomBusinessValue count=\"1\"><wsag:Penalty type=\"discount\" expression=\"5\" unit=\"%\" validity=\"P1D\"/></wsag:CustomBusinessValue></wsag:BusinessValueList></wsag:GuaranteeTerm></wsag:All></wsag:Terms></wsag:Template>','vnfvnf6gold',5),(4,'service6gold','TC / should an ontology be defined or this is free text input?','<wsag:Template wsag:TemplateId=\"serviceservice6gold\" xmlns:wsag=\"http://www.ggf.org/namespaces/ws-agreement\" xmlns:sla=\"http://sla.atos.eu\"><wsag:Name>service6gold</wsag:Name><wsag:Context><wsag:AgreementResponder>p6</wsag:AgreementResponder><wsag:ServiceProvider>AgreementResponder</wsag:ServiceProvider><wsag:TemplateId>serviceservice6gold</wsag:TemplateId><sla:Service>TC / should an ontology be defined or this is free text input?</sla:Service></wsag:Context><wsag:Terms><wsag:All><wsag:ServiceDescriptionTerm wsag:Name=\"requirements\" wsag:ServiceName=\"calls5k\"><wsag:Requirements wsag:Name=\"virt_mem_res_element\" wsag:Value=\"6\" wsag:Unit=\"GB\"/><wsag:Requirements wsag:Name=\"CPU\" wsag:Value=\"6\" wsag:Unit=\"cores\"/><wsag:Requirements wsag:Name=\"TLB size\" wsag:Value=\"1024\" wsag:Unit=\"\"/><wsag:Requirements wsag:Name=\"storage\" wsag:Value=\"20\" wsag:Unit=\"GB\"/></wsag:ServiceDescriptionTerm><wsag:ServiceProperties wsag:Name=\"MonitoredMetrics\" wsag:ServiceName=\"default\"><wsag:VariableSet><wsag:Variable wsag:Name=\"pepitoservice6\" wsag:Metric=\"xs:double\"><wsag:Location>/monitor/pepitoservice6</wsag:Location></wsag:Variable><wsag:Variable wsag:Name=\"juanitoservice6\" wsag:Metric=\"xs:double\"><wsag:Location>/monitor/juanitoservice6</wsag:Location></wsag:Variable></wsag:VariableSet></wsag:ServiceProperties><wsag:GuaranteeTerm wsag:Name=\"pepitoservice6\"><wsag:ServiceLevelObjective><wsag:KPITarget><wsag:KPIName>pepitoservice6</wsag:KPIName><wsag:CustomServiceLevel> { \"policies\": [ { \"count\" : 2, \"interval\": 30 } ], \"constraint\" : \"pepitoservice6 GT 0.5\" }</wsag:CustomServiceLevel></wsag:KPITarget></wsag:ServiceLevelObjective><wsag:BusinessValueList><wsag:CustomBusinessValue count=\"1\"><wsag:Penalty type=\"discount\" expression=\"5\" unit=\"%\" validity=\"P1D\"/></wsag:CustomBusinessValue></wsag:BusinessValueList></wsag:GuaranteeTerm><wsag:GuaranteeTerm wsag:Name=\"juanitoservice6\"><wsag:ServiceLevelObjective><wsag:KPITarget><wsag:KPIName>juanitoservice6</wsag:KPIName><wsag:CustomServiceLevel> { \"policies\": [ { \"count\" : 2, \"interval\": 30 } ], \"constraint\" : \"juanitoservice6 GT 0.7\" }</wsag:CustomServiceLevel></wsag:KPITarget></wsag:ServiceLevelObjective><wsag:BusinessValueList><wsag:CustomBusinessValue count=\"1\"><wsag:Penalty type=\"discount\" expression=\"5\" unit=\"%\" validity=\"P1D\"/></wsag:CustomBusinessValue></wsag:BusinessValueList></wsag:GuaranteeTerm></wsag:All></wsag:Terms></wsag:Template>','serviceservice6gold',6),(5,'vnf6goldtest','TC / should an ontology be defined or this is free text input?','<wsag:Template wsag:TemplateId=\"vnfvnf6goldtest\" xmlns:wsag=\"http://www.ggf.org/namespaces/ws-agreement\" xmlns:sla=\"http://sla.atos.eu\"><wsag:Name>vnf6goldtest</wsag:Name><wsag:Context><wsag:AgreementResponder>f6</wsag:AgreementResponder><wsag:ServiceProvider>AgreementResponder</wsag:ServiceProvider><wsag:TemplateId>vnfvnf6goldtest</wsag:TemplateId><sla:Service>TC / should an ontology be defined or this is free text input?</sla:Service></wsag:Context><wsag:Terms><wsag:All><wsag:ServiceDescriptionTerm wsag:Name=\"requirements\" wsag:ServiceName=\"calls5k\"><wsag:Requirements wsag:Name=\"virt_mem_res_element\" wsag:Value=\"6\" wsag:Unit=\"GB\"/><wsag:Requirements wsag:Name=\"CPU\" wsag:Value=\"6\" wsag:Unit=\"cores\"/><wsag:Requirements wsag:Name=\"TLB size\" wsag:Value=\"1024\" wsag:Unit=\"\"/><wsag:Requirements wsag:Name=\"storage\" wsag:Value=\"20\" wsag:Unit=\"GB\"/></wsag:ServiceDescriptionTerm><wsag:ServiceProperties wsag:Name=\"MonitoredMetrics\" wsag:ServiceName=\"default\"><wsag:VariableSet><wsag:Variable wsag:Name=\"pepitovnf6\" wsag:Metric=\"xs:double\"><wsag:Location>/monitor/pepitovnf6</wsag:Location></wsag:Variable><wsag:Variable wsag:Name=\"juanitovnf6\" wsag:Metric=\"xs:double\"><wsag:Location>/monitor/juanitovnf6</wsag:Location></wsag:Variable></wsag:VariableSet></wsag:ServiceProperties><wsag:GuaranteeTerm wsag:Name=\"pepitovnf6\"><wsag:ServiceLevelObjective><wsag:KPITarget><wsag:KPIName>pepitovnf6</wsag:KPIName><wsag:CustomServiceLevel> { \"policies\": [ { \"count\" : 2, \"interval\": 30 } ], \"constraint\" : \"pepitovnf6 GT 0.5\" }</wsag:CustomServiceLevel></wsag:KPITarget></wsag:ServiceLevelObjective><wsag:BusinessValueList><wsag:CustomBusinessValue count=\"1\"><wsag:Penalty type=\"discount\" expression=\"3\" unit=\"%\" validity=\"P1D\"/></wsag:CustomBusinessValue></wsag:BusinessValueList></wsag:GuaranteeTerm><wsag:GuaranteeTerm wsag:Name=\"juanitovnf6\"><wsag:ServiceLevelObjective><wsag:KPITarget><wsag:KPIName>juanitovnf6</wsag:KPIName><wsag:CustomServiceLevel> { \"policies\": [ { \"count\" : 2, \"interval\": 30 } ], \"constraint\" : \"juanitovnf6 GT 0.7\" }</wsag:CustomServiceLevel></wsag:KPITarget></wsag:ServiceLevelObjective><wsag:BusinessValueList><wsag:CustomBusinessValue count=\"1\"><wsag:Penalty type=\"discount\" expression=\"5\" unit=\"%\" validity=\"P1D\"/></wsag:CustomBusinessValue></wsag:BusinessValueList></wsag:GuaranteeTerm></wsag:All></wsag:Terms></wsag:Template>','vnfvnf6goldtest',5);
/*!40000 ALTER TABLE `template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `variable`
--

DROP TABLE IF EXISTS `variable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `variable` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `location` varchar(255) DEFAULT NULL,
  `metric` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `service_properties_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_pyy0qp89btrs2q66ivtwe46ue` (`service_properties_id`),
  CONSTRAINT `FK_pyy0qp89btrs2q66ivtwe46ue` FOREIGN KEY (`service_properties_id`) REFERENCES `service_properties` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `variable`
--

LOCK TABLES `variable` WRITE;
/*!40000 ALTER TABLE `variable` DISABLE KEYS */;
INSERT INTO `variable` VALUES (1,'/monitor/pepito','xs:double','pepito',1),(2,'/monitor/juanito','xs:double','juanito',1),(3,'/monitor/pepitovnf5','xs:double','pepitovnf5',2),(4,'/monitor/juanitovnf5','xs:double','juanitovnf5',2),(5,'/monitor/pepitovnf6','xs:double','pepitovnf6',3),(6,'/monitor/juanitovnf6','xs:double','juanitovnf6',3),(7,'/monitor/pepitoservice6','xs:double','pepitoservice6',4),(8,'/monitor/juanitoservice6','xs:double','juanitoservice6',4),(9,'/monitor/pepitovnf5','xs:double','pepitovnf5',5),(10,'/monitor/juanitovnf5','xs:double','juanitovnf5',5);
/*!40000 ALTER TABLE `variable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `violation`
--

DROP TABLE IF EXISTS `violation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `violation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `actual_value` varchar(255) DEFAULT NULL,
  `contract_uuid` varchar(255) DEFAULT NULL,
  `datetime` datetime DEFAULT NULL,
  `expected_value` varchar(255) DEFAULT NULL,
  `kpi_name` varchar(255) DEFAULT NULL,
  `service_name` varchar(255) DEFAULT NULL,
  `service_scope` varchar(255) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `policy_id` bigint(20) DEFAULT NULL,
  `guarantee_term_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_dxe6b01v3e51k3ab3fy7rk4xv` (`policy_id`),
  KEY `FK_ia6898r6ple40dimipcg8ru2u` (`guarantee_term_id`),
  CONSTRAINT `FK_dxe6b01v3e51k3ab3fy7rk4xv` FOREIGN KEY (`policy_id`) REFERENCES `policy` (`id`),
  CONSTRAINT `FK_ia6898r6ple40dimipcg8ru2u` FOREIGN KEY (`guarantee_term_id`) REFERENCES `guarantee_term` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `violation`
--

LOCK TABLES `violation` WRITE;
/*!40000 ALTER TABLE `violation` DISABLE KEYS */;
INSERT INTO `violation` VALUES (1,'0.253482343000598','vnfidf51','2015-11-03 12:52:29','juanitovnf5 GT 0.7','juanitovnf5',NULL,NULL,'ed367bf4-1f27-4279-94d8-6563c1731f00',NULL,4),(2,'0.41901074956479567','serviceids101','2015-11-03 12:52:29','pepitoservice6 GT 0.5','pepitoservice6',NULL,NULL,'6f3a8541-2d5a-4e4e-8dd9-303ba7a62044',NULL,7),(3,'0.3680034206185283','vnfidf52','2015-11-03 12:52:29','juanitovnf6 GT 0.7','juanitovnf6',NULL,NULL,'23f4dcf7-384a-4427-b10a-752f5bcb7b0d',NULL,6),(4,'0.29866123993788096','serviceids101','2015-11-03 12:52:30','pepitoservice6 GT 0.5','pepitoservice6',NULL,NULL,'9519aa33-601a-49a4-a479-ae63b2789383',NULL,7),(5,'0.5682455083655568','vnfidf51','2015-11-03 12:52:30','juanitovnf5 GT 0.7','juanitovnf5',NULL,NULL,'7d1816ea-143a-49e5-bb42-efd5b19ff491',NULL,4),(6,'0.6527415055615144','vnfidf52','2015-11-03 12:52:30','juanitovnf6 GT 0.7','juanitovnf6',NULL,NULL,'626dcb6e-df3d-4027-a0d7-9f2ec0c7616b',NULL,6),(7,'0.45551586378979037','serviceids101','2015-11-03 12:52:30','juanitoservice6 GT 0.7','juanitoservice6',NULL,NULL,'be24c880-8adb-4097-a24f-a7902c7d280e',NULL,8),(8,'0.28181408070529346','vnfidf52','2015-11-03 12:52:30','pepitovnf6 GT 0.5','pepitovnf6',NULL,NULL,'818e9e14-21a0-4871-98c6-03a333dde49e',NULL,5),(9,'0.22093395479178446','vnfidf51','2015-11-03 12:52:31','pepitovnf5 GT 0.5','pepitovnf5',NULL,NULL,'f12a4cf9-ad60-4bc9-8181-959844a51d66',NULL,3),(10,'0.48430545849878315','serviceids101','2015-11-03 12:52:31','juanitoservice6 GT 0.7','juanitoservice6',NULL,NULL,'d969af7b-86d2-4905-8d35-9e0d6f7d034b',NULL,8),(11,'0.06236873920345465','vnfidf51','2015-11-03 12:53:30','pepitovnf5 GT 0.5','pepitovnf5',NULL,NULL,'bafc6ec1-3efc-4b62-938b-7cb85d5bb271',NULL,3),(12,'0.5270007208522326','serviceids101','2015-11-03 12:53:30','juanitoservice6 GT 0.7','juanitoservice6',NULL,NULL,'e5055641-cbb7-4d67-a53d-30cd61d24695',NULL,8),(13,'0.2931895818833822','vnfidf52','2015-11-03 12:52:31','pepitovnf6 GT 0.5','pepitovnf6',NULL,NULL,'c5afdc13-0f45-4419-bb43-3539c473bb93',NULL,5),(14,'0.1878621715253539','vnfidf52','2015-11-03 12:53:30','pepitovnf6 GT 0.5','pepitovnf6',NULL,NULL,'41e0cbcb-54b0-45b4-aef9-294f76989191',NULL,5),(15,'0.4885389130808194','vnfidf51','2015-11-03 12:53:30','juanitovnf5 GT 0.7','juanitovnf5',NULL,NULL,'fadb95e5-f9a5-414f-897b-166d07c241ad',NULL,4),(16,'0.5463186909458446','vnfidf52','2015-11-03 12:52:31','juanitovnf6 GT 0.7','juanitovnf6',NULL,NULL,'1e345bd4-683c-4f5d-b033-c2adc357477d',NULL,6),(17,'0.10650219676543093','vnfidf52','2015-11-03 12:53:30','juanitovnf6 GT 0.7','juanitovnf6',NULL,NULL,'0b25ce1b-3b82-4712-9cb8-469dcae07efc',NULL,6),(18,'0.361485005037688','serviceids101','2015-11-03 12:53:31','pepitoservice6 GT 0.5','pepitoservice6',NULL,NULL,'634a3cf4-8d17-4e99-9cde-71f46f476658',NULL,7),(19,'0.6207995090761843','serviceids101','2015-11-03 12:53:31','juanitoservice6 GT 0.7','juanitoservice6',NULL,NULL,'90dc0e73-0bd0-475d-92f3-1e1dc922084c',NULL,8),(20,'0.026276974982126444','serviceids101','2015-11-03 14:58:31','juanitoservice6 GT 0.7','juanitoservice6',NULL,NULL,'5313dd70-9f62-4bae-8a15-4121656ad0e5',NULL,8),(21,'0.6206242095772277','serviceids101','2015-11-03 14:59:30','juanitoservice6 GT 0.7','juanitoservice6',NULL,NULL,'62843857-867d-4c98-a5d0-5c5dc9993fdf',NULL,8),(22,'0.12156937239178267','serviceids101','2015-11-03 14:59:30','pepitoservice6 GT 0.5','pepitoservice6',NULL,NULL,'4786f46a-12a8-43f6-b46a-3cd842bc541c',NULL,7),(23,'0.02309865744735573','serviceids101','2015-11-03 15:00:30','juanitoservice6 GT 0.7','juanitoservice6',NULL,NULL,'a2c99577-89da-4bab-8374-83f5504f6b8e',NULL,8),(24,'0.16515297922577155','serviceids101','2015-11-03 15:00:30','pepitoservice6 GT 0.5','pepitoservice6',NULL,NULL,'10033065-730d-41e2-b899-80c3ebe40e9a',NULL,7),(25,'0.386591561598297','serviceids101','2015-11-03 15:00:30','juanitoservice6 GT 0.7','juanitoservice6',NULL,NULL,'04247c1b-2049-48c6-864e-962675ff41ed',NULL,8),(26,'0.3401281118393169','serviceids101','2015-11-03 15:01:30','juanitoservice6 GT 0.7','juanitoservice6',NULL,NULL,'d3be1063-9f2b-4fe1-909a-cb4cf8b76ff7',NULL,8),(27,'0.036810713548176444','serviceids101','2015-11-03 15:01:30','pepitoservice6 GT 0.5','pepitoservice6',NULL,NULL,'976354ca-7225-4191-bf7e-c224cd81be7c',NULL,7),(28,'0.4519536197773426','serviceids101','2015-11-03 15:01:30','juanitoservice6 GT 0.7','juanitoservice6',NULL,NULL,'70c54b19-fdc3-47e5-b6e8-7a1a5d471f16',NULL,8),(29,'0.513789226731988','serviceids101','2015-11-03 15:02:30','juanitoservice6 GT 0.7','juanitoservice6',NULL,NULL,'c3257bf0-4dba-4630-9bd5-63654b652741',NULL,8),(30,'0.1885886353921169','serviceids101','2015-11-03 15:02:30','pepitoservice6 GT 0.5','pepitoservice6',NULL,NULL,'c645ed25-0660-4bf2-9ea7-5de7cf7cd528',NULL,7),(31,'0.07946663525442144','serviceids101','2015-11-03 15:03:30','pepitoservice6 GT 0.5','pepitoservice6',NULL,NULL,'789a895f-326d-4b5b-ba2b-465ad0aea6cc',NULL,7),(32,'0.04273172450245677','serviceids101','2015-11-03 15:03:30','juanitoservice6 GT 0.7','juanitoservice6',NULL,NULL,'d16a4e5a-8f34-4a2e-9b01-b950adaf87d2',NULL,8),(33,'0.009882157296997485','serviceids101','2015-11-03 15:04:30','juanitoservice6 GT 0.7','juanitoservice6',NULL,NULL,'cf181bec-70ad-41d8-81a7-0437f4d3203a',NULL,8),(34,'0.29793517208195963','serviceids101','2015-11-03 15:04:30','pepitoservice6 GT 0.5','pepitoservice6',NULL,NULL,'e8dffc15-a471-4cf8-b8b8-49c6666cea8a',NULL,7),(35,'0.44839714020725785','serviceids101','2015-11-03 15:06:30','pepitoservice6 GT 0.5','pepitoservice6',NULL,NULL,'8058e16c-1161-40dc-86cf-ea4d7b1b7f22',NULL,7),(36,'0.017569418173391194','serviceids101','2015-11-03 15:04:30','juanitoservice6 GT 0.7','juanitoservice6',NULL,NULL,'54c0e6db-3ca0-4e58-8f5b-8a44bbe5a388',NULL,8),(37,'0.17118136972782672','serviceids101','2015-11-03 15:06:30','pepitoservice6 GT 0.5','pepitoservice6',NULL,NULL,'96e99c9d-c80b-466d-9739-d055824f0215',NULL,7),(38,'0.4838976969627654','serviceids101','2015-11-03 15:07:30','pepitoservice6 GT 0.5','pepitoservice6',NULL,NULL,'6cc7539c-f375-4359-9e76-9a01126b7486',NULL,7),(39,'0.4959282837410601','serviceids101','2015-11-03 15:07:30','pepitoservice6 GT 0.5','pepitoservice6',NULL,NULL,'0e80c514-4103-41ee-9bab-85eacb10220f',NULL,7),(40,'0.41622784678069735','serviceids101','2015-11-03 15:07:30','juanitoservice6 GT 0.7','juanitoservice6',NULL,NULL,'0a3dbb98-c0c6-4e32-a6ec-277e58d19387',NULL,8),(41,'0.08956104974604129','serviceids101','2015-11-03 15:08:30','juanitoservice6 GT 0.7','juanitoservice6',NULL,NULL,'8f89222c-144a-4d54-98c1-3fdd3f93925d',NULL,8),(42,'0.1270084906807255','serviceids101','2015-11-03 15:09:30','juanitoservice6 GT 0.7','juanitoservice6',NULL,NULL,'130e9c87-c3d8-4ad9-b893-bae00ca5227b',NULL,8),(43,'0.36849305080508576','serviceids101','2015-11-03 15:08:30','pepitoservice6 GT 0.5','pepitoservice6',NULL,NULL,'edfee564-d4d7-451d-a648-a301b3e06bac',NULL,7),(44,'0.22631073838349602','serviceids101','2015-11-03 15:09:30','pepitoservice6 GT 0.5','pepitoservice6',NULL,NULL,'651cba3e-b96b-4665-a6a4-69e08df50918',NULL,7),(45,'0.031089440735814655','vnfidf51','2015-11-03 12:53:31','pepitovnf5 GT 0.5','pepitovnf5',NULL,NULL,'e2e84f38-e32e-4d8f-94ba-b732c1065b34',NULL,3),(46,'0.22053775604150716','serviceids101','2015-11-03 15:09:30','juanitoservice6 GT 0.7','juanitoservice6',NULL,NULL,'0215f5f0-911f-4afe-b6ca-00ad79891ef0',NULL,8),(47,'0.16491713356365545','vnfidf51','2015-11-03 15:10:30','pepitovnf5 GT 0.5','pepitovnf5',NULL,NULL,'597ea98b-86b6-4b70-805b-4b7097955189',NULL,3),(48,'0.2866500121573573','vnfidf52','2015-11-03 12:53:31','pepitovnf6 GT 0.5','pepitovnf6',NULL,NULL,'066eef89-f043-418f-b323-2b1a405f26f9',NULL,5),(49,'0.43127626083203485','serviceids101','2015-11-03 15:10:30','juanitoservice6 GT 0.7','juanitoservice6',NULL,NULL,'bf37cc2e-eacb-41ed-967d-df2619cc360d',NULL,8),(50,'0.6303118884587928','vnfidf51','2015-11-03 15:10:30','juanitovnf5 GT 0.7','juanitovnf5',NULL,NULL,'b631a5a8-7ce0-4c33-a70a-e2c2d0e49c09',NULL,4),(51,'0.5375311676673276','vnfidf52','2015-11-03 12:53:31','juanitovnf6 GT 0.7','juanitovnf6',NULL,NULL,'fcebd47a-d343-4ade-aeb3-b0b89890fb3c',NULL,6),(52,'0.2103542295214853','vnfidf52','2015-11-03 15:10:30','juanitovnf6 GT 0.7','juanitovnf6',NULL,NULL,'f4ea2fc9-ce78-44fc-a42d-abe17076f2ba',NULL,6),(53,'0.17356537574105313','serviceids101','2015-11-03 15:10:30','pepitoservice6 GT 0.5','pepitoservice6',NULL,NULL,'8d9f0a1b-dedf-48c2-8c4e-63d110e90328',NULL,7);
/*!40000 ALTER TABLE `violation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-04 11:13:16
