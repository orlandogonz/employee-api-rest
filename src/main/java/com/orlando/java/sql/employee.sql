DROP DATABASE IF EXISTS educacionit;
CREATE DATABASE educacionit;
USE educacionit;

CREATE TABLE `educacionit`.`employee` (
`id` INT NOT NULL AUTO_INCREMENT,
`firstName` VARCHAR(45) NOT NULL,
`lastName` VARCHAR(45) NOT NULL,
`title` VARCHAR(45) NOT NULL,
`department` VARCHAR(45) NOT NULL,
`managerId` INT NULL,
`city` VARCHAR(45) NOT NULL,
`officePhone` VARCHAR(45) NOT NULL,
`cellPhone` VARCHAR(45) NOT NULL,
`email` VARCHAR(45) NOT NULL,
`picture` VARCHAR(45) NOT NULL,
PRIMARY KEY (`id`));


INSERT INTO `educacionit`.`employee` (`firstName`, `lastName`, `title`, `department`, `city`, `officePhone`, `cellPhone`, `email`, `picture`)
VALUES ('Hugo Orlando',  'Gonzalez',  'Programmer',  'IT',  'CABA',  '+123', '+321', 'hola@gmail.com', 'xxx.jpg');

INSERT INTO `educacionit`.`employee` (`firstName`, `lastName`, `title`, `department`, `managerId`, `city`, `officePhone`, `cellPhone`, `email`, `picture`)
VALUES ('Gabriela',  'Rivarola',  'Programmer',  'IT',  1,  'CABA',  '+121212', '+212121', 'gmc@gmail.com', 'xxx.jpg');

INSERT INTO `educacionit`.`employee` (`firstName`, `lastName`, `title`, `department`, `managerId`, `city`, `officePhone`, `cellPhone`, `email`, `picture`)
VALUES ('Rosaura',  'Peralta',  'Programmer',  'IT',  1,  'Córdoba',  '+1212122', '+2121213', 'rperalta@gmail.com', 'eee.jpg');

INSERT INTO `educacionit`.`employee` (`firstName`, `lastName`, `title`, `department`, `managerId`, `city`, `officePhone`, `cellPhone`, `email`, `picture`)
VALUES ('Cesar',  'Rios',  'Programmer',  'IT',  1,  'Mendoza',  '+1213182', '+2421913', 'crios@gmail.com', 'e2ee.jpg');


INSERT INTO `educacionit`.`employee` (`firstName`, `lastName`, `title`, `department`, `managerId`, `city`, `officePhone`, `cellPhone`, `email`, `picture`)
VALUES ('Gonzalo',  'Heredia',  'Programmer',  'IT',  1,  'Ushuaia',  '+12122223', '+2126763', 'gheredia@gmail.com', 'eee4.jpg');

