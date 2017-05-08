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
  `id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(50) NOT NULL,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `image` VARCHAR(200),
  `role` VARCHAR(50) NOT NULL,
  `verified` VARCHAR(50),
  `login` VARCHAR(50),
  `message` VARCHAR(100),
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- CREATE TABLE GUESTS

DROP TABLE IF EXISTS `mrsisa_project`.`guests` ;

CREATE TABLE IF NOT EXISTS `mrsisa_project`.`guests` (
  `g_id` BIGINT(10) NOT NULL,
  PRIMARY KEY (`g_id`),
  CONSTRAINT `g_fid`
    FOREIGN KEY (`g_id`)
    REFERENCES `mrsisa_project`.`app_users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- CREATE TABLE EMPLOYEES

DROP TABLE IF EXISTS `mrsisa_project`.`employees` ;

CREATE TABLE IF NOT EXISTS `mrsisa_project`.`employees` (
  `e_id` BIGINT(10) NOT NULL,
  `birthday` DATETIME NOT NULL,
  `dress_size` VARCHAR(50) NOT NULL,
  `shoe_size` VARCHAR(50) NOT NULL,
  `first_time` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`e_id`),
  CONSTRAINT `e_fid`
    FOREIGN KEY (`e_id`)
    REFERENCES `mrsisa_project`.`app_users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- CREATE TABLE WAITERS

DROP TABLE IF EXISTS `mrsisa_project`.`waiters` ;

CREATE TABLE IF NOT EXISTS `mrsisa_project`.`waiters` (
  `w_id` BIGINT(10) NOT NULL,
  PRIMARY KEY (`w_id`),
  CONSTRAINT `w_fid`
    FOREIGN KEY (`w_id`)
    REFERENCES `mrsisa_project`.`employees` (`e_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- CREATE TABLE COOKS

DROP TABLE IF EXISTS `mrsisa_project`.`cooks` ;

CREATE TABLE IF NOT EXISTS `mrsisa_project`.`cooks` (
  `c_id` BIGINT(10) NOT NULL,
  PRIMARY KEY (`c_id`),
  CONSTRAINT `c_fid`
    FOREIGN KEY (`c_id`)
    REFERENCES `mrsisa_project`.`employees` (`e_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- CREATE TABLE BARTENDERS

DROP TABLE IF EXISTS `mrsisa_project`.`bartenders` ;

CREATE TABLE IF NOT EXISTS `mrsisa_project`.`bartenders` (
  `b_id` BIGINT(10) NOT NULL,
  PRIMARY KEY (`b_id`),
  CONSTRAINT `b_fid`
    FOREIGN KEY (`b_id`)
    REFERENCES `mrsisa_project`.`employees` (`e_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- CREATE TABLE SYSTEM MANAGERS

DROP TABLE IF EXISTS `mrsisa_project`.`sys_managers` ;

CREATE TABLE IF NOT EXISTS `mrsisa_project`.`sys_managers` (
  `sm_id` BIGINT(10) NOT NULL,
  PRIMARY KEY (`sm_id`),
  CONSTRAINT `sm_fid`
    FOREIGN KEY (`sm_id`)
    REFERENCES `mrsisa_project`.`app_users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- CREATE TABLE RESTAURANT MANAGERS

DROP TABLE IF EXISTS `mrsisa_project`.`managers` ;

CREATE TABLE IF NOT EXISTS `mrsisa_project`.`managers` (
  `m_id` BIGINT(10) NOT NULL,
  `restaurant_name` VARCHAR(50),
  PRIMARY KEY (`m_id`),
  CONSTRAINT `m_fid`
    FOREIGN KEY (`m_id`)
    REFERENCES `mrsisa_project`.`app_users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- CREATE TABLE FRIENDSHIPS

DROP TABLE IF EXISTS `mrsisa_project`.`friendships` ;

CREATE TABLE IF NOT EXISTS `mrsisa_project`.`friendships` (
  `fid` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `first` BIGINT(10) NOT NULL,
  `second` BIGINT(10) NOT NULL,
  `status` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`fid`),
  INDEX `fs_firstid_idx` (`first` ASC),
  INDEX `fs_secondid_idx` (`second` ASC),
  CONSTRAINT `fs_firstid`
    FOREIGN KEY (`first`)
    REFERENCES `mrsisa_project`.`guests` (`g_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fs_secondid`
    FOREIGN KEY (`second`)
    REFERENCES `mrsisa_project`.`guests` (`g_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------
-- ADDING TEST DATA
-- -----------------

-- ADDING USERS

INSERT INTO `mrsisa_project`.`app_users` (`id`, `email`, `first_name`, `last_name`, `password`, `image`, `role`, `verified`, `login`, `message`) VALUES(1, 'sysmanager@gmail.com', 'system', 'manager', '12345', 'pictures/user.png', 'system_manager', 'no', 'no', null);
INSERT INTO `mrsisa_project`.`app_users` (`id`, `email`, `first_name`, `last_name`, `password`, `image`, `role`, `verified`, `login`, `message`) VALUES(2, 'resmanager1@gmail.com', 'res1', 'manager1', '12345', 'pictures/user.png', 'manager', 'no', 'no', null);
INSERT INTO `mrsisa_project`.`app_users` (`id`, `email`, `first_name`, `last_name`, `password`, `image`, `role`, `verified`, `login`, `message`) VALUES(3, 'resmanager2@gmail.com', 'res2', 'manager2', '12345', 'pictures/user.png', 'manager', 'no', 'no', null);
INSERT INTO `mrsisa_project`.`app_users` (`id`, `email`, `first_name`, `last_name`, `password`, `image`, `role`, `verified`, `login`, `message`) VALUES(4, 'waiter@gmail.com', 'waiter', 'waiter', '12345', 'pictures/user.png', 'waiter', 'no', 'no', null);
INSERT INTO `mrsisa_project`.`app_users` (`id`, `email`, `first_name`, `last_name`, `password`, `image`, `role`, `verified`, `login`, `message`) VALUES(5, 'cook@gmail.com', 'cook', 'cook', '12345', 'pictures/user.png', 'cook', 'no', 'no', null);
INSERT INTO `mrsisa_project`.`app_users` (`id`, `email`, `first_name`, `last_name`, `password`, `image`, `role`, `verified`, `login`, `message`) VALUES(6, 'bartender@gmail.com', 'bartender', 'bartender', '12345', 'pictures/user.png', 'bartender', 'no', 'no', null);
INSERT INTO `mrsisa_project`.`app_users` (`id`, `email`, `first_name`, `last_name`, `password`, `image`, `role`, `verified`, `login`, `message`) VALUES(7, 'guest1@gmail.com', 'guest1', 'guest1', '12345', 'pictures/user.png', 'guest', 'no', 'no', null);
INSERT INTO `mrsisa_project`.`app_users` (`id`, `email`, `first_name`, `last_name`, `password`, `image`, `role`, `verified`, `login`, `message`) VALUES(8, 'guest2@gmail.com', 'guest2', 'guest2', '12345', 'pictures/user.png', 'guest', 'no', 'no', null);
INSERT INTO `mrsisa_project`.`app_users` (`id`, `email`, `first_name`, `last_name`, `password`, `image`, `role`, `verified`, `login`, `message`) VALUES(9, 'guest3@gmail.com', 'guest3', 'guest3', '12345', 'pictures/user.png', 'guest', 'no', 'no', null);

-- ADDING SYSTEM MANAGER

INSERT INTO `mrsisa_project`.`sys_managers` (`sm_id`) VALUES('1');

-- ADDING RESTAURANT MANAGERS

INSERT INTO `mrsisa_project`.`managers` (`m_id`, `restaurant_name`) VALUES('2', 'restaurant1');
INSERT INTO `mrsisa_project`.`managers` (`m_id`, `restaurant_name`) VALUES('3', 'restaurant2');

-- ADDING EMPLOYEES

INSERT INTO `mrsisa_project`.`employees` (`e_id`, `birthday`, `dress_size`, `shoe_size`, `first_time`) VALUES ('4', '1990-04-10', 'S', '37', 'yes');
INSERT INTO `mrsisa_project`.`employees` (`e_id`, `birthday`, `dress_size`, `shoe_size`, `first_time`) VALUES ('5', '1992-03-15', 'L', '43', 'yes');
INSERT INTO `mrsisa_project`.`employees` (`e_id`, `birthday`, `dress_size`, `shoe_size`, `first_time`) VALUES ('6', '1988-09-21', 'M', '39', 'yes');

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

-- ADDING FRIENDSHIPS

INSERT INTO `mrsisa_project`.`friendships` (`fid`,`first`,`second`,`status`) VALUES (1,'7','8','accepted');
INSERT INTO `mrsisa_project`.`friendships` (`fid`,`first`,`second`,`status`) VALUES (2,'8','9','accepted');



