-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 08, 2017 at 07:54 AM
-- Server version: 5.6.20
-- PHP Version: 5.5.15



SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `project`
--

-- --------------------------------------------------------

--
-- Table structure for table `blocks`
--

CREATE TABLE IF NOT EXISTS `blocks` (
  `id` varchar(255) NOT NULL,
  `block` varchar(100) NOT NULL,
  `building_id` varchar(255) NOT NULL,
  `name` varchar(100) NOT NULL,
  `one_bedroom_units_total` int(11) DEFAULT NULL,
  `two_bedroom_units_total` int(11) DEFAULT NULL,
  `three_bedroom_units_total` int(11) DEFAULT NULL,
  `pent_houses_total` int(11) DEFAULT NULL,
  `one_bedroom_units_available` int(11) DEFAULT NULL,
  `two_bedroom_units_available` int(11) DEFAULT NULL,
  `three_bedroom_units_available` int(11) DEFAULT NULL,
  `pent_houses_available` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `buildings`
--

CREATE TABLE IF NOT EXISTS `buildings` (
  `id` varchar(255) NOT NULL,
  `name` varchar(100) NOT NULL,
  `address` varchar(1000) NOT NULL,
  `one_bedroom_units_total` int(11) NOT NULL,
  `two_bedroom_units_total` int(11) NOT NULL,
  `three_bedroom_units_total` int(11) NOT NULL,
  `pent_houses_total` int(11) NOT NULL,
  `blocks` int(11) NOT NULL,
  `one_bedroom_units_available` int(11) NOT NULL,
  `two_bedroom_units_available` int(11) NOT NULL,
  `three_bedroom_units_available` int(11) NOT NULL,
  `pent_houses_available` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `units`
--

CREATE TABLE IF NOT EXISTS `units` (
  `id` varchar(32) NOT NULL,
  `apartment_number` int(11) NOT NULL,
  `floor_number` int(11) NOT NULL,
  `block_id` varchar(255) NOT NULL,
  `building_id` varchar(255) NOT NULL,
  `customer_number` varchar(255) DEFAULT NULL,
  `address` varchar(1000) NOT NULL,
  `type` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `blocks`
--
ALTER TABLE `blocks`
 ADD PRIMARY KEY (`id`), ADD KEY `building_id` (`building_id`);

--
-- Indexes for table `buildings`
--
ALTER TABLE `buildings`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `units`
--
ALTER TABLE `units`
 ADD PRIMARY KEY (`id`), ADD KEY `property_id` (`building_id`), ADD KEY `block_id` (`block_id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `blocks`
--
ALTER TABLE `blocks`
ADD CONSTRAINT `block_properties` FOREIGN KEY (`building_id`) REFERENCES `buildings` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `units`
--
ALTER TABLE `units`
ADD CONSTRAINT `property_one_bedroom` FOREIGN KEY (`building_id`) REFERENCES `buildings` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
ADD CONSTRAINT `units_ibfk_1` FOREIGN KEY (`block_id`) REFERENCES `blocks` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
