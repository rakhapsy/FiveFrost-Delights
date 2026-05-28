# FiveFrost-Delights
# 📦 FiveFrost Delights - Inventory Management System

FiveFrost Delights adalah aplikasi desktop Sistem Manajemen Gudang (*Inventory Management System*) berbasis Java yang dirancang khusus untuk memfasilitasi dan mengotomatisasi pencatatan distribusi bisnis makanan beku (*Frozen Food*). 

Aplikasi ini menggunakan sistem Relational Database yang kuat dan **SQL Triggers** untuk mengotomatisasi perhitungan stok barang secara *real-time* tanpa intervensi *source code* Java, menjamin akurasi data 100%.

## 🚀 Fitur Utama
- **Smart Dashboard:** Visualisasi grafik JFreeChart (Top 5 Produk & Tren Transaksi).
- **Early Warning System:** Notifikasi tabel otomatis untuk stok produk yang kritis/menipis.
- **Auto-Generate ID:** Pembuatan ID cerdas otomatis (Contoh: `PRD-FFD-0001`).
- **Validasi Transaksi Cerdas:** Pencegahan input huruf dan pemblokiran tombol simpan otomatis jika kuantitas barang melebihi sisa stok di gudang.
- **Logika Retur Ganda:** Retur 'Ke Supplier' otomatis mengurangi stok, Retur 'Dari Pelanggan' otomatis menambah stok.
- **Reporting:** Cetak laporan dinamis berdasarkan filter periode (Tanggal Awal - Akhir) menggunakan JasperReports.

## 🛠️ Persyaratan Sistem (Prerequisites)
Pastikan komputer Anda sudah terinstal perangkat lunak berikut sebelum menjalankan aplikasi:
- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/downloads/) versi 8 atau terbaru.
- [Apache NetBeans IDE](https://netbeans.apache.org/download/index.html) (Disarankan versi 8.2 atau 12+).
- [XAMPP](https://www.apachefriends.org/index.html) (Untuk server lokal MySQL).

## ⚙️ Panduan Instalasi (Installation Guide)

### 1. Konfigurasi Database
1. Buka XAMPP Control Panel dan jalankan modul **Apache** dan **MySQL**.
2. Buka browser dan akses `http://localhost/phpmyadmin/`.
3. Buat database baru dengan nama: **`db_gudang_frozen`**.
4. Pilih tab **Import**, lalu unggah file `db_gudang_frozen.sql` yang ada di repositori ini.
5. Klik **Go**. (Database, tabel, data dummy, dan 6 SQL Trigger akan otomatis terpasang).

### 2. Menjalankan Aplikasi di NetBeans
1. Clone repositori ini: `git clone https://github.com/username-anda/FiveFrost-Delights.git` atau Download sebagai `.zip` lalu ekstrak.
2. Buka **NetBeans IDE**, pilih **File > Open Project**, lalu pilih folder proyek ini.
3. Tambahkan Library:
   - Klik kanan pada folder **Libraries** di panel proyek NetBeans.
   - Pilih **Add JAR/Folder...**
   - Masukkan semua file `.jar` yang terdapat di dalam folder `_backup/lib/*` repositori ini (MySQL Connector, JFreeChart, JCalendar, JasperReports).
4. *(Opsional)* Jika MySQL/XAMPP Anda menggunakan password, sesuaikan koneksinya di dalam file `koneksi/KoneksiDB.java`.
5. Klik kanan pada nama proyek, pilih **Clean and Build**.
6. Tekan **Shift + F6** pada Form Utama/Login untuk menjalankan aplikasi.

## 📸 Tangkapan Layar (Screenshots)
*(Opsional: Anda bisa menambahkan gambar screenshot dashboard aplikasi Anda di sini)*
