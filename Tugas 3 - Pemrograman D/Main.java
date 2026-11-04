import java.util.Scanner;

public class Main {
    private static Scanner input = new Scanner(System.in);
    private static Menu menu = new Menu();
    private static Pesanan pesananAktif = null;

    private static final String FILE_MENU = "menu.txt";
    private static final String FILE_STRUK = "struk.txt";

    public static void main(String[] args) {

        menu.tambahItem(new Makanan("Nasi Goreng", 25000, "Utama"));
        menu.tambahItem(new Makanan("Kentang Goreng", 15000, "Camilan"));
        menu.tambahItem(new Minuman("Es Teh", 5000, "Dingin"));
        menu.tambahItem(new Minuman("Kopi Panas", 12000, "Panas"));

        boolean jalan = true;
        while (jalan) {
            tampilMenuUtama();
            int pilihan = bacaInt("Pilih menu: ");
            switch (pilihan) {
                case 1:
                    tambahItemBaru();
                    break;
                case 2:
                    menu.tampilkanSemuaMenu();
                    break;
                case 3:
                    terimaPesanan();
                    break;
                case 4:
                    hitungTotal();
                    break;
                case 5:
                    tampilkanStruk();
                    break;
                case 6:
                    menu.simpanMenuKeFile(FILE_MENU);
                    break;
                case 7:
                    menu.muatMenuDariFile(FILE_MENU);
                    break;
                case 8:
                    simpanStruk();
                    break;
                case 9:
                    Pesanan.muatStrukDariFile(FILE_STRUK);
                    break;
                case 0:
                    jalan = false;
                    System.out.println("Terima kasih. Program selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private static void tampilMenuUtama() {
        System.out.println("\n===== MANAJEMEN RESTORAN =====");
        System.out.println("1. Tambah item menu");
        System.out.println("2. Tampilkan menu");
        System.out.println("3. Terima pesanan");
        System.out.println("4. Hitung total biaya");
        System.out.println("5. Tampilkan struk");
        System.out.println("6. Simpan menu ke file");
        System.out.println("7. Muat menu dari file");
        System.out.println("8. Simpan struk ke file");
        System.out.println("9. Muat struk dari file");
        System.out.println("0. Keluar");
    }

    private static void tambahItemBaru() {
        System.out.println("Jenis item: 1. Makanan  2. Minuman  3. Diskon");
        int jenis = bacaInt("Pilih jenis: ");
        try {
            switch (jenis) {
                case 1: {
                    String nama = bacaString("Nama makanan: ");
                    double harga = bacaDouble("Harga: ");
                    String j = bacaString("Jenis makanan (Utama/Camilan/...): ");
                    menu.tambahItem(new Makanan(nama, harga, j));
                    break;
                }
                case 2: {
                    String nama = bacaString("Nama minuman: ");
                    double harga = bacaDouble("Harga: ");
                    String j = bacaString("Jenis minuman (Panas/Dingin/...): ");
                    menu.tambahItem(new Minuman(nama, harga, j));
                    break;
                }
                case 3: {
                    String nama = bacaString("Nama menu yang didiskon: ");
                    menu.cariMenu(nama); // pastikan menu ada
                    double persen = bacaDouble("Besar diskon (%): ");
                    menu.tambahItem(new Diskon(nama, persen));
                    break;
                }
                default:
                    System.out.println("Anda Salah Input!");
                    return;
            }
            System.out.println("Item berhasil ditambahkan.");
        } catch (IllegalArgumentException e) {
            System.out.println("Input tidak valid: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void terimaPesanan() {
        if (menu.getDaftarMenuItem().isEmpty()) {
            System.out.println("Menu masih kosong.");
            return;
        }
        String pelanggan = bacaString("Nama pelanggan: ");
        pesananAktif = new Pesanan(menu, pelanggan);
        menu.tampilkanSemuaMenu();

        String lagi = "ya";
        while (lagi.equalsIgnoreCase("ya")) {
            String nama = bacaString("Nama menu yang dipesan: ");
            try {
                MenuItem item = menu.cariMenu(nama);
                int qty = bacaInt("Jumlah: ");
                pesananAktif.tambahItem(item, qty);
                System.out.println(item.getNama() + " x" + qty + " ditambahkan.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            lagi = bacaString("Ingin pesan lagi? (ya/tidak): ");
        }
        System.out.println("Pesanan dicatat.");
    }

    private static boolean adaPesanan() {
        if (pesananAktif == null || pesananAktif.kosong()) {
            System.out.println("Belum ada pesanan. Ayo pilih menu!");
            return false;
        }
        return true;
    }

    private static void hitungTotal() {
        if (!adaPesanan())
            return;
        System.out.printf("Subtotal : Rp%,.0f%n", pesananAktif.hitungSubtotal());
        System.out.printf("Diskon   : Rp%,.0f%n", pesananAktif.hitungTotalDiskon());
        System.out.printf("Total    : Rp%,.0f%n", pesananAktif.hitungTotal());
    }

    private static void tampilkanStruk() {
        if (adaPesanan())
            pesananAktif.tampilkanStruk();
    }

    private static void simpanStruk() {
        if (adaPesanan())
            pesananAktif.simpanStrukKeFile(FILE_STRUK);
    }

    private static String bacaString(String prompt) {
        System.out.print(prompt);
        return input.nextLine().trim().replace(",", " ");
    }

    private static int bacaInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Masukkan angka bulat yang valid!");
            }
        }
    }

    private static double bacaDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Masukkan angka yang valid!");
            }
        }
    }
}
