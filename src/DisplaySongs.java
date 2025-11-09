import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DisplaySongs extends Thread {
    private final Connection conn;
    private final String label;

    public DisplaySongs(Connection conn, String label) {
        this.conn = conn;
        this.label = label;
    }

    @Override
    public synchronized void run() {
        try {
            System.out.println("\n[" + label + "] DAFTAR LAGU");
            System.out.println("===================================================================================");
            System.out.printf("| %-3s | %-25s | %-20s | %-22s |\n", "No", "Judul", "Artis", "Status");
            System.out.println("|-----|---------------------------|----------------------|------------------------|");

            String query = "SELECT * FROM songs";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            int i = 1;
            while (rs.next()) {
                String title = rs.getString("title");
                String artist = rs.getString("artist");
                String status = rs.getString("status");
                System.out.printf("| %-3d | %-25s | %-20s | %-22s |\n", i++, title, artist, status);
            }

            System.out.println("===================================================================================");
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

