CREATE DATABASE IF NOT EXISTS HNProfile;
USE HNProfile;

#DELETE FROM TypeUser WHERE TypeUser.id != -1;
#DELETE FROM Users WHERE Users.id = 5;

#SET FOREIGN_KEY_CHECKS=1;

DROP TABLE IF EXISTS TypeUser;
CREATE TABLE TypeUser (
	id int(11) NOT NULL AUTO_INCREMENT,
    typeName varchar(45) NOT NULL,
    
    PRIMARY KEY(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS Users;
CREATE TABLE Users(
	id int(11) NOT NULL AUTO_INCREMENT,
    userName varchar(45) DEFAULT NULL,
    firstName varchar(45) DEFAULT NULL,
    email varchar(45) DEFAULT NULL,
    typeName int(11) DEFAULT NULL,
    
    PRIMARY KEY(id),
    FOREIGN KEY (typeName) REFERENCES TypeUser(id)
		ON UPDATE CASCADE
		ON DELETE NO ACTION
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO TypeUser VALUES
("1", "Admin"),
("2", "Manager");

INSERT INTO Users VALUES
(1, "A", "AA", "A@mail.com", 1),
(2, "B", "BB", "B@mail.com", 1);

SELECT * FROM TypeUser;
SELECT * FROM Users;