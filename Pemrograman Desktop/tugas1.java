import java.util.Scanner;

class Menu {
    String nama;
    int harga;
    String kategori;

    public Menu(String nama, int harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }

    public int getHarga() {
        return harga;
    }

}

public class tugas1 {
    public static void main(String[] args) {

        Menu[] daftarMenu = {
                new Menu("Nasi Goreng", 25000, "Makanan"),
                new Menu("Mie Goreng", 25000, "Makanan"),
                new Menu("Ayam Geprek", 20000, "Makanan"),
                new Menu("Bakso", 30000, "Makanan"),
                new Menu("Air Mineral", 5000, "Minuman"),
                new Menu("Es Teh", 5000, "Minuman"),
                new Menu("Es Jeruk", 8000, "Minuman"),
                new Menu("Coffee Latte", 20000, "Minuman"),
        };

        Scanner scanner = new Scanner(System.in);
        int[] pesanan = new int[8];

        System.out.println("\n====== DAFTAR MENU  ======");
        System.out.println("1. Nasi Goreng - Rp 25000");
        System.out.println("2. Mie Goreng - Rp 25000");
        System.out.println("3. Ayam Geprek - Rp 20000");
        System.out.println("4. Bakso - Rp 30000");
        System.out.println("5. Air Mineral - Rp 5000");
        System.out.println("6. Es Teh - Rp 5000");
        System.out.println("7. Es Jeruk - Rp 8000");
        System.out.println("8. Coffee Latte - Rp 20000");
        System.out.println("---------------------=----");


        System.out.print("Masukan menu pertama (1-8): ");
        int pilihan1 = scanner.nextInt();
        if (pilihan1 != 0) {
            System.out.print("Jumlah: ");
            int jumlah1 = scanner.nextInt();
            pesanan[pilihan1 - 1] = jumlah1;
        }

        System.out.print("Masukan menu kedua (1-8): ");
        int pilihan2 = scanner.nextInt();
        if (pilihan2 != 0) {
            System.out.print("Jumlah: ");
            int jumlah2 = scanner.nextInt();
            pesanan[pilihan2 - 1] = jumlah2;
        }

        System.out.print("Masukan menu ketiga (1-8): ");
        int pilihan3 = scanner.nextInt();
        if (pilihan3 != 0) {
            System.out.print("Jumlah: ");
            int jumlah3 = scanner.nextInt();
            pesanan[pilihan3 - 1] = jumlah3;
        }

        System.out.print("Masukan menu keempat (1-8): ");
        int pilihan4 = scanner.nextInt();
        if (pilihan4 != 0) {
            System.out.print("Jumlah: ");
            int jumlah4 = scanner.nextInt();
            pesanan[pilihan4 - 1] = jumlah4;
        }

        int subtotal = 0;
        if (pesanan[0] > 0)
            subtotal += daftarMenu[0].getHarga() * pesanan[0];
        if (pesanan[1] > 0)
            subtotal += daftarMenu[1].getHarga() * pesanan[1];
        if (pesanan[2] > 0)
            subtotal += daftarMenu[2].getHarga() * pesanan[2];
        if (pesanan[3] > 0)
            subtotal += daftarMenu[3].getHarga() * pesanan[3];
        if (pesanan[4] > 0)
            subtotal += daftarMenu[4].getHarga() * pesanan[4];
        if (pesanan[5] > 0)
            subtotal += daftarMenu[5].getHarga() * pesanan[5];
        if (pesanan[6] > 0)
            subtotal += daftarMenu[6].getHarga() * pesanan[6];
        if (pesanan[7] > 0)
            subtotal += daftarMenu[7].getHarga() * pesanan[7];

        double pajak = 0.10 * subtotal;
        int biayaLayanan = 20000;

        double diskon = subtotal > 100000 ? 0.10 * subtotal : 0;

        double diskonMinuman = 0;
        if (subtotal > 50000) {
            if (pesanan[4] > 0)
                diskonMinuman = daftarMenu[4].getHarga();
            else if (pesanan[5] > 0)
                diskonMinuman = daftarMenu[5].getHarga();
            else if (pesanan[6] > 0)
                diskonMinuman = daftarMenu[6].getHarga();
            else if (pesanan[7] > 0)
                diskonMinuman = daftarMenu[7].getHarga();

        }

        double totalBayar = subtotal + pajak + biayaLayanan - diskon - diskonMinuman;

        System.out.println("\n====== STRUK PEMESANAN ======");
        System.out.println("------------------------------");
        if (pesanan[0] > 0)
            System.out.println("Nasi Goreng x" + pesanan[0] + " = Rp " + (pesanan[0] * 25000));
        if (pesanan[1] > 0)
            System.out.println("Mie Goreng x" + pesanan[1] + " = Rp " + (pesanan[1] * 25000));
        if (pesanan[2] > 0)
            System.out.println("Ayam Geprek x" + pesanan[2] + " = Rp " + (pesanan[2] * 20000));
        if (pesanan[3] > 0)
            System.out.println("Bakso x" + pesanan[3] + " = Rp " + (pesanan[3] * 30000));
        if (pesanan[4] > 0)
            System.out.println("Air Mineral x" + pesanan[4] + " = Rp " + (pesanan[4] * 5000));
        if (pesanan[5] > 0)
            System.out.println("Es Teh x" + pesanan[5] + " = Rp " + (pesanan[5] * 5000));
        if (pesanan[6] > 0)
            System.out.println("Es Jeruk x" + pesanan[6] + " = Rp " + (pesanan[6] * 8000));
        if (pesanan[7] > 0)
            System.out.println("Coffee Latte x" + pesanan[7] + " = Rp " + (pesanan[7] * 20000));
        System.out.println("------------------------------");
        System.out.println("Total Menu        : Rp " + subtotal);
        System.out.println("Pajak 10%         : Rp " + pajak);
        System.out.println("Biaya Layanan     : Rp " + biayaLayanan);
        System.out.println("Diskon            : Rp " + diskon);
        System.out.println("Diskon Minuman    : Rp " + diskonMinuman);
        System.out.println("Total Bayar       : Rp " + totalBayar);
        System.out.println("------------------------------");
        System.out.println("======== TERIMA KASIH ========");

    }
}