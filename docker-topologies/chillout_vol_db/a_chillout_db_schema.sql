-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: chillout_api_db
-- Generation Time: Jan 20, 2020 at 03:24 AM
-- Server version: 8.0.15
-- PHP Version: 7.2.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `chillout_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `client_entity`
--

CREATE TABLE `client_entity` (
  `email` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `order_entity`
--

CREATE TABLE `order_entity` (
  `id` bigint(20) NOT NULL,
  `client_entity_email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `order_entity_order_items`
--

CREATE TABLE `order_entity_order_items` (
  `order_entity_id` bigint(20) NOT NULL,
  `order_items_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `order_item_entity`
--

CREATE TABLE `order_item_entity` (
  `id` bigint(20) NOT NULL,
  `quantity` int(11) NOT NULL,
  `product_entity_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `product_entity`
--

CREATE TABLE `product_entity` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `unit_price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `client_entity`
--
ALTER TABLE `client_entity`
  ADD PRIMARY KEY (`email`);

--
-- Indexes for table `order_entity`
--
ALTER TABLE `order_entity`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKsadwecf977an866bif3y50oqk` (`client_entity_email`);

--
-- Indexes for table `order_entity_order_items`
--
ALTER TABLE `order_entity_order_items`
  ADD UNIQUE KEY `UK_2v14rtg4be1gysqg3msty6q07` (`order_items_id`),
  ADD KEY `FKd14ayn20i4k7dtf7lrrjfa7l9` (`order_entity_id`);

--
-- Indexes for table `order_item_entity`
--
ALTER TABLE `order_item_entity`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9swgawmiwpsh3v3e2k4ir6u4m` (`product_entity_id`);

--
-- Indexes for table `product_entity`
--
ALTER TABLE `product_entity`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `order_entity`
--
ALTER TABLE `order_entity`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `order_item_entity`
--
ALTER TABLE `order_item_entity`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `product_entity`
--
ALTER TABLE `product_entity`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `order_entity`
--
ALTER TABLE `order_entity`
  ADD CONSTRAINT `FKsadwecf977an866bif3y50oqk` FOREIGN KEY (`client_entity_email`) REFERENCES `client_entity` (`email`);

--
-- Constraints for table `order_entity_order_items`
--
ALTER TABLE `order_entity_order_items`
  ADD CONSTRAINT `FK8qj732m0awrq8g1emmw9nv41d` FOREIGN KEY (`order_items_id`) REFERENCES `order_item_entity` (`id`),
  ADD CONSTRAINT `FKd14ayn20i4k7dtf7lrrjfa7l9` FOREIGN KEY (`order_entity_id`) REFERENCES `order_entity` (`id`);

--
-- Constraints for table `order_item_entity`
--
ALTER TABLE `order_item_entity`
  ADD CONSTRAINT `FK9swgawmiwpsh3v3e2k4ir6u4m` FOREIGN KEY (`product_entity_id`) REFERENCES `product_entity` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
