CREATE DATABASE IF NOT EXISTS TESTDB1;
USE TESTDB1;
CREATE TABLE IF NOT EXISTS Passwords(
	Id int NOT NULL AUTO_INCREMENT,
	Name varchar(50),
	Category varchar(50),
	Pass varchar(30),
	PRIMARY KEY(Id)
);
	