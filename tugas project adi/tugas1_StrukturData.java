import java.util.LinkedList;

public class tugas1_StrukturData {
    public static void main(String[] args) {

        // soal 1
        System.out.println("Soal Nomor 1:");
        float StrukturBaris = 4;
        System.out.println(StrukturBaris);

        // soal 2
        System.out.println("Soal Nomor 2:");
        String SusunanKataBaru = "Pemrograman Struktur Data Java";
        System.err.println(SusunanKataBaru);

        // soal 3
        System.out.println("Soal Nomor 3:");
        int[] DelapanAngka = { 10, 12, 18, 23, 33, 58, 67, 82 };
        for (int i = 0; i < DelapanAngka.length; i++) {
            System.out.print(DelapanAngka[i] + " ");
        }
        System.out.println();

        // soal 4
        System.out.println("Soal Nomor 4:");
        String[][] DuaAngka = {
                { "2", "4", "6" },
                { "8", "10", "12" },
                { "14", "16", "18" }
        };
        for (int i = 0; i < DuaAngka.length; i++) {
            for (int j = 0; j < DuaAngka[i].length; j++) {
                System.out.print(DuaAngka[i][j] + " ");
            }

            System.out.println();

        }

        // soal 5
        System.out.println("Soal Nomor 5:");
        LinkedList<Integer> UrutanAngka = new LinkedList<>();
        UrutanAngka.add(15);
        UrutanAngka.add(28);
        UrutanAngka.add(33);
        UrutanAngka.add(47);
        UrutanAngka.add(59);
        System.out.println(UrutanAngka);

    }
}