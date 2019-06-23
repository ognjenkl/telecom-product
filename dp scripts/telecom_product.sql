CREATE DATABASE  IF NOT EXISTS `telecom_product` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `telecom_product`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: telecom_product
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `upstream` int(11) DEFAULT NULL,
  `downstream` int(11) DEFAULT NULL,
  `technology_type` varchar(45) DEFAULT NULL,
  `device_type` varchar(45) DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,10,20,'VDSL','DSLAM',0),(2,25,50,'xDSL','MDU',0),(3,35,70,'ISDN','DPU',0),(4,40,100,'VDSL','DPO',0);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_has_product`
--

DROP TABLE IF EXISTS `user_has_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_has_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `activation_date` datetime DEFAULT NULL,
  `active` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_user_has_product_product1_idx` (`product_id`),
  CONSTRAINT `fk_user_has_product_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_has_product`
--

LOCK TABLES `user_has_product` WRITE;
/*!40000 ALTER TABLE `user_has_product` DISABLE KEYS */;
INSERT INTO `user_has_product` VALUES (1,1,1,'2019-06-22 19:02:07',0),(2,1,2,'2019-06-22 19:04:17',0),(3,1,3,'2019-06-22 19:05:04',1),(4,1,4,'2019-06-22 19:07:38',0),(5,2,3,'2019-06-22 19:15:44',1);
/*!40000 ALTER TABLE `user_has_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'telecom_product'
--

--
-- Dumping routines for database 'telecom_product'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-22 23:43:09
