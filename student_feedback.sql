drop database studentfeedback;
create database studentfeedback;
use studentfeedback;

create table degree(
degreeId int auto_increment,
name varchar(100),
constraint primary key(degreeId)
);

create table semester(
semesterId int auto_increment,
name varchar(100),
constraint primary key(semesterId)
);

create table batch(
batchId int auto_increment,
intake date,
name varchar(100),
constraint primary key(batchId)
);

create table user(
uId varchar(100),
password varchar(100),
accountType varchar(20),
emailAddress varchar(100),
constraint primary key(uId)
);	

create table student(
studentId int auto_increment,
uId varchar(100),
degreeId int,
batchId int,
semesterId int,
student_name varchar(100),
national_id varchar(100),
constraint primary key(studentId),
constraint foreign key(uId) references user(uId) on delete cascade,
constraint foreign key(degreeId) references degree(degreeId) on delete cascade,
constraint foreign key(batchId) references batch(batchId) on delete cascade,
constraint foreign key(semesterId) references semester(semesterId) on delete cascade
);

create table admin(
adminid int auto_increment,
uId varchar(100),
admin_name varchar(100),
constraint primary key(adminid),
constraint foreign key(uId) references user(uId) on delete cascade
);

create table evaluation_criteria(
ecId int auto_increment,
text varchar(100),
criteriaHeading boolean,
constraint primary key(ecId)
);

create table lecturer(
lecturerId varchar(100),
lecturer_name varchar(100),
constraint primary key(lecturerId)
);

create table subject(
subjectId varchar(100),
semesterId int,
title varchar(100),
credits int,
allowed boolean,
constraint primary key(subjectId),
constraint foreign key(semesterId) references semester(semesterId) on delete cascade
);

create table subject_degree(
subdegid int auto_increment,
degreeId int,
subjectId varchar(100),
constraint primary key(subdegid),
constraint foreign key(degreeId) references degree(degreeId) on delete cascade,
constraint foreign key(subjectId) references subject(subjectId) on delete cascade
);

create table subject_lecturer(
subjectLecturerId int auto_increment,
subjectId varchar(100),
lecturerId varchar(100),
current boolean,
constraint primary key(subjectLecturerId),
constraint foreign key(subjectId) references subject(subjectId) on delete cascade,
constraint foreign key(lecturerId) references lecturer(lecturerId) on delete cascade
);

create table marks(
uId varchar(100),
ecId int,
dateOfSubmission date,
subjectLecturerId int,
marks int,
constraint primary key(uId,ecId,dateOfSubmission,subjectLecturerId),
constraint foreign key(uId) references user(uId) on delete cascade,
constraint foreign key(subjectLecturerId) references subject_lecturer(subjectLecturerId) on delete cascade,
constraint foreign key(ecId) references evaluation_criteria(ecId) on delete cascade
);

INSERT INTO `studentfeedback`.`batch`
(`intake`,
`name`)
VALUES
('2019-01-05','2019 January'),('2020-01-05','2020 January'),('2019-06-05','2019 June');

INSERT INTO `studentfeedback`.`degree`
(`name`)
VALUES
('BSc (Computer Science)'),('BSc (Information Systems)');

INSERT INTO `studentfeedback`.`user`
(`uId`,
`password`,
`accountType`,
`emailAddress`)
VALUES
('IT123','123','student','imalkagunawardana1@gmail.com'),('IT456','456','student','imalkagunawardana1@gmail.com'),('ADMIN789','789','admin','imalkagunawardana1@gmail.com');

INSERT INTO `studentfeedback`.`semester`
(`name`)
VALUES
('Year 1 / Semester 1'),('Year 1 / Semester 2'),('Year 2 / Semester 1'),('Year 2 / Semester 2');

INSERT INTO `studentfeedback`.`student`
(`uId`,
`degreeId`,
`batchId`,
`semesterId`,
`student_name`,
`national_id`)
VALUES
('IT123',1,1,1,'Amal Silva','951761150V'),('IT456',2,2,1,'Kamal Silva','961751150V');

INSERT INTO `studentfeedback`.`admin`
(`uId`,
`admin_name`)
VALUES
('ADMIN789','Nimal Silva');

INSERT INTO `studentfeedback`.`evaluation_criteria`
(`text`,
`criteriaHeading`)
VALUES
('Enthusiasm',true),
('Lecturer was punctual',false),
('Organization',true),
('Lecturer was prepared for lectures',false),
('Lectureres were well structured',false),
('Details of course content and learning outcomes were provided',false),
('Lecturer student interaction',true),
('Student were encouraged to ask questions',false),
('Lecturer appreciated students participation',false),
('Student were motivated to learn',false),
('Attention was given to the students individually',false),
('Feedback provided for the students questions were understood',false),
('Task Organization',true),
('Speed of the lecturer was reasonable',false),
('Usage of teaching aid(PPT, video clips, white board, etc)',false),
('Usage of textbooks, websites, periodicals, etc. were recommended',false),
('Worked examples/tutorials were provided',false),
('Sufficient time was provided for tutorials/worked examples',false),
('Lecturer advised regarding examinations',false),
('Feedback on continous assignments/tutorials were helpful',false),
('Clarity',true),
('Black/white board or powerpoint presentations were clear',false),
('Lecturer was sufficiently audible',false),
('Lecturer was clear when communicating with students',false),
('Learning Experiences',true),
('Lecture was good',false),
('Course content',false),
('Module/subject was understood',false);

INSERT INTO `studentfeedback`.`lecturer`
(`lecturerId`,
`lecturer_name`)
VALUES
('L001','Kamal Perera'),('L002','Nimal Silva');

INSERT INTO `studentfeedback`.`subject`
(`subjectId`,
`semesterId`,
`title`,
`credits`,
`allowed`)
VALUES
('IT 101',1,'Programming',3,true),('IT 102',1,'Database Management System',2,true),
('IT 201',2,'Programming - 2',3,true),('IT 202',2,'Database Management System - 2',2,false),
('IT 301',3,'Programming - 3',3,true),('IT 302',3,'Database Management System - 3',2,true),
('IS 103',1,'Web Development',2,true),
('IS 203',2,'Web Development - 2',2,false),
('IS 303',3,'Web Development - 3',2,false);

INSERT INTO `studentfeedback`.`subject_degree`
(`subjectId`,
`degreeId`)
VALUES
('IT 101',1),('IT 101',2),('IT 201',1),('IT 202',1),('IT 201',2),('IT 302',1),
('IS 103',2),('IS 203',2),('IS 303',2);

INSERT INTO `studentfeedback`.`subject_lecturer`
(`subjectId`,
`lecturerId`,
`current`)
VALUES
('IT 101','L001',true),('IT 102','L002',true),('IT 201','L001',true),('IT 202','L002',true),('IT 301','L001',true),('IT 302','L002',true),
('IS 103','L001',true),('IS 203','L002',true),('IS 303','L001',true);