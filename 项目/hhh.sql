-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: QGfinal_test
-- ------------------------------------------------------
-- Server version	8.0.36

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

--
-- Table structure for table `tb_admin`
--

DROP TABLE IF EXISTS `tb_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_admin` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `adminname` varchar(20) NOT NULL COMMENT '管理员名称',
  `account` varchar(20) NOT NULL COMMENT '账号',
  `password` varchar(300) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='管理员表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_admin`
--

LOCK TABLES `tb_admin` WRITE;
/*!40000 ALTER TABLE `tb_admin` DISABLE KEYS */;
INSERT INTO `tb_admin` VALUES (1,'suyyyus','1234567890','a5359c141d17f94067c02a9d473640881d92194662995305'),(3,'suyyyus','1234567899','39e30c322c7942cf8b69449d245f0e533263a38c30c2e543');
/*!40000 ALTER TABLE `tb_admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_course`
--

DROP TABLE IF EXISTS `tb_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_course` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `coursename` varchar(20) NOT NULL COMMENT '课程名称',
  `subject` varchar(10) NOT NULL COMMENT '所属学科',
  `description` varchar(50) DEFAULT NULL COMMENT '课程描述',
  `teacher_id` int unsigned NOT NULL COMMENT '教师ID',
  `limitnumber` int NOT NULL COMMENT '限制人数',
  `registernumber` int NOT NULL DEFAULT '0' COMMENT '报名人数',
  `create_time` varchar(30) NOT NULL COMMENT '开课时间',
  `end_time` varchar(30) NOT NULL COMMENT '结课时间',
  `section_number` int DEFAULT '0' COMMENT '章节数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_course`
--

LOCK TABLES `tb_course` WRITE;
/*!40000 ALTER TABLE `tb_course` DISABLE KEYS */;
INSERT INTO `tb_course` VALUES (1,'高等数学','数学','简单上手',9,50,2,'2024-04-14 13:48:24','2024-04-21 13:48:24',5),(2,'线性代数','数学','矩阵',9,0,0,'2024-04-15 00:26:17','2024-04-22 00:26:18',1),(3,'离散数学','数学','苦难',9,60,0,'2024-04-15 00:29:18','2024-04-22 00:29:18',2),(4,'大学物理','物理','手拿把掐',9,300,1,'2024-04-15 23:20:08','2024-04-22 23:20:08',2),(6,'瓦罗兰特','体育','收徒',9,10,0,'2024-04-24 10:00:19','2024-04-25 03:02:02',0),(7,'英雄联盟','体育','uzi',9,10,1,'2024-04-24 10:07:05','2024-05-16 00:00:00',0);
/*!40000 ALTER TABLE `tb_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_course_section`
--

DROP TABLE IF EXISTS `tb_course_section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_course_section` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `sectionname` varchar(20) NOT NULL COMMENT '章节名称',
  `content` varchar(1000) NOT NULL COMMENT '章节内容',
  `course_id` int unsigned NOT NULL COMMENT '课程ID',
  `create_time` varchar(30) NOT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程章节表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_course_section`
--

LOCK TABLES `tb_course_section` WRITE;
/*!40000 ALTER TABLE `tb_course_section` DISABLE KEYS */;
INSERT INTO `tb_course_section` VALUES (1,'求导','好好学习',1,'2024-04-14 14:01:44'),(2,'微分','好好学习',1,'2024-04-14 14:01:45'),(3,'积分','有点小难',1,'2024-04-16 00:52:50'),(4,'极值','有手就行',1,'2024-04-16 00:57:53'),(5,'初等变换','小菜一碟',2,'2024-04-16 00:59:31'),(6,'逻辑推理','主析取范式',3,'2024-04-16 01:03:03'),(7,'质点的运动描述','坐标系',4,'2024-04-16 01:13:39'),(8,'电磁炮','滋润炸裂',4,'2024-04-16 01:16:56'),(9,'命题逻辑','主合取范式',3,'2024-04-23 17:40:49'),(10,'别学啦','疯狂一下',1,'2024-04-26 17:44:46');
/*!40000 ALTER TABLE `tb_course_section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_discussion`
--

DROP TABLE IF EXISTS `tb_discussion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_discussion` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID\r\n',
  `student_id` int unsigned NOT NULL DEFAULT '0' COMMENT '学生ID',
  `teacher_id` int unsigned NOT NULL DEFAULT '0' COMMENT '教师ID',
  `course_id` int unsigned NOT NULL DEFAULT '0' COMMENT '课程ID',
  `question` varchar(100) DEFAULT NULL COMMENT '学生提问',
  `reply` varchar(300) DEFAULT NULL COMMENT '老师回复',
  `question_time` varchar(50) DEFAULT NULL COMMENT '提问时间',
  `reply_time` varchar(50) DEFAULT NULL COMMENT '回复时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学习讨论表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_discussion`
--

LOCK TABLES `tb_discussion` WRITE;
/*!40000 ALTER TABLE `tb_discussion` DISABLE KEYS */;
INSERT INTO `tb_discussion` VALUES (1,3,9,1,'为什么我一直在c+','菜就多练','2024-04-20 17:27:35','2024-04-21 14:51:21'),(2,3,9,1,'卡莎ap还是ad好','薇恩好','2024-04-20 17:56:02','2024-04-26 17:44:34'),(3,3,9,1,'今晚独行侠能赢吗','可以','2024-04-21 12:51:04','2024-04-21 20:14:35'),(9,3,9,1,'为什么没有M9',NULL,'2024-04-26 17:47:53',NULL);
/*!40000 ALTER TABLE `tb_discussion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_question`
--

DROP TABLE IF EXISTS `tb_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_question` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type` varchar(10) NOT NULL COMMENT '题目类型',
  `content` varchar(200) NOT NULL COMMENT '题目内容',
  `answer` varchar(20) NOT NULL COMMENT '答案',
  `score` int NOT NULL COMMENT '分值',
  `course_section_id` int unsigned NOT NULL COMMENT '章节ID',
  `course_id` int unsigned NOT NULL COMMENT '课程ID',
  `create_time` varchar(30) NOT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='题目表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_question`
--

LOCK TABLES `tb_question` WRITE;
/*!40000 ALTER TABLE `tb_question` DISABLE KEYS */;
INSERT INTO `tb_question` VALUES (1,'1','1','1',10,1,1,'2024-04-16 13:38:47'),(2,'1','1','1',10,1,1,'2024-04-16 13:50:30'),(3,'1','1','1',10,1,1,'2024-04-16 13:50:53'),(4,'2','2','2',5,5,2,'2024-04-16 15:16:01'),(5,'填空题','惯性与质量有关吗','无',20,7,4,'2024-04-16 15:20:55'),(6,'判断题','在一元函数中，可导比连续吗','不知道',5,1,1,'2024-04-20 10:57:56'),(7,'填空题','二重积分','难',10,1,1,'2024-04-23 00:21:23'),(8,'判断题','薇恩出啥都强','对',10,1,1,'2024-04-23 00:29:23'),(9,'选择题','向心力与什么有关？A.我 B.你 C.他 D.她','B',10,1,1,'2024-04-23 00:36:29'),(10,'判断题','早睡早起身体好','对',10,1,1,'2024-04-23 00:40:06'),(11,'填空题','爪子还是蝴蝶','爪子',100,1,1,'2024-04-26 17:45:50');
/*!40000 ALTER TABLE `tb_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_student`
--

DROP TABLE IF EXISTS `tb_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_student` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `studentname` varchar(20) NOT NULL COMMENT '学生姓名',
  `studentid` varchar(20) NOT NULL COMMENT '学号',
  `password` varchar(300) NOT NULL COMMENT '密码',
  `grade` varchar(10) DEFAULT NULL COMMENT '年级',
  `qq` varchar(20) DEFAULT NULL COMMENT 'QQ',
  `description` varchar(50) DEFAULT NULL COMMENT '个人介绍',
  `create_time` varchar(30) NOT NULL COMMENT '注册时间',
  `update_time` varchar(30) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tb_student_studentid_uindex` (`studentid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学生表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_student`
--

LOCK TABLES `tb_student` WRITE;
/*!40000 ALTER TABLE `tb_student` DISABLE KEYS */;
INSERT INTO `tb_student` VALUES (2,'折叠','3123004825','628c9c73c05965875e408d1594de4637265153a18f271d74','2023级','1237894560','可爱','2024-04-14 18:35:52','2024-04-14 18:35:52'),(3,'江川','3123004826','d4aa6131d14ee8e058807667e29137a43148e8581fb57c52','2023级','4567891230','勇','2024-04-14 18:37:37','2024-04-26 17:47:26'),(4,'汉密尔','1234567890','c4bb9203e56561e61d708b77637351d18a50630c0987874e','2020级','3692581470','车技拉','2024-04-23 17:47:32','2024-04-23 17:47:32'),(5,'小周','0987654321','d7b69eb3430b35c630b33035890a2b525505894e15623d2f','2021级','2583691470','王者','2024-04-23 17:55:36','2024-04-23 17:55:36'),(6,'鲤鱼','3123004833','e2ae39c4fb54f43442b9aa6442210685fa21a98a3115588b','2022级','7891234560','可爱','2024-04-26 17:52:31','2024-04-26 17:52:31');
/*!40000 ALTER TABLE `tb_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_student_answer`
--

DROP TABLE IF EXISTS `tb_student_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_student_answer` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `student_id` int unsigned NOT NULL COMMENT '学生ID',
  `question_id` int unsigned NOT NULL COMMENT '题目ID',
  `course_id` int unsigned NOT NULL COMMENT '课程ID',
  `answer` varchar(20) NOT NULL COMMENT '学生答案',
  `score` int NOT NULL DEFAULT '0' COMMENT '得分',
  `answer_time` varchar(30) NOT NULL COMMENT '答题时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学生答题记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_student_answer`
--

LOCK TABLES `tb_student_answer` WRITE;
/*!40000 ALTER TABLE `tb_student_answer` DISABLE KEYS */;
INSERT INTO `tb_student_answer` VALUES (1,3,1,1,'2',0,'2024-04-16 18:48:26'),(2,3,1,1,'2',0,'2024-04-16 18:48:40'),(3,3,5,4,'æ',0,'2024-04-16 18:50:37'),(4,3,5,4,'无',20,'2024-04-16 18:54:30'),(5,3,2,1,'1',10,'2024-04-16 18:56:08'),(6,3,5,4,'有',0,'2024-04-16 23:04:22'),(7,3,2,1,'1',10,'2024-04-17 09:01:06'),(8,3,2,1,'1',10,'2024-04-17 09:01:08'),(9,3,3,1,'1',10,'2024-04-17 09:03:10'),(10,3,2,1,'1',10,'2024-04-17 09:08:13'),(11,3,3,1,'1',10,'2024-04-17 09:11:02'),(12,3,5,4,'无',20,'2024-04-17 09:58:44'),(13,3,5,4,'无',20,'2024-04-17 09:59:11'),(14,3,5,4,'无',20,'2024-04-17 09:59:30'),(15,3,5,4,'无',20,'2024-04-17 09:59:30'),(16,3,5,4,'有',0,'2024-04-17 09:59:38'),(17,3,5,4,'无',20,'2024-04-17 09:59:43'),(18,3,6,1,'不知道',5,'2024-04-20 10:59:44'),(19,3,5,4,'无',20,'2024-04-20 11:07:27'),(20,3,6,1,'ddgd',0,'2024-04-21 20:34:50'),(21,3,6,1,'ddgd',0,'2024-04-21 20:35:52'),(22,3,6,1,'ddgd',0,'2024-04-21 20:35:54'),(23,3,6,1,'ddgd',0,'2024-04-21 20:35:56'),(24,3,6,1,'ddgd',0,'2024-04-21 20:35:59'),(25,3,6,1,'对',0,'2024-04-23 00:56:13'),(26,3,6,1,'错',0,'2024-04-23 00:56:18'),(27,3,8,1,'对',10,'2024-04-23 00:56:41'),(28,3,9,1,'C',0,'2024-04-23 00:56:51'),(29,3,9,1,'B',10,'2024-04-23 15:37:40'),(30,3,1,1,'1',10,'2024-04-26 00:10:56'),(31,3,1,1,'1',10,'2024-04-26 00:10:58'),(32,3,1,1,'1',10,'2024-04-26 00:11:01'),(33,3,1,1,'1',10,'2024-04-26 00:11:04'),(34,3,6,1,'对',0,'2024-04-26 00:18:26'),(35,3,6,1,'错',0,'2024-04-26 00:18:28'),(36,3,6,1,'对',0,'2024-04-26 00:18:32'),(37,3,6,1,'对',0,'2024-04-26 00:18:35'),(38,3,1,1,'2',0,'2024-04-26 00:22:39'),(39,3,1,1,'1',10,'2024-04-26 00:22:42'),(40,3,1,1,'1',10,'2024-04-26 00:22:44'),(41,3,1,1,'1',10,'2024-04-26 00:22:46'),(42,3,1,1,'2',0,'2024-04-26 00:22:48'),(43,3,1,1,'3',0,'2024-04-26 00:22:51'),(44,3,2,1,'1',10,'2024-04-26 00:24:29'),(45,3,2,1,'1',10,'2024-04-26 00:24:31'),(46,3,2,1,'1',10,'2024-04-26 00:24:34'),(47,3,2,1,'1',10,'2024-04-26 00:24:38'),(48,3,2,1,'1',10,'2024-04-26 00:24:40'),(49,3,2,1,'1',10,'2024-04-26 00:24:42'),(50,3,1,1,'1',10,'2024-04-26 00:29:59'),(51,3,1,1,'1',10,'2024-04-26 00:30:01'),(52,3,1,1,'1',10,'2024-04-26 00:30:03'),(53,3,1,1,'1',10,'2024-04-26 00:30:05'),(54,3,1,1,'1',10,'2024-04-26 00:30:07'),(55,3,1,1,'1',10,'2024-04-26 00:30:15'),(56,3,1,1,'2',0,'2024-04-26 00:30:17'),(57,3,1,1,'3',0,'2024-04-26 00:30:19'),(58,3,1,1,'4',0,'2024-04-26 00:30:21'),(122,3,11,1,'爪子',100,'2024-04-26 17:47:41');
/*!40000 ALTER TABLE `tb_student_answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_student_course`
--

DROP TABLE IF EXISTS `tb_student_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_student_course` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `student_id` int unsigned NOT NULL COMMENT '学生ID',
  `course_id` int unsigned NOT NULL COMMENT '课程ID',
  `register_time` varchar(30) NOT NULL COMMENT '报名时间',
  `status` int unsigned DEFAULT NULL COMMENT '状态：1.学习中，2.已完成',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学生课程表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_student_course`
--

LOCK TABLES `tb_student_course` WRITE;
/*!40000 ALTER TABLE `tb_student_course` DISABLE KEYS */;
INSERT INTO `tb_student_course` VALUES (1,3,1,'2024-04-16 10:22:56',1),(2,3,4,'2024-04-16 10:24:53',1),(3,5,7,'2024-04-25 00:14:29',1);
/*!40000 ALTER TABLE `tb_student_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_student_logging`
--

DROP TABLE IF EXISTS `tb_student_logging`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_student_logging` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `student_id` int unsigned DEFAULT NULL COMMENT '学生ID',
  `logging` varchar(100) DEFAULT NULL COMMENT '日志记录',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=143 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学生日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_student_logging`
--

LOCK TABLES `tb_student_logging` WRITE;
/*!40000 ALTER TABLE `tb_student_logging` DISABLE KEYS */;
INSERT INTO `tb_student_logging` VALUES (1,3,'2024-04-23 23:56:43:成功登录进入平台'),(2,3,'2024-04-24 00:44:10:成功登录进入平台'),(3,3,'2024-04-24 00:44:18:退出在线学习平台'),(4,3,'2024-04-24 00:44:22:成功登录进入平台'),(5,3,'2024-04-24 19:30:37:成功登录进入平台'),(6,3,'2024-04-24 19:33:13:成功登录进入平台'),(7,3,'2024-04-24 19:34:09:退出在线学习平台'),(8,3,'2024-04-24 19:34:14:成功登录进入平台'),(9,3,'2024-04-25 00:18:33:成功登录进入平台'),(10,3,'2024-04-25 00:19:58:成功登录进入平台'),(11,3,'2024-04-25 00:26:14:成功登录进入平台'),(12,3,'2024-04-25 00:27:29:退出在线学习平台'),(13,3,'2024-04-25 00:29:02:成功登录进入平台'),(14,3,'2024-04-25 09:04:46:成功登录进入平台'),(15,3,'2024-04-25 09:05:31:退出在线学习平台'),(16,3,'2024-04-25 16:41:06:成功登录进入平台'),(17,3,'2024-04-25 16:49:21:成功登录进入平台'),(18,3,'2024-04-25 16:50:41:成功登录进入平台'),(19,3,'2024-04-25 23:47:45:成功登录进入平台'),(20,3,'2024-04-25 23:49:33:成功登录进入平台'),(21,3,'2024-04-26 00:10:45:成功登录进入平台'),(22,3,'2024-04-26 00:10:56:作答了1'),(23,3,'2024-04-26 00:10:58:作答了1'),(24,3,'2024-04-26 00:11:01:作答了1'),(25,3,'2024-04-26 00:11:04:作答了1'),(26,3,'2024-04-26 00:18:19:成功登录进入平台'),(27,3,'2024-04-26 00:18:26:作答了在一元函数中，可导比连续吗'),(28,3,'2024-04-26 00:18:28:作答了在一元函数中，可导比连续吗'),(29,3,'2024-04-26 00:18:32:作答了在一元函数中，可导比连续吗'),(30,3,'2024-04-26 00:18:35:作答了在一元函数中，可导比连续吗'),(31,3,'2024-04-26 00:22:31:成功登录进入平台'),(32,3,'2024-04-26 00:22:39:作答了1'),(33,3,'2024-04-26 00:22:42:作答了1'),(34,3,'2024-04-26 00:22:44:作答了1'),(35,3,'2024-04-26 00:22:46:作答了1'),(36,3,'2024-04-26 00:22:48:作答了1'),(37,3,'2024-04-26 00:22:51:作答了1'),(38,3,'2024-04-26 00:24:23:成功登录进入平台'),(39,3,'2024-04-26 00:24:29:作答了1'),(40,3,'2024-04-26 00:24:31:作答了1'),(41,3,'2024-04-26 00:24:34:作答了1'),(42,3,'2024-04-26 00:24:38:作答了1'),(43,3,'2024-04-26 00:24:40:作答了1'),(44,3,'2024-04-26 00:24:42:作答了1'),(45,3,'2024-04-26 00:29:53:成功登录进入平台'),(46,3,'2024-04-26 00:29:59:作答了1'),(47,3,'2024-04-26 00:30:01:作答了1'),(48,3,'2024-04-26 00:30:03:作答了1'),(49,3,'2024-04-26 00:30:05:作答了1'),(50,3,'2024-04-26 00:30:07:作答了1'),(51,3,'2024-04-26 00:30:15:作答了1'),(52,3,'2024-04-26 00:30:17:作答了1'),(53,3,'2024-04-26 00:30:19:作答了1'),(54,3,'2024-04-26 00:30:21:作答了1'),(55,3,'2024-04-26 00:30:25:作答了1'),(56,3,'2024-04-26 00:30:27:作答了1'),(57,3,'2024-04-26 00:30:30:作答了1'),(58,3,'2024-04-26 00:36:09:成功登录进入平台'),(59,3,'2024-04-26 00:36:16:作答了1'),(60,3,'2024-04-26 00:36:18:作答了1'),(61,3,'2024-04-26 00:36:20:作答了1'),(62,3,'2024-04-26 00:36:24:作答了1'),(63,3,'2024-04-26 00:36:25:作答了1'),(64,3,'2024-04-26 00:36:27:作答了1'),(65,3,'2024-04-26 13:38:20:成功登录进入平台'),(66,3,'2024-04-26 13:42:20:成功登录进入平台'),(67,3,'2024-04-26 13:42:25:作答了1'),(68,3,'2024-04-26 13:42:27:作答了1'),(69,3,'2024-04-26 13:42:28:作答了1'),(70,3,'2024-04-26 13:42:31:作答了1'),(71,3,'2024-04-26 13:42:33:作答了1'),(72,3,'2024-04-26 13:42:35:作答了1'),(73,3,'2024-04-26 13:42:46:作答了1'),(74,3,'2024-04-26 13:42:48:作答了1'),(75,3,'2024-04-26 13:42:49:作答了1'),(76,3,'2024-04-26 13:42:51:作答了1'),(77,3,'2024-04-26 13:43:12:作答了1'),(78,3,'2024-04-26 13:43:14:作答了1'),(79,3,'2024-04-26 13:43:16:作答了1'),(80,3,'2024-04-26 13:43:59:作答了1'),(81,3,'2024-04-26 13:44:01:作答了1'),(82,3,'2024-04-26 13:44:03:作答了1'),(83,3,'2024-04-26 13:44:05:作答了1'),(84,3,'2024-04-26 13:44:07:作答了1'),(85,3,'2024-04-26 13:45:27:成功登录进入平台'),(86,3,'2024-04-26 13:45:38:作答了1'),(87,3,'2024-04-26 13:45:43:作答了1'),(88,3,'2024-04-26 13:45:45:作答了1'),(89,3,'2024-04-26 13:45:46:作答了1'),(90,3,'2024-04-26 13:45:53:作答了1'),(91,3,'2024-04-26 13:45:55:作答了1'),(92,3,'2024-04-26 13:45:56:作答了1'),(93,3,'2024-04-26 13:45:59:作答了1'),(94,3,'2024-04-26 13:46:03:作答了1'),(95,3,'2024-04-26 13:46:06:作答了1'),(96,3,'2024-04-26 13:46:08:作答了1'),(97,3,'2024-04-26 13:46:10:作答了1'),(98,3,'2024-04-26 13:46:12:作答了1'),(99,3,'2024-04-26 13:46:17:作答了在一元函数中，可导比连续吗'),(100,3,'2024-04-26 13:46:19:作答了在一元函数中，可导比连续吗'),(101,3,'2024-04-26 13:56:20:成功登录进入平台'),(102,3,'2024-04-26 14:05:46:成功登录进入平台'),(103,3,'2024-04-26 14:08:49:成功登录进入平台'),(104,3,'2024-04-26 14:09:04:作答了1'),(105,3,'2024-04-26 14:09:11:作答了1'),(106,3,'2024-04-26 14:13:40:成功登录进入平台'),(107,3,'2024-04-26 14:13:45:作答了1'),(108,3,'2024-04-26 14:13:46:作答了1'),(109,3,'2024-04-26 14:13:48:作答了1'),(110,3,'2024-04-26 14:13:50:作答了1'),(111,3,'2024-04-26 14:13:52:作答了1'),(112,3,'2024-04-26 14:13:53:作答了1'),(113,3,'2024-04-26 14:13:55:作答了1'),(114,3,'2024-04-26 14:13:59:作答了1'),(115,3,'2024-04-26 14:14:01:作答了1'),(116,3,'2024-04-26 14:14:03:作答了1'),(117,3,'2024-04-26 14:14:05:作答了1'),(118,3,'2024-04-26 14:14:07:作答了1'),(119,3,'2024-04-26 14:14:09:作答了1'),(120,3,'2024-04-26 14:14:11:作答了1'),(121,3,'2024-04-26 14:14:13:作答了1'),(122,3,'2024-04-26 14:14:14:作答了1'),(123,3,'2024-04-26 14:14:16:作答了1'),(124,3,'2024-04-26 14:16:16:成功登录进入平台'),(125,3,'2024-04-26 14:19:39:成功登录进入平台'),(126,3,'2024-04-26 14:23:03:成功登录进入平台'),(127,3,'2024-04-26 14:30:45:成功登录进入平台'),(128,3,'2024-04-26 14:31:42:成功登录进入平台'),(129,3,'2024-04-26 14:31:51:作答了1'),(130,3,'2024-04-26 14:31:53:作答了1'),(131,3,'2024-04-26 14:51:43:成功登录进入平台'),(132,3,'2024-04-26 15:13:31:成功登录进入平台'),(133,3,'2024-04-26 15:13:36:退出在线学习平台'),(134,2,'2024-04-26 17:42:25:重置了自己的密码'),(135,3,'2024-04-26 17:47:07:成功登录进入平台'),(136,3,'2024-04-26 17:47:41:作答了爪子还是蝴蝶'),(137,3,'2024-04-26 17:47:53:对高等数学这门课程发出了提问'),(138,3,'2024-04-26 17:51:57:退出在线学习平台'),(139,0,'2024-04-26 17:52:31:成功注册开通账号'),(140,6,'2024-04-26 17:52:42:成功登录进入平台'),(141,6,'2024-04-26 17:55:31:成功登录进入平台'),(142,6,'2024-04-26 17:55:40:退出在线学习平台');
/*!40000 ALTER TABLE `tb_student_logging` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_student_study`
--

DROP TABLE IF EXISTS `tb_student_study`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_student_study` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `student_id` int unsigned NOT NULL COMMENT '学生ID',
  `course_id` int unsigned NOT NULL COMMENT '课程ID',
  `answernumber` int DEFAULT '0' COMMENT '答题次数',
  `average_score` double DEFAULT '0' COMMENT '平均分',
  `total_score` double DEFAULT '0' COMMENT '总分',
  `accuracy` double DEFAULT '0' COMMENT '正确率',
  `rightnumber` int DEFAULT '0' COMMENT '答对次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学生学习情况表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_student_study`
--

LOCK TABLES `tb_student_study` WRITE;
/*!40000 ALTER TABLE `tb_student_study` DISABLE KEYS */;
INSERT INTO `tb_student_study` VALUES (1,3,1,110,7.045454545454546,775,0.6272727272727273,69),(2,3,4,10,14,140,0.7,7);
/*!40000 ALTER TABLE `tb_student_study` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_teacher`
--

DROP TABLE IF EXISTS `tb_teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_teacher` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `teachername` varchar(20) NOT NULL COMMENT '教师名字',
  `teacherid` varchar(20) NOT NULL COMMENT '教职工号',
  `password` varchar(300) NOT NULL COMMENT '密码',
  `college` varchar(20) NOT NULL COMMENT '所属学院',
  `qq` varchar(20) DEFAULT NULL COMMENT 'QQ',
  `email` varchar(50) DEFAULT NULL COMMENT '电子邮箱',
  `description` varchar(100) DEFAULT NULL COMMENT '个人介绍',
  `create_time` varchar(50) NOT NULL COMMENT '注册时间',
  `update_time` varchar(50) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tb_teacher_teacherid_uindex` (`teacherid`),
  UNIQUE KEY `tb_teacher_email_uindex` (`email`),
  UNIQUE KEY `tb_teacher_qq_uindex` (`qq`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='教师表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_teacher`
--

LOCK TABLES `tb_teacher` WRITE;
/*!40000 ALTER TABLE `tb_teacher` DISABLE KEYS */;
INSERT INTO `tb_teacher` VALUES (1,'张杰','1323004824','460a2cb8c957b18d47e1469d253d35b2f33045a57588f708','计算机学院','0987654321','0987654321@qq.com','凶猛','2024-04-14 13:12:52','2024-04-14 13:12:52'),(9,'江川宝宝','111','646323638414429f42258889519c91d70a12c8fc16961140','自动化学院','147258369','147258369@qq.com','天真无邪','2024-04-14 19:10:44','2024-04-26 17:44:02'),(13,'汉密尔顿','1323004830','95d00c18412c418b98495765792d7db9544508419ba46012','计算机学院','3692581470','3692581470@qq.com','车技好','2024-04-23 17:58:44','2024-04-23 17:58:44'),(14,'土家','222','08643531297108f66121e83ca2ed7da7006291167431b522','计算机学院','1237894560','1237894560@qq.com','刚枪','2024-04-26 17:46:45','2024-04-26 17:46:45');
/*!40000 ALTER TABLE `tb_teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_teacher_logging`
--

DROP TABLE IF EXISTS `tb_teacher_logging`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_teacher_logging` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `teacher_id` int unsigned DEFAULT NULL COMMENT '教师ID',
  `logging` varchar(100) DEFAULT NULL COMMENT '日志记录',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='教师日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_teacher_logging`
--

LOCK TABLES `tb_teacher_logging` WRITE;
/*!40000 ALTER TABLE `tb_teacher_logging` DISABLE KEYS */;
INSERT INTO `tb_teacher_logging` VALUES (1,9,'2024-04-24 00:29:31:成功登录平台'),(2,9,'2024-04-24 00:38:56:成功登录平台'),(3,9,'2024-04-24 00:39:52:成功登录平台'),(4,9,'2024-04-24 09:48:25:成功登录平台'),(5,9,'2024-04-24 09:53:25:成功登录平台'),(6,9,'2024-04-24 09:59:42:成功登录平台'),(7,9,'2024-04-24 10:06:37:成功登录平台'),(8,9,'2024-04-24 23:47:44:成功登录平台'),(9,9,'2024-04-25 00:11:27:成功登录平台'),(10,9,'2024-04-25 00:14:16:成功登录平台'),(11,9,'2024-04-25 00:14:29:帮助学生小周报名了英雄联盟该课程'),(12,9,'2024-04-25 09:05:49:成功登录平台'),(13,9,'2024-04-25 09:06:42:退出学习平台'),(14,9,'2024-04-25 09:06:47:成功登录平台'),(15,9,'2024-04-25 09:27:43:成功登录平台'),(16,9,'2024-04-25 09:31:55:成功登录平台'),(17,9,'2024-04-25 09:41:42:成功登录平台'),(18,9,'2024-04-26 17:43:52:成功登录平台'),(19,9,'2024-04-26 17:44:02:修改了个人信息'),(20,9,'2024-04-26 17:44:34:回复了江川的留言'),(21,9,'2024-04-26 17:44:47:高等数学课程中新增加了章节别学啦'),(22,9,'2024-04-26 17:45:15:帮助学生折叠报名了高等数学该课程'),(23,9,'2024-04-26 17:45:50:添加了题目在求导这一章节中'),(24,9,'2024-04-26 17:46:12:退出学习平台'),(25,0,'2024-04-26 17:46:45:成功注册账号'),(26,14,'2024-04-26 17:46:51:成功登录平台'),(27,14,'2024-04-26 17:46:53:退出学习平台');
/*!40000 ALTER TABLE `tb_teacher_logging` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-26 17:57:32
