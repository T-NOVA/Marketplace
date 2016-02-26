-- MySQL dump 10.13  Distrib 5.5.46, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: accounting
-- ------------------------------------------------------
-- Server version	5.5.46-0ubuntu0.14.04.2

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
-- Table structure for table `account_account`
--

DROP TABLE IF EXISTS `account_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productId` varchar(256) NOT NULL,
  `instanceId` varchar(256) NOT NULL,
  `agreementId` varchar(256) NOT NULL,
  `relatives` char(255) DEFAULT NULL,
  `productType` varchar(10) NOT NULL,
  `startDate` datetime NOT NULL,
  `lastBillDate` datetime NOT NULL,
  `providerId` varchar(256) NOT NULL,
  `clientId` varchar(256) NOT NULL,
  `status` varchar(10) NOT NULL,
  `billingModel` varchar(10) NOT NULL,
  `period` varchar(10) NOT NULL,
  `priceUnit` varchar(3) NOT NULL,
  `periodCost` double NOT NULL,
  `setupCost` double NOT NULL,
  `renew` tinyint(1) NOT NULL,
  `dateCreated` datetime NOT NULL,
  `dateModified` datetime DEFAULT NULL,
  `flavour` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_account`
--

LOCK TABLES `account_account` WRITE;
/*!40000 ALTER TABLE `account_account` DISABLE KEYS */;
INSERT INTO `account_account` VALUES (1,'vnf1','id01','s1vnf2_4','id02','vnf','2015-06-10 00:00:00','2015-06-10 00:00:00','f1','p1','stopped','PAYG','P1D','EUR',1.5,2,1,'2015-06-11 13:29:16','2015-11-03 10:53:40',NULL),(2,'s1','id02','s1vnf2_4','id01, id03','ns','2015-06-11 00:00:00','2015-06-11 00:00:00','p1','c1','running','PAYG','P1D','EUR',1,1,1,'2015-06-11 13:29:16','2015-12-10 09:29:41',NULL),(3,'vnf2','id03','slaf1s1','id02','vnf','2015-06-16 00:00:00','2015-06-25 00:00:00','f1','p1','stopped','PAYG','P1D','EUR',1,1,1,'2015-06-11 14:55:42','2015-11-03 10:53:40',NULL),(4,'vnf3','id04','s1vnf2_4','id19','vnf','2015-06-10 00:00:00','2015-06-10 00:00:00','f2','p1','running','PAYG','P1D','EUR',2,1,1,'2015-07-20 10:54:45','2015-10-08 09:19:44',NULL),(11,'s2','id19','vnf3a2971d0-2eae-11e5-a2cb-0800200c9a66calls5k','id04','ns','2015-06-10 00:00:00','2015-06-10 00:00:00','p1','c1','running','PAYG','P1D','EUR',1.5,2,0,'2015-07-28 14:36:14','2015-11-11 11:50:03','gold'),(24,'vnf5','idf50','s1vnf2_4','ids100','vnf','2015-10-08 07:07:43','2015-10-08 07:07:43','f5','p5','running','PAYG','P1D','EUR',1,2,0,'2015-10-08 07:07:43','2015-10-08 07:07:43',NULL),(25,'s5','ids100','s1vnf2_4','idf50','ns','2015-10-08 07:09:19','2015-10-08 07:09:19','p5','c5','running','PAYG','P1D','EUR',1,2,0,'2015-10-08 07:09:19','2015-10-08 07:09:19',NULL),(26,'vnf5','idf51','vnfidf51','ids101','vnf','2015-10-08 07:29:44','2015-10-08 07:29:44','f5','p6','stopped','PAYG','P1D','EUR',1,2,0,'2015-10-08 07:29:44','2015-12-10 09:43:50','gold'),(27,'vnf6','idf52','vnfidf52','ids101','vnf','2015-10-08 07:30:13','2015-10-08 07:30:13','f6','p6','stopped','PAYG','P1D','EUR',1,2,0,'2015-10-08 07:30:13','2015-12-10 09:43:52','gold'),(28,'service6','ids101','serviceids101','idf51, idf52','ns','2015-10-08 07:31:37','2015-10-08 07:31:37','p6','c1','stopped','PAYG','P1D','EUR',1,2,0,'2015-10-08 07:31:37','2015-12-10 09:43:49','gold'),(29,'vnf5','idf61','vnfidf61','ids161','vnf','2015-11-03 14:04:05','2015-11-03 14:04:05','f5','providerajax','running','PAYG','P1D','EUR',1,2,0,'2015-11-03 14:04:05','2015-11-03 15:22:49','gold'),(34,'service100','ids161','serviceids161','idf61','ns','2015-11-11 12:21:30','2015-11-11 12:21:30','providerajax','c1','running','PAYG','P1M','EUR',10,2,0,'2015-11-11 12:21:30','2015-11-11 12:21:30','silver');
/*!40000 ALTER TABLE `account_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_billingevent`
--

DROP TABLE IF EXISTS `account_billingevent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_billingevent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `agreementId` varchar(26) NOT NULL,
  `productId` varchar(26) NOT NULL,
  `productType` varchar(26) NOT NULL,
  `eventType` varchar(26) NOT NULL,
  `clientId` varchar(26) NOT NULL,
  `providerId` varchar(26) NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_billingevent`
--

LOCK TABLES `account_billingevent` WRITE;
/*!40000 ALTER TABLE `account_billingevent` DISABLE KEYS */;
/*!40000 ALTER TABLE `account_billingevent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_monitor`
--

DROP TABLE IF EXISTS `account_monitor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_monitor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serviceId` varchar(256) NOT NULL,
  `metricName` varchar(256) NOT NULL,
  `value` double NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_monitor`
--

LOCK TABLES `account_monitor` WRITE;
/*!40000 ALTER TABLE `account_monitor` DISABLE KEYS */;
/*!40000 ALTER TABLE `account_monitor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_slapenalty`
--

DROP TABLE IF EXISTS `account_slapenalty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_slapenalty` (
  `uuid` varchar(256) NOT NULL,
  `datetime` varchar(256) DEFAULT NULL,
  `agreementId` varchar(26) NOT NULL,
  `violation_id` int(11) NOT NULL,
  PRIMARY KEY (`violation_id`),
  CONSTRAINT `violation_id_refs_id_b1787d1d` FOREIGN KEY (`violation_id`) REFERENCES `account_slaviolation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_slapenalty`
--

LOCK TABLES `account_slapenalty` WRITE;
/*!40000 ALTER TABLE `account_slapenalty` DISABLE KEYS */;
/*!40000 ALTER TABLE `account_slapenalty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_slaviolation`
--

DROP TABLE IF EXISTS `account_slaviolation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_slaviolation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(256) NOT NULL,
  `datetime` datetime NOT NULL,
  `actualValue` double NOT NULL,
  `kpiName` varchar(26) NOT NULL,
  `contractUuid` varchar(26) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_slaviolation`
--

LOCK TABLES `account_slaviolation` WRITE;
/*!40000 ALTER TABLE `account_slaviolation` DISABLE KEYS */;
/*!40000 ALTER TABLE `account_slaviolation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_group`
--

DROP TABLE IF EXISTS `auth_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(80) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_group`
--

LOCK TABLES `auth_group` WRITE;
/*!40000 ALTER TABLE `auth_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_group_permissions`
--

DROP TABLE IF EXISTS `auth_group_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_group_permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `group_id` (`group_id`,`permission_id`),
  KEY `auth_group_permissions_0e939a4f` (`group_id`),
  KEY `auth_group_permissions_8373b171` (`permission_id`),
  CONSTRAINT `auth_group__permission_id_1f49ccbbdc69d2fc_fk_auth_permission_id` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`),
  CONSTRAINT `auth_group_permission_group_id_689710a9a73b7457_fk_auth_group_id` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_group_permissions`
--

LOCK TABLES `auth_group_permissions` WRITE;
/*!40000 ALTER TABLE `auth_group_permissions` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_group_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_permission`
--

DROP TABLE IF EXISTS `auth_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `content_type_id` int(11) NOT NULL,
  `codename` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `content_type_id` (`content_type_id`,`codename`),
  KEY `auth_permission_417f1b1c` (`content_type_id`),
  CONSTRAINT `auth__content_type_id_508cf46651277a81_fk_django_content_type_id` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_permission`
--

LOCK TABLES `auth_permission` WRITE;
/*!40000 ALTER TABLE `auth_permission` DISABLE KEYS */;
INSERT INTO `auth_permission` VALUES (1,'Can add log entry',1,'add_logentry'),(2,'Can change log entry',1,'change_logentry'),(3,'Can delete log entry',1,'delete_logentry'),(4,'Can add permission',2,'add_permission'),(5,'Can change permission',2,'change_permission'),(6,'Can delete permission',2,'delete_permission'),(7,'Can add group',3,'add_group'),(8,'Can change group',3,'change_group'),(9,'Can delete group',3,'delete_group'),(10,'Can add user',4,'add_user'),(11,'Can change user',4,'change_user'),(12,'Can delete user',4,'delete_user'),(13,'Can add content type',5,'add_contenttype'),(14,'Can change content type',5,'change_contenttype'),(15,'Can delete content type',5,'delete_contenttype'),(16,'Can add session',6,'add_session'),(17,'Can change session',6,'change_session'),(18,'Can delete session',6,'delete_session'),(19,'Can add account',7,'add_account'),(20,'Can change account',7,'change_account'),(21,'Can delete account',7,'delete_account'),(22,'Can add monitor',8,'add_monitor'),(23,'Can change monitor',8,'change_monitor'),(24,'Can delete monitor',8,'delete_monitor'),(25,'Can add billing event',9,'add_billingevent'),(26,'Can change billing event',9,'change_billingevent'),(27,'Can delete billing event',9,'delete_billingevent'),(28,'Can add sla violation',10,'add_slaviolation'),(29,'Can change sla violation',10,'change_slaviolation'),(30,'Can delete sla violation',10,'delete_slaviolation'),(31,'Can add sla penalty',11,'add_slapenalty'),(32,'Can change sla penalty',11,'change_slapenalty'),(33,'Can delete sla penalty',11,'delete_slapenalty');
/*!40000 ALTER TABLE `auth_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_user`
--

DROP TABLE IF EXISTS `auth_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(128) NOT NULL,
  `last_login` datetime NOT NULL,
  `is_superuser` tinyint(1) NOT NULL,
  `username` varchar(30) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `email` varchar(75) NOT NULL,
  `is_staff` tinyint(1) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `date_joined` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_user`
--

LOCK TABLES `auth_user` WRITE;
/*!40000 ALTER TABLE `auth_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_user_groups`
--

DROP TABLE IF EXISTS `auth_user_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_user_groups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`,`group_id`),
  KEY `auth_user_groups_e8701ad4` (`user_id`),
  KEY `auth_user_groups_0e939a4f` (`group_id`),
  CONSTRAINT `auth_user_groups_group_id_33ac548dcf5f8e37_fk_auth_group_id` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`),
  CONSTRAINT `auth_user_groups_user_id_4b5ed4ffdb8fd9b0_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_user_groups`
--

LOCK TABLES `auth_user_groups` WRITE;
/*!40000 ALTER TABLE `auth_user_groups` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_user_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_user_user_permissions`
--

DROP TABLE IF EXISTS `auth_user_user_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_user_user_permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`,`permission_id`),
  KEY `auth_user_user_permissions_e8701ad4` (`user_id`),
  KEY `auth_user_user_permissions_8373b171` (`permission_id`),
  CONSTRAINT `auth_user_u_permission_id_384b62483d7071f0_fk_auth_permission_id` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`),
  CONSTRAINT `auth_user_user_permissi_user_id_7f0938558328534a_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_user_user_permissions`
--

LOCK TABLES `auth_user_user_permissions` WRITE;
/*!40000 ALTER TABLE `auth_user_user_permissions` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_user_user_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `django_admin_log`
--

DROP TABLE IF EXISTS `django_admin_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `django_admin_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `action_time` datetime NOT NULL,
  `object_id` longtext,
  `object_repr` varchar(200) NOT NULL,
  `action_flag` smallint(5) unsigned NOT NULL,
  `change_message` longtext NOT NULL,
  `content_type_id` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `django_admin_log_417f1b1c` (`content_type_id`),
  KEY `django_admin_log_e8701ad4` (`user_id`),
  CONSTRAINT `djang_content_type_id_697914295151027a_fk_django_content_type_id` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`),
  CONSTRAINT `django_admin_log_user_id_52fdd58701c5f563_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `django_admin_log`
--

LOCK TABLES `django_admin_log` WRITE;
/*!40000 ALTER TABLE `django_admin_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `django_admin_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `django_content_type`
--

DROP TABLE IF EXISTS `django_content_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `django_content_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `app_label` varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `django_content_type_app_label_45f3b1d93ec8c61c_uniq` (`app_label`,`model`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `django_content_type`
--

LOCK TABLES `django_content_type` WRITE;
/*!40000 ALTER TABLE `django_content_type` DISABLE KEYS */;
INSERT INTO `django_content_type` VALUES (1,'log entry','admin','logentry'),(2,'permission','auth','permission'),(3,'group','auth','group'),(4,'user','auth','user'),(5,'content type','contenttypes','contenttype'),(6,'session','sessions','session'),(7,'account','account','account'),(8,'monitor','account','monitor'),(9,'billing event','account','billingevent'),(10,'sla violation','account','slaviolation'),(11,'sla penalty','account','slapenalty');
/*!40000 ALTER TABLE `django_content_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `django_migrations`
--

DROP TABLE IF EXISTS `django_migrations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `django_migrations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `applied` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `django_migrations`
--

LOCK TABLES `django_migrations` WRITE;
/*!40000 ALTER TABLE `django_migrations` DISABLE KEYS */;
INSERT INTO `django_migrations` VALUES (1,'contenttypes','0001_initial','2015-06-11 13:29:06'),(2,'auth','0001_initial','2015-06-11 13:29:06'),(3,'admin','0001_initial','2015-06-11 13:29:07'),(4,'sessions','0001_initial','2015-06-11 13:29:07');
/*!40000 ALTER TABLE `django_migrations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `django_session`
--

DROP TABLE IF EXISTS `django_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `django_session` (
  `session_key` varchar(40) NOT NULL,
  `session_data` longtext NOT NULL,
  `expire_date` datetime NOT NULL,
  PRIMARY KEY (`session_key`),
  KEY `django_session_de54fa62` (`expire_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `django_session`
--

LOCK TABLES `django_session` WRITE;
/*!40000 ALTER TABLE `django_session` DISABLE KEYS */;
INSERT INTO `django_session` VALUES ('8g7r4fee9vuv49hvb746jmvrbm6064ku','NjUyYTNiMmNmZTRkZWMzYjhjZDUxNTQ0ODRhOWRmMWQ1ODY2ODAzODp7fQ==','2015-08-25 08:06:38'),('8vcw218mrtcbungyr9nk1xc1ub2p1ddu','NjUyYTNiMmNmZTRkZWMzYjhjZDUxNTQ0ODRhOWRmMWQ1ODY2ODAzODp7fQ==','2015-09-03 10:09:19'),('gaahi4l4qv8yghoaybu5t661yuxwn0b0','NjUyYTNiMmNmZTRkZWMzYjhjZDUxNTQ0ODRhOWRmMWQ1ODY2ODAzODp7fQ==','2015-07-24 07:16:11'),('k72d7zh1kkmclyyqr2b4bjzrvu55s96w','NjUyYTNiMmNmZTRkZWMzYjhjZDUxNTQ0ODRhOWRmMWQ1ODY2ODAzODp7fQ==','2015-08-13 11:30:45'),('urvkckp3jwitay2oieeulvwo75gi3fde','NjUyYTNiMmNmZTRkZWMzYjhjZDUxNTQ0ODRhOWRmMWQ1ODY2ODAzODp7fQ==','2015-09-03 10:09:18');
/*!40000 ALTER TABLE `django_session` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-13 13:31:28
