use gohockeyhero;
DROP TABLE IF EXISTS `hibernate_sequence`;
DROP TABLE IF EXISTS `social_user_connection`;
DROP TABLE IF EXISTS `persistent_audit_evt_data`;
DROP TABLE IF EXISTS `persistent_audit_event`;
DROP TABLE IF EXISTS `persistent_token`;
DROP TABLE IF EXISTS `herodetails_authority`;
DROP TABLE IF EXISTS `authority`;
DROP TABLE IF EXISTS `hero_details`; 
DROP TABLE IF EXISTS `hero_keys`; 
DROP TABLE IF EXISTS `hero_loc`;

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: gohockeyhero
-- For DEV: Drop tables first

-- --------------------------------------------------------
--
-- Table structure for table `hibernate_sequence`
--
CREATE TABLE hibernate_sequence(
    sequence_name VARCHAR(20) NOT NULL,
    next_val INTEGER NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO hibernate_sequence (next_val) VALUES (0);
-- --------------------------------------------------------
--
-- Table structure for table `hero_loc`
--
CREATE TABLE `hero_loc` (
  `id` 			bigint(20) NOT NULL AUTO_INCREMENT,
  
  `hide_me` 	tinyint DEFAULT NULL,
  `latitude` 	double NOT NULL,
  `longitude` 	double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------
--
-- Table structure for table `hero_keys`
--
CREATE TABLE `hero_keys` (
  `id` 				bigint(20) NOT NULL,
  `heroloc_id`		bigint(20) NOT NULL,
  
  `myposition` 		int(8) NOT NULL,
  `age` 			int(8) NOT NULL,
  `skill` 			int(8) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `HEROKEYS_to_HEROLOC` FOREIGN KEY (`heroloc_id`) REFERENCES `hero_loc` (`id`) 
  ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------
--
-- Table structure for table `hero_details`
--
CREATE TABLE `hero_details` (
  `id` 					bigint(20) NOT NULL,
  `herokeys_id`			bigint(20) NOT NULL,
  
  -- account information
  `login` 				varchar(50) NOT NULL,
  `password_hash` 		varchar(60) NOT NULL,
  `activated` 			bit(1) 		NOT NULL,
  `activation_key` 		varchar(20) DEFAULT NULL,
  `reset_key` 			varchar(20) DEFAULT NULL,
  `reset_date` 			timestamp 	NULL DEFAULT NULL,
  `created_by` 			varchar(50) NOT NULL,
  `created_date` 		timestamp 	NOT NULL,
  `last_modified_by` 	varchar(50) DEFAULT NULL,
  `last_modified_date` 	timestamp 	NULL DEFAULT NULL,  

  -- personal information
  `first_name` 			varchar(50)  DEFAULT NULL,
  `last_name` 			varchar(50)  DEFAULT NULL,  
  `email` 				varchar(255) NOT NULL,
  `phone` 				varchar(10)  NOT NULL,  
  `lang_key` 			varchar(6) 	 DEFAULT NULL, 
  `image_url` 			varchar(256) DEFAULT NULL,
  `date_of_birth` 		date 		 DEFAULT NULL,
  `address` 			varchar(100) DEFAULT NULL,
  `quick_desc` 			varchar(250) DEFAULT NULL,
  
  -- relationship information
  PRIMARY KEY (`id`),
  CONSTRAINT `HERODETAILS_to_HEROKEYS` FOREIGN KEY (`herokeys_id`) REFERENCES `hero_keys` (`id`)
  ON DELETE CASCADE,
  UNIQUE KEY `ux_hero_login` (`login`),
  UNIQUE KEY `ux_hero_email` (`email`),
  UNIQUE KEY `ux_hero_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- NearestHero - stored procedure to find the nearest user 
-- by criteria. We search by position, age range and skill range
--
CREATE TABLE `persistent_audit_event` (
  `event_id` 		bigint(20) 	 NOT NULL AUTO_INCREMENT,
  `principal` 		varchar(50)  NOT NULL,
  `event_date` 		timestamp 	 NULL DEFAULT NULL,
  `event_type` 		varchar(255) DEFAULT NULL,
  PRIMARY KEY (`event_id`),
  KEY `idx_hero_persistent_audit_event` (`principal`,`event_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- NearestHero - stored procedure to find the nearest user 
-- by criteria. We search by position, age range and skill range
--
CREATE TABLE `persistent_audit_evt_data` (
  `event_id` 		bigint(20) 	 NOT NULL,
  `name` 			varchar(150) NOT NULL,
  `value` 			varchar(255) DEFAULT NULL,
  PRIMARY KEY (`event_id`,`name`),
  KEY `idx_hero_persistent_audit_evt_data` (`event_id`),
  CONSTRAINT `fk_evt_pers_audit_evt_data` FOREIGN KEY (`event_id`) REFERENCES `persistent_audit_event` (`event_id`)
  ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- NearestHero - stored procedure to find the nearest user 
-- by criteria. We search by position, age range and skill range
--
CREATE TABLE `persistent_token` (
  `series` 		varchar(20)	 NOT NULL,
  `herodetails_id` 	bigint(20) 	 NOT NULL,
  `token_value` varchar(20)  NOT NULL,
  `token_date` 	date 		 DEFAULT NULL,
  `ip_address` 	varchar(39)  DEFAULT NULL,
  `hero_agent` 	varchar(255) DEFAULT NULL,
  
  PRIMARY KEY (`series`),
  KEY `fk_persistent_token` (`herodetails_id`),
  CONSTRAINT `fk_persistent_token` FOREIGN KEY (`herodetails_id`) REFERENCES `hero_details` (`id`)
  ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `authority` (
  `name` 		varchar(50) NOT NULL,
  
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `social_user_connection` (
  `id` 					bigint(20) 		NOT NULL AUTO_INCREMENT,
  `access_token` 		varchar(255) 	NOT NULL,
  `display_name` 		varchar(255) 	DEFAULT NULL,
  `expire_time` 		bigint(20) 		DEFAULT NULL,
  `image_url` 			varchar(255) 	DEFAULT NULL,
  `profile_url` 		varchar(255) 	DEFAULT NULL,
  `provider_id` 	 	varchar(255) 	NOT NULL,
  `provider_user_id` 	varchar(255) 	NOT NULL,
  `rank_no` 			bigint(20) 		NOT NULL,
  `refresh_token` 		varchar(255) 	DEFAULT NULL,
  `secret` 				varchar(255) 	DEFAULT NULL,
  `user_id` 			varchar(255) 	NOT NULL,
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `herodetails_authority` (
  `id` 		bigint(20) 	NOT NULL,
  `name` 	varchar(50) NOT NULL,
  
  PRIMARY KEY (`id`,`name`),
  KEY `fx_hero_user_authority` (`name`),
  CONSTRAINT `fx_hero_user_authority1` FOREIGN KEY (`id`) REFERENCES `hero_details` (`id`)
  ON DELETE CASCADE,
  CONSTRAINT `fx_hero_user_authority2` FOREIGN KEY (`name`) REFERENCES `authority` (`name`)
  ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


commit; 




