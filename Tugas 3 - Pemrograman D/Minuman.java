public class Minuman extends MenuItem {
    private String jenisMinuman;

    public Minuman(String nama, double harga, String jenisMinuman) {
        super(nama, harga, "Minuman");
        this.jenisMinuman = jenisMinuman;
    }

    public String getJenisMinuman() { return jenisMinuman; }
    public void setJenisMinuman(String jenisMinuman) { this.jenisMinuman = jenisMinuman; }

    @Override
    public void tampilMenu() {
        System.out.printf("[%s] %s (%s) - Rp%,.0f%n", getKategori(), getNama(), jenisMinuman, getHarga());
    }
}
