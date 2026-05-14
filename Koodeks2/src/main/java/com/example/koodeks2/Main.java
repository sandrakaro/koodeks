package com.example.koodeks2;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Scanner;

public class Main {

    /* Täisarvulised tähised:
    2 - binaarkood
    8 - kaheksandkood
    10 - kümnendkood
    16 - kuueteistkümnendkood
     */

    public static Map<Integer, String> kodeeringudSõnaga = Map.of(2, "kahendkoodis", 8, "kaheksandkoodis",
            10, "kümnendkoodis", 16, "kuueteistkümnendkoodis");

    static void main(String[] args) throws IOException {

        // Paneme väljundi kodeeringuks UTF-8 (JFX ei anna muidu täpitähti, ajutine lahendus enne GUI-sse üleviimist)
        System.setOut(new PrintStream(System.out, true, "UTF-8"));

        //sisselogimine või uue kasutaja loomine
        Kasutaja sisseLoogitud = null;
        Scanner sc = new Scanner(System.in); // sellega oleks vaja try with resources
        while (sisseLoogitud == null) {
            System.out.println("1 - Logi sisse, 2 - Registreeru");
            String valik = sc.nextLine();

            System.out.println("Kasutajanimi: ");
            String nimi = sc.nextLine();
            System.out.println("Parool: ");
            String parool = sc.nextLine();

            if (valik.equals("2")) {
                if (Kasutaja.kasutajaOnOlemas(nimi)) {
                    System.out.println("Kasutaja nimega: " + nimi + "on juba olemas.");
                } else {
                    Kasutaja uus = new Kasutaja(nimi, parool);
                    uus.salvestaFaili();
                    System.out.println("Kasutaja loodud.");
                }
            } else {
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
            // siin on vaja kõik osad try-catch plokkidesse viia et ei küsitaks uuesti seda, mis oli õige

            System.out.println("\nSisesta arv, mida soovid teisendada:");
            String arv = sc.nextLine();
            if (arv.equals("q")) {
                // q vajutamine võiks töötada igal millegi küsimisel
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
                // praegu on natuke veider struktuur kus peab 2 korda kodeeringud valesti sisestama
                // kui sisestasid vale arvu, sest muidu ta arvu ennast uuesti ei küsi
                int algneKodeering = Integer.valueOf(sc.nextLine());
                Kood kood = new Kood(arv, algneKodeering);

                System.out.println("\nSisesta tulemuse soovitud kodeering:");
                int tulemuseKodeering = Integer.valueOf(sc.nextLine());

                System.out.println("\nSee arv " + kodeeringudSõnaga.get(tulemuseKodeering) +
                        ":\n" + kood.teisenda(tulemuseKodeering));

                // viimatise teisenduse salvestamine
                String tulemus = kood.teisenda(tulemuseKodeering);
                String viimane = kood.getKood() + " (algne kodeering: " + kood.getTüüp() + ") -> " + tulemus;
                sisseLoogitud.lisaTeisendus(viimane);
                sisseLoogitud.salvestaFaili();

            } catch (NumberFormatException | EbasobivaKodeeringuErind e) {
                System.out.println("Ebasobiv väärtus, sisesta väärtused uuesti.");
            } catch (IOException e) {
                throw new IOException(e);
            }
        }
    }
}
