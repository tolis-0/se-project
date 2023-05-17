-- Password is 123 for everyone

INSERT INTO users VALUES (1, "admin", "$2a$10$0APP3/oiEWh08ryVXmPeZubk13F9oxaQS2p5qVPTNkJIl.D2YGI5q", "ADMIN");
INSERT INTO users VALUES (2, "tolis", "$2a$10$0APP3/oiEWh08ryVXmPeZubk13F9oxaQS2p5qVPTNkJIl.D2YGI5q", "STUDENT");
INSERT INTO users VALUES (3, "pat", "$2a$10$0APP3/oiEWh08ryVXmPeZubk13F9oxaQS2p5qVPTNkJIl.D2YGI5q", "STUDENT");
INSERT INTO users VALUES (4, "liakos", "$2a$10$0APP3/oiEWh08ryVXmPeZubk13F9oxaQS2p5qVPTNkJIl.D2YGI5q", "STUDENT");
INSERT INTO users VALUES (5, "giorgos", "$2a$10$0APP3/oiEWh08ryVXmPeZubk13F9oxaQS2p5qVPTNkJIl.D2YGI5q", "STUDENT");
INSERT INTO users VALUES (6, "maria", "$2a$10$0APP3/oiEWh08ryVXmPeZubk13F9oxaQS2p5qVPTNkJIl.D2YGI5q", "STUDENT");
INSERT INTO users VALUES (7, "antonis", "$2a$10$0APP3/oiEWh08ryVXmPeZubk13F9oxaQS2p5qVPTNkJIl.D2YGI5q", "STUDENT");
INSERT INTO users VALUES (8, "zarras", "$2a$10$0APP3/oiEWh08ryVXmPeZubk13F9oxaQS2p5qVPTNkJIl.D2YGI5q", "PROFESSOR");
INSERT INTO users VALUES (9, "efthym", "$2a$10$0APP3/oiEWh08ryVXmPeZubk13F9oxaQS2p5qVPTNkJIl.D2YGI5q", "PROFESSOR");
INSERT INTO users VALUES (10, "dimako", "$2a$10$0APP3/oiEWh08ryVXmPeZubk13F9oxaQS2p5qVPTNkJIl.D2YGI5q", "PROFESSOR");

INSERT INTO students VALUES (2, "Apostolos Pappas", 8.1, 20, 5);
INSERT INTO students VALUES (3, "Patroklos Pappas", 7.4, 14, 5);
INSERT INTO students VALUES (4, "Hlias Konstantoulas", 6.5, 9, 6);
INSERT INTO students VALUES (5, "Giorgos Anagnostou", 5.8, 15, 5);
INSERT INTO students VALUES (6, "Maria Kanata", 7.9, 10, 6);
INSERT INTO students VALUES (7, "Antonis Papadopoulos", 6.3, 4, 7);

INSERT INTO professors VALUES (8, "Apostolos Zarras", "Software Engineering");
INSERT INTO professors VALUES (9, "Aristeidis Efthymiou", "Computer Architecture");
INSERT INTO professors VALUES (10, "Vasileios Dimakopoulos", "Parallel Systems");

INSERT INTO subjects VALUES (1, 8, "Thesis Assignment Project", "Develop a spring boot web application for professors and students to assign diploma thesis subjects.", 0);
INSERT INTO subjects VALUES (2, 8, "Course Management Project", "Develop a spring boot web application for users to manage courses.", 0);
INSERT INTO subjects VALUES (3, 9, "Multi-Level Cache Simulation", "Develop a cache simulation program for various architectures.", 1);
INSERT INTO subjects VALUES (4, 10, "OpenMP Compiler", "Develop part of an OpenMP compiler for parallel programming in C and C++.", 0);

INSERT INTO applications VALUES (1, 3, "Hello, I would like to take on this thesis diploma ... I have experience in ...");
INSERT INTO applications VALUES (1, 4, "Good Evening, I just saw your diploma thesis subject and ... I am interested in ...");
INSERT INTO applications VALUES (1, 5, "Good Morning, I am sending you this application because I would like to ... I have created web apps before and ...");
INSERT INTO applications VALUES (1, 6, "Good Afternoon, I would love to apply to ... I have experience in software engineering and ...");
INSERT INTO applications VALUES (2, 7, "Greetings, I am interested in the thesis diploma ... I have enjoyed developing spring boot ...");
INSERT INTO applications VALUES (2, 3, "Good Evening, I would like to take on this thesis diploma ... I have experience in ...");
INSERT INTO applications VALUES (2, 4, "Good Afternoon, I would love to apply to ... I am interested in ...");
INSERT INTO applications VALUES (4, 6, "Hello, I am sending you this application because ... I am interested in ...");
INSERT INTO applications VALUES (4, 7, "Greetings, I am interested in the thesis diploma ... I have enjoyed developing compilers ...");

INSERT INTO thesis VALUES (3, 2, 0, 0, 0);
