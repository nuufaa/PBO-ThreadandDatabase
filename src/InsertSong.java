import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InsertSong extends Thread {
    private final Connection conn;
    private final String title;
    private final String artist;
    private final int threadNumber;

    public InsertSong(Connection conn, String title, String artist, int threadNumber) {
        this.conn = conn;
        this.title = title;
        this.artist = artist;
        this.threadNumber = threadNumber;
    }

    @Override
    public synchronized void run() {
        try {
            System.out.println("\n[Thread " + threadNumber + " - Insert] Mengecek lagu: " + title);

            String check = "SELECT * FROM songs WHERE title = ?";
            PreparedStatement checkStmt = conn.prepareStatement(check);
            checkStmt.setString(1, title);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                System.out.println("[Thread " + threadNumber + "] Lagu \"" + title + "\" sudah ada. Tidak ditambahkan.");
            } else {
                String insert = "INSERT INTO songs (title, artist, status) VALUES (?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(insert);
                ps.setString(1, title);
                ps.setString(2, artist);
                ps.setString(3, "Berhasil Ditambahkan");
                ps.executeUpdate();
                ps.close();
                System.out.println("[Thread " + threadNumber + "] Lagu \"" + title + "\" berhasil ditambahkan!");
            }

            rs.close();
            checkStmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
