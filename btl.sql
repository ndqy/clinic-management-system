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
) ENGINE=InnoDB AUTO_INCREMENT=160 DEFAULT CHARSET=latin1;

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
 (159,'2024-01-02','2024-01-03 15:00:00','2024-01-03 15:15:00',46,11,1,NULL,24,0,NULL);
/*!40000 ALTER TABLE `tblcalendar` ENABLE KEYS */;


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
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tblcustomer`
--

/*!40000 ALTER TABLE `tblcustomer` DISABLE KEYS */;
INSERT INTO `tblcustomer` (`customer_id`,`customer_fullname`,`customer_address`,`customer_phone`,`customer_mobile`,`customer_office`,`customer_jobarea`,`customer_notes`,`customer_email`,`customer_identity_card`,`customer_username`,`customer_password`,`customer_type_id`,`customer_logined`,`customer_created_date`,`customer_modified_date`,`customer_isactive`,`customer_code`) VALUES 
 (1,'Trần Linh Chi',NULL,'0987025966',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'123456',0,NULL,NULL,NULL,1,NULL),
 (2,'Nguyễn Thị Mai',NULL,'0987369512',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'e10adc3949ba59abbe56e057f20f883e',0,NULL,NULL,NULL,1,NULL),
 (3,'Nguyễn Đình Dũng',NULL,'0934669512',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'e10adc3949ba59abbe56e057f20f883e',0,NULL,NULL,NULL,1,NULL),
 (4,'Nguyễn Xuân Hoàng',NULL,'0934682456',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'e10adc3949ba59abbe56e057f20f883e',0,NULL,NULL,NULL,1,NULL),
 (5,'L&ecirc; &#272;&igrave;nh T&agrave;i',NULL,'0931352512',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'e10adc3949ba59abbe56e057f20f883e',0,NULL,NULL,NULL,1,NULL),
 (6,'Đào Quỳnh Chi',NULL,'0987682456',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'e10adc3949ba59abbe56e057f20f883e',0,NULL,NULL,NULL,1,NULL),
 (7,'Lê Mai Anh',NULL,'0339669512',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'e10adc3949ba59abbe56e057f20f883e',0,NULL,NULL,NULL,1,NULL),
 (8,'Nguyễn Xuân Khải',NULL,'0365682456',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'e10adc3949ba59abbe56e057f20f883e',0,NULL,NULL,NULL,1,NULL),
 (9,'Lê Đình Tài',NULL,'0931352512',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'e10adc3949ba59abbe56e057f20f883e',0,NULL,NULL,NULL,1,NULL),
 (10,'Đào Quỳnh Chi',NULL,'0987682456',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'e10adc3949ba59abbe56e057f20f883e',0,NULL,NULL,NULL,1,NULL),
 (11,'Lê Mai Anh',NULL,'0339669512',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'e10adc3949ba59abbe56e057f20f883e',0,NULL,NULL,NULL,NULL,NULL),
 (12,'Nguyễn Xuân Khải',NULL,'0365682456',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'e10adc3949ba59abbe56e057f20f883e',0,NULL,NULL,NULL,NULL,NULL),
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
 (25,'Th?',NULL,'102',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'123456',0,NULL,NULL,NULL,1,NULL),
 (26,'AAAAAAAAAAAA',NULL,'0987682456	',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'123456',0,NULL,NULL,NULL,1,NULL),
 (27,'AAAAAAAA','A108 Adam Street, New York, NY 535022','123456789',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'123456',0,NULL,NULL,NULL,1,NULL),
 (28,'Nghi&ecirc;m &#272;&igrave;nh Qu&yacute;','A108 Adam Street, New York, NY 535022','999999999999',NULL,NULL,'A108 Adam Street, New York, NY 535022','null','ad9999@gmail.com',NULL,NULL,'123456',0,NULL,NULL,NULL,1,NULL),
 (29,'Nghi&ecirc;m &#272;&igrave;nh Qu&yacute;','A108 Adam Street, New York, NY 535022','0987059546',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'123456',0,NULL,NULL,NULL,1,NULL),
 (30,'Nghi&ecirc;m &#272;&igrave;nh Qu&yacute;','A108 Adam Street, New York, NY 535022','0987059542',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'123456',0,NULL,NULL,NULL,1,NULL),
 (31,'Nghi&ecirc;m &#272;&igrave;nh Qu&yacute;','A108 Adam Street, New York, NY 535022','0987059548',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'123456',0,NULL,NULL,'2023-12-06',0,NULL),
 (32,'M?n Anh Pháp',NULL,'0987025946',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'123456',0,NULL,NULL,NULL,1,NULL),
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
 (46,'Anh Anh',NULL,'09875623',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'123456',0,NULL,NULL,NULL,1,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=4490 DEFAULT CHARSET=utf8;

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
 (4489,'Th&#7911;y Ti&ecirc;n',3,'2024-01-03 11:26:27','admin',1,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tblservice`
--

/*!40000 ALTER TABLE `tblservice` DISABLE KEYS */;
INSERT INTO `tblservice` (`service_id`,`service_name`,`service_price`,`service_discount_price`,`service_created_date`,`service_delete`,`service_manager_id`,`service_enable`,`service_modified_date`,`service_expected_time`,`service_created_author_id`,`service_notes`) VALUES 
 (1,'Kh&amp;aacute;m r&amp;#259;ng',100000,100000,NULL,0,1,NULL,'2023-12-24','01:00','2','Kh&amp;aacute;m r&amp;#259;ng'),
 (2,'Nh&#7893; r&#259;ng',50000,0,NULL,0,2,NULL,'2023-12-18','00:30','2','null'),
 (3,'L&#7845;y cao r&#259;ng',50000,0,NULL,0,3,NULL,NULL,'00:30','2','Cam k&#7871;t kh&ocirc;ng &#273;au'),
 (4,'T&amp;#432; v&amp;#7845;n r&amp;#259;ng mi&amp;#7879;ng',100000,0,NULL,0,4,NULL,NULL,'01:00','2','null'),
 (6,'tesst222',100000,0,NULL,0,6,NULL,'2023-12-06','00:30','2','null'),
 (7,'them',100000,0,NULL,1,0,NULL,'2023-11-15','00:30','0',NULL),
 (8,'Ki&#7875;m tra r&#259;ng mi&#7879;ng',30000,2,NULL,0,8,NULL,'2023-12-18','00:15','2',''),
 (9,'Ki&amp;#7875;m tra r&amp;#259;ng mi&amp;#7879;ng test',30000,0,NULL,0,0,NULL,'2023-12-18','00:30','2',''),
 (10,'Ki&amp;#7875;m tra r&amp;#259;ng mi&amp;#7879;ng test 2',50000,0,NULL,0,10,NULL,'2023-12-19','00:45','24',''),
 (11,'Tr&#7891;ng r&#259;ng',30000,0,NULL,0,0,NULL,'2024-01-02','00:15','2','');
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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbluser`
--

/*!40000 ALTER TABLE `tbluser` DISABLE KEYS */;
INSERT INTO `tbluser` (`user_id`,`user_name`,`user_pass`,`user_fullname`,`user_birthday`,`user_mobilephone`,`user_homephone`,`user_officephone`,`user_email`,`user_address`,`user_jobarea`,`user_job`,`user_position`,`user_applyyear`,`user_permission`,`user_notes`,`user_roles`,`user_logined`,`user_created_date`,`user_last_modified`,`user_last_logined`,`user_parent_id`,`user_actions`,`user_deleted`) VALUES 
 (1,'super','e10adc3949ba59abbe56e057f20f883e','Super',NULL,NULL,NULL,NULL,'super@gmail.com',NULL,NULL,NULL,NULL,NULL,4,NULL,NULL,0,'',NULL,NULL,0,0,0),
 (2,'admin','e10adc3949ba59abbe56e057f20f883e','Admin',NULL,NULL,NULL,NULL,'admin@gmail.com',NULL,NULL,NULL,NULL,NULL,3,NULL,NULL,835,'',NULL,NULL,1,0,0),
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
 (25,'tientien','e10adc3949ba59abbe56e057f20f883e','Th&#7911;y Ti&ecirc;n',NULL,'098726531',NULL,NULL,'tientien@gmail.com',NULL,NULL,NULL,NULL,'0',1,NULL,NULL,0,'2024-01-03',NULL,NULL,0,0,0);
/*!40000 ALTER TABLE `tbluser` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
