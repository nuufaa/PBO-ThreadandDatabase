import java.sql.Connection;
import java.sql.DriverManager;

public class App {
    public static void main(String[] args) {
        try {
            // Koneksi ke database
            String url = "jdbc:mysql://localhost:3306/music"; // sesuaikan dengan nama DB kamu
            String user = "root";
            String password = ""; // isi jika ada password MySQL
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Koneksi ke database berhasil.\n");

            // Thread daftar & insert lagu
            DisplaySongs t1 = new DisplaySongs(conn, "Thread 1 (Daftar Awal)");
            InsertSong t2 = new InsertSong(conn, "Godspeed", "Frank Ocean", 2);
            InsertSong t3 = new InsertSong(conn, "Si Paling Mahir", "Raisa", 3);
            InsertSong t4 = new InsertSong(conn, "New Year's Day", "Taylor Swift", 4);
            DisplaySongs t5 = new DisplaySongs(conn, "Thread 5 (Setelah Penambahan)");

            // Jalankan thread secara berurutan agar output rapi
            t1.start();
            t1.join();

            t2.start();
            t2.join();

            t3.start();
            t3.join();

            t4.start();
            t4.join();

            t5.start();
            t5.join();

            System.out.println("\nSemua proses selesai!");
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
