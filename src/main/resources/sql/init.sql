-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        8.0.22 - MySQL Community Server - GPL
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- 导出 mysql_test 的数据库结构
CREATE DATABASE IF NOT EXISTS `mysql_test` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mysql_test`;

-- 导出  表 mysql_test.t_pds_brand 结构
CREATE TABLE IF NOT EXISTS `t_pds_brand` (
  `id` bigint NOT NULL,
  `name` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `english_name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `image_url` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` timestamp NOT NULL,
  `update_time` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 正在导出表  mysql_test.t_pds_brand 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `t_pds_brand` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_pds_brand` ENABLE KEYS */;

-- 导出  表 mysql_test.t_pds_category 结构
CREATE TABLE IF NOT EXISTS `t_pds_category` (
  `id` int NOT NULL,
  `name` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `parent_id` int NOT NULL DEFAULT '0',
  `level` int NOT NULL DEFAULT '1',
  `path` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `create_time` timestamp NOT NULL,
  `update_time` timestamp NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 正在导出表  mysql_test.t_pds_category 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `t_pds_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_pds_category` ENABLE KEYS */;

-- 导出  表 mysql_test.t_pds_category_specification 结构
CREATE TABLE IF NOT EXISTS `t_pds_category_specification` (
  `id` bigint NOT NULL,
  `category_id` int NOT NULL,
  `specifications` text COLLATE utf8mb4_general_ci NOT NULL,
  `create_time` timestamp NOT NULL,
  `update_time` timestamp NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 正在导出表  mysql_test.t_pds_category_specification 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `t_pds_category_specification` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_pds_category_specification` ENABLE KEYS */;

-- 导出  表 mysql_test.t_pds_sku 结构
CREATE TABLE IF NOT EXISTS `t_pds_sku` (
  `id` bigint NOT NULL,
  `spu_id` bigint NOT NULL,
  `code` varchar(50) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `name` varchar(50) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `images` text COLLATE utf8mb4_general_ci NOT NULL,
  `indexes` varchar(100) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `own_spec` varchar(200) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `create_time` timestamp NOT NULL,
  `update_time` timestamp NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 正在导出表  mysql_test.t_pds_sku 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `t_pds_sku` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_pds_sku` ENABLE KEYS */;

-- 导出  表 mysql_test.t_pds_spu 结构
CREATE TABLE IF NOT EXISTS `t_pds_spu` (
  `id` bigint NOT NULL,
  `name` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `title` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `code` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `category_id` int NOT NULL,
  `brand_id` int NOT NULL,
  `create_time` timestamp NOT NULL,
  `update_time` timestamp NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 正在导出表  mysql_test.t_pds_spu 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `t_pds_spu` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_pds_spu` ENABLE KEYS */;

-- 导出  表 mysql_test.t_pds_spu_detail 结构
CREATE TABLE IF NOT EXISTS `t_pds_spu_detail` (
  `id` bigint NOT NULL,
  `spu_id` bigint NOT NULL,
  `description` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `specifications` varchar(1000) COLLATE utf8mb4_general_ci NOT NULL,
  `spec_template` varchar(1000) COLLATE utf8mb4_general_ci NOT NULL,
  `create_time` timestamp NOT NULL,
  `update_time` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 正在导出表  mysql_test.t_pds_spu_detail 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `t_pds_spu_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_pds_spu_detail` ENABLE KEYS */;

-- 导出  表 mysql_test.t_role 结构
CREATE TABLE IF NOT EXISTS `t_role` (
  `id` bigint NOT NULL,
  `name` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `description` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `is_delete` bit(1) NOT NULL DEFAULT b'0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 正在导出表  mysql_test.t_role 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
INSERT INTO `t_role` (`id`, `name`, `description`, `is_delete`, `create_time`, `update_time`) VALUES
	(1539222917234659332, '管理员', NULL, b'0', '2022-08-17 09:57:24', '2022-08-17 09:57:24');
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;

-- 导出  表 mysql_test.t_role_source 结构
CREATE TABLE IF NOT EXISTS `t_role_source` (
  `id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  `source_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_id_source_id` (`role_id`,`source_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 正在导出表  mysql_test.t_role_source 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `t_role_source` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_role_source` ENABLE KEYS */;

-- 导出  表 mysql_test.t_source 结构
CREATE TABLE IF NOT EXISTS `t_source` (
  `id` bigint NOT NULL,
  `name` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `depth` int NOT NULL DEFAULT '0',
  `path` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `icon` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `url` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `parent_id` bigint NOT NULL,
  `is_delete` bit(1) NOT NULL DEFAULT b'0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 正在导出表  mysql_test.t_source 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `t_source` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_source` ENABLE KEYS */;

-- 导出  表 mysql_test.t_user 结构
CREATE TABLE IF NOT EXISTS `t_user` (
  `id` bigint NOT NULL,
  `username` varchar(50) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `phone_num` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `name` varchar(20) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `age` int NOT NULL DEFAULT '0',
  `description` varchar(200) COLLATE utf8mb4_general_ci DEFAULT '',
  `is_enable` bit(1) NOT NULL DEFAULT b'0',
  `is_delete` bit(1) NOT NULL DEFAULT b'0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 正在导出表  mysql_test.t_user 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` (`id`, `username`, `phone_num`, `password`, `name`, `age`, `description`, `is_enable`, `is_delete`, `create_time`, `update_time`) VALUES
	(1539222917234659330, 'admin', '', '$2a$10$445toAa0XmT90YuKMPeFa.YOKU71sKxDYTUv750rq/IkszRA1i96q', 'admin', 20, '管理员', b'1', b'0', '2022-06-21 20:25:15', '2022-08-17 09:35:00'),
	(1539223381984514050, '', '', '', 'guest', 11, '访客', b'1', b'0', '2022-06-21 20:27:06', '2022-07-07 10:06:23');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;

-- 导出  表 mysql_test.t_user_role 结构
CREATE TABLE IF NOT EXISTS `t_user_role` (
  `id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 正在导出表  mysql_test.t_user_role 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `t_user_role` DISABLE KEYS */;
INSERT INTO `t_user_role` (`id`, `user_id`, `role_id`) VALUES
	(1539222917234659335, 1539222917234659330, 1539222917234659332);
/*!40000 ALTER TABLE `t_user_role` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
