# ************************************************************
# Sequel Pro SQL dump
# Version 4004
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.6.10)
# Database: Shopping
# Generation Time: 2013-04-25 18:58:31 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table Address
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Address`;

CREATE TABLE `Address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `street1` varchar(255) DEFAULT NULL,
  `street2` varchar(255) DEFAULT NULL,
  `zip` varchar(255) DEFAULT NULL,
  `person_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1ED033D49706D81B` (`person_id`),
  CONSTRAINT `FK1ED033D49706D81B` FOREIGN KEY (`person_id`) REFERENCES `Person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Address` WRITE;
/*!40000 ALTER TABLE `Address` DISABLE KEYS */;

INSERT INTO `Address` (`id`, `city`, `state`, `street1`, `street2`, `zip`, `person_id`)
VALUES
	(1,'Boston','MA','75 St Alphonsus','Apt 2004','02120',1),
	(2,'Boston','MA','75 St Alphonsus','Apt 2004','02120',2),
	(3,'Boston','MA','75 St Alphonsus','Apt 2004','02121',3),
	(4,'Boston','MA','75 St Alphonsus','Apt 2004','02120',4),
	(5,'Boston','MA','columbus','Apt 1','1313',5),
	(6,'New York','NY','75 St Alphonsus','Apt 2004','1313',6);

/*!40000 ALTER TABLE `Address` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Depot
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Depot`;

CREATE TABLE `Depot` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `location` varchar(255) DEFAULT NULL,
  `manufacturer_id` int(11) DEFAULT NULL,
  `person_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3EDDA149706D81B` (`person_id`),
  KEY `FK3EDDA1465491DB` (`manufacturer_id`),
  CONSTRAINT `FK3EDDA1465491DB` FOREIGN KEY (`manufacturer_id`) REFERENCES `Manufacturer` (`id`),
  CONSTRAINT `FK3EDDA149706D81B` FOREIGN KEY (`person_id`) REFERENCES `Person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Depot` WRITE;
/*!40000 ALTER TABLE `Depot` DISABLE KEYS */;

INSERT INTO `Depot` (`id`, `location`, `manufacturer_id`, `person_id`)
VALUES
	(1,'MA',1,5);

/*!40000 ALTER TABLE `Depot` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table DepotOrderDetails
# ------------------------------------------------------------

DROP TABLE IF EXISTS `DepotOrderDetails`;

CREATE TABLE `DepotOrderDetails` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(255) DEFAULT NULL,
  `depot_id` int(11) DEFAULT NULL,
  `manufacturer_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK59E47F48D7B55E99` (`depot_id`),
  KEY `FK59E47F4865491DB` (`manufacturer_id`),
  CONSTRAINT `FK59E47F4865491DB` FOREIGN KEY (`manufacturer_id`) REFERENCES `Manufacturer` (`id`),
  CONSTRAINT `FK59E47F48D7B55E99` FOREIGN KEY (`depot_id`) REFERENCES `Depot` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table DepotOrderItem
# ------------------------------------------------------------

DROP TABLE IF EXISTS `DepotOrderItem`;

CREATE TABLE `DepotOrderItem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `quantity` int(11) NOT NULL,
  `depotOrderDetails_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK16DA7BCDDC523799` (`depotOrderDetails_id`),
  KEY `FK16DA7BCD9FD644F9` (`product_id`),
  CONSTRAINT `FK16DA7BCD9FD644F9` FOREIGN KEY (`product_id`) REFERENCES `Product` (`id`),
  CONSTRAINT `FK16DA7BCDDC523799` FOREIGN KEY (`depotOrderDetails_id`) REFERENCES `DepotOrderDetails` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Manufacturer
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Manufacturer`;

CREATE TABLE `Manufacturer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `companyName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Manufacturer` WRITE;
/*!40000 ALTER TABLE `Manufacturer` DISABLE KEYS */;

INSERT INTO `Manufacturer` (`id`, `companyName`)
VALUES
	(1,'Samsung');

/*!40000 ALTER TABLE `Manufacturer` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table OrderDetails
# ------------------------------------------------------------

DROP TABLE IF EXISTS `OrderDetails`;

CREATE TABLE `OrderDetails` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(255) DEFAULT NULL,
  `totalCost` int(11) NOT NULL,
  `manufacturer_id` int(11) DEFAULT NULL,
  `person_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9BB123349706D81B` (`person_id`),
  KEY `FK9BB1233465491DB` (`manufacturer_id`),
  CONSTRAINT `FK9BB1233465491DB` FOREIGN KEY (`manufacturer_id`) REFERENCES `Manufacturer` (`id`),
  CONSTRAINT `FK9BB123349706D81B` FOREIGN KEY (`person_id`) REFERENCES `Person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table OrderItem
# ------------------------------------------------------------

DROP TABLE IF EXISTS `OrderItem`;

CREATE TABLE `OrderItem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `itemStatus` varchar(255) DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `total` int(11) NOT NULL,
  `orderDetails_id` int(11) DEFAULT NULL,
  `person_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK60163F619706D81B` (`person_id`),
  KEY `FK60163F61C3FD4ADB` (`orderDetails_id`),
  KEY `FK60163F619FD644F9` (`product_id`),
  CONSTRAINT `FK60163F619FD644F9` FOREIGN KEY (`product_id`) REFERENCES `Product` (`id`),
  CONSTRAINT `FK60163F619706D81B` FOREIGN KEY (`person_id`) REFERENCES `Person` (`id`),
  CONSTRAINT `FK60163F61C3FD4ADB` FOREIGN KEY (`orderDetails_id`) REFERENCES `OrderDetails` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Person
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Person`;

CREATE TABLE `Person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contact` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fName` varchar(255) DEFAULT NULL,
  `lName` varchar(255) DEFAULT NULL,
  `manufacturer_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8E48877565491DB` (`manufacturer_id`),
  CONSTRAINT `FK8E48877565491DB` FOREIGN KEY (`manufacturer_id`) REFERENCES `Manufacturer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Person` WRITE;
/*!40000 ALTER TABLE `Person` DISABLE KEYS */;

INSERT INTO `Person` (`id`, `contact`, `email`, `fName`, `lName`, `manufacturer_id`)
VALUES
	(1,'9878766756','t.martin@gmail.com','Thomas','Martin',NULL),
	(2,'9878766756','j.hopkins@smart.com','John','Hopkins',NULL),
	(3,'98287','bothra.m@husky.neu.edu','Mayank','Bothra',NULL),
	(4,'5675638425','bothra.m@husky.neu.edu','Jack','Bauer',1),
	(5,'5675638425','bothra.m@husky.neu.edu','Martin','Cook',1),
	(6,'98287','bothra.m@husky.neu.edu','Jacob','Martin',1);

/*!40000 ALTER TABLE `Person` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Product
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Product`;

CREATE TABLE `Product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `fileName` varchar(255) DEFAULT NULL,
  `price` int(11) NOT NULL,
  `productName` varchar(255) DEFAULT NULL,
  `productType` varchar(255) DEFAULT NULL,
  `manufacturer_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK50C664CF65491DB` (`manufacturer_id`),
  CONSTRAINT `FK50C664CF65491DB` FOREIGN KEY (`manufacturer_id`) REFERENCES `Manufacturer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table StockItem
# ------------------------------------------------------------

DROP TABLE IF EXISTS `StockItem`;

CREATE TABLE `StockItem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `quantity` int(11) NOT NULL,
  `depot_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC3028B29D7B55E99` (`depot_id`),
  KEY `FKC3028B299FD644F9` (`product_id`),
  CONSTRAINT `FKC3028B299FD644F9` FOREIGN KEY (`product_id`) REFERENCES `Product` (`id`),
  CONSTRAINT `FKC3028B29D7B55E99` FOREIGN KEY (`depot_id`) REFERENCES `Depot` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table UserAccount
# ------------------------------------------------------------

DROP TABLE IF EXISTS `UserAccount`;

CREATE TABLE `UserAccount` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `rePassword` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `roleString` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `person_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB3F13C29706D81B` (`person_id`),
  CONSTRAINT `FKB3F13C29706D81B` FOREIGN KEY (`person_id`) REFERENCES `Person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `UserAccount` WRITE;
/*!40000 ALTER TABLE `UserAccount` DISABLE KEYS */;

INSERT INTO `UserAccount` (`id`, `password`, `rePassword`, `role`, `roleString`, `status`, `username`, `person_id`)
VALUES
	(1,'oprocessing','oprocessing','OPM','Order Processing Manager','active','oprocessing',1),
	(2,'mapproval','mapproval','MAM','Approval Manager','active','mapproval',2),
	(3,'mayank','mayank','Customer','Customer','active','mayank',3),
	(4,'samsung','samsung','MA','Manufacturer Admin','active','samsung',4),
	(5,'samdepma','samdepma','DM','Depot Manager','active','samdepma',5),
	(6,'samprm','samprm','PM','Product Manager','active','samprm',6);

/*!40000 ALTER TABLE `UserAccount` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
