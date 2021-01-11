-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jan 11, 2021 at 09:57 AM
-- Server version: 10.4.15-MariaDB
-- PHP Version: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `u383638241_manvaasam`
--

-- --------------------------------------------------------

--
-- Table structure for table `mas_ad_det`
--

CREATE TABLE `mas_ad_det` (
  `s.no` int(11) NOT NULL,
  `a_uname` varchar(200) NOT NULL,
  `a_pass` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mas_ad_det`
--

INSERT INTO `mas_ad_det` (`s.no`, `a_uname`, `a_pass`) VALUES
(1, 'VmtLODV1UldZdUJRNmZTYUdvZktHeVI1djV6ditEVEFtUjhGUkxsdGMyZz0=', 'UzFtVmlrYWRiZWxIQzNHcGhELzJoZz09 ');

-- --------------------------------------------------------

--
-- Table structure for table `package`
--

CREATE TABLE `package` (
  `sno` int(11) NOT NULL,
  `man_id` varchar(100) NOT NULL,
  `f_name` varchar(100) NOT NULL,
  `f_mobno` varchar(100) NOT NULL,
  `f_addr` varchar(200) NOT NULL,
  `f_pcode` varchar(100) NOT NULL,
  `t_name` varchar(100) NOT NULL,
  `t_mobno` varchar(100) NOT NULL,
  `t_addr` varchar(200) NOT NULL,
  `t_pcode` varchar(100) NOT NULL,
  `p_amt` varchar(100) NOT NULL,
  `p_date` date NOT NULL,
  `p_time` time NOT NULL,
  `c_uname` varchar(200) NOT NULL,
  `st_courid` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `package`
--

INSERT INTO `package` (`sno`, `man_id`, `f_name`, `f_mobno`, `f_addr`, `f_pcode`, `t_name`, `t_mobno`, `t_addr`, `t_pcode`, `p_amt`, `p_date`, `p_time`, `c_uname`, `st_courid`) VALUES
(1, 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', '2020-01-01', '12:00:00', 'test', NULL),
(2, 'MjRSZERINUF1UGlLT29uWmZTMkM2QT09', 'VjN5eDZiT2x2dHEvY1FweXNWajhrUT09', 'eVFCdHNYK0g5VXpHSk5RbkM5QU9BZz09', 'SjNiaXVUWGVxQlVkZmhrdkFDU25PZz09', 'eFlaUEFLc0YzelZOR25kNnpRbjVzZz09', 'dnlKdnRWVklBRlREOUxmNmNkeGVWUT09', 'YXI3NDgwZVhkbFh2eSs2Y2tZSHlVUT09', 'SjNiaXVUWGVxQlVkZmhrdkFDU25PZz09', 'bDlRRlZmNnlFNEMvK1QvejFmMU9qdz09', 'OXpNaVJOVGVzYUE5RTZCQVdEejEydz09', '2021-01-11', '14:38:09', 'VjN5eDZiT2x2dHEvY1FweXNWajhrUT09', 'bUJ4ZjFpeUFmYytwQWZjOE1Wa25pZz09');

-- --------------------------------------------------------

--
-- Table structure for table `team_login`
--

CREATE TABLE `team_login` (
  `sno` int(11) NOT NULL,
  `user` varchar(200) NOT NULL,
  `pswd` varchar(200) NOT NULL,
  `email` varchar(200) NOT NULL,
  `mobile` varchar(200) NOT NULL,
  `type` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `team_login`
--

INSERT INTO `team_login` (`sno`, `user`, `pswd`, `email`, `mobile`, `type`) VALUES
(1, 'M29ITVlJMzcrK2dtaEEvcUYxREJUUT09', 'bXNNejc3cm5CaVhERUtDc2pZZUJNQT09', 'ODN1T1dUTFFrdWlwRDZkWFlIUzhJdGw3RlBxSUZ1MXExU0dTUWkrOERuRT0=', 'WlJyczJ6NDZzS0xJQmdyVkZ2SXpIZz09', '0'),
(2, 'bW5QdVVYWTFyditOUkhQRUN6SHliUT09', 'bkFOOTY1d1ExNnUrSTRxYUl2QTZnZz09', 'RUtZZHo5OXhkOXBENDRpTU85aFdMUDFyaUJsWlZGL2E0RHFEMm1RaFBhND0=', 'Z25sRlh5NW1tVmJsV0RDVjFlMGVGQT09', '1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `mas_ad_det`
--
ALTER TABLE `mas_ad_det`
  ADD PRIMARY KEY (`s.no`);

--
-- Indexes for table `package`
--
ALTER TABLE `package`
  ADD PRIMARY KEY (`sno`);

--
-- Indexes for table `team_login`
--
ALTER TABLE `team_login`
  ADD PRIMARY KEY (`sno`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `mas_ad_det`
--
ALTER TABLE `mas_ad_det`
  MODIFY `s.no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `package`
--
ALTER TABLE `package`
  MODIFY `sno` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `team_login`
--
ALTER TABLE `team_login`
  MODIFY `sno` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
