import java.io.*;
import java.util.ArrayList;

public class Pesanan {
    private Menu menu;
    private String namaPelanggan;
    private ArrayList<MenuItem> itemDipesan;
    private ArrayList<Integer> jumlah;

    public Pesanan(Menu menu, String namaPelanggan) {
        this.menu = menu;
        this.namaPelanggan = namaPelanggan;
        this.itemDipesan = new ArrayList<>();
        this.jumlah = new ArrayList<>();
    }

    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    public boolean kosong() {
        return itemDipesan.isEmpty();
    }

    public void tambahItem(MenuItem item, int qty) {
        if (qty <= 0) {
            throw new IllegalArgumentException("Jumlah pesanan harus lebih dari 0!");
        }
        int idx = itemDipesan.indexOf(item);
        if (idx >= 0) {
            jumlah.set(idx, jumlah.get(idx) + qty);
        } else {
            itemDipesan.add(item);
            jumlah.add(qty);
        }
    }

    public double hitungSubtotal() {
        double total = 0;
        for (int i = 0; i < itemDipesan.size(); i++) {
            total += itemDipesan.get(i).getHarga() * jumlah.get(i);
        }
        return total;
    }

    public double hitungTotalDiskon() {
        double total = 0;
        for (int i = 0; i < itemDipesan.size(); i++) {
            MenuItem item = itemDipesan.get(i);
            Diskon d = menu.cariDiskon(item.getNama());
            if (d != null) {
                total += item.getHarga() * jumlah.get(i) * d.getBesarDiskon() / 100;
            }
        }
        return total;
    }

    public double hitungTotal() {
        return hitungSubtotal() - hitungTotalDiskon();
    }

    public String buatStruk() {
        StringBuilder sb = new StringBuilder();
        sb.append("========== STRUK PESANAN ==========\n");
        sb.append("-----------------------------------\n");
        sb.append("Pelanggan: ").append(namaPelanggan).append("\n");
        sb.append("-----------------------------------\n");
        for (int i = 0; i < itemDipesan.size(); i++) {
            MenuItem item = itemDipesan.get(i);
            int qty = jumlah.get(i);
            sb.append(String.format("%-18s %2d x Rp%,.0f = Rp%,.0f%n",
                    item.getNama(), qty, item.getHarga(), item.getHarga() * qty));
            Diskon d = menu.cariDiskon(item.getNama());
            if (d != null) {
                sb.append(String.format("   Diskon %.0f%%: -Rp%,.0f%n", d.getBesarDiskon(),
                        item.getHarga() * qty * d.getBesarDiskon() / 100));
            }
        }
        sb.append("-----------------------------------\n");
        sb.append(String.format("Subtotal : Rp%,.0f%n", hitungSubtotal()));
        sb.append(String.format("Diskon   : -Rp%,.0f%n", hitungTotalDiskon()));
        sb.append(String.format("TOTAL    : Rp%,.0f%n", hitungTotal()));
        sb.append("-----------------------------------\n");
        sb.append("========== TERIMA KASIH ==========\n");
        return sb.toString();
    }

    public void tampilkanStruk() {
        System.out.println();
        System.out.print(buatStruk());
    }

    public void simpanStrukKeFile(String namaFile) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(namaFile))) {
            writer.print(buatStruk());
            System.out.println("Struk berhasil disimpan ke file " + namaFile);
        } catch (IOException e) {
            System.out.println("Gagal menyimpan struk: " + e.getMessage());
        }
    }

    public static void muatStrukDariFile(String namaFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(namaFile))) {
            String baris;
            System.out.println();
            while ((baris = reader.readLine()) != null) {
                System.out.println(baris);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File '" + namaFile + "' tidak ditemukan.");
        } catch (IOException e) {
            System.out.println("Gagal membaca struk: " + e.getMessage());
        }
    }
}
