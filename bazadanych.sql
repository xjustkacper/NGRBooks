-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: registration_db
-- ------------------------------------------------------
-- Server version	8.2.0

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
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `author` varchar(255) DEFAULT NULL,
  `cover` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `pdf` varchar(255) DEFAULT NULL,
  `release_date` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'William Shakespeare','https://wolnelektury.pl/media/book/cover_clean/makbet_t0YuhWz.jpg','Makbet wraz z przyjacielem, Bankiem, są dowódcami wojsk Dunkana, króla Szkocji. Gdy wracają z jednej z bitew, ukazują im się wiedźmy, które przepowiadają, że Makbet będzie królem.','Tragedia','https://wolnelektury.pl/media/book/pdf/makbet.pdf','1606','Makbet'),(2,'William Shakespeare','https://wolnelektury.pl/media/book/cover_clean/hamlet_a9tsCdy.jpg','Na zamku Elsynor w Danii w niewyjaśnionych okolicznościach ginie król, Hamlet senior. Władzę przejmuje jego brat, Klaudiusz, który żeni się z królową-wdową.','Tragedia','https://wolnelektury.pl/media/book/pdf/hamlet.pdf','1603','Hamlet'),(3,'William Shakespeare','https://wolnelektury.pl/media/book/cover_clean/romeo-i-julia_UuIquLC.jpg','Matka Julii Kapulet postanawia wydać córkę za Parysa (jest to krewny księcia Werony). Organizuje ona bal, na którym młodzi mają się zapoznać. Zjawia się tam też potomek zwaśnionego z nimi rodu Monteki, Romeo.','Tragedia','https://wolnelektury.pl/media/book/pdf/romeo-i-julia.pdf','1597','Romeo i Julia'),(4,'Miguel de Cervantes Saavedra','https://wolnelektury.pl/media/book/cover_clean/don-kichot-z-la-manchy_nFXPDhf.jpg','Alonso jest gorliwym czytelnikiem romansów rycerskich. Po wielu godzinach spędzonych na lekturze postanawia, że on również chciałby przeżyć tak wspaniałą przygodę.','Powieść','https://wolnelektury.pl/media/book/pdf/don-kichot-z-la-manchy.pdf','1605','Don Kichot'),(5,'Aleksander Dumas','https://wolnelektury.pl/media/book/cover_clean/hrabia-monte-christo_pezq7pZ.jpg','Jedna z najsławniejszych powieści awanturniczych, malownicza opowieść o zdradzie i zemście; o człowieku, który postanowił wcielić się w rolę fatum.','Powieść','https://wolnelektury.pl/media/book/pdf/hrabia-monte-christo.pdf','1844','Hrabia Monte Christo'),(6,'Oscar Wilde','https://wolnelektury.pl/media/book/cover_clean/wilde-portret-doriana-graya_xzSt8Ms.jpg','Dorian Gray jest nieśmiałym, choć niezwykle przystojnym młodym dżentelmenem. Kiedy pozuje swemu przyjacielowi Bazylemu, spotyka jego znajomego, lorda Henryka.','Powieść','https://wolnelektury.pl/media/book/pdf/wilde-portret-doriana-graya.pdf','1890','Portret Doriana Graya'),(7,'Henryk Sienkiewicz','https://wolnelektury.pl/media/book/cover_clean/ogniem-i-mieczem_Tg8LkSo.jpg','Autor przenosi czytelnika w siedemnasty wiek, w czas powstania Chmielnickiego na Ukrainie, podczas którego Rzeczpospolita mierzyła się z Kozakami zaporoskimi i Tatarami. Na tle tego konfliktu rozgrywa się również dramat miłosny.','Powieść historyczna','https://wolnelektury.pl/media/book/pdf/ogniem-i-mieczem.pdf','1884','Ogniem i mieczem'),(8,'Henryk Sienkiewicz','https://wolnelektury.pl/media/book/cover_clean/potop_r2fHEbm.jpg','Autor przenosi czytelnika w lata 1655–1657, pierwsze dwa lata potopu szwedzkiego. Główny bohater to Andrzej Kmicic, młody chorąży, warchoł i hulaka.','Powieść historyczna','https://wolnelektury.pl/media/book/pdf/potop.pdf','1886','Potop'),(9,'Henryk Sienkiewicz','https://wolnelektury.pl/media/book/cover_clean/krzyzacy_WSqIGmk.jpg','Jagiellońska Polska uwikłana jest w konflikt z zakonem krzyżackim. Zbyszko z Bogdańca, młody rycerz, zakochuje się w ślicznej, niewinnej Danusi, córce Juranda ze Spychowa.','Powieść historyczna','https://wolnelektury.pl/media/book/pdf/krzyzacy.pdf','1899','Krzyżacy'),(10,'Johann Wolfgang von Goethe','https://wolnelektury.pl/media/book/cover_clean/cierpienia-mlodego-wertera_s3AjpZo.jpg','To powieść epistolarna, czyli napisana w formie serii listów, której głównym bohaterem jest Werter, nieszczęśliwie zakochany w Lotcie. Ukochana mężczyzny jest mężatką.','Powieść epistolarna','https://wolnelektury.pl/media/book/pdf/cierpienia-mlodego-wertera.pdf','1774','Cierpienia młodego Wertera');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `book_id` bigint DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `content` text,
  PRIMARY KEY (`id`),
  KEY `FKkko96rdq8d82wm91vh2jsfak7` (`book_id`),
  KEY `FKkfvfilv8u1bh9jyaoawa212pf` (`user_id`),
  CONSTRAINT `FKkfvfilv8u1bh9jyaoawa212pf` FOREIGN KEY (`user_id`) REFERENCES `user_profile` (`profile_id`),
  CONSTRAINT `FKkko96rdq8d82wm91vh2jsfak7` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,1,1,'Klasyka literatury'),(1,2,1,'GNIOT'),(2,3,2,'Źle się dzieję w państwie Duńskim'),(4,4,2,'Literalnie ja'),(4,5,1,'o co tu chodzi ???'),(8,6,1,'Nie będzie... pluł nam w twarz'),(5,7,1,'ZEMSTA'),(9,8,2,'Zbyszko!!!');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorites`
--

DROP TABLE IF EXISTS `favorites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favorites` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `book_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK273xtfi5rey9ay0bek4b6mey5` (`book_id`),
  KEY `FK4iagsv4nhfsd15d2ks57tnpnc` (`user_id`),
  CONSTRAINT `FK273xtfi5rey9ay0bek4b6mey5` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  CONSTRAINT `FK4iagsv4nhfsd15d2ks57tnpnc` FOREIGN KEY (`user_id`) REFERENCES `user_profile` (`profile_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorites`
--

LOCK TABLES `favorites` WRITE;
/*!40000 ALTER TABLE `favorites` DISABLE KEYS */;
INSERT INTO `favorites` VALUES (6,4,1),(7,5,2),(9,2,1),(11,1,1);
/*!40000 ALTER TABLE `favorites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `password_reset_token`
--

DROP TABLE IF EXISTS `password_reset_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `password_reset_token` (
  `expiration_time` datetime(6) DEFAULT NULL,
  `token_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`token_id`),
  UNIQUE KEY `UK_f90ivichjaokvmovxpnlm5nin` (`user_id`),
  CONSTRAINT `FK5lwtbncug84d4ero33v3cfxvl` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `password_reset_token`
--

LOCK TABLES `password_reset_token` WRITE;
/*!40000 ALTER TABLE `password_reset_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `password_reset_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `password_reset_token_seq`
--

DROP TABLE IF EXISTS `password_reset_token_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `password_reset_token_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `password_reset_token_seq`
--

LOCK TABLES `password_reset_token_seq` WRITE;
/*!40000 ALTER TABLE `password_reset_token_seq` DISABLE KEYS */;
INSERT INTO `password_reset_token_seq` VALUES (1);
/*!40000 ALTER TABLE `password_reset_token_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rating`
--

DROP TABLE IF EXISTS `rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rating` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `rating` int NOT NULL,
  `book_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7y1acs6la7vkgb5ulm44729sc` (`book_id`),
  KEY `FKg2kw5tch1ijfl5qsooegr5iuf` (`user_id`),
  CONSTRAINT `FK7y1acs6la7vkgb5ulm44729sc` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  CONSTRAINT `FKg2kw5tch1ijfl5qsooegr5iuf` FOREIGN KEY (`user_id`) REFERENCES `user_profile` (`profile_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rating`
--

LOCK TABLES `rating` WRITE;
/*!40000 ALTER TABLE `rating` DISABLE KEYS */;
INSERT INTO `rating` VALUES (1,3,1,1),(2,4,2,1),(3,3,1,2),(4,5,2,2),(5,4,4,2),(7,4,4,1),(8,3,3,1),(9,5,5,1),(10,5,6,1),(11,2,7,1),(12,2,8,1),(13,4,9,1),(14,4,10,1),(15,1,3,2),(16,5,5,2),(17,4,6,2),(18,3,7,2),(19,4,8,2),(20,3,9,2),(21,3,10,2);
/*!40000 ALTER TABLE `rating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `is_enabled` bit(1) NOT NULL,
  `join_date` date DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_t8tbwelrnviudxdaggwr1kd9b` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (_binary '','2024-01-03',1,'zgrass11@gmail.com','broken','$2a$10$rntk6qQHWlOJcmHWz08lO.rmXY9GebSB02k73O/nqSSsrADUp0J7K','USER'),(_binary '','2024-01-03',2,'zgrass666@gmail.com','bro','$2a$10$vhj0mxl54qD4KZ7rb/pvRu2qPVT5Mf.Admr7zKKPze/a04wbtMba2','USER'),(_binary '\0','2024-01-09',3,'zgrass82@gmail.com','bll','$2a$10$E9OQcQq1lRxMhEM5sulNNecs.tA.uqbdvqGcHXCD2ksoFv/5LRkDa','USER');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_profile`
--

DROP TABLE IF EXISTS `user_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_profile` (
  `join_date` date DEFAULT NULL,
  `profile_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`profile_id`),
  UNIQUE KEY `UK_ebc21hy5j7scdvcjt0jy6xxrv` (`user_id`),
  CONSTRAINT `FK6kwj5lk78pnhwor4pgosvb51r` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_profile`
--

LOCK TABLES `user_profile` WRITE;
/*!40000 ALTER TABLE `user_profile` DISABLE KEYS */;
INSERT INTO `user_profile` VALUES ('2024-01-03',1,1,'https://www.creativeuncut.com/gallery-42/art/de-harry-portrait.jpg','Opis'),('2024-01-03',2,2,'https://www.creativeuncut.com/gallery-42/art/de-harry-portrait.jpg','Opis użytkownika');
/*!40000 ALTER TABLE `user_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `verification_token`
--

DROP TABLE IF EXISTS `verification_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `verification_token` (
  `expiration_time` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_q6jibbenp7o9v6tq178xg88hg` (`user_id`),
  CONSTRAINT `FKrdn0mss276m9jdobfhhn2qogw` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `verification_token`
--

LOCK TABLES `verification_token` WRITE;
/*!40000 ALTER TABLE `verification_token` DISABLE KEYS */;
INSERT INTO `verification_token` VALUES ('2024-01-03 13:50:52.520000',1,1,'06b71b62-0130-4617-8df0-614dcd43bfb0'),('2024-01-03 15:17:51.317000',2,2,'f946e075-49e6-4002-8090-9770d11643dd'),('2024-01-09 19:26:22.518000',3,3,'372ac7ef-491a-4c20-a4f4-1b0e7422c9f4');
/*!40000 ALTER TABLE `verification_token` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-16 17:30:04
