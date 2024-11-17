import java.util.Scanner;

// Kelas Barang untuk menyimpan data barang
class Barang {
    private String kodeBarang;
    private String namaBarang;
    private double hargaBarang;

    // Constructor
    public Barang(String kodeBarang, String namaBarang, double hargaBarang) {
        if (hargaBarang < 0) {
            throw new IllegalArgumentException("Harga barang tidak boleh negatif!");
        }
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
    }

    // Getter
    public String getKodeBarang() {
        return kodeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public double getHargaBarang() {
        return hargaBarang;
    }
}

// Kelas Faktur untuk menyimpan data transaksi
class Faktur {
    private String noFaktur;
    private Barang barang; // Objek dari kelas Barang
    private int jumlahBeli;

    // Constructor
    public Faktur(String noFaktur, Barang barang, int jumlahBeli) {
        if (jumlahBeli < 0) {
            throw new IllegalArgumentException("Jumlah beli tidak boleh negatif!");
        }
        this.noFaktur = noFaktur;
        this.barang = barang;
        this.jumlahBeli = jumlahBeli;
    }

    // Method untuk menghitung total
    public double hitungTotal() {
        return barang.getHargaBarang() * jumlahBeli;
    }

    // Getter
    public String getNoFaktur() {
        return noFaktur;
    }

    public Barang getBarang() {
        return barang;
    }

    public int getJumlahBeli() {
        return jumlahBeli;
    }
}

// Kelas FakturBarang (turunan dari Faktur)
class FakturBarang extends Faktur {
    public FakturBarang(String noFaktur, Barang barang, int jumlahBeli) {
        super(noFaktur, barang, jumlahBeli);
    }

    // Menampilkan detail faktur
    public void tampilkanFaktur() {
        System.out.println("\n=== Faktur Pembelian ===");
        System.out.println("No Faktur   : " + getNoFaktur());
        System.out.println("Kode Barang : " + getBarang().getKodeBarang());
        System.out.println("Nama Barang : " + getBarang().getNamaBarang());
        System.out.println("Harga Barang: " + getBarang().getHargaBarang());
        System.out.println("Jumlah Beli : " + getJumlahBeli());
        System.out.println("Total       : " + hitungTotal());
    }
}

// Kelas utama Exception
public class Exception1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean lanjut = true;

        while (lanjut) {
            try {
                // Input data dari user
                System.out.print("Masukkan No Faktur: ");
                String noFaktur = scanner.nextLine();

                System.out.print("Masukkan Kode Barang: ");
                String kodeBarang = scanner.nextLine();

                System.out.print("Masukkan Nama Barang: ");
                String namaBarang = scanner.nextLine();

                System.out.print("Masukkan Harga Barang: ");
                double hargaBarang = scanner.nextDouble();

                System.out.print("Masukkan Jumlah Beli: ");
                int jumlahBeli = scanner.nextInt();

                // Membersihkan buffer untuk input berikutnya
                scanner.nextLine();

                // Membuat objek Barang dan FakturBarang
                Barang barang = new Barang(kodeBarang, namaBarang, hargaBarang);
                FakturBarang faktur = new FakturBarang(noFaktur, barang, jumlahBeli);

                // Menampilkan faktur
                faktur.tampilkanFaktur();

                // Menanyakan apakah user ingin melanjutkan transaksi lain
                System.out.print("\nApakah Anda ingin melanjutkan transaksi lain? (y/n): ");
                String pilihan = scanner.nextLine().trim().toLowerCase();
                if (!pilihan.equals("y")) {
                    lanjut = false;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Terjadi kesalahan: " + e.getMessage());
            }
        }

        System.out.println("Terima kasih telah menggunakan program ini!");
        scanner.close();
    }
}
