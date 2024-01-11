-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: booking
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `book_flight`
--

DROP TABLE IF EXISTS `book_flight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_flight` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `destination` varchar(255) DEFAULT NULL,
  `origin` varchar(255) DEFAULT NULL,
  `peopleq` int NOT NULL,
  `seat_type` varchar(255) DEFAULT NULL,
  `flight_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1ou9p7wwpqm68xryw1uxk4lg` (`flight_id`),
  CONSTRAINT `FK1ou9p7wwpqm68xryw1uxk4lg` FOREIGN KEY (`flight_id`) REFERENCES `flight` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_flight`
--

LOCK TABLES `book_flight` WRITE;
/*!40000 ALTER TABLE `book_flight` DISABLE KEYS */;
INSERT INTO `book_flight` VALUES (1,'2024-02-11','Madrid','Miami',1,'Business',NULL),(2,'2024-02-11','Madrid','Miami',1,'Business',NULL);
/*!40000 ALTER TABLE `book_flight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_flight_passengers`
--

DROP TABLE IF EXISTS `book_flight_passengers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_flight_passengers` (
  `book_flights_id` bigint NOT NULL,
  `passengers_id` bigint NOT NULL,
  KEY `FKchvr8rh60v6dahftmedg69v95` (`passengers_id`),
  KEY `FK4gcivge30agillrjxcydhn4tf` (`book_flights_id`),
  CONSTRAINT `FK4gcivge30agillrjxcydhn4tf` FOREIGN KEY (`book_flights_id`) REFERENCES `book_flight` (`id`),
  CONSTRAINT `FKchvr8rh60v6dahftmedg69v95` FOREIGN KEY (`passengers_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_flight_passengers`
--

LOCK TABLES `book_flight_passengers` WRITE;
/*!40000 ALTER TABLE `book_flight_passengers` DISABLE KEYS */;
INSERT INTO `book_flight_passengers` VALUES (1,5),(2,6);
/*!40000 ALTER TABLE `book_flight_passengers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_hotel`
--

DROP TABLE IF EXISTS `book_hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_hotel` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `end_date` date DEFAULT NULL,
  `peopleq` int NOT NULL,
  `start_date` date DEFAULT NULL,
  `room_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKojn93ao19a4whds60qompbt7h` (`room_id`),
  CONSTRAINT `FKojn93ao19a4whds60qompbt7h` FOREIGN KEY (`room_id`) REFERENCES `hotel_room` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_hotel`
--

LOCK TABLES `book_hotel` WRITE;
/*!40000 ALTER TABLE `book_hotel` DISABLE KEYS */;
INSERT INTO `book_hotel` VALUES (1,'2024-01-20',1,'2024-01-15',5);
/*!40000 ALTER TABLE `book_hotel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_hotel_guests`
--

DROP TABLE IF EXISTS `book_hotel_guests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_hotel_guests` (
  `hotels_id` bigint NOT NULL,
  `guests_id` bigint NOT NULL,
  KEY `FK38uodsqmh2yn691j8pcda5ure` (`guests_id`),
  KEY `FKg9g7l0wg41nimiwf9oldpep0g` (`hotels_id`),
  CONSTRAINT `FK38uodsqmh2yn691j8pcda5ure` FOREIGN KEY (`guests_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKg9g7l0wg41nimiwf9oldpep0g` FOREIGN KEY (`hotels_id`) REFERENCES `book_hotel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_hotel_guests`
--

LOCK TABLES `book_hotel_guests` WRITE;
/*!40000 ALTER TABLE `book_hotel_guests` DISABLE KEYS */;
INSERT INTO `book_hotel_guests` VALUES (1,7);
/*!40000 ALTER TABLE `book_hotel_guests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flight`
--

DROP TABLE IF EXISTS `flight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flight` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `departure_date` date DEFAULT NULL,
  `destination` varchar(255) DEFAULT NULL,
  `flight_code` varchar(255) DEFAULT NULL,
  `origin` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight`
--

LOCK TABLES `flight` WRITE;
/*!40000 ALTER TABLE `flight` DISABLE KEYS */;
INSERT INTO `flight` VALUES (1,'2024-02-11','Madrid','MIMA-9854','Miami'),(2,'2024-04-15','Cartagena','BOCA-4213','Bogotá'),(3,'2024-02-10','Miami','MEMI-9986','Medellín');
/*!40000 ALTER TABLE `flight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flight_seat`
--

DROP TABLE IF EXISTS `flight_seat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flight_seat` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `available` bit(1) NOT NULL,
  `number` int NOT NULL,
  `price` double NOT NULL,
  `seat_type` varchar(255) DEFAULT NULL,
  `flight_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc9a3xu176fgmgwxbtti5hir7n` (`flight_id`),
  CONSTRAINT `FKc9a3xu176fgmgwxbtti5hir7n` FOREIGN KEY (`flight_id`) REFERENCES `flight` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight_seat`
--

LOCK TABLES `flight_seat` WRITE;
/*!40000 ALTER TABLE `flight_seat` DISABLE KEYS */;
INSERT INTO `flight_seat` VALUES (1,_binary '',1,1350,'Business',1),(2,_binary '',33,150,'Economy',1),(3,_binary '',1,950,'Business',2),(4,_binary '',2,123,'Economy',2),(5,_binary '',1,2950,'Business',3),(6,_binary '',2,1100,'Economy',3);
/*!40000 ALTER TABLE `flight_seat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel`
--

DROP TABLE IF EXISTS `hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hotel` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `hotel_code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel`
--

LOCK TABLES `hotel` WRITE;
/*!40000 ALTER TABLE `hotel` DISABLE KEYS */;
INSERT INTO `hotel` VALUES (3,'Malaga','BH-0004','Hotel Barcelo'),(4,'Miami','AR-0002','Atlantis Resort'),(5,'Miami','AR-0003','Atlantis Resort 2'),(6,'Buenos Aires','RC-0001','Ritz-Carlton'),(7,'Medellín','RC-0002','Ritz-Carlton 2'),(8,'Madrid','GH-0002','Grand Hyatt');
/*!40000 ALTER TABLE `hotel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel_room`
--

DROP TABLE IF EXISTS `hotel_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hotel_room` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `available` bit(1) NOT NULL,
  `price` double NOT NULL,
  `room_number` int NOT NULL,
  `room_type` varchar(255) DEFAULT NULL,
  `hotel_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkidx9n5p4parnjnpg912svvgi` (`hotel_id`),
  CONSTRAINT `FKkidx9n5p4parnjnpg912svvgi` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel_room`
--

LOCK TABLES `hotel_room` WRITE;
/*!40000 ALTER TABLE `hotel_room` DISABLE KEYS */;
INSERT INTO `hotel_room` VALUES (1,_binary '\0',125,101,'Suite',3),(2,_binary '\0',50,102,'Estandar',3),(3,_binary '',54,103,'Estandar',3),(4,_binary '',630,101,'Doble',4),(5,_binary '\0',400,102,'Estandar',4),(6,_binary '',500,103,'Estandar',4),(7,_binary '',820,101,'Triple',5),(8,_binary '',3400,201,'Suite',5),(9,_binary '',500,103,'Estandar',5),(10,_binary '',520,21,'Triple',6),(11,_binary '',2450,22,'Suite',6),(12,_binary '',500,23,'Single',6),(13,_binary '',520,21,'Triple',7),(14,_binary '',2450,22,'Suite',7),(15,_binary '',500,23,'Single',7),(16,_binary '',520,11,'Triple',8),(17,_binary '',3450,12,'Suite',8),(18,_binary '',140,33,'Single',8);
/*!40000 ALTER TABLE `hotel_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `doc_id` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'22334455E','Perales Manzanárez','Jose Luis'),(2,'99887766P','Sánchez Pérez','Fernando'),(5,NULL,NULL,'Antonio'),(6,'2222222S','Martinez','Luisa'),(7,'22334455E','Moreno Martinez','María');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-11  9:27:39
