import java.io.*;
import java.util.ArrayList;

public class Menu {
    private ArrayList<MenuItem> daftarMenuItem;

    public Menu() {
        daftarMenuItem = new ArrayList<>();
    }

    public void tambahItem(MenuItem item) {
        daftarMenuItem.add(item);
    }

    public ArrayList<MenuItem> getDaftarMenuItem() {
        return daftarMenuItem;
    }

    public MenuItem cariMenu(String nama) throws Exception {
        for (MenuItem item : daftarMenuItem) {
            if (!(item instanceof Diskon) && item.getNama().equalsIgnoreCase(nama)) {
                return item;
            }
        }
        throw new Exception("Menu dengan nama '" + nama + "' tidak ditemukan dalam daftar!");
    }

    public Diskon cariDiskon(String namaMenu) {
        for (MenuItem item : daftarMenuItem) {
            if (item instanceof Diskon && item.getNama().equalsIgnoreCase(namaMenu)) {
                return (Diskon) item;
            }
        }
        return null;
    }

    public void tampilkanSemuaMenu() {
        System.out.println("\n====== DAFTAR MENU RESTORAN ======");
        if (daftarMenuItem.isEmpty()) {
            System.out.println("Belum ada menu tersedia.");
            return;
        }
        for (int i = 0; i < daftarMenuItem.size(); i++) {
            System.out.print((i + 1) + ". ");
            daftarMenuItem.get(i).tampilMenu();
        }
        System.out.println("==================================");
    }

    public void simpanMenuKeFile(String namaFile) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(namaFile))) {
            for (MenuItem item : daftarMenuItem) {
                if (item instanceof Makanan) {
                    Makanan m = (Makanan) item;
                    writer.println("MAKANAN," + m.getNama() + "," + m.getHarga() + "," + m.getJenisMakanan());
                } else if (item instanceof Minuman) {
                    Minuman mn = (Minuman) item;
                    writer.println("MINUMAN," + mn.getNama() + "," + mn.getHarga() + "," + mn.getJenisMinuman());
                } else if (item instanceof Diskon) {
                    Diskon d = (Diskon) item;
                    writer.println("DISKON," + d.getNama() + "," + d.getBesarDiskon());
                }
            }
            System.out.println("Data menu berhasil disimpan ke file " + namaFile);
        } catch (IOException e) {
            System.out.println("Gagal menyimpan menu: " + e.getMessage());
        }
    }

    public void muatMenuDariFile(String namaFile) {
        ArrayList<MenuItem> hasil = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(namaFile))) {
            String baris;
            int noBaris = 0;
            while ((baris = reader.readLine()) != null) {
                noBaris++;
                if (baris.trim().isEmpty())
                    continue;
                String[] p = baris.split(",");
                try {
                    switch (p[0]) {
                        case "MAKANAN":
                            hasil.add(new Makanan(p[1], Double.parseDouble(p[2]), p[3]));
                            break;
                        case "MINUMAN":
                            hasil.add(new Minuman(p[1], Double.parseDouble(p[2]), p[3]));
                            break;
                        case "DISKON":
                            hasil.add(new Diskon(p[1], Double.parseDouble(p[2])));
                            break;
                        default:
                            System.out.println("Baris " + noBaris + " dilewati (tipe tidak dikenal).");
                    }
                } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
                    System.out.println("Baris " + noBaris + " dilewati (format salah).");
                }
            }
            daftarMenuItem = hasil;
            System.out.println("Berhasil memuat " + hasil.size() + " item menu dari " + namaFile);
        } catch (FileNotFoundException e) {
            System.out.println("File '" + namaFile + "' tidak ditemukan.");
        } catch (IOException e) {
            System.out.println("Gagal membaca file: " + e.getMessage());
        }
    }
}
