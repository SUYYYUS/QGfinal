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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_course`
--

LOCK TABLES `tb_course` WRITE;
/*!40000 ALTER TABLE `tb_course` DISABLE KEYS */;
INSERT INTO `tb_course` VALUES (1,'高等数学','数学','简单上手',9,50,1,'2024-04-14 13:48:24','2024-04-21 13:48:24',4),(2,'线性代数','数学','矩阵',9,0,0,'2024-04-15 00:26:17','2024-04-22 00:26:18',1),(3,'离散数学','数学','苦难',9,60,0,'2024-04-15 00:29:18','2024-04-22 00:29:18',2),(4,'大学物理','物理','手拿把掐',9,300,1,'2024-04-15 23:20:08','2024-04-22 23:20:08',2);
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程章节表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_course_section`
--

LOCK TABLES `tb_course_section` WRITE;
/*!40000 ALTER TABLE `tb_course_section` DISABLE KEYS */;
INSERT INTO `tb_course_section` VALUES (1,'求导','好好学习',1,'2024-04-14 14:01:44'),(2,'微分','好好学习',1,'2024-04-14 14:01:45'),(3,'积分','有点小难',1,'2024-04-16 00:52:50'),(4,'极值','有手就行',1,'2024-04-16 00:57:53'),(5,'初等变换','小菜一碟',2,'2024-04-16 00:59:31'),(6,'逻辑推理','主析取范式',3,'2024-04-16 01:03:03'),(7,'质点的运动描述','坐标系',4,'2024-04-16 01:13:39'),(8,'电磁炮','滋润炸裂',4,'2024-04-16 01:16:56'),(9,'命题逻辑','主合取范式',3,'2024-04-23 17:40:49');
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学习讨论表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_discussion`
--

LOCK TABLES `tb_discussion` WRITE;
/*!40000 ALTER TABLE `tb_discussion` DISABLE KEYS */;
INSERT INTO `tb_discussion` VALUES (1,3,9,1,'为什么我一直在c+','菜就多练','2024-04-20 17:27:35','2024-04-21 14:51:21'),(2,3,9,1,'卡莎ap还是ad好','看你自己吧','2024-04-20 17:56:02','2024-04-21 15:24:55'),(3,3,9,1,'今晚独行侠能赢吗','可以','2024-04-21 12:51:04','2024-04-21 20:14:35'),(4,3,9,4,'红牛车好还是法拉利的',NULL,'2024-04-21 15:26:47',NULL),(6,3,9,1,NULL,NULL,'2024-04-21 20:36:42',NULL),(7,3,9,1,NULL,NULL,'2024-04-21 20:36:44',NULL),(8,3,9,1,'111111','222222','2024-04-22 13:32:41','2024-04-23 17:41:18');
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='题目表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_question`
--

LOCK TABLES `tb_question` WRITE;
/*!40000 ALTER TABLE `tb_question` DISABLE KEYS */;
INSERT INTO `tb_question` VALUES (1,'1','1','1',10,1,1,'2024-04-16 13:38:47'),(2,'1','1','1',10,1,1,'2024-04-16 13:50:30'),(3,'1','1','1',10,1,1,'2024-04-16 13:50:53'),(4,'2','2','2',5,5,2,'2024-04-16 15:16:01'),(5,'填空题','惯性与质量有关吗','无',20,7,4,'2024-04-16 15:20:55'),(6,'判断题','在一元函数中，可导比连续吗','不知道',5,1,1,'2024-04-20 10:57:56'),(7,'填空题','二重积分','难',10,1,1,'2024-04-23 00:21:23'),(8,'判断题','薇恩出啥都强','对',10,1,1,'2024-04-23 00:29:23'),(9,'选择题','向心力与什么有关？A.我 B.你 C.他 D.她','B',10,1,1,'2024-04-23 00:36:29'),(10,'判断题','早睡早起身体好','对',10,1,1,'2024-04-23 00:40:06');
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学生表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_student`
--

LOCK TABLES `tb_student` WRITE;
/*!40000 ALTER TABLE `tb_student` DISABLE KEYS */;
INSERT INTO `tb_student` VALUES (2,'折叠','3123004825','01c18933cc8169763271192e213f5cd26516731136210d0c','2023级','1237894560','可爱','2024-04-14 18:35:52','2024-04-14 18:35:52'),(3,'江川','3123004826','d4aa6131d14ee8e058807667e29137a43148e8581fb57c52','2024级','4567891230','勇','2024-04-14 18:37:37','2024-04-21 20:33:08'),(4,'汉密尔','1234567890','c4bb9203e56561e61d708b77637351d18a50630c0987874e','2020级','3692581470','车技拉','2024-04-23 17:47:32','2024-04-23 17:47:32'),(5,'小周','0987654321','d7b69eb3430b35c630b33035890a2b525505894e15623d2f','2021级','2583691470','王者','2024-04-23 17:55:36','2024-04-23 17:55:36');
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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学生答题记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_student_answer`
--

LOCK TABLES `tb_student_answer` WRITE;
/*!40000 ALTER TABLE `tb_student_answer` DISABLE KEYS */;
INSERT INTO `tb_student_answer` VALUES (1,3,1,1,'2',0,'2024-04-16 18:48:26'),(2,3,1,1,'2',0,'2024-04-16 18:48:40'),(3,3,5,4,'æ',0,'2024-04-16 18:50:37'),(4,3,5,4,'无',20,'2024-04-16 18:54:30'),(5,3,2,1,'1',10,'2024-04-16 18:56:08'),(6,3,5,4,'有',0,'2024-04-16 23:04:22'),(7,3,2,1,'1',10,'2024-04-17 09:01:06'),(8,3,2,1,'1',10,'2024-04-17 09:01:08'),(9,3,3,1,'1',10,'2024-04-17 09:03:10'),(10,3,2,1,'1',10,'2024-04-17 09:08:13'),(11,3,3,1,'1',10,'2024-04-17 09:11:02'),(12,3,5,4,'无',20,'2024-04-17 09:58:44'),(13,3,5,4,'无',20,'2024-04-17 09:59:11'),(14,3,5,4,'无',20,'2024-04-17 09:59:30'),(15,3,5,4,'无',20,'2024-04-17 09:59:30'),(16,3,5,4,'有',0,'2024-04-17 09:59:38'),(17,3,5,4,'无',20,'2024-04-17 09:59:43'),(18,3,6,1,'不知道',5,'2024-04-20 10:59:44'),(19,3,5,4,'无',20,'2024-04-20 11:07:27'),(20,3,6,1,'ddgd',0,'2024-04-21 20:34:50'),(21,3,6,1,'ddgd',0,'2024-04-21 20:35:52'),(22,3,6,1,'ddgd',0,'2024-04-21 20:35:54'),(23,3,6,1,'ddgd',0,'2024-04-21 20:35:56'),(24,3,6,1,'ddgd',0,'2024-04-21 20:35:59'),(25,3,6,1,'对',0,'2024-04-23 00:56:13'),(26,3,6,1,'错',0,'2024-04-23 00:56:18'),(27,3,8,1,'对',10,'2024-04-23 00:56:41'),(28,3,9,1,'C',0,'2024-04-23 00:56:51'),(29,3,9,1,'B',10,'2024-04-23 15:37:40');
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学生课程表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_student_course`
--

LOCK TABLES `tb_student_course` WRITE;
/*!40000 ALTER TABLE `tb_student_course` DISABLE KEYS */;
INSERT INTO `tb_student_course` VALUES (1,3,1,'2024-04-16 10:22:56',1),(2,3,4,'2024-04-16 10:24:53',1);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学生日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_student_logging`
--

LOCK TABLES `tb_student_logging` WRITE;
/*!40000 ALTER TABLE `tb_student_logging` DISABLE KEYS */;
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
INSERT INTO `tb_student_study` VALUES (1,3,1,17,3.823529411764706,65,0.4117647058823529,7),(2,3,4,10,14,140,0.7,7);
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='教师表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_teacher`
--

LOCK TABLES `tb_teacher` WRITE;
/*!40000 ALTER TABLE `tb_teacher` DISABLE KEYS */;
INSERT INTO `tb_teacher` VALUES (1,'张杰','1323004824','460a2cb8c957b18d47e1469d253d35b2f33045a57588f708','计算机学院','0987654321','0987654321@qq.com','凶猛','2024-04-14 13:12:52','2024-04-14 13:12:52'),(9,'江川','111','646323638414429f42258889519c91d70a12c8fc16961140','自动化学院','147258369','147258369@qq.com','天真无邪','2024-04-14 19:10:44','2024-04-14 19:10:44'),(13,'汉密尔顿','1323004830','95d00c18412c418b98495765792d7db9544508419ba46012','计算机学院','3692581470','3692581470@qq.com','车技好','2024-04-23 17:58:44','2024-04-23 17:58:44');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='教师日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_teacher_logging`
--

LOCK TABLES `tb_teacher_logging` WRITE;
/*!40000 ALTER TABLE `tb_teacher_logging` DISABLE KEYS */;
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

-- Dump completed on 2024-04-23 23:32:41
