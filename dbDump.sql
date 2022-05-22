/*
SQLyog Community v13.1.7 (64 bit)
MySQL - 8.0.27 : Database - webshop
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`webshop` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `webshop`;

/*Table structure for table `brand` */

DROP TABLE IF EXISTS `brand`;

CREATE TABLE `brand` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb3;

/*Data for the table `brand` */

insert  into `brand`(`id`,`name`) values 
(1,'Razer'),
(2,'Intel'),
(3,'AMD'),
(17,'Nvidia'),
(18,'MSI'),
(19,'novibrand');

/*Table structure for table `discount` */

DROP TABLE IF EXISTS `discount`;

CREATE TABLE `discount` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `discount_percentage` decimal(10,0) NOT NULL,
  `is_used` tinyint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;

/*Data for the table `discount` */

insert  into `discount`(`id`,`code`,`discount_percentage`,`is_used`) values 
(1,'A1A1',10,0),
(2,'A2A2',20,0),
(6,'CODE1',5,1),
(7,'CODE2',5,0);

/*Table structure for table `item` */

DROP TABLE IF EXISTS `item`;

CREATE TABLE `item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `price` decimal(10,0) NOT NULL,
  `quantity` int NOT NULL,
  `brand_id` int NOT NULL,
  PRIMARY KEY (`id`,`brand_id`),
  KEY `fk_item_brand_idx` (`brand_id`),
  CONSTRAINT `fk_item_brand` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb3;

/*Data for the table `item` */

insert  into `item`(`id`,`name`,`description`,`price`,`quantity`,`brand_id`) values 
(1,'Razer Huntsman Elite','Gaming keyboard',200,5,1),
(2,'Razer Black Widow','Mechanical keyboard',400,9,1),
(3,'Intel Core i5','Desktop processor',130,10,2),
(4,'Intel Core i7','Powerful desktop processor',550,24,2),
(5,'AMD Ryzen 5','12-Thread Unlocked Desktop Processor with Wraith Stealth Cooler',350,46,3),
(6,'AMD Ryzen 9','32-Thread Unlocked Desktop Processor',650,14,3),
(12,'Intel Core i9-11900K','12-Thread Unlocked Desktop Processor with Wraith Stealth Cooler',453,32,2),
(13,'Intel Core i3-10100F','Intel® Core i3 3.30 GHz processor uses less power and the hyper-threading architecture delivers high performance for demanding applications at an affordable price',100,28,2),
(14,'GeForce RTX 3090Ti','NVIDIA GeForce RTX 3090 Ti Graphics Card with 1,560/TBD MHz Base/Boost Clock Speed',2350,2,17),
(15,'GeForce RTX 2060 12GB','NVIDIA GeForce RTX 2060 Graphics Card with 1,560/TBD MHz Base/Boost Clock Speed',400,6,17),
(16,'GeForce 770','NVIDIA GeForce RTX 770 Graphics Card',500,2,17),
(17,'Vector GP66','The brand new MSI Vector GP series, as its name implies, is the basic element of dimensional space. Using the “Vector” as a starting point, forming 2nd and 3rd dimension a stereoscopic structure and extending to the unknown existence.',3000,4,18),
(18,'Stealth 15M',' The Stealth 15M is one of the thinnest and lightest gaming laptops. With the latest 12th Gen. Intel Core i7 processor and NVIDIA® GeForce RTX™ 3060 Laptop GPU, it caters to all of your needs.',4550,3,18),
(19,'NewItem','aaaaaa',2400,1,1);

/*Table structure for table `order_has_item` */

DROP TABLE IF EXISTS `order_has_item`;

CREATE TABLE `order_has_item` (
  `order_id` int NOT NULL,
  `item_id` int NOT NULL,
  KEY `fk_order_has_item_item1_idx` (`item_id`),
  KEY `fk_order_has_item_order1_idx` (`order_id`),
  CONSTRAINT `fk_order_has_item_item1` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`),
  CONSTRAINT `fk_order_has_item_order1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `order_has_item` */

insert  into `order_has_item`(`order_id`,`item_id`) values 
(35,1),
(35,2),
(35,14),
(35,16),
(35,12),
(36,1),
(37,6),
(37,5),
(37,17),
(37,16);

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` date NOT NULL,
  `price` decimal(10,0) NOT NULL,
  `price_with_discount` decimal(10,0) NOT NULL,
  `address` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone_number` varchar(55) NOT NULL,
  `discount_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_discount1_idx` (`discount_id`),
  CONSTRAINT `fk_order_discount1` FOREIGN KEY (`discount_id`) REFERENCES `discount` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb3;

/*Data for the table `orders` */

insert  into `orders`(`id`,`created_at`,`price`,`price_with_discount`,`address`,`email`,`phone_number`,`discount_id`) values 
(35,'2022-05-22',3903,3903,'address','mail@mail.com','123123',NULL),
(36,'2022-05-22',200,200,'address','mail@mail.com','123123',NULL),
(37,'2022-05-22',5200,4940,'address','mail@mail.com','123123',6);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
