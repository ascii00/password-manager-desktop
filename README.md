# About

Password manager, which helps you to manage your passwords in a secure way. You can store all your passwords in one
database in encrypted form.

*the following actions are supported
- Add a new passwords to the database
- Delete passwords from database
- Delete the whole category of passwords

MySQL is used us a database

# Built

- instal JDK 17
- intstall MySQL server and run it
- Create a new table and database
```
CREATE DATABASE IF NOT EXISTS Pass;
USE Pass;

CREATE TABLE IF NOT EXISTS Passwords(
    Id int NOT NULL AUTO_INCREMENT,
    Name varchar(50),
    Category varchar(50),
    Pass varchar(30),
    PRIMARY KEY(Id)
    );
```
- add your login, password and database name to the hibernate.cfg.xml
