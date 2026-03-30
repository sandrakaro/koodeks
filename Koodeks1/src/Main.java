import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    /* Täisarvulised tähised:
    2 - binaarkood
    8 - kaheksandkood
    10 - kümnendkood
    16 - kuueteistkümnendkood
     */

    public static int[] võimalikudKodeeringud = {2,8,10,16};
    public static String[] sõnad = {"kahendkoodis","kaheksandkoodis","kümnendkoodis","kuueteistkümnendkoodis"};
    public static HashMap<Integer,String> kodeeringudSõnaga = new HashMap<Integer,String>();
    static {
        for (int i = 0; i < võimalikudKodeeringud.length; i++) {
            kodeeringudSõnaga.put(võimalikudKodeeringud[i],sõnad[i]);
        }
    }

    static void main(String[] args) {

        //sisseloogimine või uue kasutaja loomine
        Kasutaja sisseLoogitud = null;
        Scanner sc = new Scanner(System.in);
        while (sisseLoogitud == null) {
            System.out.println("1 - Logi sisse, 2 - Registreeru");
            String valik = sc.nextLine();

            System.out.println("Kasutajanimi: ");
            String nimi = sc.nextLine();
            System.out.println("Parool: ");
            String parool = sc.nextLine();

            if(valik.equals("2")) {
                Kasutaja uus = new Kasutaja(nimi, parool);
                uus.salvestaFaili();
                System.out.println("Kasutaja loodud.");
            }
            else {
                sisseLoogitud = Kasutaja.loeFailist(nimi, parool);
                if (sisseLoogitud == null) {
                    System.out.println("Vale nimi või parool.");
                }
            }
        }
        System.out.println("\nTere tulemast, " + sisseLoogitud.getKasutajaNimi());
        System.out.println("Viimane teisendus: " + sisseLoogitud.getViimaneTeisendus());



        // Teisendatava arvu küsimine ja vastuse väljastamine
        System.out.println("Lõpetamiseks sisesta q.");
        while (true) {
            System.out.println("\nSisesta arv, mida soovid teisendada:");
            String arv = sc.nextLine();
            if (arv.equals("q")) {
                sc.close();
                break;
            }
            System.out.println("\nSisesta arvu algne kodeering.");
            System.out.println(
                    "    2 – binaarkood\n" +
                    "    8 – kaheksandkood\n" +
                    "    10 – kümnendkood\n" +
                    "    16 – kuueteistkümnendkood");

            try {
                int algneKodeering = Integer.valueOf(sc.nextLine());

                System.out.println("\nSisesta tulemuse soovitud kodeering:");
                int tulemuseKodeering = Integer.valueOf(sc.nextLine());


                Kood kood = new Kood(arv, algneKodeering);
                System.out.println("\nSee arv " + kodeeringudSõnaga.get(tulemuseKodeering) + ":\n" + kood.teisenda(tulemuseKodeering));


                // viimatise teisenduse salvestamine
                String tulemus = kood.teisenda(tulemuseKodeering);
                String viimane = kood.getKood() + " (algne kodeering: " + kood.getTüüp() + ") -> " + tulemus;
                sisseLoogitud.lisaTeisendus(viimane);
                sisseLoogitud.salvestaFaili();
            }
            catch (Exception e) {
                System.out.println("Sisestasid ebasobiva väärtuse või tekkis muu error. Programm lõpetab töö.");
                sc.close();
                break;
            }
        }
    }
}