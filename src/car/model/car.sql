-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 2020 m. Kov 29 d. 17:33
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db`
--

-- --------------------------------------------------------

--
-- Sukurta duomenų struktūra lentelei `car`
--

CREATE TABLE `car` (
  `id` int(11) NOT NULL,
  `make` varchar(255) COLLATE utf8mb4_lithuanian_ci NOT NULL,
  `model` varchar(255) COLLATE utf8mb4_lithuanian_ci NOT NULL,
  `feature` varchar(255) COLLATE utf8mb4_lithuanian_ci NOT NULL,
  `fuel` varchar(255) COLLATE utf8mb4_lithuanian_ci NOT NULL,
  `door` int(2) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_lithuanian_ci;

--
-- Sukurta duomenų kopija lentelei `car`
--

INSERT INTO `car` (`id`, `make`, `model`, `feature`, `fuel`, `door`, `user_id`) VALUES
(1, 'Toyota ', 'SupraGT', 'Spoilers,Automated parking,Sport seats,', 'Petrol/electricity', 3, 3),
(2, 'qwerty', 'qweqe 3222', 'Spoilers,Automated parking,Sport seats,', 'Diesel/electricity', 5, 4),
(3, '234234', '234234', 'Spoilers,', 'Electricity', 3, 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `car`
--
ALTER TABLE `car`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `car`
--
ALTER TABLE `car`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
