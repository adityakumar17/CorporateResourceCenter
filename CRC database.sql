CREATE DATABASE crc;

CREATE TABLE admin (
    admin_id INT AUTO_INCREMENT PRIMARY KEY,
    user VARCHAR(255) NOT NULL,
    pass VARCHAR(255) NOT NULL
);

CREATE TABLE Company (
    CompanyId INT PRIMARY KEY AUTO_INCREMENT,
    CompanyName VARCHAR(255) NOT NULL,
    Email VARCHAR(255),
    Location VARCHAR(255) NOT NULL
);

CREATE TABLE placement (
    PlacementId INT PRIMARY KEY NOT NULL,
    Status VARCHAR(255)
);


CREATE TABLE student (
    ERPID VARCHAR(255) PRIMARY KEY NOT NULL,
    Name VARCHAR(255),
    Course VARCHAR(255),
    Batch VARCHAR(255),
    MobileNumber BIGINT NOT NULL,
    EmailAddress VARCHAR(255),
    PlacementStatus INT DEFAULT 1,
    CompanyId INT DEFAULT 0
);

CREATE TABLE usercredentials (
    User_Id INT PRIMARY KEY NOT NULL,
    user VARCHAR(255) NOT NULL,
    pass VARCHAR(255) NOT NULL
);

INSERT INTO `crc`.`placement` (`PlacementId`, `Status`) VALUES ('1', 'Not Placed');
INSERT INTO `crc`.`placement` (`PlacementId`, `Status`) VALUES ('2','Placed');

