DROP DATABASE IF EXISTS `tracker_finance`;
CREATE DATABASE `tracker_finance`;
USE `tracker_finance`;

DROP TABLE IF EXISTS `expenses`;
CREATE TABLE `expenses` (
`expenseID` INT(3) NOT NULL AUTO_INCREMENT ,
`title` VARCHAR(50) NOT NULL ,
`category` VARCHAR(30) NOT NULL ,
`amount` DOUBLE(10,2) NOT NULL ,
`dateIncurred` DATE NOT NULL ,
PRIMARY KEY   (`expenseID`)
);

DROP TABLE IF EXISTS `income`;
CREATE TABLE `income` (
`incomeID` INT(3) NOT NULL AUTO_INCREMENT ,
`title` VARCHAR(50) NOT NULL ,
`amount` DOUBLE(10,2) NOT NULL ,
`dateEarned` DATE NOT NULL ,
PRIMARY KEY   (`incomeID`)
);

INSERT INTO expenses VALUES
(null, 'Weekly Shopping', 'Groceries', 68.50, '2025-02-03'),
(null, 'Gym Membership', 'Fitness', 32.00, '2025-02-06'),
(null, 'Phone Bill', 'Utilities', 55.23, '2025-02-12'),
(null, 'Takeout', 'food & Drinks', 25.50 , '2025-02-11'),
(null, 'Electricity Bill', 'Utilities', 85.00 , '2025-02-08'),
(null, 'Netflix Membership', 'Entertainment', 14.99 , '2025-02-06');

INSERT INTO income VALUES
(null, 'Waitering', 250.00, '2025-02-06'),
(null, 'Baby Sitting', 60.00, '2025-02-01'),
(null, 'Vinted', 20.00, '2025-02-03'),
(null, 'Tutoring', 75.00, '2025-02-13');



