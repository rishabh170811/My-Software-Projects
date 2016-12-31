-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 14, 2014 at 09:33 AM
-- Server version: 5.6.10
-- PHP Version: 5.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `library`
--
CREATE DATABASE IF NOT EXISTS `library` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `library`;

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE IF NOT EXISTS `admins` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `F_Name` varchar(25) NOT NULL,
  `L_Name` varchar(25) NOT NULL,
  `Email` varchar(20) NOT NULL,
  `Username` varchar(10) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `Mobile_No` varchar(20) NOT NULL,
  `Status` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `admins`
--

INSERT INTO `admins` (`ID`, `F_Name`, `L_Name`, `Email`, `Username`, `Password`, `Mobile_No`, `Status`) VALUES
(1, 'Rishabh', 'Sharma', 'sharma@gmail.com', 'sharma', 'sharma', '9920190058', 1);

-- --------------------------------------------------------

--
-- Table structure for table `authors`
--

CREATE TABLE IF NOT EXISTS `authors` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `F_Name` varchar(25) NOT NULL,
  `L_Name` varchar(25) NOT NULL,
  `Email` varchar(20) NOT NULL,
  `Username` varchar(10) NOT NULL,
  `Password` varchar(10) NOT NULL,
  `Mobile_No` varchar(20) NOT NULL,
  `STATUS` int(1) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `authors`
--

INSERT INTO `authors` (`ID`, `F_Name`, `L_Name`, `Email`, `Username`, `Password`, `Mobile_No`, `STATUS`) VALUES
(4, 'jignesh', 'jain', 'jj@jj.com', 'jhj', 'jjjj', '9920472410', 1);

-- --------------------------------------------------------

--
-- Table structure for table `bookmarks`
--

CREATE TABLE IF NOT EXISTS `bookmarks` (
  `ID` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Username` varchar(10) NOT NULL,
  `Type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bookmarks`
--

INSERT INTO `bookmarks` (`ID`, `Name`, `Username`, `Type`) VALUES
(12, 'Servlets.pptx', 'tanny', 3),
(6, 'aon.docx', 'tanny', 2),
(9, 'Amazing_choreography.3gp', 'tanny', 4),
(4, 'INSERT_ASCENDING.pdf', 'tanny', 1);

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE IF NOT EXISTS `categories` (
  `id` smallint(6) NOT NULL,
  `name` varchar(256) NOT NULL,
  `description` text NOT NULL,
  `position` smallint(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE IF NOT EXISTS `course` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(50) NOT NULL,
  `Course_desc` varchar(500) NOT NULL,
  `Author_name` varchar(10) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `Image` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=42 ;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`ID`, `course_name`, `Course_desc`, `Author_name`, `start_date`, `end_date`, `Image`) VALUES
(39, 'LearnCryptography', 'Public key and Private key Cryptographic algorithms', 'jhj', '2014-03-22', '2014-04-26', 'upload_CourseVideos/Crypto.jpg'),
(41, 'LearnDSA', 'Data structures like linked lists, stacks and queues', 'jhj', '2014-03-24', '2014-04-29', 'upload_CourseVideos/dsa.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `course_videos`
--

CREATE TABLE IF NOT EXISTS `course_videos` (
  `Course_Name` varchar(50) NOT NULL,
  `Author_Name` varchar(50) NOT NULL,
  `Link` varchar(50) NOT NULL,
  `Video_Name` varchar(50) NOT NULL,
  `Video_desc` varchar(200) NOT NULL,
  `Video_ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`Video_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=27 ;

--
-- Dumping data for table `course_videos`
--

INSERT INTO `course_videos` (`Course_Name`, `Author_Name`, `Link`, `Video_Name`, `Video_desc`, `Video_ID`) VALUES
('LearnCryptography', 'jhj', 'upload_CourseVideos/DNA_Replication_Process.mp4', 'DNA_Replication_Process.mp4', 'DNA Replication in Cryptography!!!', 25);

-- --------------------------------------------------------

--
-- Table structure for table `enrolled_courses`
--

CREATE TABLE IF NOT EXISTS `enrolled_courses` (
  `Username` varchar(10) NOT NULL,
  `Course_name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `enrolled_courses`
--

INSERT INTO `enrolled_courses` (`Username`, `Course_name`) VALUES
('tanny', 'LearnCryptography'),
('tanny', 'LearnDSA'),
('niketh', 'LearnCryptography'),
('niketh', 'LearnDSA');

-- --------------------------------------------------------

--
-- Table structure for table `e_books`
--

CREATE TABLE IF NOT EXISTS `e_books` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` char(100) NOT NULL,
  `Link` char(100) NOT NULL,
  `Description` varchar(200) NOT NULL,
  `Status` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `e_books`
--

INSERT INTO `e_books` (`ID`, `Name`, `Link`, `Description`, `Status`) VALUES
(7, 'Credit_Suisse_VJTI_Coding_Challenge_2014_Rules.pdf', 'upload/Credit_Suisse_VJTI_Coding_Challenge_2014_Rules.pdf', 'Credit Suisse Rules', 1),
(8, 'Virtual Library Project.pdf', 'upload/Virtual Library Project.pdf', 'Virtual Library Problem Statement', 1),
(9, 'INSERT_ASCENDING.pdf', 'upload/INSERT_ASCENDING.pdf', 'Insert ascending in linked list', 0),
(10, 'T_Y_B_Tech_computer.pdf', 'upload/T_Y_B_Tech_computer.pdf', 'TY BTech Syllabus Description ', 0),
(11, 'JNP_Lab.pdf', 'upload/JNP_Lab.pdf', 'JNP Lab Programs', 0);

-- --------------------------------------------------------

--
-- Table structure for table `final_result`
--

CREATE TABLE IF NOT EXISTS `final_result` (
  `studentname` varchar(1000) NOT NULL,
  `coursename` varchar(1000) NOT NULL,
  `Test_Number` int(10) NOT NULL,
  `Student_Avg` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `final_result`
--

INSERT INTO `final_result` (`studentname`, `coursename`, `Test_Number`, `Student_Avg`) VALUES
('tanny', 'LearnCryptography', 1, 5);

-- --------------------------------------------------------

--
-- Table structure for table `managers`
--

CREATE TABLE IF NOT EXISTS `managers` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `F_Name` varchar(25) NOT NULL,
  `L_Name` varchar(25) NOT NULL,
  `Email` varchar(20) NOT NULL,
  `Username` varchar(10) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `Mobile_No` varchar(20) NOT NULL,
  `Status` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `managers`
--

INSERT INTO `managers` (`ID`, `F_Name`, `L_Name`, `Email`, `Username`, `Password`, `Mobile_No`, `Status`) VALUES
(3, 'Murali', 'Kembhavi', 'muralik746@yahoo.com', 'muralik', 'muralik', '9987190083', 0),
(6, 'swapnil', 'rathod', 'swap@google.com', 'swap', 'swapya', '6547893214', 0);

-- --------------------------------------------------------

--
-- Table structure for table `members`
--

CREATE TABLE IF NOT EXISTS `members` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `F_Name` varchar(25) NOT NULL,
  `L_Name` varchar(25) NOT NULL,
  `Email` varchar(20) NOT NULL,
  `Username` varchar(10) NOT NULL,
  `Password` varchar(10) NOT NULL,
  `Mobile_No` varchar(20) NOT NULL,
  `Status` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `members`
--

INSERT INTO `members` (`ID`, `F_Name`, `L_Name`, `Email`, `Username`, `Password`, `Mobile_No`, `Status`) VALUES
(5, 'Niket', 'Khandelwal', 'niket@gmail.com', 'niketh', 'niketh', '1478523690', 0),
(6, 'tan', 'inamdar', 'tan@math.com', 'tanny', 'tanny', '9921478569', 1);

-- --------------------------------------------------------

--
-- Table structure for table `pending_authors`
--

CREATE TABLE IF NOT EXISTS `pending_authors` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `F_Name` varchar(25) NOT NULL,
  `L_Name` varchar(25) NOT NULL,
  `Email` varchar(20) NOT NULL,
  `Username` varchar(10) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `Mobile_No` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `pending_managers`
--

CREATE TABLE IF NOT EXISTS `pending_managers` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `F_Name` varchar(25) NOT NULL,
  `L_Name` varchar(25) NOT NULL,
  `Email` varchar(20) NOT NULL,
  `Username` varchar(10) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `Mobile_No` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10 ;

-- --------------------------------------------------------

--
-- Table structure for table `pending_resources`
--

CREATE TABLE IF NOT EXISTS `pending_resources` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(10) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Link` varchar(100) NOT NULL,
  `Description` varchar(200) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

-- --------------------------------------------------------

--
-- Table structure for table `pm`
--

CREATE TABLE IF NOT EXISTS `pm` (
  `id` bigint(20) NOT NULL,
  `id2` int(11) NOT NULL,
  `title` varchar(256) NOT NULL,
  `user1` bigint(20) NOT NULL,
  `user2` bigint(20) NOT NULL,
  `message` text NOT NULL,
  `timestamp` int(10) NOT NULL,
  `user1read` varchar(3) NOT NULL,
  `user2read` varchar(3) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `research_papers`
--

CREATE TABLE IF NOT EXISTS `research_papers` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(10) NOT NULL,
  `Name` char(100) NOT NULL,
  `Link` char(100) NOT NULL,
  `Description` varchar(200) NOT NULL,
  `Status` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `research_papers`
--

INSERT INTO `research_papers` (`ID`, `Username`, `Name`, `Link`, `Description`, `Status`) VALUES
(7, 'jhj', 'Final_CSL_Report.docx', 'upload/Final_CSL_Report.docx', 'Final CSL Lab Report ', 0),
(8, 'jhj', 'Cover_page.docx', 'upload/Cover_page.docx', 'SAL Lab Cover Page', 1),
(9, 'jhj', 'FinalSELab_report.docx', 'upload/FinalSELab_report.docx', 'SAL Lab Final Report', 1),
(10, 'jhj', 'ExperimentNo1.docx', 'upload/ExperimentNo1.docx', 'SAL Lab Expt No. 1', 0);

-- --------------------------------------------------------

--
-- Table structure for table `result`
--

CREATE TABLE IF NOT EXISTS `result` (
  `studentname` varchar(1000) NOT NULL,
  `Coursename` varchar(1000) NOT NULL,
  `Test_Number` int(10) NOT NULL,
  `Marks` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `result`
--

INSERT INTO `result` (`studentname`, `Coursename`, `Test_Number`, `Marks`) VALUES
('tanny', 'LearnCryptography', 1, 5);

-- --------------------------------------------------------

--
-- Table structure for table `reviews`
--

CREATE TABLE IF NOT EXISTS `reviews` (
  `Username` varchar(10) NOT NULL,
  `Course_name` varchar(20) NOT NULL,
  `Review` varchar(200) NOT NULL,
  `Rate` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `reviews`
--

INSERT INTO `reviews` (`Username`, `Course_name`, `Review`, `Rate`) VALUES
('tanny', 'LearnCryptography', 'very good', 4),
('niketh', 'LearnCryptography', 'very very good', 4);

-- --------------------------------------------------------

--
-- Table structure for table `slides`
--

CREATE TABLE IF NOT EXISTS `slides` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` char(100) NOT NULL,
  `Link` char(100) NOT NULL,
  `Description` varchar(200) NOT NULL,
  `Status` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=17 ;

--
-- Dumping data for table `slides`
--

INSERT INTO `slides` (`ID`, `Name`, `Link`, `Description`, `Status`) VALUES
(13, 'Database Normalization.pptx', 'upload/Database Normalization.pptx', 'Database Normalization', 0),
(14, 'Recovery System.pptx', 'upload/Recovery System.pptx', 'Recovery system ppt', 1),
(15, 'SQL.ppt', 'upload/SQL.ppt', 'SQL commnads and desc ppt', 1),
(16, 'My lecture Indexing and hashing.ppt', 'upload/My lecture Indexing and hashing.ppt', 'Indexing and Hashing ppt', 0);

-- --------------------------------------------------------

--
-- Table structure for table `test`
--

CREATE TABLE IF NOT EXISTS `test` (
  `Test_Number` varchar(50) NOT NULL,
  `Course_name` varchar(50) NOT NULL,
  `Question` varchar(10000) NOT NULL,
  `Answer` varchar(10) NOT NULL,
  `OptA` varchar(1000) NOT NULL,
  `OptB` varchar(1000) NOT NULL,
  `OptC` varchar(1000) NOT NULL,
  `OptD` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `test`
--

INSERT INTO `test` (`Test_Number`, `Course_name`, `Question`, `Answer`, `OptA`, `OptB`, `OptC`, `OptD`) VALUES
('1', 'LearnCryptography', 'What is the science and art of transforming messages to make them secure and immune to attacks?', 'B', 'Sample A', 'Sample B', 'Sample C', 'Sample D'),
('1', 'LearnCryptography', 'What is the original message before transformation?', 'B', 'Sample A', 'Sample B', 'Sample C', 'Sample D'),
('1', 'LearnCryptography', 'What is the message after transformation?', 'B', 'Sample A', 'Sample B', 'Sample C', 'Sample D'),
('1', 'LearnCryptography', 'What algorithm transforms plaintext to ciphertext?', 'B', 'Sample A', 'Sample B', 'Sample C', 'Sample D'),
('1', 'LearnCryptography', 'What algorithm transforms ciphertext to plaintext?', 'D', 'Sample A', 'Sample B', 'Sample C', 'Sample D'),
('1', 'LearnCryptography', 'A combination of an encryption algorithm and a decryption algorithm is called?', 'C', 'Sample A', 'Sample B', 'Sample C', 'Sample D'),
('1', 'LearnCryptography', 'What is a number or a set of numbers on which the cipher operates?', 'A', 'Sample A', 'Sample B', 'Sample C', 'Sample D'),
('1', 'LearnCryptography', 'In a(n) ________ cipher, the same key is used by both the sender and receiver?', 'C', 'Sample A', 'Sample B', 'Sample C', 'Sample D'),
('1', 'LearnCryptography', 'In a(n) ________, the key is called the secret key?', 'D', 'Sample A', 'Sample B', 'Sample C', 'Sample D'),
('1', 'LearnCryptography', 'In a(n) ________ cipher, a pair of keys is used?', 'C', 'Sample A', 'Sample B', 'Sample C', 'Sample D'),
('1', 'LearnCryptography', 'In an asymmetric-key cipher, the sender uses the__________ key?', 'D', 'Sample A', 'Sample B', 'Sample C', 'Sample D'),
('1', 'LearnCryptography', 'In an asymmetric-key cipher, the receiver uses the ______ key?', 'A', 'Sample A', 'Sample B', 'Sample C', 'Sample D'),
('1', 'LearnCryptography', 'A ________ cipher replaces one character with another character?', 'C', 'Sample A', 'Sample B', 'Sample C', 'Sample D'),
('1', 'LearnCryptography', '_________ ciphers can be categorized into two broad categories: monoalphabetic and polyalphabetic?', 'B', 'Sample A', 'Sample B', 'Sample C', 'Sample D'),
('1', 'LearnCryptography', 'The _______ cipher is the simplest monoalphabetic cipher. It uses modular arithmetic with a modulus of 26?', 'A', 'Sample A', 'Sample B', 'Sample C', 'Sample D'),
('1', 'LearnCryptography', 'The Caesar cipher is a _______cipher that has a key of 3?', 'C', 'Sample A', 'Sample B', 'Sample C', 'Sample D'),
('1', 'LearnCryptography', 'The ________ cipher reorders the plaintext characters to create a ciphertext?', 'A', 'Sample A', 'Sample B', 'Sample C', 'Sample D'),
('1', 'LearnCryptography', 'A modern cipher is usually a complex _____cipher made of a combination of different simple ciphers?', 'B', 'Sample A', 'Sample B', 'Sample C', 'Sample D'),
('1', 'LearnCryptography', 'The DES function has _______ components?', 'B', 'Sample A', 'Sample B', 'Sample C', 'Sample D'),
('1', 'LearnCryptography', 'DES uses a key generator to generate sixteen _______ round keys?', 'A', 'Sample A', 'Sample B', 'Sample C', 'Sample D');

-- --------------------------------------------------------

--
-- Table structure for table `topics`
--

CREATE TABLE IF NOT EXISTS `topics` (
  `parent` smallint(6) NOT NULL,
  `id` int(11) NOT NULL,
  `id2` int(11) NOT NULL,
  `title` varchar(256) NOT NULL,
  `message` longtext NOT NULL,
  `authorid` int(11) NOT NULL,
  `timestamp` int(11) NOT NULL,
  `timestamp2` int(11) NOT NULL,
  PRIMARY KEY (`id`,`id2`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `avatar` text NOT NULL,
  `signup_date` int(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `email`, `avatar`, `signup_date`) VALUES
(1, 'muralik', 'f476079fb5a3f59c57f92152a9da9d13f0db6123', 'muralikembhavi@gmail.com', '', 1394784019);

-- --------------------------------------------------------

--
-- Table structure for table `videos`
--

CREATE TABLE IF NOT EXISTS `videos` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Link` varchar(100) NOT NULL,
  `Description` varchar(200) NOT NULL,
  `Status` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=14 ;

--
-- Dumping data for table `videos`
--

INSERT INTO `videos` (`ID`, `Name`, `Link`, `Description`, `Status`) VALUES
(11, 'DNA_Replication_Process.mp4', 'upload/DNA_Replication_Process.mp4', 'DNA Replication process video', 0),
(12, 'Amazing_choreography.3gp', 'upload/Amazing_choreography.3gp', 'Amazing choreography\r\nvideo', 0),
(13, 'CHEETA.3gp', 'upload/CHEETA.3gp', 'Cheetah video', 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
