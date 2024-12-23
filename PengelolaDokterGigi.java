import java.sql.*;
import java.util.Scanner;

public class PengelolaDokterGigi {

    // Menampilkan daftar dokter dan total gaji mereka
    public void lihatDokterDanGaji() {
        String sql = "SELECT id, nama, spesialisasi, jam_kerja FROM dokter_gigi";
    try (Connection conn = DatabaseHelper.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        int gajiPerJam = 150000; // Gaji per jam (dalam rupiah)

        // Tentukan panjang kolom yang cukup besar
        int columnWidthId = 5;
        int columnWidthNama = 25;
        int columnWidthSpesialisasi = 30;
        int columnWidthJamKerja = 12;
        int columnWidthGaji = 20;
        int totalWidth = columnWidthId + columnWidthNama + columnWidthSpesialisasi 
                         + columnWidthJamKerja + columnWidthGaji + 16;

        // Cetak header tabel
        printLine(totalWidth);
        System.out.printf("| %-"+columnWidthId+"s | %-"+columnWidthNama+"s | %-"+columnWidthSpesialisasi+"s | %-"+columnWidthJamKerja+"s | %-"+columnWidthGaji+"s |\n", 
                          "ID", "Nama", "Spesialisasi", "Jam Kerja", "Total Gaji");
        printLine(totalWidth);

        // Cetak isi tabel
        while (rs.next()) {
            int id = rs.getInt("id");
            String nama = rs.getString("nama");
            String spesialisasi = rs.getString("spesialisasi");
            int jamKerja = rs.getInt("jam_kerja");
            int gajiTotal = jamKerja * gajiPerJam; // Menghitung total gaji berdasarkan jam kerja

            System.out.printf("| %-"+columnWidthId+"d | %-"+columnWidthNama+"s | %-"+columnWidthSpesialisasi+"s | %-"+columnWidthJamKerja+"d | Rp%-"+(columnWidthGaji-2)+"d |\n", 
                              id, nama, spesialisasi, jamKerja, gajiTotal);
        }
        printLine(totalWidth);
    } catch (SQLException e) {
        System.out.println("Error saat melihat daftar dokter dan gaji: " + e.getMessage());
    }
}

private void printLine(int length) {
    for (int i = 0; i < length; i++) {
        System.out.print("-");
    }
    System.out.println();
}


    // Tambah Dokter Gigi
    public void tambahDokterGigi(String nama, String spesialisasi) {
        String sql = "INSERT INTO dokter_gigi (nama, spesialisasi) VALUES (?, ?)";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nama);
            pstmt.setString(2, spesialisasi);
            pstmt.executeUpdate();
            System.out.println("Dokter Gigi berhasil ditambahkan.");
        } catch (SQLException e) {
            System.out.println("Error saat menambahkan dokter gigi: " + e.getMessage());
        }
    }

    // Lihat Daftar Dokter Gigi
    public void lihatDokterGigi() {
        String sql = "SELECT id, nama, spesialisasi FROM dokter_gigi";
    try (Connection conn = DatabaseHelper.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        // Tentukan panjang kolom yang cukup besar
        int columnWidthId = 5;
        int columnWidthNama = 25;
        int columnWidthSpesialisasi = 30;
        int totalWidth = columnWidthId + columnWidthNama + columnWidthSpesialisasi + 10;

        // Cetak header tabel
        printLine(totalWidth);
        System.out.printf("| %-"+columnWidthId+"s | %-"+columnWidthNama+"s | %-"+columnWidthSpesialisasi+"s |\n", 
                          "ID", "Nama", "Spesialisasi");
        printLine(totalWidth);

        // Cetak isi tabel
        while (rs.next()) {
            int id = rs.getInt("id");
            String nama = rs.getString("nama");
            String spesialisasi = rs.getString("spesialisasi");

            System.out.printf("| %-"+columnWidthId+"d | %-"+columnWidthNama+"s | %-"+columnWidthSpesialisasi+"s |\n", 
                              id, nama, spesialisasi);
        }
        printLine(totalWidth);
    } catch (SQLException e) {
        System.out.println("Error saat melihat dokter gigi: " + e.getMessage());
    }
}

    // Perbarui Dokter Gigi
    public void perbaruiDokterGigi(int id, String nama, String spesialisasi) {
        String sql = "UPDATE dokter_gigi SET nama = ?, spesialisasi = ? WHERE id = ?";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nama);
            pstmt.setString(2, spesialisasi);
            pstmt.setInt(3, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Dokter Gigi berhasil diperbarui.");
            } else {
                System.out.println("Dokter Gigi dengan ID tersebut tidak ditemukan.");
            }
        } catch (SQLException e) {
            System.out.println("Error saat memperbarui dokter gigi: " + e.getMessage());
        }
    }

    // Hapus Dokter Gigi
    public void hapusDokterGigi(int id) {
        String sql = "DELETE FROM dokter_gigi WHERE id = ?";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Dokter Gigi berhasil dihapus.");
            } else {
                System.out.println("Dokter Gigi dengan ID tersebut tidak ditemukan.");
            }
        } catch (SQLException e) {
            System.out.println("Error saat menghapus dokter gigi: " + e.getMessage());
        }
    }
    // Menambahkan jam kerja untuk dokter gigi
public void tambahJamKerja(int id, int jamKerja) {
    String sql = "UPDATE dokter_gigi SET jam_kerja = jam_kerja + ? WHERE id = ?";
    try (Connection conn = DatabaseHelper.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, jamKerja);
        pstmt.setInt(2, id);
        int rowsAffected = pstmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Jam kerja berhasil ditambahkan.");
        } else {
            System.out.println("Dokter Gigi dengan ID tersebut tidak ditemukan.");
        }
    } catch (SQLException e) {
        System.out.println("Error saat menambahkan jam kerja: " + e.getMessage());
    }
}

// Menghitung gaji dokter gigi berdasarkan jam kerja
public void hitungGajiDokter(int id) {
    String sql = "SELECT nama, jam_kerja FROM dokter_gigi WHERE id = ?";
    try (Connection conn = DatabaseHelper.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            String nama = rs.getString("nama");
            int jamKerja = rs.getInt("jam_kerja");
            int gajiPerJam = 150000; // Gaji per jam (dalam rupiah)
            int gajiTotal = jamKerja * gajiPerJam;

            System.out.println("Nama Dokter: " + nama);
            System.out.println("Total Jam Kerja: " + jamKerja);
            System.out.println("Total Gaji: Rp " + gajiTotal);
        } else {
            System.out.println("Dokter Gigi dengan ID tersebut tidak ditemukan.");
        }
    } catch (SQLException e) {
        System.out.println("Error saat menghitung gaji: " + e.getMessage());
    }
}


    // Tampilkan Menu Interaktif
    public void tampilkanMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        DatabaseHelper.checkConnection(); // Periksa koneksi sebelum memulai

        while (running) {
            System.out.println("\nManajemen Klinik Gigi");
            System.out.println("1. Tambah Dokter Gigi");
            System.out.println("2. Lihat Dokter Gigi");
            System.out.println("3. Perbarui Dokter Gigi");
            System.out.println("4. Hapus Dokter Gigi");
            System.out.println("5. Keluar");
            System.out.println("6. Tambah Jam Kerja Dokter Gigi");
            System.out.println("7. Hitung Gaji Dokter Gigi");
            System.out.println("8. Lihat Daftar Dokter dan Gaji");
            System.out.print("Masukkan pilihan Anda: ");

            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Konsum newline

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan nama dokter: ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan spesialisasi dokter: ");
                    String spesialisasi = scanner.nextLine();
                    tambahDokterGigi(nama, spesialisasi);
                    break;
                case 2:
                    lihatDokterGigi();
                    break;
                case 3:
                    System.out.print("Masukkan ID dokter gigi yang akan diperbarui: ");
                    int idPerbarui = scanner.nextInt();
                    scanner.nextLine(); // Konsum newline
                    System.out.print("Masukkan nama baru dokter: ");
                    String namaBaru = scanner.nextLine();
                    System.out.print("Masukkan spesialisasi baru dokter: ");
                    String spesialisasiBaru = scanner.nextLine();
                    perbaruiDokterGigi(idPerbarui, namaBaru, spesialisasiBaru);
                    break;
                case 4:
                    System.out.print("Masukkan ID dokter gigi yang akan dihapus: ");
                    int idHapus = scanner.nextInt();
                    hapusDokterGigi(idHapus);
                    break;
                case 5:
                    running = false;
                    System.out.println("Keluar dari program.");
                    break;
                    case 6:
                    System.out.print("Masukkan ID dokter gigi: ");
                    int idJamKerja = scanner.nextInt();
                    System.out.print("Masukkan jumlah jam kerja yang ingin ditambahkan: ");
                    int jamKerja = scanner.nextInt();
                    tambahJamKerja(idJamKerja, jamKerja);
                    break;
                
                case 7:
                    System.out.print("Masukkan ID dokter gigi untuk menghitung gaji: ");
                    int idGaji = scanner.nextInt();
                    hitungGajiDokter(idGaji);
                    break;
                case 8:
                    lihatDokterDanGaji(); // Memanggil metode untuk melihat daftar dokter dan gaji
                    break;
                    default:
                    System.out.println("Pilihan tidak valid.");
                    break;
                
            }
        }
        scanner.close();
    }
}
