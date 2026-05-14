package com.example.koodeks2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kasutaja {
    private String kasutajaNimi;
    private String parooliRasi;
    private String viimaneTeisendus;

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

    public String getViimaneTeisendus() {
        return viimaneTeisendus;
    }

    // uuendame kasutaja viimase teisenduse infot
    public void lisaTeisendus(String uusTeisendus) {
        this.viimaneTeisendus = uusTeisendus;
    }

    /**
     * Salvestame kasutaja andmed faili. Failikirjutatakse üle.
     * Andmed salvestatakse formaadis nimi:parooliräsi:viimaneTeisendus
     */
    public void salvestaFaili() throws IOException {
        Path path = Paths.get("kasutajad.txt");
        List<String> failiRead = new ArrayList<>();

        if (Files.exists(path)) {
            failiRead = Files.readAllLines(path);
        }

        boolean kasutajaOlemas = false;
        String uuedAndmed = kasutajaNimi + ":" + parooliRasi + ":" + viimaneTeisendus;

        for (int i = 0; i < failiRead.size(); i++) {
            String rida = failiRead.get(i);
            String[] osad = rida.split(":", 2);

            if (osad[0].equals(this.kasutajaNimi)) {
                failiRead.set(i, uuedAndmed);
                kasutajaOlemas = true;
                break;
            }
        }

        if (!kasutajaOlemas) {
            failiRead.add(uuedAndmed);
        }

        Files.write(path, failiRead);
    }


    /**
     * Loeme failist kasutajate andmed ja otsime vastet nimele ja paroolile.
     * Kuna faili lisatakse uusi kirjeid lõppu, tagastab meetod viimase leitud vaste, et tagada kõige uuema teisenduse kättesaamine.
     * @param otsitavNimi Kasutajanimi, mida otsitakse.
     * @param otsitavParool Parool, mille räsi kontrollitakse
     * @return Kasutaja objekt, kui andmed on õiged.
     */
    public static Kasutaja loeFailist(String otsitavNimi, String otsitavParool) throws IOException {
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
            throw new IOException(e);
        }
        return leitudKasutaja;
    }

    /**
     * Kontrollib, kas sisestatud kasutaja nimi registreerimisel on juba olemas.
     * @param nimi sisestatud kasutaja nimi.
     * @return true, kui sisestatud kasutajanimi on juba registreeritud. false, kui mitte.
     */
    public static boolean kasutajaOnOlemas(String nimi) throws IOException {
        Path path = Paths.get("kasutajad.txt");

        List<String> read = Files.readAllLines(path);
        for (String rida : read) {
            String[] osad = rida.split(":");

            if (osad.length > 0 && osad[0].equals(nimi)) {
                return true;
            }
        }

        return false;
    }
}