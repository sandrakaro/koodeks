package com.example.koodeks2;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Kasutaja {
    private String kasutajaNimi;
    private String parooliRasi;

    public Kasutaja(String kasutajaNimi, String parool) {
        this.kasutajaNimi = kasutajaNimi;
        this.parooliRasi = looRasi(parool); // parool salvestatakse räsina
        this.viimaneTeisendus = "Puudub";
    }

    public String getKasutajaNimi() {
        return kasutajaNimi;
    }

    public String getParooliRasi() {
        return parooliRasi;
    }

    /**
     * Loob sisestatud paroolist SHA-256 räsi
     * @param sisestatudParool Kasutaja poolt sisestatud parool
     * @return Parooli räsi kuueteistkümnendsüsteemis sõnena
     */
    public static String looRasi(String sisestatudParool) {
        try {
            // loome MessageDigest objekti, kasutades SHA-256 algoritmi
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            // teisendame sisestatud parooli baitideks ja arvutame räsi
            byte[] digest = messageDigest.digest(sisestatudParool.getBytes());

            // käime läbi kõik räsi baitid
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < digest.length; i++) {
                // teisendame iga baidi kuueteistkümnendsüsteemi kujule
                sb.append(String.format("%02x", digest[i]));
            }
            // tagastame lõpliku räsi stringina
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Kontrollib kasutajanime ja parooli vastavust
     * @param sisestatudNimi Kasutaja sisestatu nimi
     * @param sisestatudParool Kasutaja sisestatud parool
     * @return True, kui andmed on õiged.
     */
    public boolean sisseloogimine(String sisestatudNimi, String sisestatudParool) {
        if(kasutajaNimi.equals(sisestatudNimi) && looRasi(sisestatudParool).equals(parooliRasi)) {
            return true;
        }
        else {
            return false;
        }
    }

    public String viimaneTeisendus;

    public String getViimaneTeisendus() {
        return viimaneTeisendus;
    }

    // uuendame kasutaja viimase teisenduse infot
    public void lisaTeisendus(String uusTeisendus) {
        this.viimaneTeisendus = uusTeisendus;
    }

    /**
     * Salvestame kasutaja andmed faili, lisades uue rea faili lõppu.
     * Andmed salvestatakse formaadis nimi:parooliräsi:viimaneTeisendus
     */
    public void salvestaFaili() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("kasutajad.txt", true))) {
            pw.println(kasutajaNimi + ":" + parooliRasi + ":" + viimaneTeisendus);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Loeme failist kasutajate andmed ja otsime vastet nimele ja paroolile.
     * Kuna faili lisatakse uusi kirjeid lõppu, tagastab meetod viimase leitud vaste, et tagada kõige uuema teisenduse kättesaamine.
     * @param otsitavNimi Kasutajanimi, mida otsitakse.
     * @param otsitavParool Parool, mille räsi kontrollitakse
     * @return Kasutaja objekt, kui andmed on õiged.
     */
    public static Kasutaja loeFailist(String otsitavNimi, String otsitavParool) {
        File fail = new File("kasutajad.txt");
        String rasiKontroll = looRasi(otsitavParool);
        Kasutaja leitudKasutaja = null;

        try (Scanner sc = new Scanner(fail, "UTF-8")) {
            while (sc.hasNextLine()) {
                String rida = sc.nextLine();
                String[] osad = rida.split(":", 3);

                String kasutajaNimi = osad[0];
                String parooliRaasi = osad[1];
                String viimaneTeisendus = osad[2];

                // kontrollime vastavust nimele ja parooli räsile
                if(osad[0].equals(otsitavNimi) && osad[1].equals(rasiKontroll)) {
                    leitudKasutaja = new Kasutaja(osad[0], otsitavParool);
                    leitudKasutaja.lisaTeisendus(osad[2]);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return leitudKasutaja;
    }
}