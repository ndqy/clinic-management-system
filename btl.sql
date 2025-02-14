-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.7.39-log


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema btl
--

CREATE DATABASE IF NOT EXISTS btl;
USE btl;

--
-- Definition of table `tblcalendar`
--

DROP TABLE IF EXISTS `tblcalendar`;
CREATE TABLE `tblcalendar` (
  `calendar_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `calendar_create_date` varchar(45) DEFAULT NULL,
  `calendar_start_date` datetime NOT NULL,
  `calendar_end_date` datetime DEFAULT NULL,
  `calendar_customer_id` int(10) unsigned NOT NULL,
  `calendar_service_id` int(10) unsigned NOT NULL,
  `calendar_room_id` int(10) unsigned NOT NULL,
  `calendar_notes` varchar(200) DEFAULT NULL,
  `calendar_dentist_id` int(10) unsigned NOT NULL,
  `calendar_delete` tinyint(1) unsigned DEFAULT NULL,
  `calendar_last_modified` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`calendar_id`)
) ENGINE=InnoDB AUTO_INCREMENT=170 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tblcalendar`
--

/*!40000 ALTER TABLE `tblcalendar` DISABLE KEYS */;
INSERT INTO `tblcalendar` (`calendar_id`,`calendar_create_date`,`calendar_start_date`,`calendar_end_date`,`calendar_customer_id`,`calendar_service_id`,`calendar_room_id`,`calendar_notes`,`calendar_dentist_id`,`calendar_delete`,`calendar_last_modified`) VALUES 
 (100,'27/10/2023','2023-10-10 08:00:00','2023-10-10 08:10:00',1,1,1,NULL,4,0,''),
 (101,'27/10/2023','2023-10-10 08:00:00','2023-10-10 09:45:00',3,1,2,NULL,4,0,''),
 (102,'27/10/2023','2023-10-10 08:30:00','2023-10-10 09:15:00',3,1,5,NULL,5,0,''),
 (103,'27/10/2023','2023-10-10 10:00:00','2023-10-10 11:00:00',1,2,1,NULL,4,0,''),
 (104,'27/10/2023','2023-10-10 08:00:00','2023-10-10 09:45:00',3,1,3,NULL,5,0,''),
 (105,'27/10/2023','2023-10-10 08:30:00','2023-10-10 09:45:00',1,1,4,NULL,4,0,''),
 (106,'27/10/2023','2023-10-10 11:00:00','2023-10-10 11:30:00',4,3,1,NULL,7,0,''),
 (107,'27/10/2023','2023-10-10 11:45:00','2023-10-10 12:30:00',4,4,1,NULL,8,0,''),
 (108,'27/10/2023','2023-10-10 10:45:00','2023-10-10 11:30:00',6,3,2,NULL,4,0,''),
 (109,'27/10/2023','2023-10-10 15:45:00','2023-10-10 16:30:00',2,2,3,NULL,6,0,''),
 (110,'27/10/2023','2023-10-10 15:45:00','2023-10-10 16:30:00',5,2,4,NULL,4,0,''),
 (111,'27/10/2023','2023-10-10 11:45:00','2023-10-10 13:30:00',1,3,4,NULL,5,0,''),
 (112,'2023-12-15','2023-10-10 11:45:00','2023-10-10 12:15:00',5,3,1,'null',16,0,''),
 (114,'30/10/2023','2023-10-30 12:45:00','2023-10-30 15:45:00',1,3,5,NULL,4,0,''),
 (116,'30/10/2023','2023-10-31 10:45:00','2023-10-31 11:45:00',3,3,6,NULL,10,1,NULL),
 (117,'14/11/2023','2023-11-14 11:00:00','2023-11-14 11:00:00',11,1,5,NULL,16,0,NULL),
 (118,'2023-11-14','2023-11-12 11:00:00','2023-11-12 11:00:00',11,1,5,NULL,16,NULL,NULL),
 (119,'2023-11-14','2023-11-15 11:00:00','2023-11-15 11:30:00',11,1,5,NULL,16,0,NULL),
 (121,'2023-11-16','2023-11-16 11:11:00','2023-11-16 11:41:00',22,6,1,'null',12,0,NULL),
 (122,'2023-11-15','2023-11-16 12:00:00','2023-11-16 12:30:00',1,2,3,'',5,0,NULL),
 (123,'2023-11-15','2023-11-16 13:00:00','2023-11-16 13:30:00',1,2,3,'',5,0,NULL),
 (124,'2023-11-15','2023-11-16 13:00:00','2023-11-16 13:30:00',1,2,3,'',5,0,NULL),
 (125,'2023-11-15','2023-11-16 15:00:00','2023-11-16 15:30:00',1,2,3,'',5,0,NULL),
 (126,'2023-11-15','2023-11-16 15:00:00','2023-11-16 16:03:00',5,2,5,'',5,0,NULL),
 (127,'2023-11-15','2023-11-16 15:00:00','2023-11-16 12:30:00',5,3,5,'',5,0,NULL),
 (128,NULL,'2023-11-16 11:00:00','2023-11-16 11:00:00',4,4,4,NULL,4,0,NULL),
 (129,'2023-11-15','2023-11-16 15:55:00','2023-11-16 16:18:00',5,2,5,'',5,0,NULL),
 (130,NULL,'2023-11-16 11:00:00','2023-11-17 17:00:00',4,4,4,NULL,4,0,NULL),
 (131,NULL,'2023-11-16 11:00:00','2023-11-17 09:00:00',4,4,4,NULL,4,0,NULL),
 (132,NULL,'2023-11-16 11:11:00','2023-11-16 11:33:00',4,4,4,NULL,4,0,NULL),
 (135,'2023-11-16','2023-11-16 11:00:00','2023-11-16 11:30:00',14,2,1,NULL,12,0,NULL),
 (136,'2023-11-16','2023-11-10 11:00:00','2023-11-10 11:30:00',6,3,1,NULL,12,0,NULL),
 (139,'2023-11-17','2023-11-17 12:34:00','2023-11-17 13:34:00',29,1,1,'',16,0,NULL),
 (140,'2023-11-17','2023-11-17 12:34:00','2023-11-17 13:34:00',30,1,1,'',12,0,NULL),
 (141,'2023-11-17','2023-11-17 12:36:00','2023-11-17 13:36:00',31,1,2,'',12,0,NULL),
 (142,'2023-11-17','2023-11-17 12:55:00','2023-11-17 13:55:00',29,1,4,'null',16,0,NULL),
 (145,'2023-12-15','2023-10-10 15:00:00','2023-10-10 15:30:00',33,2,4,NULL,14,0,NULL),
 (146,'2023-12-16','2023-10-10 08:00:00','2023-10-10 09:00:00',33,1,1,NULL,16,0,NULL),
 (150,'2023-12-18','2023-12-18 15:00:00','2023-12-18 15:30:00',33,2,3,NULL,14,0,NULL),
 (154,'2023-12-18','2023-12-16 11:00:00','2023-12-16 12:00:00',38,4,4,NULL,12,0,NULL),
 (155,'2023-12-24','2023-12-24 15:00:00','2023-12-24 16:00:00',43,1,2,NULL,24,0,NULL),
 (156,'2023-12-24','2023-10-10 14:00:00','2023-10-10 15:00:00',44,1,1,NULL,24,0,NULL),
 (157,'2024-01-02','2024-01-02 11:00:00','2024-01-02 12:00:00',45,1,2,'null',18,0,NULL),
 (158,'2024-01-02','2024-01-02 11:00:00','2024-01-02 11:15:00',33,11,3,NULL,24,0,NULL),
 (159,'2024-01-02','2024-01-03 15:00:00','2024-01-03 15:15:00',46,11,1,NULL,24,0,NULL),
 (160,'2024-02-22','2024-02-22 15:00:00','2024-02-22 15:45:00',47,10,1,'null',18,0,NULL),
 (161,'2024-02-23','2024-03-24 15:00:00','2024-03-24 16:00:00',47,4,2,NULL,24,0,NULL),
 (162,'2024-02-23','2024-04-23 15:00:00','2024-04-23 15:15:00',47,11,3,NULL,12,0,NULL),
 (163,'2024-02-25','2024-02-10 15:00:00','2024-02-10 16:00:00',49,1,1,NULL,18,0,NULL),
 (164,'2024-02-25','2024-02-21 19:00:00','2024-02-21 20:00:00',53,1,1,NULL,18,0,NULL),
 (165,'2024-02-25','2024-02-25 15:45:00','2024-02-25 16:15:00',54,2,2,NULL,23,0,NULL),
 (166,'2024-02-25','2024-02-25 15:00:00','2024-02-25 15:30:00',55,3,2,NULL,23,0,NULL),
 (167,'2024-02-25','2024-02-25 15:00:00','2024-02-25 16:00:00',53,1,1,NULL,18,0,NULL),
 (168,'2024-02-25','2024-02-25 15:00:00','2024-02-25 16:00:00',53,1,1,NULL,18,0,NULL),
 (169,'2024-03-13','2024-03-13 11:11:00','2024-03-13 12:11:00',56,1,2,NULL,28,0,NULL);
/*!40000 ALTER TABLE `tblcalendar` ENABLE KEYS */;


--
-- Definition of table `tblcertificate`
--

DROP TABLE IF EXISTS `tblcertificate`;
CREATE TABLE `tblcertificate` (
  `certificate_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `certificate_code` varchar(20) NOT NULL,
  `certificate_customer_id` int(10) NOT NULL,
  `certificate_created_date` datetime NOT NULL,
  `certificate_driving_license_type` varchar(50) DEFAULT NULL COMMENT 'Loại bằng lái',
  `certificate_created_author_id` int(10) DEFAULT NULL COMMENT 'Người tạo',
  `certificate_last_modified` datetime DEFAULT NULL,
  `certificate_1_1_yesno` tinyint(1) DEFAULT NULL COMMENT 'Có ai trong gia đình ông (bà) mắc một trong các bệnh: truyền nhiễm, tim mạch, đái tháo đường, lao, hen \r\nphế quản, ung thư, động kinh, rối loạn tâm thần, bệnh khác:',
  `certificate_1_1_notes` varchar(200) DEFAULT NULL COMMENT 'Tên bệnh',
  `certificate_1_2_yesno` varchar(50) DEFAULT NULL COMMENT 'Tiểu sử, bệnh sử bản thân',
  `certificate_1_2_notes` varchar(200) DEFAULT NULL COMMENT 'Tên bệnh',
  `certificate_1_3_question_1` varchar(200) DEFAULT NULL COMMENT 'Thuốc đang dùng và liều lượng',
  `certificate_1_3_question_2` varchar(200) DEFAULT NULL COMMENT 'Đang có thai hoặc nuôi con nhỏ dưới 12 tháng hay không',
  `certificate_2_mental_notes` varchar(200) DEFAULT NULL COMMENT 'Nội dung khám',
  `certificate_2_mental_conclusion` tinyint(1) DEFAULT NULL COMMENT 'Kết luận',
  `certificate_2_mental_user_id` int(10) DEFAULT NULL COMMENT 'Bác sĩ khám',
  `certificate_2_nervous_notes` varchar(200) DEFAULT NULL,
  `certificate_2_nervous_conclusion` tinyint(1) DEFAULT NULL,
  `certificate_2_nervous_user_id` int(10) DEFAULT NULL,
  `certificate_2_eyes_each_eye_1` varchar(200) DEFAULT NULL COMMENT 'Thị lực nhìn xa từng mắt CÓ KÍNH',
  `certificate_2_eyes_each_eye_2` varchar(200) DEFAULT NULL COMMENT 'Thị lực nhìn xa từng mắt KHÔNG KÍNH',
  `certificate_2_eyes_both_eye` varchar(200) DEFAULT NULL COMMENT 'Thị lực nhìn xa hai mắt',
  `certificate_2_eyes_visual_fields` varchar(10) DEFAULT NULL COMMENT 'Thị trường ngang, đứng',
  `certificate_2_eyes_color_vision` varchar(20) DEFAULT NULL COMMENT 'Mù màu',
  `certificate_2_eyes_notes` varchar(200) DEFAULT NULL,
  `certificate_2_eyes_conclusion` tinyint(1) DEFAULT NULL,
  `certificate_2_eyes_user_id` int(10) DEFAULT NULL,
  `certificate_2_ears_nose_throat_left` varchar(20) DEFAULT NULL COMMENT 'Thính lực tai trái',
  `certificate_2_ears_nose_throat_right` varchar(20) DEFAULT NULL COMMENT 'Thính lực tai phải',
  `certificate_2_ears_nose_throat_notes` varchar(200) DEFAULT NULL,
  `certificate_2_ears_nose_throat_conclusion` tinyint(1) DEFAULT NULL,
  `certificate_2_ears_nose_throat_user_id` int(10) DEFAULT NULL,
  `certificate_2_circulatory_pulse` varchar(20) DEFAULT NULL COMMENT 'Huyết áp',
  `certificate_2_circulatory_blood_pressure` varchar(20) DEFAULT NULL COMMENT 'Mạch',
  `certificate_2_circulatory_notes` varchar(200) DEFAULT NULL,
  `certificate_2_circulatory_conclusion` tinyint(1) DEFAULT NULL,
  `certificate_2_circulatory_user_id` int(10) DEFAULT NULL,
  `certificate_2_respiratory_notes` varchar(200) DEFAULT NULL COMMENT 'Hô hấp',
  `certificate_2_respiratory_conclusion` tinyint(1) DEFAULT NULL,
  `certificate_2_respiratory_user_id` int(10) DEFAULT NULL,
  `certificate_2_musculoskeletal_notes` varchar(200) DEFAULT NULL COMMENT 'Cơ xương khớp',
  `certificate_2_musculoskeletal_conclusion` tinyint(1) DEFAULT NULL,
  `certificate_2_musculoskeletal_user_id` int(10) DEFAULT NULL,
  `certificate_2_endocrine_notes` varchar(200) DEFAULT NULL COMMENT 'Nội tiết',
  `certificate_2_endocrine_conclusion` tinyint(1) DEFAULT NULL,
  `certificate_2_endocrine_user_id` int(10) DEFAULT NULL,
  `certificate_2_obstetrics_notes` varchar(200) DEFAULT NULL COMMENT 'Thai sản',
  `certificate_2_obstetrics_conclusion` tinyint(1) DEFAULT NULL,
  `certificate_2_obstetrics_user_id` int(10) DEFAULT NULL,
  `certificate_3_1_drug_tests` varchar(20) DEFAULT NULL COMMENT 'Test',
  `certificate_3_1_additional_tests` varchar(200) DEFAULT NULL COMMENT 'Nồng độ cồn',
  `certificate_3_2_results` varchar(200) DEFAULT NULL COMMENT 'Kết quả test',
  `certificate_3_2_conclusion` varchar(200) DEFAULT NULL COMMENT 'Kết luận',
  `certificate_conclusion` tinyint(1) DEFAULT NULL COMMENT 'Kết luận cuối cùng',
  `certificate_author_conclusion` int(10) DEFAULT NULL COMMENT 'Người kết luận',
  `certificate_conclusion_type` tinyint(1) DEFAULT NULL COMMENT 'Loại kết luận',
  `certificate_conclusion_notes` varchar(20) DEFAULT NULL COMMENT 'Ghi chú, ngày hẹn khám  lại',
  `certificate_delete` tinyint(1) DEFAULT NULL,
  `certificate_image` varchar(200) DEFAULT '',
  PRIMARY KEY (`certificate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tblcertificate`
--

/*!40000 ALTER TABLE `tblcertificate` DISABLE KEYS */;
INSERT INTO `tblcertificate` (`certificate_id`,`certificate_code`,`certificate_customer_id`,`certificate_created_date`,`certificate_driving_license_type`,`certificate_created_author_id`,`certificate_last_modified`,`certificate_1_1_yesno`,`certificate_1_1_notes`,`certificate_1_2_yesno`,`certificate_1_2_notes`,`certificate_1_3_question_1`,`certificate_1_3_question_2`,`certificate_2_mental_notes`,`certificate_2_mental_conclusion`,`certificate_2_mental_user_id`,`certificate_2_nervous_notes`,`certificate_2_nervous_conclusion`,`certificate_2_nervous_user_id`,`certificate_2_eyes_each_eye_1`,`certificate_2_eyes_each_eye_2`,`certificate_2_eyes_both_eye`,`certificate_2_eyes_visual_fields`,`certificate_2_eyes_color_vision`,`certificate_2_eyes_notes`,`certificate_2_eyes_conclusion`,`certificate_2_eyes_user_id`,`certificate_2_ears_nose_throat_left`,`certificate_2_ears_nose_throat_right`,`certificate_2_ears_nose_throat_notes`,`certificate_2_ears_nose_throat_conclusion`,`certificate_2_ears_nose_throat_user_id`,`certificate_2_circulatory_pulse`,`certificate_2_circulatory_blood_pressure`,`certificate_2_circulatory_notes`,`certificate_2_circulatory_conclusion`,`certificate_2_circulatory_user_id`,`certificate_2_respiratory_notes`,`certificate_2_respiratory_conclusion`,`certificate_2_respiratory_user_id`,`certificate_2_musculoskeletal_notes`,`certificate_2_musculoskeletal_conclusion`,`certificate_2_musculoskeletal_user_id`,`certificate_2_endocrine_notes`,`certificate_2_endocrine_conclusion`,`certificate_2_endocrine_user_id`,`certificate_2_obstetrics_notes`,`certificate_2_obstetrics_conclusion`,`certificate_2_obstetrics_user_id`,`certificate_3_1_drug_tests`,`certificate_3_1_additional_tests`,`certificate_3_2_results`,`certificate_3_2_conclusion`,`certificate_conclusion`,`certificate_author_conclusion`,`certificate_conclusion_type`,`certificate_conclusion_notes`,`certificate_delete`,`certificate_image`) VALUES 
 (1,'123456',1,'2024-04-12 00:00:00',NULL,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL,0,0,NULL,NULL,NULL,0,0,NULL,0,0,NULL,0,0,NULL,0,0,NULL,0,0,NULL,NULL,NULL,NULL,0,0,0,NULL,0,NULL),
 (2,'123456',1,'2024-04-12 00:00:00',NULL,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL,0,0,NULL,NULL,NULL,0,0,NULL,0,0,NULL,0,0,NULL,0,0,NULL,0,0,NULL,NULL,NULL,NULL,0,0,0,NULL,0,NULL),
 (3,'654321',100,'2024-04-12 00:00:00',NULL,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL,0,0,NULL,NULL,NULL,0,0,NULL,0,0,NULL,0,0,NULL,0,0,NULL,0,0,NULL,NULL,NULL,NULL,0,0,0,NULL,0,NULL),
 (4,'654321',100,'2024-04-12 00:00:00',NULL,0,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL,0,0,NULL,NULL,NULL,0,0,NULL,0,0,NULL,0,0,NULL,0,0,NULL,0,0,NULL,NULL,NULL,NULL,0,0,0,NULL,0,NULL);
/*!40000 ALTER TABLE `tblcertificate` ENABLE KEYS */;


--
-- Definition of table `tblcustomer`
--

DROP TABLE IF EXISTS `tblcustomer`;
CREATE TABLE `tblcustomer` (
  `customer_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `customer_fullname` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `customer_address` text COLLATE utf8_unicode_ci,
  `customer_phone` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `customer_mobile` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `customer_office` text COLLATE utf8_unicode_ci,
  `customer_jobarea` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL,
  `customer_notes` text COLLATE utf8_unicode_ci,
  `customer_email` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `customer_identity_card` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'CMND',
  `customer_username` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `customer_password` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `customer_type_id` smallint(5) unsigned DEFAULT '0',
  `customer_logined` int(10) unsigned DEFAULT NULL COMMENT 'So lan dang nhap',
  `customer_created_date` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Ngay tao tai khoan',
  `customer_modified_date` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Ngay sua chua',
  `customer_isactive` tinyint(1) DEFAULT NULL COMMENT 'Khoa hay khong',
  `customer_code` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tblcustomer`
--

/*!40000 ALTER TABLE `tblcustomer` DISABLE KEYS */;
INSERT INTO `tblcustomer` (`customer_id`,`customer_fullname`,`customer_address`,`customer_phone`,`customer_mobile`,`customer_office`,`customer_jobarea`,`customer_notes`,`customer_email`,`customer_identity_card`,`customer_username`,`customer_password`,`customer_type_id`,`customer_logined`,`customer_created_date`,`customer_modified_date`,`customer_isactive`,`customer_code`) VALUES 
 (1,'Tr&#7847;n Linh Chi','null','0987',NULL,NULL,'null','null','null',NULL,NULL,'123456',0,NULL,NULL,'2024-02-25',1,NULL),
 (2,'Nguy&#7877;n Th&#7883; Mai','null','0987369512',NULL,NULL,'null','null','null',NULL,NULL,'e10adc3949ba59abbe56e057f20f883e',0,NULL,NULL,'2024-02-23',1,NULL),
 (3,'Nguy&#7877;n &#272;&igrave;nh D&#361;ng','null','0934669512',NULL,NULL,'null','null','null',NULL,NULL,'e10adc3949ba59abbe56e057f20f883e',0,NULL,NULL,'2024-02-23',1,NULL),
 (4,'Nguy&#7877;n Xu&acirc;n Ho&agrave;ng','null','0934682456',NULL,NULL,'null','null','null',NULL,NULL,'e10adc3949ba59abbe56e057f20f883e',0,NULL,NULL,'2024-02-23',1,NULL),
 (5,'L&ecirc; &#272;&igrave;nh T&agrave;i',NULL,'0931352512',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'e10adc3949ba59abbe56e057f20f883e',0,NULL,NULL,NULL,1,NULL),
 (6,'&#272;&agrave;o Qu&#7923;nh Chi','null','0987682456',NULL,NULL,'null','null','null',NULL,NULL,'e10adc3949ba59abbe56e057f20f883e',0,NULL,NULL,'2024-02-23',1,NULL),
 (7,'L&ecirc; Mai Anh','updating','0339669512',NULL,NULL,'updating','updating','abc@gmail.com',NULL,NULL,'e10adc3949ba59abbe56e057f20f883e',0,NULL,NULL,'2024-02-23',1,NULL),
 (8,'Nguy&#7877;n Xu&acirc;n Kh&#7843;i','null','0365682456',NULL,NULL,'null','null','null',NULL,NULL,'e10adc3949ba59abbe56e057f20f883e',0,NULL,NULL,'2024-02-23',1,NULL),
 (9,'L&ecirc; &#272;&igrave;nh T&agrave;i','null','0931352512',NULL,NULL,'null','null','null',NULL,NULL,'e10adc3949ba59abbe56e057f20f883e',0,NULL,NULL,'2024-02-23',1,NULL),
 (10,'&#272;&agrave;o Qu&#7923;nh Chi','null','0987682456',NULL,NULL,'null','null','null',NULL,NULL,'e10adc3949ba59abbe56e057f20f883e',0,NULL,NULL,'2024-02-23',1,NULL),
 (11,'Lê Mai Anh',NULL,'0339669512',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'e10adc3949ba59abbe56e057f20f883e',0,NULL,NULL,NULL,1,NULL),
 (12,'Nguyễn Xuân Khải',NULL,'0365682456',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'e10adc3949ba59abbe56e057f20f883e',0,NULL,NULL,NULL,1,NULL),
 (14,'Nguy&#7877;n V&#259;n Linh','null','0321564987',NULL,NULL,'null','null','null',NULL,NULL,'123456',0,NULL,NULL,NULL,1,NULL),
 (15,'&#272;&igrave;nh Minh','null','0321564988',NULL,NULL,'null','null','null',NULL,NULL,'123456',0,NULL,NULL,NULL,1,NULL),
 (16,'X&oacute;a &#272;&igrave;nh Minh','null','0321564985',NULL,NULL,'null','null','null',NULL,NULL,'123456',0,NULL,NULL,'2023-11-15',0,NULL),
 (17,'&#272;&#7895; Ti&#7871;n Anh','','01236549',NULL,NULL,NULL,'',NULL,NULL,'','123456',0,NULL,NULL,'2023-11-15',1,NULL),
 (18,'Nguy&#7877;n Lan Anh','','012365499',NULL,NULL,NULL,'',NULL,NULL,'','123456',0,NULL,NULL,NULL,1,NULL),
 (19,'&#272;&#7895; Ti&#7871;n Anh','','014111111',NULL,NULL,NULL,'',NULL,NULL,'','123456',0,NULL,NULL,NULL,1,NULL),
 (20,'Sua',NULL,'1111111111',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'123456',0,NULL,NULL,NULL,1,NULL),
 (21,'QQQQQQQQQ',NULL,'011111111111',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'123456',0,NULL,NULL,NULL,1,NULL),
 (22,'Nghieem Kieemr Thuw',NULL,'11111111111',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'123456',0,NULL,NULL,NULL,1,NULL),
 (23,'Th?',NULL,'22222222',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'123456',0,NULL,NULL,NULL,1,NULL),
 (24,'AAAAA',NULL,'222222',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'123456',0,NULL,NULL,NULL,1,NULL),
 (25,'Th&#7917;','null','102',NULL,NULL,'null','null','null',NULL,NULL,'123456',0,NULL,NULL,'2024-02-23',1,NULL),
 (26,'AAAAAAAAAAAA',NULL,'0987682456	',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'123456',0,NULL,NULL,NULL,1,NULL),
 (27,'AAAAAAAA','A108 Adam Street, New York, NY 535022','123456789',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'123456',0,NULL,NULL,NULL,1,NULL),
 (28,'Nghi&ecirc;m &#272;&igrave;nh Qu&yacute;','A108 Adam Street, New York, NY 535022','999999999999',NULL,NULL,'A108 Adam Street, New York, NY 535022','null','ad9999@gmail.com',NULL,NULL,'123456',0,NULL,NULL,NULL,1,NULL),
 (29,'Nghi&ecirc;m &#272;&igrave;nh Qu&yacute;','A108 Adam Street, New York, NY 535022','0987059546',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'123456',0,NULL,NULL,NULL,1,NULL),
 (30,'Nghi&ecirc;m &#272;&igrave;nh Qu&yacute;','A108 Adam Street, New York, NY 535022','0987059542',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'123456',0,NULL,NULL,NULL,1,NULL),
 (31,'Nghi&ecirc;m &#272;&igrave;nh Qu&yacute;','A108 Adam Street, New York, NY 535022','0987059548',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'123456',0,NULL,NULL,'2023-12-06',0,NULL),
 (32,'M&#7851;n Anh Ph&aacute;p','null','0987025946',NULL,NULL,'null','null','null',NULL,NULL,'123456',0,NULL,NULL,'2024-02-23',1,NULL),
 (33,'M&#7851;n Anh Ph&aacute;p',NULL,'0987664552',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'123456',0,NULL,NULL,NULL,1,NULL),
 (34,'&#272;&igrave;nh &#272;&igrave;nh','B&#7855;c Ninh','098709852',NULL,NULL,NULL,'K&#7871; to&aacute;n',NULL,NULL,'testtesst','123456',0,NULL,NULL,NULL,1,NULL),
 (35,'Linh Linh','B&#7855;c Ninh','01236549101',NULL,NULL,NULL,'K&#7871; to&aacute;n',NULL,NULL,'cuong','123456',0,NULL,NULL,NULL,1,NULL),
 (36,'Lan Lan','B&#7855;c Ninh','0123654910',NULL,NULL,NULL,'K&#7871; to&aacute;n',NULL,NULL,'tienanh','123456',0,NULL,NULL,NULL,1,NULL),
 (37,'&#272;&igrave;nh &#272;&igrave;nh',NULL,'0987561334',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'123456',0,NULL,NULL,NULL,1,NULL),
 (38,'Lan Lan',NULL,'0321654978523',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'123456',0,NULL,NULL,NULL,1,NULL),
 (39,'Linh Linh',NULL,'03213',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'123456',0,NULL,NULL,NULL,1,NULL),
 (40,'V&#259;n Linh',NULL,'1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'123456',0,NULL,NULL,NULL,1,NULL),
 (41,'&#272;&#7895; Ti&#7871;n Anh','B&#7855;c Ninh','12313',NULL,NULL,'ABC','K&#7871; to&aacute;n','e@gmail.com',NULL,NULL,'123456',0,NULL,NULL,NULL,1,NULL),
 (42,'&#272;&#7895; Ti&#7871;n Anh','B&#7855;c Ninh','012365494',NULL,NULL,NULL,'kh&aacute;m',NULL,NULL,'tienanh','123456',0,NULL,NULL,NULL,1,NULL),
 (43,'Linh Long',NULL,'0987236541',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'123456',0,NULL,NULL,'2023-12-24',0,NULL),
 (44,'Th&#7883; Linh',NULL,'098756234',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'123456',0,NULL,NULL,NULL,1,NULL),
 (45,'Linh Lan',NULL,'02654328',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'123456',0,NULL,NULL,NULL,1,NULL),
 (46,'Anh Anh',NULL,'09875623',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'123456',0,NULL,NULL,NULL,1,NULL),
 (47,'Nguyen Van Linh ',NULL,'0654963218',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'123456',0,NULL,NULL,NULL,1,NULL),
 (48,'Tr&#7847;n Duy To&agrave;n','abc','0824948514',NULL,NULL,'nulldsafd','kjhdsf','null',NULL,NULL,'123456',0,NULL,'2024-02-25','2024-02-25',1,NULL),
 (49,'Ph&#7841;m Th&aacute;i H&#432;ng',NULL,'412',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'123456',0,NULL,NULL,NULL,1,NULL),
 (50,'olj',NULL,'l',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'123456',0,NULL,NULL,NULL,1,NULL),
 (51,'Barca',NULL,'123',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'123456',0,NULL,NULL,NULL,1,NULL),
 (52,'Tr&#7847;n Duy To&agrave;n','23sds','1230328947',NULL,NULL,NULL,'ko',NULL,NULL,'duytoan','123456',0,NULL,'2024-02-25',NULL,1,NULL),
 (53,'Nguy&#7877;n V&#259;n kh&#7843;i',NULL,'0362863252',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'123456',0,NULL,NULL,NULL,1,NULL),
 (54,'Nguyen Nguyen',NULL,'0987562324',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'123456',0,NULL,NULL,NULL,1,NULL),
 (55,'Quy Quy',NULL,'098756321',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'123456',0,NULL,NULL,NULL,1,NULL),
 (56,'testapi',NULL,'123456',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'123456',0,NULL,NULL,NULL,1,NULL);
/*!40000 ALTER TABLE `tblcustomer` ENABLE KEYS */;


--
-- Definition of table `tbllog`
--

DROP TABLE IF EXISTS `tbllog`;
CREATE TABLE `tbllog` (
  `log_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `log_name` text NOT NULL,
  `log_user_permission` smallint(5) unsigned DEFAULT NULL,
  `log_date` varchar(45) DEFAULT NULL,
  `log_user_name` varchar(200) DEFAULT NULL,
  `log_action` smallint(5) unsigned DEFAULT NULL COMMENT '1. Thêm mới\r\n2. Sửa\r\n3. Xóa',
  `log_position` smallint(5) unsigned DEFAULT NULL COMMENT '1. Người dùng\r\n2. Lịch\r\n3. Dịch vụ\r\n4. Khách hàng',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4528 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbllog`
--

/*!40000 ALTER TABLE `tbllog` DISABLE KEYS */;
INSERT INTO `tbllog` (`log_id`,`log_name`,`log_user_permission`,`log_date`,`log_user_name`,`log_action`,`log_position`) VALUES 
 (4461,'Lan Lan',3,'2023-12-16 21:10:10','admin',1,1),
 (4462,'Linh Linh',3,'2023-12-17 21:11:38','admin',1,1),
 (4463,'Lan Lan',3,'2023-12-18 21:15:12','admin',1,1),
 (4464,'Nguy&#7877;n Duy V&#361;',3,'2023-12-19 13:49:15','admin',2,1),
 (4465,'Nguy&#7877;n C&ocirc;ng V&#297;nh',3,'2023-12-19 13:53:41','quynd',2,1),
 (4466,'&#272;&#7895; Minh Anh',3,'2023-12-19 13:55:16','quynd',5,1),
 (4467,'',3,'2023-12-19 13:55:39','quynd',4,1),
 (4468,'Nguy&#7877;n C&ocirc;ng V&#297;nh',3,'2023-12-19 13:59:07','quynd',3,1),
 (4469,'Nguy&#7877;n C&ocirc;ng V&#297;nh',3,'2023-12-19 13:59:24','quynd',5,1),
 (4470,'Ng&ocirc; T&eacute;t Tho',3,'2023-12-19 13:59:37','quynd',4,1),
 (4471,'Ki&#7875;m tra r&#259;ng mi&#7879;ng test 2',1,'2023-12-19 14:41:59','abcabc',2,3),
 (4472,'Ki&#7875;m tra r&#259;ng mi&#7879;ng test 2',1,'2023-12-19 14:42:13','abcabc',3,3),
 (4473,'Ki&#7875;m tra r&#259;ng mi&#7879;ng test 2',1,'2023-12-19 14:42:30','abcabc',5,3),
 (4474,'Th&#7917; Th&#7917;',1,'2023-12-19 14:42:41','abcabc',2,4),
 (4475,'Th&#7917; Th&#7917;',3,'2023-12-19 14:54:52','admin',5,4),
 (4477,'Th&#7917; Th&#7917;',3,'2023-12-19 14:55:25','admin',4,4),
 (4478,'&#272;&#7895; Ti&#7871;n Anh',3,'2023-12-24 13:35:37','admin',1,4),
 (4479,'&#272;&#7895; Ti&#7871;n Anh',3,'2023-12-24 20:49:08','admin',2,4),
 (4480,'&#272;&#7895; Ti&#7871;n Anh',3,'2023-12-24 20:49:48','admin',1,4),
 (4481,'Kh&aacute;m r&#259;ng',3,'2023-12-24 21:34:54','admin',2,3),
 (4482,'Linh Long',3,'2023-12-24 22:27:26','admin',6,4),
 (4483,'Nguy&#7877;n Duy V&#361;',3,'2023-12-24 22:28:30','admin',1,1),
 (4484,'Nguy&#7877;n Duy V&#361; Linh',3,'2023-12-24 22:29:09','admin',2,1),
 (4485,'Nguy&#7877;n Duy V&#361; Linh',3,'2023-12-24 22:29:30','admin',3,1),
 (4486,'Nguy&#7877;n Duy V&#361; Linh',3,'2023-12-24 22:29:44','admin',4,1),
 (4487,'Nguy&#7877;n Lan Anh',3,'2023-12-24 22:29:48','admin',5,1),
 (4488,'Tr&#7891;ng r&#259;ng',3,'2024-01-02 23:04:52','admin',1,3),
 (4489,'Th&#7911;y Ti&ecirc;n',3,'2024-01-03 11:26:27','admin',1,1),
 (4490,'Kh&aacute;m r&#259;ng',3,'2024-02-22 20:23:28','admin',2,3),
 (4491,'Kh&aacute;m r&#259;ng',3,'2024-02-22 20:25:42','admin',2,3),
 (4492,'T&#432; v&#7845;n r&#259;ng mi&#7879;ng',3,'2024-02-22 20:26:21','admin',2,3),
 (4493,'Tr&#7891;ng r&#259;ng',3,'2024-02-22 20:27:44','admin',2,3),
 (4494,'Ki&#7875;m tra r&#259;ng mi&#7879;ng test 2',3,'2024-02-22 20:27:48','admin',2,3),
 (4495,'Ki&#7875;m tra r&#259;ng mi&#7879;ng test',3,'2024-02-22 20:27:51','admin',2,3),
 (4496,'L&ecirc; Mai Anh',3,'2024-02-23 09:55:44','admin',2,4),
 (4497,'L&ecirc; Mai Anh',3,'2024-02-23 09:58:32','admin',2,4),
 (4498,'M&#7851;n Anh Ph&aacute;p',3,'2024-02-23 10:00:08','admin',2,4),
 (4499,'Th&#7917;',3,'2024-02-23 10:00:20','admin',2,4),
 (4500,'Tr&#7847;n Linh Chi',3,'2024-02-23 10:00:46','admin',2,4),
 (4501,'Nguy&#7877;n Th&#7883; Mai',3,'2024-02-23 10:00:51','admin',2,4),
 (4502,'Nguy&#7877;n &#272;&igrave;nh D&#361;ng',3,'2024-02-23 10:00:58','admin',2,4),
 (4503,'Nguy&#7877;n Xu&acirc;n Ho&agrave;ng',3,'2024-02-23 10:01:05','admin',2,4),
 (4504,'Nguy&#7877;n Xu&acirc;n Kh&#7843;i',3,'2024-02-23 10:01:51','admin',2,4),
 (4505,'&#272;&agrave;o Qu&#7923;nh Chi',3,'2024-02-23 10:02:02','admin',2,4),
 (4506,'L&ecirc; &#272;&igrave;nh T&agrave;i',3,'2024-02-23 10:02:11','admin',2,4),
 (4507,'Th&#7911;y Ti&ecirc;n',3,'2024-02-25 08:05:55','admin',2,1),
 (4508,'Tr&#7847;n Duy To&agrave;n',3,'2024-02-25 08:12:19','admin',1,4),
 (4509,'Tr&#7847;n Duy To&agrave;n',3,'2024-02-25 08:12:50','admin',2,4),
 (4510,'Tr&#7847;n Linh Chi',3,'2024-02-25 08:18:16','admin',2,4),
 (4511,'Tr&#7847;n Duy To&agrave;n',3,'2024-02-25 08:26:11','admin',1,1),
 (4512,'to&agrave;n',3,'2024-02-25 08:27:59','admin',1,1),
 (4513,'Tr&#7847;n Duy To&agrave;n',3,'2024-02-25 08:31:57','admin',1,4),
 (4514,'Tr&#7847;n Duy To&agrave;n',3,'2024-02-25 08:34:33','admin',1,1),
 (4515,'Tr&#7847;n Duy To&agrave;n',3,'2024-02-25 08:45:49','admin',2,1),
 (4516,'Tr&#7847;n Duy To&agrave;n',3,'2024-02-25 08:46:04','admin / Admin',3,1),
 (4517,'Tr&#7847;n Duy To&agrave;n',3,'2024-02-25 08:46:16','admin / Admin',5,1),
 (4518,'nguy&#7877;n v&#259;n kh&#7843;i@@@@@',3,'2024-02-25 08:55:08','admin',1,1),
 (4519,'abc',3,'2024-02-25 09:12:24','admin',1,3),
 (4520,'abc',3,'2024-02-25 09:18:36','admin',2,3),
 (4521,'nguy&#7877;n v&#259;n kh&#7843;i@@@@@',3,'2024-02-25 09:51:20','admin / Admin',3,1),
 (4522,'nguy&#7877;n v&#259;n kh&#7843;i@@@@@',3,'2024-02-25 09:51:30','admin / Admin',5,1),
 (4523,'nguy&#7877;n v&#259;n kh&#7843;i',3,'2024-02-25 09:51:55','admin',2,1),
 (4524,'abc',3,'2024-03-19 09:49:51','admin / Admin',3,3),
 (4525,'Ki&#7875;m tra r&#259;ng mi&#7879;ng test 2',3,'2024-03-19 09:50:04','admin / Admin',3,3),
 (4526,'Ki&#7875;m tra r&#259;ng mi&#7879;ng test',3,'2024-03-19 09:50:13','admin / Admin',3,3),
 (4527,'tesst222',3,'2024-03-19 09:50:17','admin / Admin',3,3);
/*!40000 ALTER TABLE `tbllog` ENABLE KEYS */;


--
-- Definition of table `tblroom`
--

DROP TABLE IF EXISTS `tblroom`;
CREATE TABLE `tblroom` (
  `room_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `room_name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `room_manager_id` int(10) unsigned DEFAULT NULL,
  `room_create_date` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `room_last_modified` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `room_delete` tinyint(1) unsigned DEFAULT NULL,
  `room_notes` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `tblroom`
--

/*!40000 ALTER TABLE `tblroom` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblroom` ENABLE KEYS */;


--
-- Definition of table `tblservice`
--

DROP TABLE IF EXISTS `tblservice`;
CREATE TABLE `tblservice` (
  `service_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `service_name` varchar(200) CHARACTER SET latin1 NOT NULL,
  `service_price` float NOT NULL,
  `service_discount_price` float DEFAULT NULL,
  `service_created_date` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `service_delete` tinyint(1) unsigned DEFAULT NULL,
  `service_manager_id` int(10) unsigned DEFAULT NULL,
  `service_enable` tinyint(1) unsigned DEFAULT NULL,
  `service_modified_date` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `service_expected_time` varchar(45) CHARACTER SET latin1 NOT NULL,
  `service_created_author_id` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `service_notes` varchar(200) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`service_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tblservice`
--

/*!40000 ALTER TABLE `tblservice` DISABLE KEYS */;
INSERT INTO `tblservice` (`service_id`,`service_name`,`service_price`,`service_discount_price`,`service_created_date`,`service_delete`,`service_manager_id`,`service_enable`,`service_modified_date`,`service_expected_time`,`service_created_author_id`,`service_notes`) VALUES 
 (1,'Kh&aacute;m r&#259;ng',100000,10000,NULL,0,1,NULL,'2024-02-22','01:00','2','Kh&aacute;m r&#259;ng'),
 (2,'Nh&#7893; r&#259;ng',50000,0,NULL,0,2,NULL,'2023-12-18','00:30','2','null'),
 (3,'L&#7845;y cao r&#259;ng',50000,0,NULL,0,3,NULL,NULL,'00:30','2','Cam k&#7871;t kh&ocirc;ng &#273;au'),
 (4,'T&#432; v&#7845;n r&#259;ng mi&#7879;ng',100000,0,NULL,0,4,NULL,'2024-02-22','01:00','2','updating'),
 (6,'tesst222',100000,0,NULL,1,6,NULL,'2024-03-19','00:30','2','null'),
 (7,'them',100000,0,NULL,1,0,NULL,'2023-11-15','00:30','0',NULL),
 (8,'Ki&#7875;m tra r&#259;ng mi&#7879;ng',30000,2,NULL,0,8,NULL,'2023-12-18','00:15','2',''),
 (9,'Ki&#7875;m tra r&#259;ng mi&#7879;ng test',30000,0,NULL,1,9,NULL,'2024-03-19','00:30','2',''),
 (10,'Ki&#7875;m tra r&#259;ng mi&#7879;ng test 2',50000,0,NULL,1,10,NULL,'2024-03-19','00:45','2',''),
 (11,'Tr&#7891;ng r&#259;ng',30000,0,NULL,0,11,NULL,'2024-02-22','00:15','2',''),
 (12,'abc',180000,189000,NULL,1,12,NULL,'2024-03-19','10:05','2','');
/*!40000 ALTER TABLE `tblservice` ENABLE KEYS */;


--
-- Definition of table `tbluser`
--

DROP TABLE IF EXISTS `tbluser`;
CREATE TABLE `tbluser` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) NOT NULL,
  `user_pass` varchar(100) NOT NULL,
  `user_fullname` varchar(100) NOT NULL,
  `user_birthday` varchar(45) DEFAULT NULL,
  `user_mobilephone` varchar(45) DEFAULT NULL,
  `user_homephone` varchar(45) DEFAULT NULL,
  `user_officephone` varchar(45) DEFAULT NULL,
  `user_email` varchar(100) NOT NULL,
  `user_address` varchar(500) DEFAULT NULL,
  `user_jobarea` varchar(500) DEFAULT NULL,
  `user_job` varchar(500) DEFAULT NULL,
  `user_position` varchar(500) DEFAULT NULL,
  `user_applyyear` varchar(200) DEFAULT NULL COMMENT 'Nam lam viec, hoac la so nam lam viec',
  `user_permission` smallint(5) unsigned DEFAULT NULL,
  `user_notes` text,
  `user_roles` varchar(200) DEFAULT NULL,
  `user_logined` smallint(5) unsigned DEFAULT '0',
  `user_created_date` varchar(45) NOT NULL COMMENT 'Ngay tao',
  `user_last_modified` varchar(45) DEFAULT NULL COMMENT 'Ngay sua',
  `user_last_logined` varchar(45) DEFAULT NULL COMMENT 'Ngay dang nhap',
  `user_parent_id` int(11) NOT NULL COMMENT 'Nguoi tao ra tai khoan',
  `user_actions` tinyint(3) unsigned DEFAULT '0' COMMENT 'Duoc thuc hien nhung cong viec gi (add, edit, del, comment)',
  `user_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Nếu deleted =1 là tài khoản được đánh dấu xóa - xóa tương đối. Sẽ ko xuất hiện ở danh sách bình thường',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbluser`
--

/*!40000 ALTER TABLE `tbluser` DISABLE KEYS */;
INSERT INTO `tbluser` (`user_id`,`user_name`,`user_pass`,`user_fullname`,`user_birthday`,`user_mobilephone`,`user_homephone`,`user_officephone`,`user_email`,`user_address`,`user_jobarea`,`user_job`,`user_position`,`user_applyyear`,`user_permission`,`user_notes`,`user_roles`,`user_logined`,`user_created_date`,`user_last_modified`,`user_last_logined`,`user_parent_id`,`user_actions`,`user_deleted`) VALUES 
 (1,'super','e10adc3949ba59abbe56e057f20f883e','Super',NULL,NULL,NULL,NULL,'super@gmail.com',NULL,NULL,NULL,NULL,NULL,4,NULL,NULL,0,'',NULL,NULL,0,0,0),
 (2,'admin','e10adc3949ba59abbe56e057f20f883e','Admin',NULL,NULL,NULL,NULL,'admin@gmail.com',NULL,NULL,NULL,NULL,NULL,3,NULL,NULL,923,'',NULL,NULL,1,0,0),
 (3,'quynd','e10adc3949ba59abbe56e057f20f883e','Nghiêm Đình Quý',NULL,'0987021564',NULL,NULL,'quynd@gmail.com',NULL,NULL,NULL,NULL,NULL,3,NULL,NULL,114,'',NULL,NULL,2,0,0),
 (4,'linhnv','e10adc3949ba59abbe56e057f20f883e','Nguyễn Văn Linh',NULL,'0963258147',NULL,NULL,'linhnv@gmail.com',NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,120,'',NULL,NULL,0,0,0),
 (5,'longmb','e10adc3949ba59abbe56e057f20f883e','Mẫn Bá Long',NULL,'0951234687',NULL,NULL,'longmb@gmail.com',NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,200,'',NULL,NULL,2,0,0),
 (6,'tienkiki','e10adc3949ba59abbe56e057f20f883e','Lê Đức Tiến',NULL,NULL,NULL,NULL,'tienkiki@gmail.com',NULL,NULL,NULL,NULL,NULL,3,NULL,NULL,0,'',NULL,NULL,2,0,0),
 (9,'tienanh','e10adc3949ba59abbe56e057f20f883e','&#272;&#7895; Ti&#7871;n Anh',NULL,'0951236478',NULL,NULL,'anhdt@gmail.com','B?c Ninh',NULL,NULL,NULL,'0',2,NULL,NULL,0,'24/10/2023','2023-12-18',NULL,0,0,1),
 (10,'lananh','e10adc3949ba59abbe56e057f20f883e','Nguy&#7877;n Lan Anh',NULL,'0987664552',NULL,NULL,'lanhn@gmai.com','Hà N?i',NULL,NULL,NULL,'0',2,NULL,NULL,0,'24/10/2023','2023-12-24',NULL,0,0,0),
 (12,'lanlan','e10adc3949ba59abbe56e057f20f883e','Nguy&#7877;n Lan Anh',NULL,'0987664552',NULL,NULL,'lanhl@gmai.com',NULL,NULL,NULL,NULL,'0',1,NULL,NULL,0,'24/10/2023','2023-11-14',NULL,0,0,0),
 (13,'anhanh','e10adc3949ba59abbe56e057f20f883e','Nguy&#7877;n &#272;&#7895; Ti&#7871;n Th&#7917;',NULL,'0987664552',NULL,NULL,'tanhasx@gmail.com',NULL,NULL,NULL,NULL,'0',1,NULL,NULL,0,'24/10/2023','2023-12-06',NULL,0,0,0),
 (14,'dota','e10adc3949ba59abbe56e057f20f883e','&#272;&#7895; Ti&#7871;n Anh',NULL,'0987664552',NULL,NULL,'tanhacs@gmail.com',NULL,NULL,NULL,NULL,'0',1,NULL,NULL,1,'25/10/2023',NULL,NULL,0,0,0),
 (15,'abcacb','e10adc3949ba59abbe56e057f20f883e','Nguy&#7877;n &#272;&#7895; Ti&#7871;n Th&#7917;',NULL,'0987664552',NULL,NULL,'lanha@gmai.com',NULL,NULL,NULL,NULL,'0',1,NULL,NULL,0,'25/10/2023',NULL,NULL,0,0,0),
 (16,'admin123','e10adc3949ba59abbe56e057f20f883e','Nguy&#7877;n &#272;&#7895; Ti&#7871;n S&#7917;a',NULL,'0987664552',NULL,NULL,'tanh@gmail.com','B&#7855;c Ninh',NULL,NULL,NULL,'0',2,NULL,NULL,0,'2023-11-13','2023-12-18',NULL,0,0,0),
 (17,'cuong','e10adc3949ba59abbe56e057f20f883e','B&ugrave;i &#272;&igrave;nh C&#432;&#7901;ng',NULL,'99999999',NULL,NULL,'cuong123@gmail.com','H&#7843;i Ph&ograve;ng',NULL,NULL,NULL,'0',2,NULL,NULL,0,'2023-12-16','2023-12-18',NULL,0,0,0),
 (18,'admincacsa','e10adc3949ba59abbe56e057f20f883e','&#272;&#7895; Ti&#7871;n Anh',NULL,'999999999',NULL,NULL,'tanh@gmail.com','null',NULL,NULL,NULL,'0',1,NULL,NULL,0,'2023-12-16','2023-12-18',NULL,0,0,0),
 (19,'linhtest','e10adc3949ba59abbe56e057f20f883e','V&#259;n Linh',NULL,'098702641',NULL,NULL,'linhlv@gmail.com',NULL,NULL,NULL,NULL,'0',2,NULL,NULL,0,'2023-12-18',NULL,NULL,0,0,0),
 (20,'linhvl','e10adc3949ba59abbe56e057f20f883e','Nguy&#7877;n V&#259;n Linh',NULL,'0987259684',NULL,NULL,'linhlinh@gmail.com','B&#7855;c Ninh',NULL,NULL,NULL,'0',2,NULL,NULL,0,'2023-12-18','2023-12-19',NULL,0,0,0),
 (21,'llan','e10adc3949ba59abbe56e057f20f883e','Lan Lan',NULL,'0987026345',NULL,NULL,'lanlan@gmai.com','B&#7855;c C&#7841;n',NULL,NULL,NULL,'0',2,NULL,NULL,0,'2023-12-18','2023-12-18',NULL,0,0,0),
 (22,'linhlan','e10adc3949ba59abbe56e057f20f883e','Linh Linh',NULL,'0987012354',NULL,NULL,'linhlinh@gmail.com',NULL,NULL,NULL,NULL,'0',2,NULL,NULL,0,'2023-12-18',NULL,NULL,0,0,0),
 (23,'abcxyz','e10adc3949ba59abbe56e057f20f883e','Nguy&#7877;n Duy V&#361;',NULL,'0987026543',NULL,NULL,'vund@gmail.com','B&#7855;c Ninh',NULL,NULL,NULL,'0',1,NULL,NULL,0,'2023-12-19','2023-12-19',NULL,0,0,0),
 (24,'abcabc','e10adc3949ba59abbe56e057f20f883e','Nguy&#7877;n C&ocirc;ng V&#297;nh',NULL,'12345678',NULL,NULL,'vinhnc@gmail.com','B&#7855;c Ninh',NULL,NULL,NULL,'0',1,NULL,NULL,2,'2023-12-19','2023-12-19',NULL,0,0,0),
 (25,'tientien','e10adc3949ba59abbe56e057f20f883e','Th&#7911;y Ti&ecirc;n',NULL,'098726531',NULL,NULL,'tientien@gmail.com','null',NULL,NULL,NULL,'0',1,NULL,NULL,0,'2024-01-03','2024-02-25',NULL,0,0,0),
 (26,'duytoan','e10adc3949ba59abbe56e057f20f883e','Tr&#7847;n Duy To&agrave;n',NULL,'12345',NULL,NULL,'toanduytran@gmail.com',NULL,NULL,NULL,NULL,'0',1,NULL,NULL,2,'2024-02-25',NULL,NULL,0,0,0),
 (27,'toann','e10adc3949ba59abbe56e057f20f883e','to&agrave;n',NULL,'1234567',NULL,NULL,'toan@gmail.com',NULL,NULL,NULL,NULL,'0',2,NULL,NULL,1,'2024-02-25',NULL,NULL,0,0,0),
 (28,'duytoan123','e10adc3949ba59abbe56e057f20f883e','Tr&#7847;n Duy To&agrave;n',NULL,'12345678',NULL,NULL,'duytoan@gmail.com','null',NULL,NULL,NULL,'0',1,NULL,NULL,0,'2024-02-25','2024-02-25',NULL,0,0,0),
 (29,'khai','b32d5649e10e84b390cf7669cefcbfe0','nguy&#7877;n v&#259;n kh&#7843;i',NULL,'0362863252',NULL,NULL,'quyet3582@gmail.com','H&agrave; N&#7897;i',NULL,NULL,NULL,'0',1,NULL,NULL,1,'2024-02-25','2024-02-25',NULL,0,0,0);
/*!40000 ALTER TABLE `tbluser` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
