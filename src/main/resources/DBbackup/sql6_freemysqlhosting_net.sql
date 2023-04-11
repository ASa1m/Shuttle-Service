-- phpMyAdmin SQL Dump
-- version 4.7.1
-- https://www.phpmyadmin.net/
--
-- Host: sql6.freemysqlhosting.net
-- Generation Time: Jul 04, 2022 at 01:17 PM
-- Server version: 5.5.62-0ubuntu0.14.04.1
-- PHP Version: 7.0.33-0ubuntu0.16.04.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sql6499104`
--
CREATE DATABASE IF NOT EXISTS `sql6499104` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `sql6499104`;

-- --------------------------------------------------------

--
-- Table structure for table `Account`
--

CREATE TABLE `Account` (
  `Name` text,
  `UserName` text,
  `Password` text,
  `SecurityQuestion` text,
  `Answer` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Account`
--

INSERT INTO `Account` (`Name`, `UserName`, `Password`, `SecurityQuestion`, `Answer`) VALUES
('Chaitanya', 'Jack', 'skumar14', 'What is your favourite colour?', 'Orange'),
('Soumay', 'Sam', 'kayapata', 'What is your favourite colour?', 'Blue'),
('Yoyo', 'Kabuto', 'cartoon', 'What is your nick name?', 'Pogo'),
('Danish', 'Danish', 'mda1458', 'What is your nick name?', 'Dani'),
('Tosiq', 'Tosiq', 'tosiq302', 'What is your nick name?', 'Toshi'),
('Hassan', 'Hassan', 'Hassan', 'What is your nick name?', 'Hassan'),
('Onkar', 'Onkar', 'Onkar', 'What is your nick name?', 'Onkar'),
('Abdullah Saim', 'Abdullah', 'Saim', 'What is your nick name?', 'Saim'),
('Python', 'Python', 'Saim', 'What is your nick name?', 'SAim'),
('Areeba', 'Areeba', 'Areeba', 'What is your nick name?', 'Areeba'),
('Saim', 'Saim', 'Saim', 'What is your nick name?', 'Saim'),
('MehranWahid', 'Mehran', '12345', 'What is your nick name?', 'moo'),
('M YAmeen', 'Yameen', 'Ass', 'What is your favourite colour?', 'Pink');

-- --------------------------------------------------------

--
-- Table structure for table `AdminAccount`
--

CREATE TABLE `AdminAccount` (
  `UserName` text,
  `Password` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `AdminAccount`
--

INSERT INTO `AdminAccount` (`UserName`, `Password`) VALUES
('Admin', 'Admin');

-- --------------------------------------------------------

--
-- Table structure for table `Bookings`
--

CREATE TABLE `Bookings` (
  `Username` text,
  `Source` text,
  `Destination` text,
  `Seats` text,
  `Date` text,
  `TimeOfPickup` text,
  `Status` text,
  `Time` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Bookings`
--

INSERT INTO `Bookings` (`Username`, `Source`, `Destination`, `Seats`, `Date`, `TimeOfPickup`, `Status`, `Time`) VALUES
('Saim', 'C1', 'Library', '1', '11/06/2022', '2:00pm', 'Rejected', 0),
('Saim', 'C1', 'C2', '2', '01/01/2015', '2:00pm', 'Accepted', 0),
('Danish', 'Hostel', 'HBL Ground', '1', '11/06/2022', '2:00pm', 'Accepted', 5),
('Saim', 'Gate 2', 'Hostel', '4', '11/06/2022', '9:00am', 'Accepted', 15),
('Saim', 'HBL Bank', 'Gate 1', '1', '12/06/2022', '8:30pm', 'Accepted', 5),
('Tosiq', 'Library', 'G-9', '1', '11/06/2022', '8:00pm', 'Rejected', 120),
('Saim', 'Library', 'C1', '2', '11/06/2022', '7:00pm', 'Accepted', 8),
('Mehran', 'C2', 'SEECS', '1', '01/06/2022', '2:00pm', 'Accepted', 0),
('Saim', 'Zakaria', 'Attar', '2', '01/07/2022', '2:00pm', 'Accepted', 50),
('Saim', 'Attar', 'Retro', '4', '01/07/2022', '2:00pm', 'Accepted', 0),
('Saim', 'Zakaria', 'Shuttle', '2', '01/07/2022', '2:00pm', 'Rejected', 5),
('Mehran', 'zakaria', 'Retro', '4', '04/07/2022', '9:00 am', 'Accepted', 50),
('Yameen', 'Zakaria', 'Library', '2', '04/07/2022', '9:00 am', 'Accepted', 20),
('Mehran', 'Zakaria', 'C2', '4', '04/07/2022', '9:00 am', 'Accepted', 120),
('Mehran', 'Zakaria', 'Retro', '3', '04/07/2022', '9:00 am', 'Accepted', 120);

-- --------------------------------------------------------

--
-- Table structure for table `Drivers`
--

CREATE TABLE `Drivers` (
  `Name` text,
  `Username` text,
  `Password` text,
  `SecurityQuestion` text,
  `Answer` text,
  `ID` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Drivers`
--

INSERT INTO `Drivers` (`Name`, `Username`, `Password`, `SecurityQuestion`, `Answer`, `ID`) VALUES
('Python', 'Python', 'Saim', 'What is your nick name?', 'SAim', 123),
('123', 'Yameen', '123', 'What is your favourite colour?', 'Pink', 111);

-- --------------------------------------------------------

--
-- Table structure for table `ShuttleLocations`
--

CREATE TABLE `ShuttleLocations` (
  `ID` double NOT NULL DEFAULT '0',
  `X` double DEFAULT NULL,
  `Y` double DEFAULT NULL,
  `status` tinyint(1) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ShuttleLocations`
--

INSERT INTO `ShuttleLocations` (`ID`, `X`, `Y`, `status`) VALUES
(111, 193.39999999999998, -50, 1),
(123, 163.8, 84.39999999999998, 1),
(145, 100, -25, 1),
(223, 1, 1, 0),
(342, 78, 33, 1);

-- --------------------------------------------------------

--
-- Table structure for table `UserLocations`
--

CREATE TABLE `UserLocations` (
  `ID` double NOT NULL DEFAULT '0',
  `X` double DEFAULT NULL,
  `Y` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `UserLocations`
--

INSERT INTO `UserLocations` (`ID`, `X`, `Y`) VALUES
(111, 170.39999999999998, -30),
(145, 87, -60),
(164, 67, -120);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ShuttleLocations`
--
ALTER TABLE `ShuttleLocations`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `UserLocations`
--
ALTER TABLE `UserLocations`
  ADD PRIMARY KEY (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
