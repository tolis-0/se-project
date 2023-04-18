CREATE DATABASE  IF NOT EXISTS `myy803db`;
USE `myy803db`;


-- DROP TABLE IF EXISTS `students`;
-- DROP TABLE IF EXISTS `professors`;


CREATE TABLE IF NOT EXISTS `students` (
  `username` text PRIMARY KEY,
  `password` text DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1; 

CREATE TABLE IF NOT EXISTS `professors` (
  `username` text PRIMARY KEY,
  `password` text DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1; 
