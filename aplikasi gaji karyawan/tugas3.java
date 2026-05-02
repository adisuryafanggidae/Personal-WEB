import java.util.Scanner;

public class tugas3 {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        double[] arrayGaji = { 5000000, 6500000, 9500000 };
        double[] arrayLembur = { 0.30, 0.32, 0.34, 0.36, 0.38 };

        String golongan;
        int jamLembur;

        System.out.print("Masukan Golongan Karyawan (A/B/C): ");
        golongan = input.next().toUpperCase();

        System.out.print("Masukkan Jam Lembur: ");
        jamLembur = input.nextInt();

        // gaji pokok
        double gajiPokok;

        if (golongan.equals("A")) {
            gajiPokok = arrayGaji[0];
        } else if (golongan.equals("B")) {
            gajiPokok = arrayGaji[1];
        } else if (golongan.equals("C")) {
            gajiPokok = arrayGaji[2];
        } else {
            System.out.println("Golongan tidak valid.");
            input.close();
            return;
        }

        // Hitung jam lembur
        double persenLembur;
        double gajiLembur;

        if (jamLembur >= 1 && jamLembur <= 4) {
            persenLembur = arrayLembur[jamLembur - 1];
        } else if (jamLembur >= 5) {
            persenLembur = arrayLembur[4];
        } else {
            System.out.println("Jam lembur tidak valid.");
            input.close();
            return;
        }

        gajiLembur = gajiPokok * persenLembur * jamLembur;
        double totalGaji = gajiPokok + gajiLembur;

        // OUTPUT
        System.out.println("\n=== HASIL PERHITUNGAN GAJI ===");
        System.out.println("Golongan            : " + golongan);
        System.out.println("Gaji pokok          : Rp " + (long) gajiPokok);
        System.out.println("Jam lembur          : " + jamLembur + " jam");
        System.out.println("Persentase Lembur   : " + (int) (persenLembur * 100) + "%");
        System.out.println("Gaji lembur         : Rp " + (long) gajiLembur);
        System.out.println("------------------------------------");
        System.out.println("Total penghasilan   : Rp " + (long) totalGaji);
        System.out.println("\n===== TERIMA KASIH =====");
        System.out.println("===== ADI SURYA FANGIDAE 055378839 =====");

        input.close();
    }
}
