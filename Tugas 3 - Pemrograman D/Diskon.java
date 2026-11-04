public class Diskon extends MenuItem {
    private double besarDiskon;

    public Diskon(String namaMenuTarget, double besarDiskon) {
        super(namaMenuTarget, 0, "Diskon");
        setBesarDiskon(besarDiskon);
    }

    public double getBesarDiskon() {
        return besarDiskon;
    }

    public void setBesarDiskon(double besarDiskon) {
        if (besarDiskon < 0 || besarDiskon > 100) {
            throw new IllegalArgumentException("Diskon antara 0 - 100 persen!");
        }
        this.besarDiskon = besarDiskon;
    }

    @Override
    public void tampilMenu() {
        System.out.printf("[%s] Diskon %.0f%% untuk %s%n", getKategori(), besarDiskon, getNama());
    }
}
