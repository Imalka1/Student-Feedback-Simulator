drop database studentfeedback;
create database studentfeedback;
use studentfeedback;

create table faculty(
facid int auto_increment,
name varchar(100),
constraint primary key(facid)
);

create table degree(
degid int auto_increment,
facid int,
name varchar(100),
constraint primary key(degid),
constraint foreign key(facid) references faculty(facid) on delete cascade
);

create table batch(
batchid int auto_increment,
intake date,
name varchar(100),
constraint primary key(batchid)
);

create table user(
uid varchar(100),
password varchar(100),
accountType varchar(20),
constraint primary key(uid)
);	

create table student(
stid int auto_increment,
uid varchar(100),
degid int,
batchid int,
student_name varchar(100),
national_id varchar(100),
constraint primary key(stid),
constraint foreign key(uid) references user(uid) on delete cascade,
constraint foreign key(degid) references degree(degid) on delete cascade,
constraint foreign key(batchid) references batch(batchid) on delete cascade
);

create table admin(
adminid int auto_increment,
uid varchar(100),
admin_name varchar(100),
constraint primary key(adminid),
constraint foreign key(uid) references user(uid) on delete cascade
);

create table evaluation_criteria_heading(
echid int,
text varchar(100),
constraint primary key(echid)
);

create table evaluation_criteria(
ecid int auto_increment,
echid int,
text varchar(100),
constraint primary key(ecid),
constraint foreign key(echid) references evaluation_criteria_heading(echid) on delete cascade
);

create table semester(
semid int auto_increment,
text varchar(100),
constraint primary key(semid)
);

create table lecturer(
lecid varchar(100),
name varchar(100),
constraint primary key(lecid)
);

create table subject(
subid varchar(100),
degid int,
semid int,
title varchar(100),
credits int,
constraint primary key(subid),
constraint foreign key(degid) references degree(degid) on delete cascade,
constraint foreign key(semid) references semester(semid) on delete cascade
);

create table subject_lecturer(
sublecid int auto_increment,
subid varchar(100),
lecid varchar(100),
current boolean,
constraint primary key(sublecid),
constraint foreign key(subid) references subject(subid) on delete cascade,
constraint foreign key(lecid) references lecturer(lecid) on delete cascade
);

create table marks(
ecid int,
dateOfSubmitted date,
sublecid int,
marks int,
constraint primary key(ecid,dateOfSubmitted,sublecid),
constraint foreign key(sublecid) references subject_lecturer(sublecid) on delete cascade,
constraint foreign key(ecid) references evaluation_criteria(ecid) on delete cascade
);

INSERT INTO `studentfeedback`.`faculty`
(`name`)
VALUES
('Department of Computer Science');

INSERT INTO `studentfeedback`.`batch`
(`intake`,
`name`)
VALUES
('2019-01-05','Year 1 Semester 1'),('2020-01-05','Year 1 Semester 2');

INSERT INTO `studentfeedback`.`degree`
(`facid`,`name`)
VALUES
(1,'BSc (Computer Science)'),(1,'BSc (Information Systems)');

INSERT INTO `studentfeedback`.`user`
(`uid`,
`password`,
`accountType`)
VALUES
('IT123','123','student'),('IT456','456','student'),('ADMIN789','789','admin');

INSERT INTO `studentfeedback`.`student`
(`uid`,
`degid`,
`batchid`,
`student_name`,
`national_id`)
VALUES
('IT123',1,1,'Amal Silva','951761150V'),('IT456',2,2,'Kamal Silva','961751150V');

INSERT INTO `studentfeedback`.`admin`
(`uid`,
`admin_name`)
VALUES
('ADMIN789','Nimal Silva');


INSERT INTO `studentfeedback`.`evaluation_criteria_heading`
(`echid`,
`text`)
VALUES
(1,'Enthusiasm'),
(2,'Organization'),
(3,'Lecturer student interaction'),
(4,'Task Organization'),
(5,'Clarity'),
(6,'Learning Experiences');

INSERT INTO `studentfeedback`.`evaluation_criteria`
(`echid`,
`text`)
VALUES
(1,'Lecturer was punctual'),
(2,'Lecturer was prepared for lectures'),
(2,'Lectureres were well structured'),
(2,'Details of course content and learning outcomes were provided'),
(3,'Student were encouraged to ask questions'),
(3,'Lecturer appreciated students participation'),
(3,'Student were motivated to learn'),
(3,'Attention was given to the students individually'),
(3,'Feedback provided for the students questions were understood'),
(4,'Speed of the lecturer was reasonable'),
(4,'Usage of teaching aid(PPT, video clips, white board, etc)'),
(4,'Usage of textbooks, websites, periodicals, etc. were recommended'),
(4,'Worked examples/tutorials were provided'),
(4,'Sufficient time was provided for tutorials/worked examples'),
(4,'Lecturer advised regarding examinations'),
(4,'Feedback on continous assignments/tutorials were helpful'),
(5,'Black/white board or powerpoint presentations were clear'),
(5,'Lecturer was sufficiently audible'),
(5,'Lecturer was clear when communicating with students'),
(6,'Lecture was good'),
(6,'Course content'),
(6,'Module/subject was understood');

INSERT INTO `studentfeedback`.`semester`
(`text`)
VALUES
('Year 1 / Semester 1'),('Year 1 / Semester 2'),('Year 2 / Semester 1'),('Year 2 / Semester 2');

INSERT INTO `studentfeedback`.`lecturer`
(`lecid`,
`name`)
VALUES
('L001','Kamal Perera'),('L002','Nimal Silva');

INSERT INTO `studentfeedback`.`subject`
(`subid`,
`degid`,
`semid`,
`title`,
`credits`)
VALUES
('CSC 101',1,1,'Progamming',3),('CSC 102',1,1,'Database Management System',2),
('CSC 201',1,2,'Progamming - 2',3),('CSC 202',1,2,'Database Management System - 2',2),
('CSC 301',1,3,'Progamming - 3',3),('CSC 302',1,3,'Database Management System - 3',2),
('IS 101',2,1,'Progamming',3),('IS 102',2,1,'Web Development',2),
('IS 201',2,2,'Progamming - 2',3),('IS 202',2,2,'Web Development - 2',2),
('IS 301',2,3,'Progamming - 3',3),('IS 302',2,3,'Web Development - 3',2);

INSERT INTO `studentfeedback`.`subject_lecturer`
(`subid`,
`lecid`,
`current`)
VALUES
('CSC 101','L001',true),('CSC 102','L002',true),('CSC 201','L001',true),('CSC 202','L002',true),('CSC 301','L001',true),('CSC 302','L002',true),
('IS 101','L001',true),('IS 102','L002',true),('IS 201','L001',true),('IS 202','L002',true),('IS 301','L001',true),('IS 302','L002',true);

SELECT `evaluation_criteria_heading`.`echid`,
    `evaluation_criteria_heading`.`text`
FROM `studentfeedback`.`evaluation_criteria_heading`;

SELECT `evaluation_criteria`.`ecid`,
    `evaluation_criteria`.`echid`,
    `evaluation_criteria`.`text`
FROM `studentfeedback`.`evaluation_criteria`;


SELECT `user`.`uid`,
    `user`.`username`,
    `user`.`password`
FROM `studentfeedback`.`user`;

SELECT `batch`.`batchid`,
    `batch`.`intake`
FROM `studentfeedback`.`batch`;


select f.name,d.name from user u,faculty f,degree d where f.facid=d.facid && d.degid=u.degid && u.uid='abc123';

select year(curdate())-year(b.intake),month(curdate())-month(b.intake) from student s,batch b,user u where s.batchid=b.batchid && u.uid=s.uid && s.uid='IT123';

select month(curdate())-month('2020-04-03');

select text from semester where semid=2;

select distinct year(intake) from batch;

select uid,student_name,national_id,b.name from student s,batch b,degree d where b.batchid=s.batchid && d.degid=s.degid && d.degid=1 && b.batchid=1 && year(b.intake)='2019' order by uid asc limit 0,20;

SELECT 
    `marks`.`ecid`,
    `marks`.`dateOfSubmitted`,
    `marks`.`sublecid`,
    `marks`.`marks`
FROM `studentfeedback`.`marks`;


