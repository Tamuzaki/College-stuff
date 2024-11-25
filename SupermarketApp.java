import java.text.SimpleDateFormat;
import java.util.*;

// Base Class: User
class User {
    protected String username;
    protected String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

// turunan user
class Cashier extends User {
    public Cashier(String username, String password) {
        super(username, password);
    }
}

// Main Application
public class SupermarketApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final User[] users = {
        new Cashier("admin", "3034"),
        new Cashier("cashier1", "skibidi")
    };

    public static void main(String[] args) {
        System.out.println("+-----------------------------------------------------+");
        System.out.println("Log in");
        System.out.println("+-----------------------------------------------------+");

        boolean loggedIn = false;
        String cashierName = "";
        String correctCaptcha = generateCaptcha();

        while (!loggedIn) {
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();
            System.out.println("Captcha: " + correctCaptcha);
            System.out.print("Masukkan Captcha: ");
            String captchaInput = scanner.nextLine();

            for (User user : users) {
                if (user.username.equals(username) && user.password.equals(password) && correctCaptcha.equals(captchaInput)) {
                    loggedIn = true;
                    cashierName = user.username;
                    break;
                }
            }

            if (!loggedIn) {
                System.out.println("Login gagal, silakan coba lagi.");
                correctCaptcha = generateCaptcha(); // Regenerate captcha
            }
        }

        System.out.println("Login berhasil!");
        System.out.println("+-----------------------------------------------------+");
        System.out.println("Selamat Datang di Supermarket Indra wahyu");

        boolean continueTransaction = true;

        while (continueTransaction) {
            // menampilkan waktu dan tanggal
            Date now = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            System.out.println("Tanggal dan Waktu: " + formatter.format(now));
            System.out.println("+-----------------------------------------------------+");

            // Transaction input
            System.out.print("No. Faktur: ");
            String invoiceNo = scanner.nextLine();
            System.out.print("Kode Barang: ");
            String itemCode = scanner.nextLine();
            System.out.print("Nama Barang: ");
            String itemName = scanner.nextLine();

            double price = -1;
            while (price <= 0) {
                System.out.print("Harga Barang: ");
                price = scanner.nextDouble();
                if (price <= 0) {
                    System.out.println("Harga barang tidak boleh 0 atau negatif. Masukkan harga yang valid.");
                }
            }

            System.out.print("Jumlah Beli: ");
            int quantity = scanner.nextInt();

            // Calculate total
            double total = price * quantity;

            // menampilkan rincian transaksi
            System.out.println("+-----------------------------------------------------+");
            System.out.println("No. Faktur     : " + invoiceNo);
            System.out.println("Kode Barang    : " + itemCode);
            System.out.println("Nama Barang    : " + itemName);
            System.out.println("Harga Barang   : " + price);
            System.out.println("Jumlah Beli    : " + quantity);
            System.out.println("TOTAL          : " + total);
            System.out.println("+-----------------------------------------------------+");
            System.out.println("Kasir          : " + cashierName);
            System.out.println("+-----------------------------------------------------+");

            scanner.nextLine(); // Consume newline left-over

            // Bertanya untuk transaksi lain
            System.out.print("Apakah Anda ingin melanjutkan transaksi lain? (y/n): ");
            String response = scanner.nextLine().trim().toLowerCase();
            if (!response.equals("y")) {
                continueTransaction = false;
            }
        }

        System.out.println("Terima kasih telah menggunakan layanan kami. Selamat bekerja, " + cashierName + "!");
    }

    // Generate simple captcha
    private static String generateCaptcha() {
        Random random = new Random();
        int number = random.nextInt(9000) + 1000; // Random 4-digit number
        return String.valueOf(number);
    }
}
