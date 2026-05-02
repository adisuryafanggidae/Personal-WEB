import java.util.Scanner;

public class tugas2 {
    public static void main(String[] args) {

        // deklarasi variabel
        Scanner input = new Scanner(System.in);
        char golongan;
        int jamlembur;
        double gajipokok = 0, persenlembur = 0, gajilembur, totalgaji;

        System.out.print(import java.util.Scanner;

public class tugas3 {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        double[] arrayGaji = { 5000000, 6500000, 9500000 };
        double[] arrayLembur = { 0.30, 0.32, 0.34, 0.36, 0.38 };

        String golongan;
        int jamlembur;

        System.out.println("===== APLIKASI HITUNG GAJI =====");
        System.out.print("Masukan Golongan Karyawan (A/B/C): ");
        golongan = input.next().toUpperCase();

        System.out.print("Masukkan Jam Lembur: ");
        jamlembur = input.nextInt();

        // Tentukan gaji pokok
        double gajiPokok;

        if (golongan.equals("A")) {
            gajiPokok = arrayGaji[0];
        } else if (golongan.equals("B")) {
            gajiPokok = arrayGaji[1];
        } else if (golongan.equals("C")) {
            gajiPokok = arrayGaji[2];
        } else {
            System.out.println("❌ KESALAHAN: Golongan tidak valid.");
            input.close();
            return; // PERBAIKAN PENTING: Program harus berhenti di sini jika salah
        }

        // Hitung lembur berdasarkan jam
        double persenLembur;
        double gajiLembur;

        if (jamlembur >= 1 && jamlembur <= 4) {
            persenLembur = arrayLembur[jamlembur - 1];
        } else if (jamlembur >= 5) {
            persenLembur = arrayLembur[4]; // Mengambil index terakhir (max)
        } else {
            System.out.println("❌ KESALAHAN: Jam lembur tidak valid (minimal 1 jam).");
            input.close();
            return; // Program berhenti
        }

        // Perhitungan
        gajiLembur = gajiPokok * persenLembur * jamlembur;
        double totalGaji = gajiPokok + gajiLembur;

        // OUTPUT (Menggunakan (long) agar tidak ada koma .0 di belakang angka rupiah)
        System.out.println("\n=== HASIL PERHITUNGAN GAJI ===");
        System.out.println("Golongan            : " + golongan);
        System.out.println("Gaji pokok          : Rp " + (long)gajiPokok);
        System.out.println("Jam lembur          : " + jamlembur + " jam");
        System.out.println("Persentase Lembur   : " + (int)(persenLembur * 100) + "%");
        System.out.println("Gaji lembur         : Rp " + (long)gajiLembur);
        System.out.println("------------------------------------");
        System.out.println("Total penghasilan   : Rp " + (long)totalGaji);
        System.out.println("\n===== TERIMA KASIH =====");
        System.out.println("===== ADI SURYA FANGIDAE 055378839 =====");

        input.close();
    }
});
        golongan = input.next().toUpperCase().charAt(0);

        System.out.print("Masukkan Jam Lembur: ");
        jamlembur = input.nextInt();

        // golongan
        if (golongan == 'A') {
            gajipokok = 5000000;
        } else if (golongan == 'B') {
            gajipokok = 6500000;
        } else if (golongan == 'C') {
            gajipokok = 9500000;
        } else {
            System.out.println("Golongan tidak valid");
            System.exit(0);
        }

        // jamlembur
        if (jamlembur == 1) {
            persenlembur = 0.30;
        } else if (jamlembur == 2) {
            persenlembur = 0.32;
        } else if (jamlembur == 3) {
            persenlembur = 0.34;
        } else if (jamlembur == 4) {
            persenlembur = 0.36;
        } else if (jamlembur >= 5) {
            persenlembur = 0.38;
        }

        gajilembur = persenlembur * gajipokok;
        totalgaji = gajipokok + gajilembur;

        // outputhasilgaji
        System.out.println("\n=== Hasil PERHITUNGAN GAJI ===");
        System.out.println("Golongan            : " + golongan);
        System.out.println("Gaji pokok          : Rp " + (int) gajipokok);
        System.out.println("Jam lembur          : " + jamlembur + "jam");
        System.out.println("Gaji lembur         : Rp " + (int) gajilembur);
        System.out.println("Total penghasilan   : Rp " + (int) totalgaji);
        System.out.println("\n===== TERIMA KASIH  =====");
        System.out.println("\n===== ADI SURYA FANGIDAE 055378839  =====");

        input.close();

    }
}