drop database studentfeedback;
create database studentfeedback;
use studentfeedback;
create table user(uid int auto_increment,username varchar(100),password varchar(100),constraint primary key(uid));	

INSERT INTO `studentfeedback`.`user`
(
`username`,
`password`)
VALUES
('abc@gmail.com','123');

SELECT `user`.`uid`,
    `user`.`username`,
    `user`.`password`
FROM `studentfeedback`.`user`;


