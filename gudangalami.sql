-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 07, 2022 at 03:00 AM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gudangalami`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `id_barang` int(11) NOT NULL,
  `nama_barang` varchar(255) NOT NULL,
  `satuan` varchar(255) DEFAULT NULL,
  `stock` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`id_barang`, `nama_barang`, `satuan`, `stock`) VALUES
(24, 'Liquid vape', 'box', '83'),
(30, 'Asbak', 'lusin', '185'),
(31, 'Mouse', 'Lusin', '5'),
(33, 'Keyboard', 'pcs', '12'),
(36, 'Skin care', 'Pcs', '10'),
(37, 'Floridina', 'Pcs', '811'),
(38, 'Logitic', 'pcs', '158'),
(39, 'sabun', 'pcs', '136'),
(40, 'Korek', 'pcs', '47');

-- --------------------------------------------------------

--
-- Table structure for table `barang_keluar`
--

CREATE TABLE `barang_keluar` (
  `id_barang_keluar` int(11) NOT NULL,
  `kode_barang_keluar` varchar(11) NOT NULL,
  `id_barang` int(200) NOT NULL,
  `tanggal_barang_keluar` varchar(255) NOT NULL,
  `jumlah_barang_keluar` varchar(255) NOT NULL,
  `pt` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `barang_keluar`
--

INSERT INTO `barang_keluar` (`id_barang_keluar`, `kode_barang_keluar`, `id_barang`, `tanggal_barang_keluar`, `jumlah_barang_keluar`, `pt`) VALUES
(25, 'S0BTN', 24, '2022-03-02', '10', 'pt kiyomasaa'),
(26, '9Y2AP', 24, '2022-03-03', '10', 'pt kiyomasa');

-- --------------------------------------------------------

--
-- Table structure for table `barang_masuk`
--

CREATE TABLE `barang_masuk` (
  `id_barang_masuk` int(11) NOT NULL,
  `kode_barang_masuk` varchar(200) NOT NULL,
  `id_barang` int(11) NOT NULL,
  `tanggal_barang_masuk` varchar(200) NOT NULL,
  `jumlah_barang_masuk` varchar(200) NOT NULL,
  `pt` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `barang_masuk`
--

INSERT INTO `barang_masuk` (`id_barang_masuk`, `kode_barang_masuk`, `id_barang`, `tanggal_barang_masuk`, `jumlah_barang_masuk`, `pt`) VALUES
(186, 'N9HSJ', 24, '2022-03-03', '10', 'Pt Kiyomasa'),
(187, 'D9MX3', 39, '2022-03-02', '45', 'PT stick'),
(188, 'VAE3O', 38, '2022-03-02', '78', 'pt Kiyomasa'),
(189, 'NKITA', 24, '2022-03-02', '30', 'pt kiyomasa'),
(190, '8ZMWP', 30, '2022-03-09', '12', 'pt kiyomasa');

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE `kategori` (
  `id_kategori` int(11) NOT NULL,
  `kategori` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `user_id` int(11) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `level` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`user_id`, `username`, `password`, `email`, `level`) VALUES
(2, 'admin', '123', 'bagusromadhon08@gmail.com', 'admin'),
(3, 'bagus', 'bagus', 'email', 'admin'),
(6, 'kepala', '123', 'bagushusaus@gmail.com', 'kepala gudang'),
(7, 'lobot', '123456', 'bagushasadnsa@gmail.com', 'kepala gudang'),
(8, 'ADMINSJAASDAS', '456', 'emails@gmail.com', 'ADMIN'),
(9, 'admin kols', '123', 'bagsus @gmail.com', 'admin'),
(12, 'lobot', '123', 'email', 'Kepala Gudang'),
(13, 'baguslobot', '123', 'email@gmail.com', 'Kepala Gudang');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id_barang`);

--
-- Indexes for table `barang_keluar`
--
ALTER TABLE `barang_keluar`
  ADD PRIMARY KEY (`id_barang_keluar`);

--
-- Indexes for table `barang_masuk`
--
ALTER TABLE `barang_masuk`
  ADD PRIMARY KEY (`id_barang_masuk`);

--
-- Indexes for table `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`id_kategori`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `barang`
--
ALTER TABLE `barang`
  MODIFY `id_barang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT for table `barang_keluar`
--
ALTER TABLE `barang_keluar`
  MODIFY `id_barang_keluar` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `barang_masuk`
--
ALTER TABLE `barang_masuk`
  MODIFY `id_barang_masuk` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=191;

--
-- AUTO_INCREMENT for table `kategori`
--
ALTER TABLE `kategori`
  MODIFY `id_kategori` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
