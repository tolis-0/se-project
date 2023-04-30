CREATE DATABASE  IF NOT EXISTS `myy803db`;
USE `myy803db`;

-- DROP TABLE IF EXISTS Users;
-- DROP TABLE IF EXISTS Students;
-- DROP TABLE IF EXISTS Professors;
-- DROP TABLE IF EXISTS Subjects;
-- DROP TABLE IF EXISTS Thesis;
-- DROP TABLE IF EXISTS Applications;

CREATE TABLE Users (
  id int not null auto_increment primary key,
  username varchar(32) default null,
  password text default null,
  role text default null  									--TODO
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS Students (
	id int references users(id) not null primary key,
	full_name varchar(64) default null,
	average_grade real,
	remaining_courses int,
	year int
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1; 


CREATE TABLE IF NOT EXISTS Professors (
	id int references Users(id) not null primary key,
	full_name varchar(64) default null,
	specialty varchar(32) default null
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS Subjects (
	id int not null auto_increment primary key,
	professor int not null references Professors(id),
	name text not null,
	objectives text not null,
	assigned boolean default false
); ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS Thesis (
	id int not null references Subjects(id) primary key,
	student int not null references Student(id),
	imp_grade real,
	rep_grade real,
	pres_grade real
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS Applications (
	subject int not null references Subjects(id),
	student int not null references Students(id),
	message text,
	primary key (subject, student)
); ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


