
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `forum_project` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `forum_project`;
DROP TABLE IF EXISTS `faculty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faculty` (
  `department` varchar(255) DEFAULT NULL,
  `domains` varbinary(255) DEFAULT NULL,
  `job_title` varchar(255) DEFAULT NULL,
  `publications` varbinary(255) DEFAULT NULL,
  `id` binary(16) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKb1yy5qmooengqabpgn6wygj7x` FOREIGN KEY (`id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `forums`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `forums` (
  `id` binary(16) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `admin_id` binary(16) DEFAULT NULL,
  `section_id` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rnpkpt26uq9f72jit595eea8y` (`section_id`),
  KEY `FKj01o19vnvgrn1h0nsrscekloo` (`admin_id`),
  CONSTRAINT `FK9sabjkrxlc9lnrsde6w77wl5k` FOREIGN KEY (`section_id`) REFERENCES `sections` (`id`),
  CONSTRAINT `FKj01o19vnvgrn1h0nsrscekloo` FOREIGN KEY (`admin_id`) REFERENCES `faculty` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `forums_posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `forums_posts` (
  `forum_entity_id` binary(16) NOT NULL,
  `posts_id` binary(16) NOT NULL,
  PRIMARY KEY (`forum_entity_id`,`posts_id`),
  KEY `fk1_forupost` (`posts_id`),
  CONSTRAINT `fk1_forupost` FOREIGN KEY (`posts_id`) REFERENCES `posts` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk2_forupost` FOREIGN KEY (`forum_entity_id`) REFERENCES `forums` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `posts` (
  `id` binary(16) NOT NULL,
  `content` text,
  `parent_id` binary(16) DEFAULT NULL,
  `poster_id` binary(16) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `poster_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `posts_posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `posts_posts` (
  `post_entity_id` binary(16) NOT NULL,
  `posts_id` binary(16) NOT NULL,
  PRIMARY KEY (`post_entity_id`,`posts_id`),
  UNIQUE KEY `UK_mdb12y38q7xmkn486gru7l86` (`posts_id`),
  CONSTRAINT `FK3jetw2chrr6i0tq994xv055ew` FOREIGN KEY (`post_entity_id`) REFERENCES `posts` (`id`),
  CONSTRAINT `FKd771cpesmpfl7qsavtr4j1yfj` FOREIGN KEY (`posts_id`) REFERENCES `posts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `posts_resources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `posts_resources` (
  `post_id` binary(16) NOT NULL,
  `resource_id` binary(16) NOT NULL,
  KEY `FKbgvks9xd1dayk0a2jhbg3pxhw` (`post_id`),
  KEY `fk1_postresc` (`resource_id`),
  CONSTRAINT `fk1_postresc` FOREIGN KEY (`resource_id`) REFERENCES `resources` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKbgvks9xd1dayk0a2jhbg3pxhw` FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `resources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resources` (
  `id` binary(16) NOT NULL,
  `content_data` longblob,
  `content_type` varchar(255) DEFAULT NULL,
  `date_of_publish` datetime(6) DEFAULT NULL,
  `validated` bit(1) DEFAULT NULL,
  `validator_id` binary(16) DEFAULT NULL,
  `owner_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `resources_forums`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resources_forums` (
  `resource_entity_id` binary(16) NOT NULL,
  `forums_id` binary(16) NOT NULL,
  PRIMARY KEY (`resource_entity_id`,`forums_id`),
  KEY `FKg171c0511omwh9l8peuwoyp4k` (`forums_id`),
  CONSTRAINT `FKf62f75tob5q1bprx22rsnfh01` FOREIGN KEY (`resource_entity_id`) REFERENCES `resources` (`id`),
  CONSTRAINT `FKg171c0511omwh9l8peuwoyp4k` FOREIGN KEY (`forums_id`) REFERENCES `forums` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `sections`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sections` (
  `id` binary(16) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `class_rep_id` binary(16) DEFAULT NULL,
  `class_teacher_id` binary(16) DEFAULT NULL,
  `forum_id` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_n2g7q31yiq0sner6ipfujy6vs` (`class_rep_id`),
  UNIQUE KEY `UK_4o9a9059rv2vu4uhholktx8dg` (`class_teacher_id`),
  UNIQUE KEY `UK_4h9wqc5o10g5595cc2l110dxp` (`forum_id`),
  CONSTRAINT `FKauael80krxfx0al5q240pclbn` FOREIGN KEY (`class_rep_id`) REFERENCES `students` (`id`),
  CONSTRAINT `FKlxt7ipv1ojlffd1cp5coj0rit` FOREIGN KEY (`forum_id`) REFERENCES `forums` (`id`),
  CONSTRAINT `FKrxab81vlhmkxm28qbcto0ix3s` FOREIGN KEY (`class_teacher_id`) REFERENCES `faculty` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`projectadmin`@`localhost`*/ /*!50003 TRIGGER `createSectionForum` AFTER INSERT ON `sections` FOR EACH ROW begin
declare uuid binary(16);
    select UUID_TO_BIN(UUID()) into uuid;
insert into forums values(uuid, new.name, new.class_teacher_id, new.id);
    insert into users_forums values(new.class_teacher_id, uuid);
    insert into users_forums values(new.class_rep_id, uuid);
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students` (
  `class_id` binary(16) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `memberships` varbinary(255) DEFAULT NULL,
  `publications` varbinary(255) DEFAULT NULL,
  `roll_no` varchar(255) DEFAULT NULL,
  `skills` varbinary(255) DEFAULT NULL,
  `id` binary(16) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK7xqmtv7r2eb5axni3jm0a80su` FOREIGN KEY (`id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `user_notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_notifications` (
  `user_id` binary(16) DEFAULT NULL,
  `notification` varchar(255) DEFAULT NULL,
  `ts` datetime DEFAULT NULL,
  `id` binary(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` binary(16) NOT NULL,
  `contact` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `picture` longblob,
  `mime_type` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `role` enum('FACULTY','STUDENT') DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `users_forums`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_forums` (
  `user_id` binary(16) NOT NULL,
  `forum_id` binary(16) NOT NULL,
  PRIMARY KEY (`user_id`,`forum_id`),
  KEY `fk1_userforu` (`forum_id`),
  CONSTRAINT `fk1_userforu` FOREIGN KEY (`forum_id`) REFERENCES `forums` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK9rkxu5sh9kd2rdd7j3j6kw6gi` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 DROP FUNCTION IF EXISTS `getPostCount` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`projectadmin`@`localhost` FUNCTION `getPostCount`(userid varchar(255)) RETURNS int
begin
declare count int;
    select count(*) into count from posts where posts.poster_id = UUID_TO_BIN(userid);
return count;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `notifyUserAddToForum` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`projectadmin`@`localhost` PROCEDURE `notifyUserAddToForum`(in username varchar(255), in forum_id varchar(255))
begin
declare user_id binary(16);
    declare forum_name varchar(255);
    select users.id into user_id from users where users.username = username;
    select forums.name into forum_name from forums where forums.id = UUID_TO_BIN(forum_id);
    insert into user_notifications(id, user_id, notification,ts) values(UUID_TO_BIN(UUID()), user_id, CONCAT("You were added to the ", forum_name, " forum"), now());
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `notifyUserDeleteFromForum` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`projectadmin`@`localhost` PROCEDURE `notifyUserDeleteFromForum`(in user_id varchar(255), in forum_id varchar(255))
begin
    declare forum_name varchar(255);
    select forums.name into forum_name from forums where forums.id = UUID_TO_BIN(forum_id);
    insert into user_notifications(id, user_id, notification,ts) values(UUID_TO_BIN(UUID()),UUID_TO_BIN(user_id), CONCAT("You were deleted from the ", forum_name, " forum"), now());
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

