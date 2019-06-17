-- MySQL dump 10.13  Distrib 5.7.26, for Linux (x86_64)
--
-- Host: localhost    Database: jeeschool
-- ------------------------------------------------------
-- Server version	5.7.26-0ubuntu0.18.04.1

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
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `solution_id` int(11) DEFAULT NULL,
                           `user_name` varchar(45) COLLATE utf8_polish_ci DEFAULT NULL,
                           `description` text CHARACTER SET utf8,
                           `created` timestamp NULL DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           KEY `fk_comment_1_idx` (`solution_id`),
                           CONSTRAINT `fk_comment_1` FOREIGN KEY (`solution_id`) REFERENCES `solution` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (15,6,'unknown','czwarty','2019-06-13 21:36:28'),(16,6,'unknown','hhhh','2019-06-13 21:44:23'),(19,2,'Adam','I dodajemy komentarz','2019-06-14 11:55:39');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exercise`
--

DROP TABLE IF EXISTS `exercise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exercise` (
                            `id` int(11) NOT NULL AUTO_INCREMENT,
                            `title` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
                            `description` text CHARACTER SET utf8mb4,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exercise`
--

LOCK TABLES `exercise` WRITE;
/*!40000 ALTER TABLE `exercise` DISABLE KEYS */;
INSERT INTO `exercise` VALUES (2,'Sklepik','Modyfikujemy zawarość zadania'),(3,'Losowanie','Losowanie liczb'),(4,'Kostka','Kostka rubika'),(5,'Gra','Gra RPG'),(6,'Nowe zadanie do wykonania','Witam serdecznie,\r\nProszę o napisanie programu który będzie służył do wyświetlania krótkich komunikatów na konsoli.\r\n');
/*!40000 ALTER TABLE `exercise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_privileges`
--

DROP TABLE IF EXISTS `group_privileges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_privileges` (
                                    `id` int(11) NOT NULL AUTO_INCREMENT,
                                    `group_id` int(11) DEFAULT NULL,
                                    `learner` int(11) DEFAULT NULL,
                                    `teacher` int(11) DEFAULT NULL,
                                    PRIMARY KEY (`id`),
                                    KEY `fk_group_privileges_1_idx` (`group_id`),
                                    CONSTRAINT `fk_group_privileges_1` FOREIGN KEY (`group_id`) REFERENCES `user_group` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_privileges`
--

LOCK TABLES `group_privileges` WRITE;
/*!40000 ALTER TABLE `group_privileges` DISABLE KEYS */;
INSERT INTO `group_privileges` VALUES (3,3,1,0),(4,4,0,1),(5,5,1,1);
/*!40000 ALTER TABLE `group_privileges` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solution`
--

DROP TABLE IF EXISTS `solution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `solution` (
                            `id` int(11) NOT NULL AUTO_INCREMENT,
                            `created` datetime DEFAULT NULL,
                            `updated` datetime DEFAULT NULL,
                            `description` text CHARACTER SET utf8mb4,
                            `exercise_id` int(11) DEFAULT NULL,
                            `users_id` int(11) DEFAULT NULL,
                            `rating` int(11) DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            KEY `users_id` (`users_id`),
                            KEY `fk_solution_1_idx` (`exercise_id`),
                            CONSTRAINT `fk_solution_1` FOREIGN KEY (`exercise_id`) REFERENCES `exercise` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                            CONSTRAINT `solution_ibfk_2` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solution`
--

LOCK TABLES `solution` WRITE;
/*!40000 ALTER TABLE `solution` DISABLE KEYS */;
INSERT INTO `solution` VALUES (2,'2019-05-04 14:00:28','2019-06-14 11:55:49','Dodajemy jakieś rozwiązanie do bazy.',2,2,5),(3,'2019-05-06 09:16:11','2019-06-14 13:17:03','i dodaję jakieś rozwiązanie.',2,3,5),(5,'2019-05-06 11:54:43','2019-06-14 11:54:41','#include <iostream>\r\nusing namespace std;\r\n\r\nint main() \r\n{\r\n    cout << \"Hello, World!\";\r\n    return 0;\r\n}',4,2,0),(6,'2019-05-06 15:38:37','2019-05-06 16:35:40','test',2,6,5),(7,'2019-06-14 12:42:14',NULL,'Najlepsza to oczywiście Baldurs Gate\r\nNajwiekszy sentyment to Ishar.',5,2,NULL),(8,'2019-06-14 12:43:33',NULL,'public static void main(String[] arg) {\r\n    System.out.println(\"Hello world\");\r\n}',6,2,NULL),(9,'2019-06-14 13:16:44','2019-06-14 13:17:17','ttuaj jakies rozwiązanie.kkk',3,3,0);
/*!40000 ALTER TABLE `solution` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_group`
--

DROP TABLE IF EXISTS `user_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_group` (
                              `id` int(11) NOT NULL AUTO_INCREMENT,
                              `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_group`
--

LOCK TABLES `user_group` WRITE;
/*!40000 ALTER TABLE `user_group` DISABLE KEYS */;
INSERT INTO `user_group` VALUES (3,'uczeń'),(4,'nauczyciel'),(5,'administrator');
/*!40000 ALTER TABLE `user_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `username` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
                         `email` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
                         `password` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
                         `group_id` int(11) DEFAULT '0',
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `email_UNIQUE` (`email`),
                         KEY `fk_users_1_idx` (`group_id`),
                         CONSTRAINT `fk_users_1` FOREIGN KEY (`group_id`) REFERENCES `user_group` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (2,'Adam','szambur@o2.pl','$2a$10$/1k1AjrmNWnucrxCi6nj5uyWSVmBfK4Muve9UlWC0ZrtxZTFLaoXO',4),(3,'Michał','michal@o2.pl','$2a$10$odm64meG9JY1GS3lA8fXveqUMPuk7/M3FkaC.h0dmTyqB06tNW0oq',3),(4,'Piotr','piotr@o2.pl','$2a$10$L7bKDmo3v2uWmn9IxI9k9uoWHUezJfegMSCromcx5BBS3w6BifpI2',3),(5,'Nauczyciel','test@test.pl','$2a$10$8SsZJWoV5T2hYRJVHKcGYeAUdfe/cnR4mjvPeAGNiPOGm79DzD.ja',3),(6,'root','root@o2.pl','$2a$10$0Qu/95oTxRDdvfmqzl//6eEPixuSQ1qiNbRSQtAsP0DScAOBzhncq',3),(9,'test2','test2@op.pl','$2a$10$GiiyskUP1MUCJOpEIVVL2u5KmTTwMakwXEqJNmUuBbfSDzPYQUZMu',3),(12,'Łukasz','luk@o2.pl','$2a$10$tk66KYnq4.d3Sd15y6QOouI.dgsvfah1JWHo/zs5eJc9OWkB0Fj7e',3);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-14 13:30:23