import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);

    static Menu[] daftarMenu = new Menu[200];
    static int jumlahMenu = 0;

    static Menu[] pesananMenu = new Menu[100];
    static int[] pesananJumlah = new int[100];
    static int jumlahPesanan = 0;

    public static void main(String[] args) {
        seedDataMenu();
        menuUtama();
    }

    public static void seedDataMenu() {
        tambahMenu(new Menu("Nasi Goreng", 25000, "Makanan"));
        tambahMenu(new Menu("Mie Goreng", 25000, "Makanan"));
        tambahMenu(new Menu("Ayam Geprek", 20000, "Makanan"));
        tambahMenu(new Menu("Bakso", 30000, "Makanan"));

        tambahMenu(new Menu("Air Mineral", 5000, "Minuman"));
        tambahMenu(new Menu("Es Teh", 5000, "Minuman"));
        tambahMenu(new Menu("Es Jeruk", 8000, "Minuman"));
        tambahMenu(new Menu("Coffee Latte", 20000, "Minuman"));
    }

    public static void tambahMenu(Menu m) {
        if (jumlahMenu < daftarMenu.length) {
            daftarMenu[jumlahMenu] = m;
            jumlahMenu++;
        }
    }

    public static void menuUtama() {
        while (true) {
            System.out.println("\n====== Menu Utama ======");
            System.out.println("1. Pemesanan");
            System.out.println("2. Manajemen Menu Restoran");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu: (1-2-3) ");

            String pilih = input.nextLine();

            switch (pilih) {
                case "1":
                    menuPesanan();
                    break;
                case "2":
                    menuManajemen();
                    break;
                case "3":
                    System.out.println("Terima kasih telah berkunjung!");
                    return;
                default:
                    System.out.println("Anda salah input, silakan coba lagi!!!");
            }
        }
    }

    public static void menuPesanan() {
        jumlahPesanan = 0;

        while (true) {
            tampilkanMenu();

            System.out.print("Masukan nama menu (atau 'selesai'): ");
            String pilih = input.nextLine();

            if (pilih.equalsIgnoreCase("selesai"))
                break;

            Menu m = cariMenu(pilih);

            if (m == null) {
                System.out.println("Menu tidak ditemukan, coba lagi!!!");
                continue;
            }

            System.out.print("Jumlah: ");
            int jml = Integer.parseInt(input.nextLine());

            pesananMenu[jumlahPesanan] = m;
            pesananJumlah[jumlahPesanan] = jml;
            jumlahPesanan++;

            System.out.println("Berhasil ditambahkan!\n");
        }

        if (jumlahPesanan > 0) {
            cetakStruk();
        } else {
            System.out.println("Tidak ada pesanan yang dilakukan.");
        }
    }

    public static void tampilkanMenu() {
        System.out.println("\n====== DAFTAR MENU ======");
        System.out.println("\n------ Makanan ------");
        for (int i = 0; i < jumlahMenu; i++) {
            if (daftarMenu[i].getKategori().equalsIgnoreCase("Makanan")) {
                System.out.println("- " + daftarMenu[i].getNama() + " : Rp " + daftarMenu[i].getHarga());
            }
        }

        System.out.println("\n------ Minuman ------");
        for (int i = 0; i < jumlahMenu; i++) {
            if (daftarMenu[i].getKategori().equalsIgnoreCase("Minuman")) {
                System.out.println("- " + daftarMenu[i].getNama() + " : Rp " + daftarMenu[i].getHarga());
            }
        }
        System.out.println("------------------------");
    }

    public static Menu cariMenu(String nama) {
        for (int i = 0; i < jumlahMenu; i++) {
            if (daftarMenu[i].getNama().equalsIgnoreCase(nama)) {
                return daftarMenu[i];
            }
        }
        return null;
    }

    public static void cetakStruk() {
        System.out.println("\n====== STRUK PEMBAYARAN ======");

        int subtotal = 0;
        boolean adaMinuman = false;

        for (int i = 0; i < jumlahPesanan; i++) {
            Menu m = pesananMenu[i];
            int jumlah = pesananJumlah[i];
            int totalItem = m.getHarga() * jumlah;
            subtotal += totalItem;

            if (m.getKategori().equalsIgnoreCase("Minuman")) {
                adaMinuman = true;
            }

            System.out.println(m.getNama() + " x " + jumlah + " = Rp " + totalItem);
        }
        System.out.println("-----------------------------");
        System.out.println("Subtotal: Rp " + subtotal);

        int diskon = 0;
        if (subtotal > 100000) {
            diskon = subtotal * 10 / 100;
            System.out.println("Diskon 10%: -Rp " + diskon);
        }

        if (subtotal > 50000 && adaMinuman) {
            System.out.println("Promo: Beli 1 Gratis 1 (Kategori Minuman Aktif!)");
        }

        int totalSetelahDiskon = subtotal - diskon;
        int pajak = totalSetelahDiskon * 10 / 100;
        int service = 20000;

        int totalAkhir = totalSetelahDiskon + pajak + service;
        System.out.println("Pajak 10% : Rp " + pajak);
        System.out.println("Biaya Service : Rp " + service);
        System.out.println("-----------------------------");
        System.out.println("TOTAL : Rp " + totalAkhir);
        System.out.println("=============================\n");
    }

    public static void menuManajemen() {
        while (true) {
            System.out.println("\n====== MANAJEMEN MENU ======");
            System.out.println("1. Tambah menu baru");
            System.out.println("2. Ubah harga menu");
            System.out.println("3. Hapus menu");
            System.out.println("4. Lihat menu");
            System.out.println("5. Kembali ke Menu Utama");
            System.out.print("Pilih: (1-5) ");
            String pilih = input.nextLine();

            switch (pilih) {
                case "1":
                    tambahMenuBaru();
                    break;
                case "2":
                    ubahHargaMenu();
                    break;
                case "3":
                    hapusMenu();
                    break;
                case "4":
                    lihatMenuManajemen();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Pilihan tidak valid, silakan coba lagi!!!");
            }
        }
    }

    public static void lihatMenuManajemen() {
        System.out.println("\n====== DAFTAR MENU RESTORAN ======");

        int nomor = 1;
        System.out.println("\n------ Makanan ------");
        for (int i = 0; i < jumlahMenu; i++) {
            if (daftarMenu[i].getKategori().equalsIgnoreCase("Makanan")) {
                System.out.println(nomor + ". " + daftarMenu[i].getNama() + " - Rp. " + daftarMenu[i].getHarga());
                nomor++;
            }
        }

        System.out.println("\n------ Minuman ------");
        for (int i = 0; i < jumlahMenu; i++) {
            if (daftarMenu[i].getKategori().equalsIgnoreCase("Minuman")) {
                System.out.println(nomor + ". " + daftarMenu[i].getNama() + " - Rp. " + daftarMenu[i].getHarga());
                nomor++;
            }
        }
    }

    public static void tambahMenuBaru() {
        System.out.print("Nama Menu Baru: ");
        String nama = input.nextLine();

        System.out.print("Harga: ");
        int harga = Integer.parseInt(input.nextLine());

        System.out.print("Kategori (Makanan/Minuman): ");
        String kategori = input.nextLine();

        tambahMenu(new Menu(nama, harga, kategori));
        System.out.println("Menu baru berhasil ditambahkan!");
    }

    public static void ubahHargaMenu() {
        tampilkanMenu();
        System.out.print("Masukan nama menu yang ingin diubah: ");
        String nama = input.nextLine();
        Menu m = cariMenu(nama);

        if (m == null) {
            System.out.println("Menu tidak ditemukan!");
            return;
        }

        System.out.print("Masukkan Harga baru: ");
        int hargaBaru = Integer.parseInt(input.nextLine());

        System.out.print("Anda yakin untuk mengubah harga? (Ya/Tidak): ");
        if (input.nextLine().equalsIgnoreCase("Ya")) {
            m.setHarga(hargaBaru);
            System.out.println("Harga berhasil diubah!");
        } else {
            System.out.println("Perubahan harga dibatalkan.");
        }
    }

    public static void hapusMenu() {
        tampilkanMenu();
        System.out.print("Masukan nama menu yang ingin dihapus: ");
        String nama = input.nextLine();

        int index = -1;
        for (int i = 0; i < jumlahMenu; i++) {
            if (daftarMenu[i].getNama().equalsIgnoreCase(nama)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Menu tidak ditemukan!!!");
            return;
        }

        System.out.print("Anda yakin ingin menghapus menu ini? (Ya/Tidak): ");
        if (!input.nextLine().equalsIgnoreCase("Ya")) {
            System.out.println("Penghapusan dibatalkan.");
            return;
        }

        for (int i = index; i < jumlahMenu - 1; i++) {
            daftarMenu[i] = daftarMenu[i + 1];
        }
        daftarMenu[jumlahMenu - 1] = null;
        jumlahMenu--;

        System.out.println("Menu berhasil dihapus!");
    }
}