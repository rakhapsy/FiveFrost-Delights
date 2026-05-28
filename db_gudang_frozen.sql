-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 28 Bulan Mei 2026 pada 09.40
-- Versi server: 10.4.22-MariaDB
-- Versi PHP: 7.3.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_gudang_frozen`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `master_pelanggan`
--

CREATE TABLE `master_pelanggan` (
  `id_pelanggan` varchar(20) NOT NULL,
  `nama_pelanggan` varchar(100) NOT NULL,
  `no_telp` varchar(15) DEFAULT NULL,
  `alamat` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `master_pelanggan`
--

INSERT INTO `master_pelanggan` (`id_pelanggan`, `nama_pelanggan`, `no_telp`, `alamat`) VALUES
('PLG-FFD-0001', 'Supermarket Maju Bersama', '081122221111', 'Jl. Jendral Sudirman No. 45, Jakarta Pusat'),
('PLG-FFD-0002', 'Kedai Burger Bangjo', '085788889999', 'Jl. Gejayan No. 89, Yogyakarta'),
('PLG-FFD-0003', 'Toko Frozen Market Depok', '081255556666', 'Jl. Margonda Raya No. 12, Depok'),
('PLG-FFD-0004', 'Warung Makan Mang Ucup', '089611112222', 'Jl. Cihampelas Bawah, Bandung'),
('PLG-FFD-0005', 'Agen Reseller Ibu Sisca', '081377778888', 'Perumahan BSD Sektor 1.2, Tangerang'),
('PLG-FFD-0006', 'Kafe Senja Kopi', '087812345678', 'Jl. Kemang Raya No. 15, Jakarta Selatan'),
('PLG-FFD-0007', 'Kantin SMA Negeri 1', '082233445566', 'Jl. Budi Utomo No. 7, Jakarta Pusat');

-- --------------------------------------------------------

--
-- Struktur dari tabel `master_produk`
--

CREATE TABLE `master_produk` (
  `id_produk` varchar(20) NOT NULL,
  `nama_produk` varchar(100) NOT NULL,
  `kategori` varchar(50) NOT NULL,
  `harga_satuan` decimal(10,2) NOT NULL,
  `stok` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `master_produk`
--

INSERT INTO `master_produk` (`id_produk`, `nama_produk`, `kategori`, `harga_satuan`, `stok`) VALUES
('PRD-FFD-0001', 'Fiesta Chicken Nugget 500g', 'Daging Olahan', '45000.00', 100),
('PRD-FFD-0002', 'Champ Sosis Sapi Bakar 500g', 'Sosis', '35000.00', 100),
('PRD-FFD-0003', 'So Good Spicy Chicken Wing 400g', 'Daging Olahan', '48000.00', 100),
('PRD-FFD-0004', 'Golden Farm French Fries Shoestring 1Kg', 'Kentang', '32000.00', 100),
('PRD-FFD-0005', 'Kanzler Cheese Frankfurter 360g', 'Sosis', '47000.00', 100),
('PRD-FFD-0006', 'Minaku Bola Ikan 500g', 'Seafood', '28000.00', 150),
('PRD-FFD-0007', 'Cedea Chikuwa 250g', 'Seafood', '25000.00', 50),
('PRD-FFD-0008', 'Belfoods Favorite Chicken Karage 500g', 'Daging Olahan', '42000.00', 100),
('PRD-FFD-0009', 'Bernardi Bakso Sapi 50 Pcs', 'Bakso', '85000.00', 0),
('PRD-FFD-0010', 'Edo Edamame Kupas 400g', 'Sayuran', '20000.00', 50),
('PRD-FFD-0011', 'Fiesta Spicy Chick 500g', 'Daging Olahan', '46000.00', 10),
('PRD-FFD-0012', 'Vigo Sosis Ayam 500g', 'Sosis', '22000.00', 50);

-- --------------------------------------------------------

--
-- Struktur dari tabel `master_supplier`
--

CREATE TABLE `master_supplier` (
  `id_supplier` varchar(20) NOT NULL,
  `nama_supplier` varchar(100) NOT NULL,
  `no_telp` varchar(15) DEFAULT NULL,
  `alamat` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `master_supplier`
--

INSERT INTO `master_supplier` (`id_supplier`, `nama_supplier`, `no_telp`, `alamat`) VALUES
('SUP-FFD-0001', 'PT Charoen Pokphand Indonesia (Fiesta)', '0215551234', 'Jl. Ancol Barat VIII, Jakarta Utara'),
('SUP-FFD-0002', 'PT Macroprima Panganutama (Kanzler)', '0218889999', 'Kawasan Industri Sentul, Bogor'),
('SUP-FFD-0003', 'CV Golden Mutiara Makmur (Kentang)', '081233334444', 'Jl. Rungkut Industri, Surabaya'),
('SUP-FFD-0004', 'UD Lautan Rejeki (Seafood)', '081566667777', 'Pelabuhan Muara Baru, Jakarta'),
('SUP-FFD-0005', 'PT Kelola Mina Laut', '0313984567', 'Jl. Veteran No. 12, Gresik'),
('SUP-FFD-0006', 'PT Japfa Comfeed Indonesia (So Good)', '083877340694', 'Jl. Tipar Cakung No.49 3'),
('SUP-FFD-0007', 'PT Champ Resto Indonesia (Champ)', '021777882362', 'Jl. Jendral Sudirman'),
('SUP-FFD-0008', 'PT CitraDimensi Arthali (Cedea)', '021888387427', 'Jl. Pari Raya'),
('SUP-FFD-0009', 'PT Belfoods Indonesia (Belfoods)', '02199939482', 'Komplek Citra Indah Bukit Bunga No. 3'),
('SUP-FFD-0010', 'PT Austindo Nusantara Jaya (Edamame)', '02155523747', 'Jl. Jendral Sudirman'),
('SUP-FFD-0011', 'PT Madusari Nusaperdana (Vigo)', '02178783471', 'Jl. Jababeka XIV A, Blok J No. 5 N-O');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pengguna`
--

CREATE TABLE `pengguna` (
  `id_user` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('Superadmin','Admin','Gudang') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `pengguna`
--

INSERT INTO `pengguna` (`id_user`, `username`, `password`, `role`) VALUES
(1, 'Rakha', 'e34f92a20532a873cb3184398070b4b82a8fa29cf48572c203dc5f0fa6158231', 'Superadmin'),
(2, 'Bima', '1a62eac618f519df0f710271f67285afe8bab689f9c7d902157c50a9c90f4f9e', 'Gudang'),
(5, 'Tsaira', '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9', 'Admin');

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi_keluar`
--

CREATE TABLE `transaksi_keluar` (
  `id_keluar` int(11) NOT NULL,
  `tanggal` date NOT NULL,
  `id_produk` varchar(20) DEFAULT NULL,
  `id_pelanggan` varchar(20) DEFAULT NULL,
  `qty_keluar` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `transaksi_keluar`
--

INSERT INTO `transaksi_keluar` (`id_keluar`, `tanggal`, `id_produk`, `id_pelanggan`, `qty_keluar`) VALUES
(1, '2026-04-06', 'PRD-FFD-0001', 'PLG-FFD-0003', 100),
(2, '2026-04-06', 'PRD-FFD-0001', 'PLG-FFD-0005', 100),
(3, '2026-04-06', 'PRD-FFD-0001', 'PLG-FFD-0001', 50),
(4, '2026-04-07', 'PRD-FFD-0003', 'PLG-FFD-0001', 200),
(5, '2026-04-07', 'PRD-FFD-0004', 'PLG-FFD-0002', 100),
(6, '2026-04-07', 'PRD-FFD-0004', 'PLG-FFD-0001', 100),
(7, '2026-04-08', 'PRD-FFD-0004', 'PLG-FFD-0004', 50),
(8, '2026-04-08', 'PRD-FFD-0004', 'PLG-FFD-0006', 200),
(9, '2026-05-07', 'PRD-FFD-0004', 'PLG-FFD-0007', 50),
(10, '2026-05-08', 'PRD-FFD-0005', 'PLG-FFD-0001', 100),
(11, '2026-05-11', 'PRD-FFD-0005', 'PLG-FFD-0003', 100),
(12, '2026-04-20', 'PRD-FFD-0006', 'PLG-FFD-0007', 100),
(13, '2026-05-04', 'PRD-FFD-0001', 'PLG-FFD-0001', 150),
(14, '2026-05-11', 'PRD-FFD-0005', 'PLG-FFD-0004', 150),
(15, '2026-05-15', 'PRD-FFD-0006', 'PLG-FFD-0007', 50),
(16, '2026-05-18', 'PRD-FFD-0004', 'PLG-FFD-0002', 200),
(17, '2026-05-20', 'PRD-FFD-0004', 'PLG-FFD-0003', 100),
(18, '2026-05-20', 'PRD-FFD-0003', 'PLG-FFD-0001', 100),
(19, '2026-05-21', 'PRD-FFD-0005', 'PLG-FFD-0005', 50),
(20, '2026-05-25', 'PRD-FFD-0001', 'PLG-FFD-0006', 100);

--
-- Trigger `transaksi_keluar`
--
DELIMITER $$
CREATE TRIGGER `trigger_batal_stok_keluar` AFTER DELETE ON `transaksi_keluar` FOR EACH ROW BEGIN
    UPDATE master_produk 
    SET stok = stok + OLD.qty_keluar 
    WHERE id_produk = OLD.id_produk;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trigger_kurang_stok_keluar` AFTER INSERT ON `transaksi_keluar` FOR EACH ROW BEGIN
    UPDATE master_produk 
    SET stok = stok - NEW.qty_keluar 
    WHERE id_produk = NEW.id_produk;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi_masuk`
--

CREATE TABLE `transaksi_masuk` (
  `id_masuk` int(11) NOT NULL,
  `tanggal` date NOT NULL,
  `id_produk` varchar(20) DEFAULT NULL,
  `id_supplier` varchar(20) DEFAULT NULL,
  `qty_masuk` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `transaksi_masuk`
--

INSERT INTO `transaksi_masuk` (`id_masuk`, `tanggal`, `id_produk`, `id_supplier`, `qty_masuk`) VALUES
(1, '2026-04-01', 'PRD-FFD-0001', 'SUP-FFD-0001', 300),
(2, '2026-04-03', 'PRD-FFD-0003', 'SUP-FFD-0006', 200),
(4, '2026-04-03', 'PRD-FFD-0005', 'SUP-FFD-0002', 250),
(5, '2026-04-10', 'PRD-FFD-0006', 'SUP-FFD-0005', 150),
(6, '2026-04-17', 'PRD-FFD-0004', 'SUP-FFD-0003', 500),
(7, '2026-05-01', 'PRD-FFD-0001', 'SUP-FFD-0001', 300),
(8, '2026-05-08', 'PRD-FFD-0005', 'SUP-FFD-0002', 250),
(9, '2026-05-08', 'PRD-FFD-0003', 'SUP-FFD-0006', 200),
(10, '2026-05-08', 'PRD-FFD-0006', 'SUP-FFD-0005', 150),
(11, '2026-05-15', 'PRD-FFD-0004', 'SUP-FFD-0003', 400),
(12, '2026-04-27', 'PRD-FFD-0002', 'SUP-FFD-0007', 100),
(13, '2026-05-01', 'PRD-FFD-0007', 'SUP-FFD-0008', 50),
(14, '2026-05-01', 'PRD-FFD-0008', 'SUP-FFD-0009', 100),
(15, '2026-04-30', 'PRD-FFD-0010', 'SUP-FFD-0010', 50),
(16, '2026-05-01', 'PRD-FFD-0012', 'SUP-FFD-0011', 50),
(17, '2026-04-06', 'PRD-FFD-0011', 'SUP-FFD-0001', 10);

--
-- Trigger `transaksi_masuk`
--
DELIMITER $$
CREATE TRIGGER `trigger_batal_stok_masuk` AFTER DELETE ON `transaksi_masuk` FOR EACH ROW BEGIN
    UPDATE master_produk 
    SET stok = stok - OLD.qty_masuk 
    WHERE id_produk = OLD.id_produk;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trigger_tambah_stok_masuk` AFTER INSERT ON `transaksi_masuk` FOR EACH ROW BEGIN
    UPDATE master_produk 
    SET stok = stok + NEW.qty_masuk 
    WHERE id_produk = NEW.id_produk;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi_retur`
--

CREATE TABLE `transaksi_retur` (
  `id_retur` int(11) NOT NULL,
  `tanggal` date NOT NULL,
  `id_produk` varchar(20) DEFAULT NULL,
  `jenis_retur` enum('Ke Supplier','Dari Pelanggan') NOT NULL,
  `qty_retur` int(11) NOT NULL,
  `keterangan` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `transaksi_retur`
--

INSERT INTO `transaksi_retur` (`id_retur`, `tanggal`, `id_produk`, `jenis_retur`, `qty_retur`, `keterangan`) VALUES
(4, '2026-05-22', 'PRD-FFD-0006', 'Dari Pelanggan', 20, 'Isi produk tidak sesuai'),
(5, '2026-05-25', 'PRD-FFD-0006', 'Ke Supplier', 20, 'Kemasan rusak');

--
-- Trigger `transaksi_retur`
--
DELIMITER $$
CREATE TRIGGER `trigger_batal_stok_retur` AFTER DELETE ON `transaksi_retur` FOR EACH ROW BEGIN
    -- Jika retur DARI PELANGGAN dibatalkan (stok dikurangi kembali)
    IF OLD.jenis_retur = 'Dari Pelanggan' THEN
        UPDATE master_produk 
        SET stok = stok - OLD.qty_retur 
        WHERE id_produk = OLD.id_produk;
        
    -- Jika retur KE SUPPLIER dibatalkan (stok ditambahkan kembali)
    ELSEIF OLD.jenis_retur = 'Ke Supplier' THEN
        UPDATE master_produk 
        SET stok = stok + OLD.qty_retur 
        WHERE id_produk = OLD.id_produk;
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trigger_tambah_stok_retur` AFTER INSERT ON `transaksi_retur` FOR EACH ROW BEGIN
    -- Cek apakah retur dari pelanggan (barang masuk kembali ke gudang)
    IF NEW.jenis_retur = 'Dari Pelanggan' THEN
        UPDATE master_produk 
        SET stok = stok + NEW.qty_retur 
        WHERE id_produk = NEW.id_produk;
        
    -- Cek apakah retur ke supplier (barang cacat dikembalikan ke pabrik)
    ELSEIF NEW.jenis_retur = 'Ke Supplier' THEN
        UPDATE master_produk 
        SET stok = stok - NEW.qty_retur 
        WHERE id_produk = NEW.id_produk;
    END IF;
END
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `master_pelanggan`
--
ALTER TABLE `master_pelanggan`
  ADD PRIMARY KEY (`id_pelanggan`);

--
-- Indeks untuk tabel `master_produk`
--
ALTER TABLE `master_produk`
  ADD PRIMARY KEY (`id_produk`);

--
-- Indeks untuk tabel `master_supplier`
--
ALTER TABLE `master_supplier`
  ADD PRIMARY KEY (`id_supplier`);

--
-- Indeks untuk tabel `pengguna`
--
ALTER TABLE `pengguna`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indeks untuk tabel `transaksi_keluar`
--
ALTER TABLE `transaksi_keluar`
  ADD PRIMARY KEY (`id_keluar`),
  ADD KEY `fk_keluar_produk` (`id_produk`),
  ADD KEY `fk_keluar_pelanggan` (`id_pelanggan`);

--
-- Indeks untuk tabel `transaksi_masuk`
--
ALTER TABLE `transaksi_masuk`
  ADD PRIMARY KEY (`id_masuk`),
  ADD KEY `fk_masuk_produk` (`id_produk`),
  ADD KEY `fk_masuk_supplier` (`id_supplier`);

--
-- Indeks untuk tabel `transaksi_retur`
--
ALTER TABLE `transaksi_retur`
  ADD PRIMARY KEY (`id_retur`),
  ADD KEY `fk_retur_produk` (`id_produk`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `pengguna`
--
ALTER TABLE `pengguna`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT untuk tabel `transaksi_keluar`
--
ALTER TABLE `transaksi_keluar`
  MODIFY `id_keluar` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT untuk tabel `transaksi_masuk`
--
ALTER TABLE `transaksi_masuk`
  MODIFY `id_masuk` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT untuk tabel `transaksi_retur`
--
ALTER TABLE `transaksi_retur`
  MODIFY `id_retur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `transaksi_keluar`
--
ALTER TABLE `transaksi_keluar`
  ADD CONSTRAINT `fk_keluar_pelanggan` FOREIGN KEY (`id_pelanggan`) REFERENCES `master_pelanggan` (`id_pelanggan`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_keluar_produk` FOREIGN KEY (`id_produk`) REFERENCES `master_produk` (`id_produk`) ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `transaksi_masuk`
--
ALTER TABLE `transaksi_masuk`
  ADD CONSTRAINT `fk_masuk_produk` FOREIGN KEY (`id_produk`) REFERENCES `master_produk` (`id_produk`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_masuk_supplier` FOREIGN KEY (`id_supplier`) REFERENCES `master_supplier` (`id_supplier`) ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `transaksi_retur`
--
ALTER TABLE `transaksi_retur`
  ADD CONSTRAINT `fk_retur_produk` FOREIGN KEY (`id_produk`) REFERENCES `master_produk` (`id_produk`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
