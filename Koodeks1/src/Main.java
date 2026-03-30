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
        sc.close();
        System.out.println("\nTere tulemast, " + sisseLoogitud.getKasutajaNimi());
        System.out.println("Viimane teisendus: " + sisseLoogitud.getViimaneTeisendus());



        // Teisendatava arvu küsimine ja vastuse väljastamine
        Scanner scFail = new Scanner(System.in);
        System.out.println("Lõpetamiseks sisesta q.");
        while (true) {
            System.out.println("\nSisesta arv, mida soovid teisendada:");
            String arv = scFail.nextLine();
            if (arv.equals("q")) {
                scFail.close();
                break;
            }
            System.out.println("\nSisesta arvu algne kodeering.");
            System.out.println(
                    "    2 – binaarkood\n" +
                    "    8 – kaheksandkood\n" +
                    "    10 – kümnendkood\n" +
                    "    16 – kuueteistkümnendkood");

            try {
                int algneKodeering = Integer.valueOf(scFail.nextLine());

                System.out.println("\nSisesta tulemuse soovitud kodeering:");
                int tulemuseKodeering = Integer.valueOf(scFail.nextLine());

                Kood kood = new Kood(arv, algneKodeering);
                System.out.println("\nSee arv " + kodeeringudSõnaga.get(tulemuseKodeering) + ":\n" + kood.teisenda(tulemuseKodeering));


                // viimatise teisenduse salvestamine
                String tulemus = kood.teisenda(tulemuseKodeering);
                String viimane = arv + " (algne kodeering: " + algneKodeering + ") -> " + tulemus;
                sisseLoogitud.lisaTeisendus(viimane);
                sisseLoogitud.salvestaFaili();


            }
            catch (Exception e) {
                System.out.println("Sisestasid mingi ebasobiva väärtuse. Programm lõpetab töö.");
                scFail.close();
                break;
            }
        }
    }
}