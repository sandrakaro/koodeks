package com.example.koodeks2;
import java.util.HashMap;
import java.util.Map;

public class Kood {
    // võibolla vaja lisada getter ja setter?
    // kas lisada komaga arvude funktsionaalsus???
    // äkki osad teisendused saab kokku võtta sest valem sisuliselt sama
    // äkki oleks lihtsam kui lähtekoodi tüüp oleks meetodi parameeter?
    // see teeks testimise ja koodi muutmise lihtsamaks

    /* Täisarvulised tähised:
    2 - binaarkood
    8 - kaheksandkood
    10 - kümnendkood
    16 - kuueteistkümnendkood
     */

    private String kood;
    private int tüüp;

    private static final String tähed = "ABCDEF"; // Tähed heksakoodi f-nides kasutamiseks
    private static final HashMap<Character,Integer> tähtedeVasted = new HashMap<Character,Integer>();

    // Täidame hashmapi heksakoodi tähe-numbri vastetega
    static {
    char[] tähedChar = {'A','B','C','D','E','F','a','b','c','d','e','f'};
    int vaste = 10;
    for (int i = 0; i < tähedChar.length; i++) {
        tähtedeVasted.put(tähedChar[i],vaste);
        vaste++;
        if (tähedChar[i] == 'F')
            vaste = 10;
    }
    }

    public Kood(String kood, int tüüp) throws EbasobivaKodeeringuErind {
        this.kood = kood;
        if (tüüp == 2 || tüüp == 8 || tüüp == 10 || tüüp == 16)
            this.tüüp = tüüp;
        else throw new EbasobivaKodeeringuErind();
    }

    // getterid ja setterid
    public String getKood() {
        return kood;
    }
    public void setKood(String kood) {
        this.kood = kood;
    }

    public int getTüüp() {
        return tüüp;
    }
    public void setTüüp(int tüüp) {
        this.tüüp = tüüp;
    }

    public boolean onKomakohtadega() {
        for (int i = 0; i < kood.length(); i++) {
            String märk = String.valueOf(kood.charAt(i));
            if (märk.equals("."))
                return true;
        }
        return false;
    }

    /**
     * Meetod teisendab etteantud koodi kümnendkoodi.
     * Teisendatava kodeering sisaldub klassi isendis ja on kasutaja ette antud.
     * @return Teisendamisel saadud kümnendkoodi arv
     * @throws Exception "Ebasobiv väärtus"
     */
    public String teisendaKümnendkoodi() throws EbasobivaKodeeringuErind {
        // peaklassis on kood ja tüüp vaja enne klassi isendiks tegemist ja
        // meetodite kasutamist trimmida ja õigeks tüübiks ja kujule teisendada

        double arvKümnendkoodis = 0;

        if (tüüp==10) arvKümnendkoodis = Double.parseDouble(kood);

        // Teisendamine kahendkoodist kümnendkoodi
        // Teisendamine kaheksandkoodist kümnendkoodi
        else if (tüüp == 2 || tüüp == 8) {

            String täisosa = kood; // kui on täisarv, siis täisosa on arv ise, kui mitte,
            // siis loome eraldi muutujad täisosaks ja komakohtadeks

            if (this.onKomakohtadega()) {
                String[] arvuOsad = kood.split(".");
                täisosa = arvuOsad[0];
                String komakohad = arvuOsad[1];

                // Loogika komakohtade arvutamiseks
                int asteKahanev = -1;
                for (int i = 0; i < komakohad.length(); i++) {
                    int bitt = Integer.parseInt(String.valueOf(komakohad.charAt(i)));
                    arvKümnendkoodis += bitt * Math.pow(tüüp,asteKahanev);
                    asteKahanev--;
                }
            }

            // Loogika täisosa arvutamiseks
            int asteKasvav = 0;
            for (int i = täisosa.length()-1; i >=0; i--) {
                int bitt = Integer.parseInt(String.valueOf(täisosa.charAt(i)));
                arvKümnendkoodis += bitt * Math.pow(tüüp,asteKasvav);
                asteKasvav++;
            }

        }

        // Teisendamine kuueteistkümnendkoodist kümnendkoodi
        else if (tüüp == 16) {
            int kuueteistkümneAste = 0;

            for (int i = kood.length()-1; i >=0; i--) {
                // A = 10, ..., F = 15
                int bitt = 0;
                if (tähtedeVasted.containsKey(kood.charAt(i))) {
                    bitt = tähtedeVasted.get(kood.charAt(i));
                }
                else {
                    bitt = Integer.parseInt(String.valueOf(kood.charAt(i)));
                }
                arvKümnendkoodis += bitt * Math.pow(16,kuueteistkümneAste);
                kuueteistkümneAste++;
            }
        }

        else {
            throw new EbasobivaKodeeringuErind();
        }

        return String.valueOf(arvKümnendkoodis);
    }

    /**
     * Meetod teisendab antud kümnendkoodi kasutaja soovitud kodeeringusse.
     * @param tulemuseTüüp Soovitud kodeering, mis antakse kasutaja sisendist funktsioonile ette
     * @return Teisendamisel saadud arv vastavas kodeeringus
     * @throws Exception "Ebasobiv väärtus"
     */
    public String teisenda(int tulemuseTüüp) throws EbasobivaKodeeringuErind {
        Kood kümnendkood = new Kood(teisendaKümnendkoodi(), 10); // kui on juba kümenndkoodis siis teisendaKümnendkoodi tagastab kohe sama arvu
        // tulemuseTüübiks on samad valikud, mis koodi algseks tüübiks

        StringBuilder saadudKood = new StringBuilder();
        int koodInt = Integer.parseInt(kümnendkood.kood);

        if (tulemuseTüüp == 10)
            return kümnendkood.kood;

        else if (tulemuseTüüp == 2 || tulemuseTüüp == 8) {
            for (; koodInt > 0; koodInt /= tulemuseTüüp) {
                saadudKood.append(koodInt % tulemuseTüüp);
            }
        }

        else if (tulemuseTüüp == 16) {
            for (; koodInt > 0; koodInt /= tulemuseTüüp) {
                int jääk = koodInt % tulemuseTüüp;
                if (jääk < 10) {
                    saadudKood.append(jääk);
                }
                else {
                    saadudKood.append(tähed.charAt(jääk-10));
                }
            }
        }

        else {
            throw new EbasobivaKodeeringuErind();
        }

        if (saadudKood.isEmpty()) return "0";
        return saadudKood.reverse().toString();
    }
}