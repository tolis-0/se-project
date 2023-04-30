CREATE DATABASE  IF NOT EXISTS `myy803db`;
USE `myy803db`;

-- DROP TABLE IF EXISTS `users`;
-- DROP TABLE IF EXISTS `students`;
-- DROP TABLE IF EXISTS `professors`;
-- DROP TABLE IF EXISTS `thesis`;
-- DROP TABLE IF EXISTS `application`;

CREATE TABLE IF NOT EXISTS `users` (
 `username` varchar(32) not null primary key,
 `password` text default null,
 `enabled` boolean not null
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1; 

CREATE TABLE IF NOT EXISTS `students` (
  `full_name` varchar(64) default null,
  `password` text default null,
  `average_grade` real,
  `remaining_courses` int,
  `year` int,
  `username` varchar(32) not null,
  foreign key (username) references users (username)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1; 

CREATE TABLE IF NOT EXISTS `professors` (
  `full_name` varchar(64) default null,
  `password` text default null,
  `specialty` varchar(32) default null
  `username` varchar(32) not null,
  foreign key (username) references users (username)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `thesis` (
 `professor` varchar(32) not null references professor(username),
 `thesis_id` int not null primary key,
 `objectives` text not null,
 `student` varchar(32) references Student(username),
 `assigned` boolean not null,
 `imp_grade` real,
 `rep_grade` real,
 `pres_grade` real
); ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `application`(
 `thesis` int not null references thesis(thesis_id),
 `student` varchar(32) not null references student(username),
 `message` text,
 primary key (thesis, student)
); ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

