# Thread and Database
**Nama:** `[Fitri Nufa Dastana]`
**NIM:** `[F1D02310052]`

## 🎯 Deskripsi Tugas
Tugas ini dibuat untuk mendemonstrasikan penggunaan konsep multithreading dalam Java yang terhubung ke database MySQL.  
Program ini menggunakan beberapa thread yang berjalan secara paralel dengan tujuan:
1. Menampilkan daftar lagu dari database.  
2. Menambahkan lagu baru jika belum ada.  
3. Menampilkan ulang daftar lagu setelah proses penambahan selesai.  
Seluruh proses dijalankan menggunakan koneksi JDBC melalui driver MySQL Connector.

## 🧵 Penjelasan Tiap Thread
### **1️⃣ DisplaySongs (Thread Penampil Data Lagu)**
- Thread ini bertugas **menampilkan seluruh data lagu** yang ada pada tabel `songs` di database.  
- Thread dijalankan **dua kali**, yaitu:
  - Sebelum proses penambahan lagu (untuk menampilkan daftar awal).  
  - Setelah proses penambahan lagu (untuk menampilkan daftar akhir).  
- Fungsinya agar pengguna dapat melihat perubahan data setelah thread penambah dijalankan.

### **2️⃣ InsertSong (Thread Penambah Lagu)**
- Thread ini bertugas **mengecek dan menambahkan lagu baru** ke dalam tabel `songs`.  
- Sebelum menambah data, thread melakukan **pengecekan apakah lagu sudah ada** dengan membandingkan judul dan artis.  
- Jika lagu sudah ada → akan menampilkan pesan bahwa lagu tidak ditambahkan.  
- Jika belum ada → lagu akan dimasukkan ke database dengan status “Berhasil Ditambahkan”.  
- Beberapa instance dari thread ini dapat berjalan bersamaan (contohnya tiga thread untuk tiga lagu berbeda).

## ⚙️ Alur Jalannya Thread (Urutan Eksekusi)
1. **Thread 1 → DisplaySongs**  
   Menampilkan daftar lagu awal dari database.
2. **Thread 2 → InsertSong (Lagu 1)**  
   Mengecek dan menambahkan lagu pertama jika belum ada.
3. **Thread 3 → InsertSong (Lagu 2)**  
   Mengecek dan menambahkan lagu kedua jika belum ada.
4. **Thread 4 → InsertSong (Lagu 3)**  
   Mengecek dan menambahkan lagu ketiga jika belum ada.
5. **Thread 5 → DisplaySongs**  
   Menampilkan daftar lagu setelah proses penambahan selesai.

## 🗄️ Struktur Tabel Database

Gunakan database MySQL dengan struktur tabel berikut:

```sql
CREATE DATABASE music;
USE music;

CREATE TABLE songs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100),
    artist VARCHAR(100),
    status VARCHAR(50)
);
```
## ⚙️ Konfigurasi Database
```java
String url = "jdbc:mysql://localhost:3306/music"; // nama database
String user = "root";                              // user MySQL 
String password = "";                              // isi jika ada password
```
Pastikan driver JDBC MySQL (misalnya mysql-connector-j.jar) sudah ditambahkan ke classpath saat menjalankan program.

## 💡 Penjelasan Program
Secara garis besar, program ini bekerja dengan urutan berikut:
1. Koneksi ke Database MySQL dilakukan di App.java menggunakan JDBC.
2. Thread pertama (DisplaySongs) menampilkan seluruh lagu yang sudah tersimpan.
3. Thread berikutnya (InsertSong) dijalankan untuk menambahkan beberapa lagu baru.
- Jika lagu sudah ada, maka tidak ditambahkan ulang.
- Jika belum ada, lagu ditambahkan dengan status "Berhasil Ditambahkan".
4. Setelah semua proses penambahan selesai, thread terakhir (DisplaySongs) dijalankan kembali untuk menampilkan daftar terbaru.
5. Setiap proses dijalankan secara bergantian menggunakan start() dan join() agar thread sebelumnya selesai sebelum thread berikutnya dimulai.

## Output di Terminal dan Database
Tampilan di terminal

<img width="811" height="688" alt="image" src="https://github.com/user-attachments/assets/48e189fb-2fb4-4131-a874-a3c02452656f" />

Database sebelum lagu ditambahkan

<img width="725" height="131" alt="image" src="https://github.com/user-attachments/assets/050e64ca-3c98-44a8-841b-72a5caac6374" />

Database setelah lagu ditambahkan
<img width="820" height="202" alt="image" src="https://github.com/user-attachments/assets/c3137952-7644-4eeb-956b-da0a5489bfb8" />
