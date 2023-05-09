
-- DROP TABLE IF EXISTS applications;
-- DROP TABLE IF EXISTS thesis;
-- DROP TABLE IF EXISTS subjects;
-- DROP TABLE IF EXISTS professors;
-- DROP TABLE IF EXISTS students;
-- DROP TABLE IF EXISTS users;


CREATE TABLE IF NOT EXISTS users (
	id int not null auto_increment primary key,
	username varchar(32) not null unique,
	password text not null,
	role text not null
) ENGINE=InnoDB AUTO_INCREMENT=1;


CREATE TABLE IF NOT EXISTS students (
	id int not null primary key,
	full_name varchar(64) default null,
	average_grade real,
	rem_courses int,
	year int,
	constraint fk_stuid foreign key (id) references users(id)
) ENGINE=InnoDB AUTO_INCREMENT=1;


CREATE TABLE IF NOT EXISTS professors (
	id int not null primary key,
	full_name varchar(64) default null,
	specialty varchar(32) default null,
	constraint fk_proid foreign key (id) references users(id)
) ENGINE=InnoDB AUTO_INCREMENT=1;


CREATE TABLE IF NOT EXISTS subjects (
	id int not null auto_increment primary key,
	professor int not null,
	name text not null,
	objectives text not null,
	assigned boolean default false,
	constraint fk_subid foreign key (professor) references professors(id)
) ENGINE=InnoDB AUTO_INCREMENT=1;


CREATE TABLE IF NOT EXISTS thesis (
	id int not null primary key,
	student int not null,
	imp_grade real,
	rep_grade real,
	pres_grade real,
	constraint fk_thesub foreign key (id) references subjects(id),
	constraint fk_thestu foreign key (student) references students(id)
) ENGINE=InnoDB AUTO_INCREMENT=1;


CREATE TABLE IF NOT EXISTS applications (
	subject int not null,
	student int not null,
	message text,
	primary key (subject, student),
	constraint fk_appsub foreign key (subject) references subjects(id),
	constraint fk_appstu foreign key (student) references students(id)
) ENGINE=InnoDB AUTO_INCREMENT=1;

