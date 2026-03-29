import java.util.Scanner;

public class Main {
    /* Täisarvulised tähised:
    1 - ASCII
    2 - binaarkood
    8 - kaheksandkood
    10 - kümnendkood
    16 - kuueteistkümnendkood
     */
    static void main(String[] args) {

        //sisseloogimine või uue kasutaja loomine
        Kasutaja sisseLoogitud = null;
        Scanner sс = new Scanner(System.in);
        while (sisseLoogitud == null) {
            System.out.println("1 - Logi sisse, 2 - Registreeru");
            String valik = sс.nextLine();

            System.out.println("Kasutajanimi: ");
            String nimi = sс.nextLine();
            System.out.println("Parool: ");
            String parool = sс.nextLine();

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
        Scanner sc = new Scanner(System.in);
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
                    "    1 – ASCII\n" +
                    "    2 – binaarkood\n" +
                    "    8 – kaheksandkood\n" +
                    "    10 – kümnendkood\n" +
                    "    16 – kuueteistkümnendkood");
            int algneKodeering = Integer.valueOf(sc.nextLine());

            System.out.println("\nSisesta tulemuse soovitud kodeering:");
            int tulemuseKodeering = Integer.valueOf(sc.nextLine());

            Kood kood = new Kood(arv,algneKodeering);
            // praegu teeb kümnendkoodiks, sest muid funktsioone veel pole
            System.out.println("\nSee arv kümnendkoodis:\n" + kood.teisendaKümnendkoodi());

            // viimatise teisenduse salvestamine
            int tulemus = kood.teisendaKümnendkoodi();
            String viimane = arv + " (algne kodeering: " + algneKodeering + ") -> " + tulemus;
            sisseLoogitud.lisaTeisendus(viimane);
            sisseLoogitud.salvestaFaili();
        }

    }
}