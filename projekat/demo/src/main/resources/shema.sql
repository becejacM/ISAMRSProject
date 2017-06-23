-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema isa_mrs_project
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mrsisa_project` DEFAULT CHARACTER SET utf8 ;
USE `mrsisa_project` ;

-- CREATE TABLE APP_USERS

DROP TABLE IF EXISTS `mrsisa_project`.`app_users` ;

CREATE TABLE IF NOT EXISTS `mrsisa_project`.`app_users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) NOT NULL,
  `login` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `verified` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `broj_poseta` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_4vj92ux8a2eehds1mdvmks473` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- CREATE TABLE SYS MANAGERS

DROP TABLE IF EXISTS `mrsisa_project`.`sys_managers` ;

CREATE TABLE IF NOT EXISTS `mrsisa_project`.`sys_managers` (
  `sm_id` bigint(20) NOT NULL,
  PRIMARY KEY (`sm_id`),
  CONSTRAINT `FK38dg95ja3vrvy6p5xq8pah2f4` FOREIGN KEY (`sm_id`) REFERENCES `mrsisa_project`.`app_users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- CREATE TABLE GUESTS

DROP TABLE IF EXISTS `mrsisa_project`.`guests` ;

CREATE TABLE IF NOT EXISTS `mrsisa_project`.`guests` (
  `g_id` bigint(20) NOT NULL,
  PRIMARY KEY (`g_id`),
  CONSTRAINT `FKmka0eihg1qb5fifjfh0g66knd` FOREIGN KEY (`g_id`) REFERENCES `mrsisa_project`.`app_users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- CREATE TABLE RESTAURANTS

DROP TABLE IF EXISTS `mrsisa_project`.`restaurants` ;

CREATE TABLE IF NOT EXISTS `mrsisa_project`.`restaurants` (
  `rid` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `end_time` int(11) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `info` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `start_time` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `sm_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`rid`),
  KEY `FK7l2gplrfdl1m10wofwlakuabl` (`sm_id`),
  CONSTRAINT `FK7l2gplrfdl1m10wofwlakuabl` FOREIGN KEY (`sm_id`) REFERENCES `mrsisa_project`.`sys_managers` (`sm_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- CREATE TABLE REGIONS

DROP TABLE IF EXISTS `mrsisa_project`.`regions` ;

CREATE TABLE IF NOT EXISTS `mrsisa_project`.`regions` (
  `regid` bigint(20) NOT NULL AUTO_INCREMENT,
  `color` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `region_no` int(11) DEFAULT NULL,
  `rid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`regid`),
  KEY `FKn3nd4r601o8bsmuooy9bquwro` (`rid`),
  CONSTRAINT `FKn3nd4r601o8bsmuooy9bquwro` FOREIGN KEY (`rid`) REFERENCES `mrsisa_project`.`restaurants` (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- CREATE TABLE MANAGERS

DROP TABLE IF EXISTS `mrsisa_project`.`managers` ;

CREATE TABLE IF NOT EXISTS `mrsisa_project`.`managers` (
  `m_id` bigint(20) NOT NULL,
  `rid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`m_id`),
  KEY `FKg5bo1vxr85xc623todb2j2fk8` (`rid`),
  CONSTRAINT `FK6cqn3bc6amjfl55keawuvwnuy` FOREIGN KEY (`m_id`) REFERENCES `mrsisa_project`.`app_users` (`id`),
  CONSTRAINT `FKg5bo1vxr85xc623todb2j2fk8` FOREIGN KEY (`rid`) REFERENCES `mrsisa_project`.`restaurants` (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- CREATE TABLE EMPLOYEES

DROP TABLE IF EXISTS `mrsisa_project`.`employees` ;

CREATE TABLE IF NOT EXISTS `mrsisa_project`.`employees` (
  `birthday` datetime NOT NULL,
  `dress_size` varchar(255) NOT NULL,
  `e_id` bigint(20) NOT NULL,
  `first_time` varchar(255) NOT NULL,
  `shoe_size` varchar(255) NOT NULL,
  `regid` bigint(20) DEFAULT NULL,
  `rid` bigint(20) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`e_id`),
  KEY `FKsrkc3wjctcrijhy1y9r4cnh1n` (`regid`),
  KEY `FK4fl23j80sctsmkb0xmmhw9va7` (`rid`),
  CONSTRAINT `FK4fl23j80sctsmkb0xmmhw9va7` FOREIGN KEY (`rid`) REFERENCES `mrsisa_project`.`restaurants` (`rid`),
  CONSTRAINT `FKmrb21r7qf6g8phiw2niybyx1h` FOREIGN KEY (`e_id`) REFERENCES `mrsisa_project`.`app_users` (`id`),
  CONSTRAINT `FKsrkc3wjctcrijhy1y9r4cnh1n` FOREIGN KEY (`regid`) REFERENCES `mrsisa_project`.`regions` (`regid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- CREATE TABLE BARTENDERS

DROP TABLE IF EXISTS `mrsisa_project`.`bartenders` ;

CREATE TABLE IF NOT EXISTS `mrsisa_project`.`bartenders` (
  `b_id` bigint(20) NOT NULL,
  PRIMARY KEY (`b_id`),
  CONSTRAINT `FKpdc62yqjwddvn0p52ag9kwqti` FOREIGN KEY (`b_id`) REFERENCES `mrsisa_project`.`employees` (`e_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- CREATE TABLE COOKS

DROP TABLE IF EXISTS `mrsisa_project`.`cooks` ;

CREATE TABLE IF NOT EXISTS `mrsisa_project`.`cooks` (
  `c_id` bigint(20) NOT NULL,
  PRIMARY KEY (`c_id`),
  CONSTRAINT `FKutldh5log8mse7uhuyi320ri` FOREIGN KEY (`c_id`) REFERENCES `mrsisa_project`.`employees` (`e_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- CREATE TABLE WAITERS

DROP TABLE IF EXISTS `mrsisa_project`.`waiters` ;

CREATE TABLE IF NOT EXISTS `mrsisa_project`.`waiters` (
  `w_id` bigint(20) NOT NULL,
  PRIMARY KEY (`w_id`),
  CONSTRAINT `FKd278hjf3dthmjtdn1l9f2h74p` FOREIGN KEY (`w_id`) REFERENCES `mrsisa_project`.`employees` (`e_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- CREATE TABLE FRIENDSHIPS

DROP TABLE IF EXISTS `mrsisa_project`.`friendships` ;

CREATE TABLE IF NOT EXISTS `mrsisa_project`.`friendships` (
  `fid` bigint(20) NOT NULL AUTO_INCREMENT,
  `status` varchar(255) DEFAULT NULL,
  `second` bigint(20) DEFAULT NULL,
  `first` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`fid`),
  KEY `FKn9mo6ybdurk52sp73y2yd63i7` (`second`),
  KEY `FK5vbvgyvloh4kw62kieoxt6gnu` (`first`),
  CONSTRAINT `FK5vbvgyvloh4kw62kieoxt6gnu` FOREIGN KEY (`first`) REFERENCES `mrsisa_project`.`app_users` (`id`),
  CONSTRAINT `FKn9mo6ybdurk52sp73y2yd63i7` FOREIGN KEY (`second`) REFERENCES `mrsisa_project`.`app_users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- CREATE TABLE RESTAURANT TABLES

DROP TABLE IF EXISTS `mrsisa_project`.`restaurant_tables` ;

CREATE TABLE IF NOT EXISTS `mrsisa_project`.`restaurant_tables` (
  `tid` bigint(20) NOT NULL AUTO_INCREMENT,
  `deleted` varchar(255) DEFAULT NULL,
  `height` double DEFAULT NULL,
  `num_of_chairs` int(11) DEFAULT NULL,
  `positions` int(11) DEFAULT NULL,
  `rt_table_in_restaurant_no` int(11) DEFAULT NULL,
  `width` double DEFAULT NULL,
  `x` double DEFAULT NULL,
  `y` double DEFAULT NULL,
  `regid` bigint(20) DEFAULT NULL,
  `br_rezervacija` int(11) NOT NULL,
  `version` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`tid`),
  KEY `FK2m0g98cpk750jfgq1q48d98fl` (`regid`),
  CONSTRAINT `FK2m0g98cpk750jfgq1q48d98fl` FOREIGN KEY (`regid`) REFERENCES `mrsisa_project`.`regions` (`regid`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

-- CREATE TABLE CLIENT ORDERS

DROP TABLE IF EXISTS `mrsisa_project`.`client_orders` ;

CREATE TABLE IF NOT EXISTS `mrsisa_project`.`client_orders` (
  `oid` bigint(20) NOT NULL AUTO_INCREMENT,
  `client_id` int(11) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `deadline` datetime DEFAULT NULL,
  `order_number` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `eid` bigint(20) DEFAULT NULL,
  `rsid` bigint(20) DEFAULT NULL,
  `rid` bigint(20) DEFAULT NULL,
  `table_id` bigint(20) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `FKfbbiwsxkd1k9dvwigaxlqjlag` (`eid`),
  KEY `FKmci4v8o6mse76x7faajggvxgy` (`rsid`),
  KEY `FK56cpgmdlo9i1310dpcjynyyr1` (`rid`),
  KEY `FK7b14045ppe015grn80cymhvtx` (`table_id`),
  CONSTRAINT `FK56cpgmdlo9i1310dpcjynyyr1` FOREIGN KEY (`rid`) REFERENCES `mrsisa_project`.`restaurants` (`rid`),
  CONSTRAINT `FK7b14045ppe015grn80cymhvtx` FOREIGN KEY (`table_id`) REFERENCES `mrsisa_project`.`restaurant_tables` (`tid`),
  CONSTRAINT `FKfbbiwsxkd1k9dvwigaxlqjlag` FOREIGN KEY (`eid`) REFERENCES `mrsisa_project`.`employees` (`e_id`),
  CONSTRAINT `FKmci4v8o6mse76x7faajggvxgy` FOREIGN KEY (`rsid`) REFERENCES `mrsisa_project`.`reservations` (`rsid`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;

-- CREATE TABLE MENU ITEMS

DROP TABLE IF EXISTS `mrsisa_project`.`menu_items` ;

CREATE TABLE IF NOT EXISTS `mrsisa_project`.`menu_items` (
  `miid` bigint(20) NOT NULL AUTO_INCREMENT,
  `deleted` bit(1) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `info` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `spec_type` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `rid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`miid`),
  KEY `FKt6nu1vvm0si1hxac7dfe8mu6b` (`rid`),
  CONSTRAINT `FKt6nu1vvm0si1hxac7dfe8mu6b` FOREIGN KEY (`rid`) REFERENCES `mrsisa_project`.`restaurants` (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- CREATE TABLE ORDER ITEMS

DROP TABLE IF EXISTS `mrsisa_project`.`order_items` ;

CREATE TABLE IF NOT EXISTS `mrsisa_project`.`order_items` (
  `oiid` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `restaurant_id` bigint(20) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `oi_table_id` int(11) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `miid` bigint(20) DEFAULT NULL,
  `oid` bigint(20) DEFAULT NULL,
  `menu_item_id` bigint(20) DEFAULT NULL,
  `item_number` int(11) DEFAULT NULL,
  PRIMARY KEY (`oiid`),
  KEY `FKt4wcxsjxaq8k319o4am5i07tk` (`miid`),
  KEY `FK9toiyh4e6knqkgxpwy4v7cp8a` (`oid`),
  KEY `FKdtfg1f49yr5yye2fpl2xid2xo` (`menu_item_id`),
  CONSTRAINT `FK9toiyh4e6knqkgxpwy4v7cp8a` FOREIGN KEY (`oid`) REFERENCES `mrsisa_project`.`client_orders` (`oid`),
  CONSTRAINT `FKt4wcxsjxaq8k319o4am5i07tk` FOREIGN KEY (`miid`) REFERENCES `mrsisa_project`.`menu_items` (`miid`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;

-- CREATE TABLE BILLS

DROP TABLE IF EXISTS `mrsisa_project`.`bills` ;

CREATE TABLE IF NOT EXISTS `mrsisa_project`.`bills` (
  `bid` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `eid` bigint(20) DEFAULT NULL,
  `oid` bigint(20) DEFAULT NULL,
  `wid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`bid`),
  KEY `FKkpqg3jhd4rjqhxa0x6thgj3dc` (`eid`),
  KEY `FKah37hqn9klrhg7un4h2lk7uiq` (`oid`),
  KEY `FKar3fx23knm5b41hruqbnwk7lr` (`wid`),
  CONSTRAINT `FKah37hqn9klrhg7un4h2lk7uiq` FOREIGN KEY (`oid`) REFERENCES `mrsisa_project`.`client_orders` (`oid`),
  CONSTRAINT `FKkpqg3jhd4rjqhxa0x6thgj3dc` FOREIGN KEY (`eid`) REFERENCES `mrsisa_project`.`employees` (`e_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- CREATE TABLE FRIENDINVITATIONS

DROP TABLE IF EXISTS `mrsisa_project`.`friendinvitations` ;

CREATE TABLE IF NOT EXISTS `mrsisa_project`.`friendinvitations` (
  `fid` bigint(20) NOT NULL AUTO_INCREMENT,
  `status` varchar(255) DEFAULT NULL,
  `second` bigint(20) DEFAULT NULL,
  `resid` bigint(20) DEFAULT NULL,
  `first` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`fid`),
  KEY `FKk107rb56ym6g09gonm8r5f4f4` (`second`),
  KEY `FK2ghqonwbdqbv5p6ym65bg9fry` (`resid`),
  KEY `FK52l0lu6kjgyj21o81g58pfp7w` (`first`),
  CONSTRAINT `FK2ghqonwbdqbv5p6ym65bg9fry` FOREIGN KEY (`resid`) REFERENCES `mrsisa_project`.`reservations` (`rsid`),
  CONSTRAINT `FK52l0lu6kjgyj21o81g58pfp7w` FOREIGN KEY (`first`) REFERENCES `mrsisa_project`.`app_users` (`id`),
  CONSTRAINT `FKk107rb56ym6g09gonm8r5f4f4` FOREIGN KEY (`second`) REFERENCES `mrsisa_project`.`app_users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- CREATE TABLE GRADES

DROP TABLE IF EXISTS `mrsisa_project`.`grades` ;

CREATE TABLE IF NOT EXISTS `mrsisa_project`.`grades` (
  `grid` bigint(20) NOT NULL AUTO_INCREMENT,
  `general_grade` int(11) DEFAULT NULL,
  `meal_grade` int(11) DEFAULT NULL,
  `service_grade` int(11) DEFAULT NULL,
  `eid` bigint(20) DEFAULT NULL,
  `oid` bigint(20) DEFAULT NULL,
  `rsid` bigint(20) DEFAULT NULL,
  `rid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`grid`),
  KEY `FKnojibkw4q3aeyw7jc4uou6wsw` (`eid`),
  KEY `FKlpsrpgv5dcss6s8tr6jypmqgx` (`oid`),
  KEY `FKpa8tjycplxiw8cor8vo04rn00` (`rsid`),
  KEY `FKsbec1r1x8up3regmddmqdefi` (`rid`),
  CONSTRAINT `FKlpsrpgv5dcss6s8tr6jypmqgx` FOREIGN KEY (`oid`) REFERENCES `mrsisa_project`.`client_orders` (`oid`),
  CONSTRAINT `FKnojibkw4q3aeyw7jc4uou6wsw` FOREIGN KEY (`eid`) REFERENCES `mrsisa_project`.`employees` (`e_id`),
  CONSTRAINT `FKpa8tjycplxiw8cor8vo04rn00` FOREIGN KEY (`rsid`) REFERENCES `mrsisa_project`.`reservations` (`rsid`),
  CONSTRAINT `FKsbec1r1x8up3regmddmqdefi` FOREIGN KEY (`rid`) REFERENCES `mrsisa_project`.`restaurants` (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- CREATE TABLE RESERVATIONS

DROP TABLE IF EXISTS `mrsisa_project`.`reservations` ;

CREATE TABLE IF NOT EXISTS `mrsisa_project`.`reservations` (
  `rsid` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` varchar(255) DEFAULT NULL,
  `length` varchar(255) DEFAULT NULL,
  `name_rest` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `rid` bigint(20) DEFAULT NULL,
  `tid` bigint(20) DEFAULT NULL,
  `uid` bigint(20) DEFAULT NULL,
  `grid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`rsid`),
  KEY `FKmx5wgxmrgr3n651drvmy8bbjy` (`rid`),
  KEY `FK3qt34jf2k5b0ppipiv4jotl9y` (`tid`),
  KEY `FK8arn1ndibcwggbe273p07p4tf` (`uid`),
  KEY `FKjewggq5t0t2c2b2y3edpq0jh` (`grid`),
  CONSTRAINT `FK3qt34jf2k5b0ppipiv4jotl9y` FOREIGN KEY (`tid`) REFERENCES `mrsisa_project`.`restaurant_tables` (`tid`),
  CONSTRAINT `FK8arn1ndibcwggbe273p07p4tf` FOREIGN KEY (`uid`) REFERENCES `mrsisa_project`.`app_users` (`id`),
  CONSTRAINT `FKjewggq5t0t2c2b2y3edpq0jh` FOREIGN KEY (`grid`) REFERENCES `mrsisa_project`.`grades` (`grid`),
  CONSTRAINT `FKmx5wgxmrgr3n651drvmy8bbjy` FOREIGN KEY (`rid`) REFERENCES `mrsisa_project`.`restaurants` (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------
-- ADDING TEST DATA
-- -----------------

-- ADDING USERS

INSERT INTO `mrsisa_project`.`app_users` (`id`, `email`, `first_name`, `last_name`, `password`, `image`, `role`, `verified`, `login`, `message`, `token`, `broj_poseta`) VALUES('1', 'sysmanager@gmail.com', 'system', 'manager', '12345', 'pictures/user.png', 'system_manager', 'no', 'no', null, null, '0');
INSERT INTO `mrsisa_project`.`app_users` (`id`, `email`, `first_name`, `last_name`, `password`, `image`, `role`, `verified`, `login`, `message`, `token`, `broj_poseta`) VALUES('2', 'resmanager1@gmail.com', 'res1', 'manager1', '12345', 'pictures/user.png', 'manager', 'no', 'no', null, null, '0');
INSERT INTO `mrsisa_project`.`app_users` (`id`, `email`, `first_name`, `last_name`, `password`, `image`, `role`, `verified`, `login`, `message`, `token`, `broj_poseta`) VALUES('3', 'resmanager2@gmail.com', 'res2', 'manager2', '12345', 'pictures/user.png', 'manager', 'no', 'no', null, null, '0');
INSERT INTO `mrsisa_project`.`app_users` (`id`, `email`, `first_name`, `last_name`, `password`, `image`, `role`, `verified`, `login`, `message`, `token`, `broj_poseta`) VALUES('4', 'waiter@gmail.com', 'waiter', 'waiter', '12345', 'pictures/user.png', 'waiter', 'no', 'no', null, null, '0');
INSERT INTO `mrsisa_project`.`app_users` (`id`, `email`, `first_name`, `last_name`, `password`, `image`, `role`, `verified`, `login`, `message`, `token`, `broj_poseta`) VALUES('5', 'cook@gmail.com', 'cook', 'cook', '12345', 'pictures/user.png', 'cook', 'no', 'no', null, null, '0');
INSERT INTO `mrsisa_project`.`app_users` (`id`, `email`, `first_name`, `last_name`, `password`, `image`, `role`, `verified`, `login`, `message`, `token`, `broj_poseta`) VALUES('6', 'bartender@gmail.com', 'bartender', 'bartender', '12345', 'pictures/user.png', 'bartender', 'no', 'no', null, null, '0');
INSERT INTO `mrsisa_project`.`app_users` (`id`, `email`, `first_name`, `last_name`, `password`, `image`, `role`, `verified`, `login`, `message`, `token`, `broj_poseta`) VALUES('7', 'guest1@gmail.com', 'guest1', 'guest1', '12345', 'pictures/user.png', 'guest', 'no', 'no', null, null, '0');
INSERT INTO `mrsisa_project`.`app_users` (`id`, `email`, `first_name`, `last_name`, `password`, `image`, `role`, `verified`, `login`, `message`, `token`, `broj_poseta`) VALUES('8', 'guest2@gmail.com', 'guest2', 'guest2', '12345', 'pictures/user.png', 'guest', 'no', 'no', null, null, '0');
INSERT INTO `mrsisa_project`.`app_users` (`id`, `email`, `first_name`, `last_name`, `password`, `image`, `role`, `verified`, `login`, `message`, `token`, `broj_poseta`) VALUES('9', 'guest3@gmail.com', 'guest3', 'guest3', '12345', 'pictures/user.png', 'guest', 'no', 'no', null, null, '0');

-- ADDING SYSTEM MANAGER

INSERT INTO `mrsisa_project`.`sys_managers` (`sm_id`) VALUES('1');

-- ADDING RESTAURANTS

INSERT INTO `mrsisa_project`.`restaurants` (`rid`, `address`, `end_time`, `image`, `info`, `name`, `start_time`, `type`, `sm_id`) VALUES ('1', 'Novi Sad, Srbija', '20', 'pictures/user.png', 'lep', 'venecia', '8', 'italian', '1');
INSERT INTO `mrsisa_project`.`restaurants` (`rid`, `address`, `end_time`, `image`, `info`, `name`, `start_time`, `type`, `sm_id`) VALUES ('2', 'Beograd, Srbija', '22', 'pictures/user.png', 'lep', 'atina', '10', 'greek', '1');

-- ADDING RESTAURANT MANAGERS

INSERT INTO `mrsisa_project`.`managers` (`m_id`, `rid`) VALUES('2', '1');
INSERT INTO `mrsisa_project`.`managers` (`m_id`, `rid`) VALUES('3', '2');

-- ADDING REGIONS

INSERT INTO `mrsisa_project`.`regions` (`regid`, `color`, `name`, `region_no`, `rid`) VALUES ('1', 'e846df', 'pusacki deo', '1', '1');
INSERT INTO `mrsisa_project`.`regions` (`regid`, `color`, `name`, `region_no`, `rid`) VALUES ('2', '7ee9f1', 'nepusacki deo', '2', '1');
INSERT INTO `mrsisa_project`.`regions` (`regid`, `color`, `name`, `region_no`, `rid`) VALUES ('3', '50f737', 'basta', '3', '1');

-- ADDING EMPLOYEES

INSERT INTO `mrsisa_project`.`employees` (`birthday`, `dress_size`, `e_id`, `first_time`, `shoe_size`, `regid`, `rid`, `type`) VALUES ('1990-04-10', 'S', '4', 'yes', '37', '1', '1', null);
INSERT INTO `mrsisa_project`.`employees` (`birthday`, `dress_size`, `e_id`, `first_time`, `shoe_size`, `regid`, `rid`, `type`) VALUES ('1992-03-15', 'L', '5', 'yes', '43', null, '1', 'desert');
INSERT INTO `mrsisa_project`.`employees` (`birthday`, `dress_size`, `e_id`, `first_time`, `shoe_size`, `regid`, `rid`, `type`) VALUES ('1988-09-21', 'M', '6', 'yes', '39', null, '1', null);

-- ADDING WAITER

INSERT INTO `mrsisa_project`.`waiters` (w_id) VALUES ('4');

-- ADDING COOK

INSERT INTO `mrsisa_project`.`cooks` (c_id) VALUES ('5');

-- ADDING BARTENDER

INSERT INTO `mrsisa_project`.`bartenders` (b_id) VALUES ('6');

-- ADDING GUESTS

INSERT INTO `mrsisa_project`.`guests` (g_id) VALUES ('7');
INSERT INTO `mrsisa_project`.`guests` (g_id) VALUES ('8');
INSERT INTO `mrsisa_project`.`guests` (g_id) VALUES ('9');

-- ADDING TABLES

INSERT INTO `mrsisa_project`.`restaurant_tables` (`tid`, `deleted`, `height`, `num_of_chairs`, `positions`, `rt_table_in_restaurant_no`, `width`, `x`, `y`, `regid`, `br_rezervacija`, `version`) VALUES ('1', 'no', '50', '4', NULL, '1', '50', '400', '400', '1', '0', '0');
INSERT INTO `mrsisa_project`.`restaurant_tables` (`tid`, `deleted`, `height`, `num_of_chairs`, `positions`, `rt_table_in_restaurant_no`, `width`, `x`, `y`, `regid`, `br_rezervacija`, `version`) VALUES ('2', 'no', '50', '4', NULL, '2', '50', '488', '393', '1', '0', '0');
INSERT INTO `mrsisa_project`.`restaurant_tables` (`tid`, `deleted`, `height`, `num_of_chairs`, `positions`, `rt_table_in_restaurant_no`, `width`, `x`, `y`, `regid`, `br_rezervacija`, `version`) VALUES ('3', 'no', '50', '4', NULL, '3', '50', '564', '365', '1', '0', '0');
INSERT INTO `mrsisa_project`.`restaurant_tables` (`tid`, `deleted`, `height`, `num_of_chairs`, `positions`, `rt_table_in_restaurant_no`, `width`, `x`, `y`, `regid`, `br_rezervacija`, `version`) VALUES ('4', 'no', '50', '4', NULL, '4', '50', '75', '143', '3', '0', '0');
INSERT INTO `mrsisa_project`.`restaurant_tables` (`tid`, `deleted`, `height`, `num_of_chairs`, `positions`, `rt_table_in_restaurant_no`, `width`, `x`, `y`, `regid`, `br_rezervacija`, `version`) VALUES ('5', 'no', '50', '4', NULL, '5', '50', '80', '242', '3', '0', '0');
INSERT INTO `mrsisa_project`.`restaurant_tables` (`tid`, `deleted`, `height`, `num_of_chairs`, `positions`, `rt_table_in_restaurant_no`, `width`, `x`, `y`, `regid`, `br_rezervacija`, `version`) VALUES ('6', 'no', '50', '4', NULL, '6', '50', '404', '131', '2', '0', '0');
INSERT INTO `mrsisa_project`.`restaurant_tables` (`tid`, `deleted`, `height`, `num_of_chairs`, `positions`, `rt_table_in_restaurant_no`, `width`, `x`, `y`, `regid`, `br_rezervacija`, `version`) VALUES ('7', 'no', '50', '4', NULL, '7', '50', '492', '122', '2', '0', '0');

-- ADDING MENU ITEMS

INSERT INTO `mrsisa_project`.`menu_items` (`miid`, `deleted`, `image`, `info`, `name`, `price`, `spec_type`, `type`, `rid`) VALUES ('1', 0, 'pictures/user.png', 'lep', 'pizza', '150', 'fried', 'dish', '1');
INSERT INTO `mrsisa_project`.`menu_items` (`miid`, `deleted`, `image`, `info`, `name`, `price`, `spec_type`, `type`, `rid`) VALUES ('2', 0, 'pictures/user.png', 'lep', 'giros', '200', 'fried', 'dish', '1');
INSERT INTO `mrsisa_project`.`menu_items` (`miid`, `deleted`, `image`, `info`, `name`, `price`, `spec_type`, `type`, `rid`) VALUES ('3', 0, 'pictures/user.png', 'lep', 'blue lagoon', '300', 'coctail', 'drink', '1');
INSERT INTO `mrsisa_project`.`menu_items` (`miid`, `deleted`, `image`, `info`, `name`, `price`, `spec_type`, `type`, `rid`) VALUES ('4', 0, 'pictures/user.png', 'lep', 'wine', '250', 'alcohol', 'drink', '1');