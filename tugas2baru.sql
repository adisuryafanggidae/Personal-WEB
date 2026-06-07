-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 05 Jun 2026 pada 09.31
-- Versi server: 10.4.32-MariaDB-log
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tugas2baru`
--

DELIMITER $$
--
-- Prosedur
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `transaksi1` ()   BEGIN
START TRANSACTION;
INSERT INTO `pasien`
(`id_pasien`, `nama_pasien`, `alamat_pasien`, `jenis_kelamin`)
VALUES(5, 'bowo', 'Jl.Morowali no.10', 'L');
SELECT * FROM pasien; 
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `transaksi2` ()   BEGIN
START TRANSACTION;
INSERT INTO `pasien`
(`id_pasien`, `nama_pasien`, `alamat_pasien`, `jenis_kelamin`)
VALUES(5, 'bowo', 'Jl.Morowali no.10', 'L');
COMMIT;
SELECT * FROM pasien; 
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `transaksi3` ()   BEGIN
START TRANSACTION;
UPDATE pasien
SET nama_pasien='bambang'
WHERE id_pasien='5';
ROLLBACK;
COMMIT;
SELECT * FROM pasien; 
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `transaksi4` ()   BEGIN
START TRANSACTION;
INSERT INTO `pasien`(`id_pasien`, `nama_pasien`, `alamat_pasien`, `jenis_kelamin`)
VALUES(7, 'buda', 'Jl.pahlawan', 'L');
SAVEPOINT POINT1;

INSERT INTO `pasien`(`id_pasien`, `nama_pasien`, `alamat_pasien`, `jenis_kelamin`)
VALUES(8, 'budi', 'Jl.seminyak', 'L');
ROLLBACK TO SAVEPOINT POINT1;

INSERT INTO `pasien`(`id_pasien`, `nama_pasien`, `alamat_pasien`, `jenis_kelamin`)
VALUES(9, 'bude', 'Jl.kesehatan', 'P');
COMMIT;
SELECT * FROM pasien; 
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `administrator`
--

CREATE TABLE `administrator` (
  `id_admin` int(3) NOT NULL,
  `waktu_jaga` varchar(100) DEFAULT NULL,
  `nama_admin` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `daftar`
--

CREATE TABLE `daftar` (
  `id_daftar` int(3) NOT NULL,
  `id_pasien` int(3) DEFAULT NULL,
  `id_admin` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `dokter`
--

CREATE TABLE `dokter` (
  `id_dokter` int(3) NOT NULL,
  `nama_dokter` varchar(30) DEFAULT NULL,
  `tanggal_lahir` date DEFAULT NULL,
  `spesialis` varchar(15) DEFAULT NULL,
  `waktu_kerja` varchar(50) DEFAULT NULL,
  `no_hp` varchar(12) DEFAULT NULL,
  `alamat_dokter` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `dokter_admin`
--

CREATE TABLE `dokter_admin` (
  `id_data` int(3) NOT NULL,
  `id_dokter` int(3) DEFAULT NULL,
  `id_admin` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `obat`
--

CREATE TABLE `obat` (
  `id_obat` int(6) NOT NULL,
  `kode_obat` varchar(5) DEFAULT NULL,
  `nama_obat` varchar(30) DEFAULT NULL,
  `harga` int(10) DEFAULT NULL,
  `stok` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `obat`
--

INSERT INTO `obat` (`id_obat`, `kode_obat`, `nama_obat`, `harga`, `stok`) VALUES
(1, 'A001', 'Amoxilin', 5000, 20),
(2, 'A002', 'Paracetamol', 5000, 10),
(3, 'A003', 'Vitamin C', 5000, 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `pasien`
--

CREATE TABLE `pasien` (
  `id_pasien` int(3) NOT NULL,
  `nama_pasien` varchar(30) DEFAULT NULL,
  `jenis_kelamin` enum('L','P') DEFAULT NULL,
  `alamat_pasien` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `pasien`
--

INSERT INTO `pasien` (`id_pasien`, `nama_pasien`, `jenis_kelamin`, `alamat_pasien`) VALUES
(1, 'siti', 'P', 'jln. pramuka'),
(2, 'jokowi', 'L', 'jln. soekarno-hatta'),
(3, 'angel', 'P', 'jln. bangsawan'),
(4, 'aditya', 'L', 'jln. ks tubun'),
(5, 'bowo', 'L', 'Jl.Morowali no.10'),
(7, 'buda', 'L', 'Jl.pahlawan'),
(9, 'bude', 'P', 'Jl.kesehatan');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pasien_dokter`
--

CREATE TABLE `pasien_dokter` (
  `id` int(3) NOT NULL,
  `id_dokter` int(3) DEFAULT NULL,
  `id_pasien` int(3) DEFAULT NULL,
  `resep` varchar(100) DEFAULT NULL,
  `waktu_periksa` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi_obat`
--

CREATE TABLE `transaksi_obat` (
  `id_transaksi` int(3) NOT NULL,
  `id_pasien` int(3) DEFAULT NULL,
  `id_obat` int(5) DEFAULT NULL,
  `jumlah` int(10) DEFAULT NULL,
  `total_harga` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `transaksi_obat`
--

INSERT INTO `transaksi_obat` (`id_transaksi`, `id_pasien`, `id_obat`, `jumlah`, `total_harga`) VALUES
(123, 4, 3, 4, NULL);

--
-- Trigger `transaksi_obat`
--
DELIMITER $$
CREATE TRIGGER `belanja` AFTER INSERT ON `transaksi_obat` FOR EACH ROW UPDATE obat SET stok = stok-new.jumlah
WHERE id_obat = new.id_obat
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `administrator`
--
ALTER TABLE `administrator`
  ADD PRIMARY KEY (`id_admin`),
  ADD KEY `id_admin` (`id_admin`);

--
-- Indeks untuk tabel `daftar`
--
ALTER TABLE `daftar`
  ADD PRIMARY KEY (`id_daftar`),
  ADD KEY `id_daftar` (`id_daftar`),
  ADD KEY `id_pasien` (`id_pasien`),
  ADD KEY `id_admin` (`id_admin`);

--
-- Indeks untuk tabel `dokter`
--
ALTER TABLE `dokter`
  ADD PRIMARY KEY (`id_dokter`),
  ADD KEY `id_dokter` (`id_dokter`);

--
-- Indeks untuk tabel `dokter_admin`
--
ALTER TABLE `dokter_admin`
  ADD PRIMARY KEY (`id_data`),
  ADD KEY `id_data` (`id_data`),
  ADD KEY `id_dokter` (`id_dokter`),
  ADD KEY `id_admin` (`id_admin`);

--
-- Indeks untuk tabel `obat`
--
ALTER TABLE `obat`
  ADD PRIMARY KEY (`id_obat`);

--
-- Indeks untuk tabel `pasien`
--
ALTER TABLE `pasien`
  ADD PRIMARY KEY (`id_pasien`),
  ADD KEY `id_pasien` (`id_pasien`);

--
-- Indeks untuk tabel `pasien_dokter`
--
ALTER TABLE `pasien_dokter`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`id`),
  ADD KEY `id_dokter` (`id_dokter`),
  ADD KEY `id_pasien` (`id_pasien`);

--
-- Indeks untuk tabel `transaksi_obat`
--
ALTER TABLE `transaksi_obat`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `id_pasien` (`id_pasien`),
  ADD KEY `id_obat` (`id_obat`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `administrator`
--
ALTER TABLE `administrator`
  MODIFY `id_admin` int(3) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `daftar`
--
ALTER TABLE `daftar`
  MODIFY `id_daftar` int(3) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `dokter`
--
ALTER TABLE `dokter`
  MODIFY `id_dokter` int(3) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `dokter_admin`
--
ALTER TABLE `dokter_admin`
  MODIFY `id_data` int(3) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `obat`
--
ALTER TABLE `obat`
  MODIFY `id_obat` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT untuk tabel `pasien`
--
ALTER TABLE `pasien`
  MODIFY `id_pasien` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT untuk tabel `pasien_dokter`
--
ALTER TABLE `pasien_dokter`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `transaksi_obat`
--
ALTER TABLE `transaksi_obat`
  MODIFY `id_transaksi` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=124;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `administrator`
--
ALTER TABLE `administrator`
  ADD CONSTRAINT `administrator_ibfk_1` FOREIGN KEY (`id_admin`) REFERENCES `daftar` (`id_admin`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `daftar`
--
ALTER TABLE `daftar`
  ADD CONSTRAINT `daftar_ibfk_1` FOREIGN KEY (`id_pasien`) REFERENCES `pasien` (`id_pasien`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `dokter`
--
ALTER TABLE `dokter`
  ADD CONSTRAINT `dokter_ibfk_1` FOREIGN KEY (`id_dokter`) REFERENCES `dokter_admin` (`id_dokter`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `dokter_admin`
--
ALTER TABLE `dokter_admin`
  ADD CONSTRAINT `dokter_admin_ibfk_1` FOREIGN KEY (`id_admin`) REFERENCES `administrator` (`id_admin`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `pasien_dokter`
--
ALTER TABLE `pasien_dokter`
  ADD CONSTRAINT `pasien_dokter_ibfk_1` FOREIGN KEY (`id_dokter`) REFERENCES `dokter` (`id_dokter`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pasien_dokter_ibfk_2` FOREIGN KEY (`id_pasien`) REFERENCES `pasien` (`id_pasien`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `transaksi_obat`
--
ALTER TABLE `transaksi_obat`
  ADD CONSTRAINT `transaksi_obat_ibfk_1` FOREIGN KEY (`id_pasien`) REFERENCES `pasien` (`id_pasien`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `transaksi_obat_ibfk_2` FOREIGN KEY (`id_obat`) REFERENCES `obat` (`id_obat`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
