CREATE DATABASE  IF NOT EXISTS `jspdemodb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `jspdemodb`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: jspdemodb
-- ------------------------------------------------------
-- Server version	5.6.14-log

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
-- Table structure for table `rolest`
--

DROP TABLE IF EXISTS `rolest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rolest` (
  `roleName` varchar(20) NOT NULL,
  PRIMARY KEY (`roleName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rolest`
--

LOCK TABLES `rolest` WRITE;
/*!40000 ALTER TABLE `rolest` DISABLE KEYS */;
INSERT INTO `rolest` VALUES ('admin'),('user');
/*!40000 ALTER TABLE `rolest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userroles`
--

DROP TABLE IF EXISTS `userroles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userroles` (
  `userName` varchar(20) NOT NULL,
  `roleName` varchar(20) NOT NULL,
  PRIMARY KEY (`userName`,`roleName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userroles`
--

LOCK TABLES `userroles` WRITE;
/*!40000 ALTER TABLE `userroles` DISABLE KEYS */;
INSERT INTO `userroles` VALUES ('admin','admin'),
('admin','user'),('ahmets','user'),
('alit','user'),('ardas','user'),('bilalh','user'),
('elifa','user'),('emina','user'),('jalel','user'),
('keremc','user'),('mehmetg','user'),('murata','user'),
('oktaya','admin'),('sukrana','user'),('velik','user');
/*!40000 ALTER TABLE `userroles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userst`
--

DROP TABLE IF EXISTS `userst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userst` (
  `userName` varchar(12) NOT NULL,
  `firstName` varchar(20) DEFAULT NULL,
  `lastName` varchar(20) DEFAULT NULL,
  `passWord` varchar(8) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `creationDate` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userst`
--

LOCK TABLES `userst` WRITE;
/*!40000 ALTER TABLE `userst` DISABLE KEYS */;
INSERT INTO `userst` VALUES 
('admin','admin','yonetici','admin','admin@oka.com','2013-12-01 23:35:59'),
('alit','Ali','Tek','1234','alit@yahoo.com','2013-12-01 16:08:11'),
('ardas','Arda','Salim','1234','ardas@yahoo.com','2013-12-01 16:08:35'),
('bilalh','Bilal','Habesi','1234','bilalh@yahoo.com','2013-12-01 20:04:41'),
('elifa','Elif','ARI','1234','elifa@yahoo.com','2013-12-01 23:11:34'),
('emina','Emin','ARI','1234','emina@yahoo.com','2013-11-29 20:06:47'),
('jalel','Jale','Lale','1234','jalal@yahoo.com','2013-12-01 16:08:51'),
('keremc','Kerem','Ceme','1234','keremc@yahoo.com','2013-12-01 17:21:54'),
('mehmetg','Mehmet','Gul','1234','mehmetg@yahoo.com','2013-12-01 17:12:41'),
('murata','Murat','ARI','1234','murata@yahoo.com','2013-12-01 16:09:10'),
('oktaya','Oktay','ARI','1234','oktaya@yahoo.com','2013-12-01 23:34:12'),
('sukrana','Sukran','ARI','1234','sukrana@yahoo.com','2013-12-01 17:17:30'),
('velik','Veli','Kalem','1234','velik@yahoo.com','2013-12-01 16:09:37');
/*!40000 ALTER TABLE `userst` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-12-07  1:02:46
