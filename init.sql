CREATE DATABASE  IF NOT EXISTS `635466` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `635466`;
-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: 635466
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bookmark`
--

DROP TABLE IF EXISTS `bookmark`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookmark` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(10) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `lat` double DEFAULT NULL,
  `lon` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookmark`
--

LOCK TABLES `bookmark` WRITE;
/*!40000 ALTER TABLE `bookmark` DISABLE KEYS */;
INSERT INTO `bookmark` VALUES (31,'default','Prova',12,12),(51,'default','prova2',2,12),(53,'default','prova3',12,12),(54,'default','prova4',12,12),(55,'default','prova5',12,12),(56,'default','prova6',12,12),(57,'default','prova7',12,12),(58,'default','prova8',12,12),(59,'default','prova9',12,12),(60,'default','prova10',12,12);
/*!40000 ALTER TABLE `bookmark` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (3,'Pisa',43.7159395,10.4018624),(7,'Spoleto',42.7342971,12.7382035),(9,'City of New York',40.7127281,-74.0060152),(11,'Roma',41.8933203,12.4829321),(13,'Milano',45.4641943,9.1896346),(15,'Napoli',40.8358846,14.2487679),(17,'Palermo',38.1112268,13.3524434),(19,'Torino',45.0677551,7.6824892),(21,'Aosta / Aoste',45.7370885,7.3196649),(23,'Trento',46.0664228,11.1257601),(25,'Paris',48.8588897,2.3200410217200766),(27,'London',51.5074456,-0.1277653),(66,'City of New York',40.7127281,-74.0060152);
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (68);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sessions`
--

DROP TABLE IF EXISTS `sessions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sessions` (
  `id` int NOT NULL,
  `username` varchar(10) DEFAULT NULL,
  `token` varchar(32) DEFAULT NULL,
  `expiredate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sessions`
--

LOCK TABLES `sessions` WRITE;
/*!40000 ALTER TABLE `sessions` DISABLE KEYS */;
INSERT INTO `sessions` VALUES (52,'default','979e5fe9216c458ba74cf559e633bc9d','2024-01-19 02:48:03');
/*!40000 ALTER TABLE `sessions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL,
  `username` varchar(10) NOT NULL,
  `password` varchar(60) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'default','$2a$10$AhxjL/gp6XmSPXCdfwEWjeQIXybhd8LnDNtA1ssiGEtfMLbHKeKwi');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `weather`
--

DROP TABLE IF EXISTS `weather`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `weather` (
  `id` int NOT NULL AUTO_INCREMENT,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `temperature` double DEFAULT NULL,
  `humidity` int DEFAULT NULL,
  `precipitation` double DEFAULT NULL,
  `snow` tinyint(1) DEFAULT NULL,
  `datetime` datetime DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weather`
--

LOCK TABLES `weather` WRITE;
/*!40000 ALTER TABLE `weather` DISABLE KEYS */;
INSERT INTO `weather` VALUES (4,43.7159395,10.4018624,14.9,73,0,0,'2024-01-18 20:20:16','Pisa'),(5,43.7159395,10.4018624,14.9,73,0,0,'2024-01-18 20:20:20','Pisa'),(6,12,12,20.6,21,0,0,'2024-01-18 20:20:34',NULL),(8,42.7342971,12.7382035,11.8,88,0,0,'2024-01-18 20:23:18','Spoleto'),(10,40.7127281,-74.0060152,-1.2,59,0,0,'2024-01-18 20:29:52','New+York'),(12,41.8933203,12.4829321,15.5,76,0,0,'2024-01-18 20:32:30','Roma'),(14,45.4641943,9.1896346,3.3,99,0,0,'2024-01-18 20:32:35','Milano'),(16,40.8358846,14.2487679,15.5,93,0,0,'2024-01-18 20:34:37','Napoli'),(18,38.1112268,13.3524434,17.3,62,0,0,'2024-01-18 20:34:49','Palermo'),(20,45.0677551,7.6824892,2.8,97,0,0,'2024-01-18 20:34:56','Torino'),(22,45.7370885,7.3196649,9.1,57,0,0,'2024-01-18 20:35:01','Aosta'),(24,46.0664228,11.1257601,3.5,87,0,0,'2024-01-18 20:35:07','Trento'),(26,48.8588897,2.3200410217200766,-0.4,85,0,0,'2024-01-18 20:35:17','Parigi'),(28,51.5074456,-0.1277653,0.1,79,0,0,'2024-01-18 20:35:22','Londra'),(29,90,90,-29,72,0,0,'2024-01-18 20:35:33',NULL),(30,12,12,20.2,21,0,0,'2024-01-18 20:35:36',NULL),(34,90,90,-29.1,74,0,0,'2024-01-18 22:07:26',NULL),(35,43.7159395,10.4018624,14.8,73,0,0,'2024-01-18 22:07:31','Pisa'),(36,43.7159395,10.4018624,14.8,73,0,0,'2024-01-18 22:07:33','Pisa'),(37,41.8933203,12.4829321,14.7,80,0,0,'2024-01-18 22:07:36','Roma'),(38,45.4641943,9.1896346,2.7,100,0,0,'2024-01-18 22:07:40','Milano'),(41,12,12,17.3,29,0,0,'2024-01-18 23:28:11',NULL),(42,45.4641943,9.1896346,4.1,99,0,0,'2024-01-18 23:28:23','Milano'),(44,42.7342971,12.7382035,11.9,88,0.6,0,'2024-01-18 23:46:53','Spoleto'),(45,43.7159395,10.4018624,14.6,72,0,0,'2024-01-18 23:46:58','Pisa'),(46,43.7159395,10.4018624,14.6,72,0,0,'2024-01-19 00:14:22','Pisa'),(47,43.7159395,10.4018624,14.6,72,0,0,'2024-01-19 00:15:43','Pisa'),(49,43.7159395,10.4018624,14.5,74,0,0,'2024-01-19 00:57:46','Pisa'),(50,12,12,15.8,32,0,0,'2024-01-19 00:57:50',NULL),(61,-12,-12,24.7,76,0,0,'2024-01-19 01:50:38',NULL),(62,43.7159395,10.4018624,14.4,74,0,0,'2024-01-19 01:50:46','Pisa'),(63,45.4641943,9.1896346,4.2,98,0,0,'2024-01-19 01:50:49','Milano'),(64,40.8358846,14.2487679,15.4,91,0,0,'2024-01-19 01:50:52','Napoli'),(65,41.8933203,12.4829321,14.8,81,0,0,'2024-01-19 01:50:55','Roma'),(67,40.7127281,-74.0060152,-1.7,70,0,0,'2024-01-19 01:51:02','New+York');
/*!40000 ALTER TABLE `weather` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-19  2:44:49
