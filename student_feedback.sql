drop database studentfeedback;
create database studentfeedback;
use studentfeedback;

create table faculty(
facultyId int auto_increment,
name varchar(100),
constraint primary key(facultyId)
);

create table degree(
degreeId int auto_increment,
facultyId int,
name varchar(100),
constraint primary key(degreeId),
constraint foreign key(facultyId) references faculty(facultyId) on delete cascade
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

create table evaluation_criteria_heading(
echId int,
text varchar(100),
constraint primary key(echId)
);

create table evaluation_criteria(
ecId int auto_increment,
echId int,
text varchar(100),
constraint primary key(ecId),
constraint foreign key(echId) references evaluation_criteria_heading(echId) on delete cascade
);

create table lecturer(
lecturerId varchar(100),
name varchar(100),
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

INSERT INTO `studentfeedback`.`faculty`
(`name`)
VALUES
('Faculty of Computing');

INSERT INTO `studentfeedback`.`batch`
(`intake`,
`name`)
VALUES
('2019-01-05','2019 January'),('2020-01-05','2020 January'),('2019-06-05','2019 June');

INSERT INTO `studentfeedback`.`degree`
(`facultyId`,`name`)
VALUES
(1,'BSc (Computer Science)'),(1,'BSc (Information Systems)');

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


INSERT INTO `studentfeedback`.`evaluation_criteria_heading`
(`echId`,
`text`)
VALUES
(1,'Enthusiasm'),
(2,'Organization'),
(3,'Lecturer student interaction'),
(4,'Task Organization'),
(5,'Clarity'),
(6,'Learning Experiences');

INSERT INTO `studentfeedback`.`evaluation_criteria`
(`echId`,
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

INSERT INTO `studentfeedback`.`lecturer`
(`lecturerId`,
`name`)
VALUES
('L001','Kamal Perera'),('L002','Nimal Silva');

INSERT INTO `studentfeedback`.`subject`
(`subjectId`,
`semesterId`,
`title`,
`credits`,
`allowed`)
VALUES
('IT 101',1,'Progamming',3,true),('IT 102',1,'Database Management System',2,true),
('IT 201',2,'Progamming - 2',3,true),('IT 202',2,'Database Management System - 2',2,false),
('IT 301',3,'Progamming - 3',3,true),('IT 302',3,'Database Management System - 3',2,true),
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

SELECT `evaluation_criteria_heading`.`echId`,
    `evaluation_criteria_heading`.`text`
FROM `studentfeedback`.`evaluation_criteria_heading`;

SELECT `evaluation_criteria`.`ecId`,
    `evaluation_criteria`.`echId`,
    `evaluation_criteria`.`text`
FROM `studentfeedback`.`evaluation_criteria`;


SELECT `user`.`uId`,
    `user`.`password`,
    `user`.`emailAddress`
FROM `studentfeedback`.`user`;

SELECT `batch`.`batchId`,
    `batch`.`intake`
FROM `studentfeedback`.`batch`;

SELECT `student`.`studentId`,
    `student`.`uId`,
    `student`.`degreeId`,
    `student`.`batchId`,
    `student`.`student_name`,
    `student`.`national_id`
FROM `studentfeedback`.`student`;

SELECT `degree`.`degreeId`,
    `degree`.`facultyId`,
    `degree`.`name`
FROM `studentfeedback`.`degree`;

SELECT `faculty`.`facultyId`,
    `faculty`.`name`
FROM `studentfeedback`.`faculty`;


select f.name,d.name from user u,faculty f,degree d where f.facultyId=d.facultyId && d.degreeId=u.degreeId && u.uId='abc123';

select year(curdate())-year(b.intake),month(curdate())-month(b.intake) from student s,batch b,user u where s.batchId=b.batchId && u.uId=s.uId && s.uId='IT123';

select month(curdate())-month('2020-04-03');

select text from semester where semesterId=2;

select distinct year(intake) from batch;

select uId,student_name,national_id,b.name from student s,batch b,degree d where b.batchId=s.batchId && d.degreeId=s.degreeId && d.degreeId=1 && b.batchId=1 && year(b.intake)='2019' order by uId desc;

SELECT `marks`.`uId`,
    `marks`.`ecId`,
    `marks`.`dateOfSubmission`,
    `marks`.`subjectLecturerId`,
    `marks`.`marks`
FROM `studentfeedback`.`marks`;

SELECT `subject_lecturer`.`subjectLecturerId`,
    `subject_lecturer`.`subjectId`,
    `subject_lecturer`.`lecturerId`,
    `subject_lecturer`.`current`
FROM `studentfeedback`.`subject_lecturer`;


select emailAddress from user where uId='IT123';

select accountType from user where password='951761150V' COLLATE latin1_general_cs;

select accountType from user where BINARY(password) = BINARY('951761151V');

select text,(select sum(marks) from marks m,evaluation_criteria ec where m.ecId=ec.ecId && lecturerId='L001' && subjectId='CSC 201' && text=text),(select count(m.ecId) from marks m,evaluation_criteria ec where m.ecId=ec.ecId && lecturerId='L001' && subjectId='CSC 201') from subject_lecturer sl,marks m,evaluation_criteria ec where sl.subjectLecturerId=m.subjectLecturerId && ec.ecId=m.ecId && lecturerId='L001' && subjectId='CSC 201';

select text,sum(marks),count(marks) from subject_lecturer sl,marks m,evaluation_criteria ec where sl.subjectLecturerId=m.subjectLecturerId && ec.ecId=m.ecId && lecturerId='L001' && subjectId='CSC 201' && dateOfSubmission='2019-07-26' group by 1;

select text,sum(marks),count(marks) from evaluation_criteria 
left join marks on evaluation_criteria.ecId=marks.ecId
inner join subject_lecturer on subject_lecturer.subjectLecturerId=marks.subjectLecturerId && lecturerId='L001' && subjectId='CSC 201' && dateOfSubmission='2019-07-26' group by evaluation_criteria.ecId asc;

select text,sum(marks),count(marks) from evaluation_criteria 
left join marks on evaluation_criteria.ecId=marks.ecId && dateOfSubmission='2019-07-26' && subjectLecturerId=(select subjectLecturerId from subject_lecturer where lecturerId='L001' && subjectId='CSC 201') group by evaluation_criteria.ecId asc;

select text,marks from evaluation_criteria 
left join marks on evaluation_criteria.ecId=marks.ecId;

select year(curdate())-year('2015-09-21'),month(curdate())-month('2015-09-21');

SELECT DATEDIFF(curdate(),'2018-09-21');

select subject.subjectId,title,credits,allowed,lecturer.name,count(marks) from subject
inner join subject_lecturer on subject.subjectId=subject_lecturer.subjectId && semesterId=2
inner join lecturer on subject_lecturer.lecturerId=lecturer.lecturerId && current=true
inner join subject_degree on subject.subjectId=subject_degree.subjectId && degreeId=1
left join marks on marks.subjectLecturerId=subject_lecturer.subjectLecturerId && uId='IT789' && dateOfSubmission='2019-08-01' group by 1;

select s.subjectId,title,l.name,credits from subject s,lecturer l,subject_lecturer sl,subject_degree sd where l.lecturerId=sl.lecturerId && s.subjectId=sl.subjectId && s.subjectId=sd.subjectId && degreeId=? && semesterId=? && current=true;

select count(marks),allowed from subject s,marks m,subject_lecturer sl where s.subjectId=sl.subjectId && sl.subjectLecturerId=m.subjectLecturerId && m.uid='IT789' && m.subjectLecturerId=4 && dateOfSubmission=curdate();