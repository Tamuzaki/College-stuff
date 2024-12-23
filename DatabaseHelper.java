import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {
    private static final String URL = "jdbc:postgresql://localhost:5432/Klinik"; // URL database
    private static final String USER = "postgres"; // Username PostgreSQL
    private static final String PASSWORD = "1czfilczfil9"; // Password PostgreSQL

    // Method untuk mendapatkan koneksi ke database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Method untuk memeriksa koneksi ke database
    public static void checkConnection() {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Koneksi berhasil ke database.");
            } else {
                System.out.println("Koneksi gagal.");
            }
        } catch (SQLException e) {
            System.out.println("Koneksi ke database gagal: " + e.getMessage());
        }
    }
}
