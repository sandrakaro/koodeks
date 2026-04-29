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
        this.kood = kood.replace(',','.'); // juhuks kui sisestati vale komakoha sümbol

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

    /**
     * Meetod kontrollib, kas antud koodi arvuline väärtus on mitte-täisarv
     * ehk kas selles sisaldub punkt
     * @return True, kui leidub punkt (komakohtade eraldaja), false, kui ei leidu
     */
    public boolean onKomakohtadega() {
        return kood.contains(".");
    }

    /**
     * Meetod teisendab etteantud koodi kümnendkoodi.
     * Teisendatava kodeering sisaldub klassi isendis ja on kasutaja ette antud.
     * @return Teisendamisel saadud kümnendkoodi arv
     */
    public String teisendaKümnendkoodi() {
        // peaklassis on kood ja tüüp vaja enne klassi isendiks tegemist ja
        // meetodite kasutamist trimmida ja õigeks tüübiks ja kujule teisendada

        double arvKümnendkoodis = 0;
        boolean onKomakohtadega = this.onKomakohtadega(); // nii ei pea igal korral kontrollf-ni minema


        String täisosa = kood;
        String murdosa = ""; // siin tegelikult võiks parem lahendus olla kui luua tühje muutujaid, samas
        // koodi dubleerida igale poole ka ei tahaks
        // ---------
        // kui on täisarv, siis täisosa on arv ise ja murdosa tühi, kui mitte,
        // siis loome eraldi muutujad täisosaks ja komakohtadeks
        if (onKomakohtadega && tüüp!=10) { // väldime veidi ajakulu, sest neid muutujaid pole kümnendkoodidel vaja
            String[] arvuOsad = kood.split("\\.");
            täisosa = arvuOsad[0];
            murdosa = arvuOsad[1];
        }


        if (tüüp==10) {
            if (onKomakohtadega) arvKümnendkoodis = Double.parseDouble(kood);
            else arvKümnendkoodis = Integer.parseInt(kood);
        }

        // Teisendamine kahendkoodist kümnendkoodi
        // Teisendamine kaheksandkoodist kümnendkoodi
        else if (tüüp == 2 || tüüp == 8) {



            if (onKomakohtadega) {
                // Loogika murdosa arvutamiseks
                int asteKahanev = -1;
                for (int i = 0; i < murdosa.length(); i++) {
                    int bitt = Integer.parseInt(String.valueOf(murdosa.charAt(i)));
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
            int asteKasvav = 0;

            if (onKomakohtadega) {
                // Loogika murdosa arvutamiseks
                int asteKahanev = -1;
                for (int i = 0; i < murdosa.length(); i++) {
                    int bitt = 0;
                    if (tähtedeVasted.containsKey(murdosa.charAt(i)))
                        bitt = tähtedeVasted.get(murdosa.charAt(i));
                    else
                        bitt = Integer.parseInt(String.valueOf(murdosa.charAt(i)));
                    arvKümnendkoodis += bitt * Math.pow(tüüp,asteKahanev);
                    asteKahanev--;
                }
            }

            // Loogika täisosa arvutamiseks
            for (int i = täisosa.length()-1; i >=0; i--) {
                // A = 10, ..., F = 15
                int bitt = 0;
                if (tähtedeVasted.containsKey(täisosa.charAt(i)))
                    bitt = tähtedeVasted.get(täisosa.charAt(i));
                else
                    bitt = Integer.parseInt(String.valueOf(täisosa.charAt(i)));
                arvKümnendkoodis += bitt * Math.pow(16,asteKasvav);
                asteKasvav++;
            }
        }

        return String.valueOf(arvKümnendkoodis);
    }

    /**
     * Meetod teisendab antud kümnendkoodi kasutaja soovitud kodeeringusse.
     * @param tulemuseTüüp Soovitud kodeering, mis antakse kasutaja sisendist funktsioonile ette
     * @return Teisendamisel saadud arv vastavas kodeeringus
     * @throws EbasobivaKodeeringuErind
     */
    public String teisenda(int tulemuseTüüp) throws EbasobivaKodeeringuErind {
        Kood kümnendkood = new Kood(teisendaKümnendkoodi(), 10);
        // kui on juba kümnendkoodis siis teisendaKümnendkoodi tagastab kohe sama arvu
        // tulemuseTüübiks on samad valikud, mis koodi algseks tüübiks

        //StringBuilder saadudKood = new StringBuilder();
        boolean onKomakohtadega = this.onKomakohtadega();

        double koodDouble = Double.parseDouble(kümnendkood.kood);
        int täisosa = (int) koodDouble;
        double murdosa = koodDouble - täisosa;

        StringBuilder saadudTäisosa = new StringBuilder();
        StringBuilder saadudMurdosa = new StringBuilder();

        if (tulemuseTüüp == 10)
            return kümnendkood.kood;

        else if (tulemuseTüüp == 2 || tulemuseTüüp == 8) {
            // Täisosa lisamine
            for (; täisosa > 0; täisosa /= tulemuseTüüp) {
                saadudTäisosa.append(täisosa % tulemuseTüüp);
            }

            // Murdosa lisamine
            if (onKomakohtadega) {
                for (int i = 0; i < 15; i++) { // piirame lõpmatuid arve 15 komakohaga
                    if (murdosa==0.0)
                        break;
                    murdosa *= tulemuseTüüp;
                    int lisatavTäisarv = (int)murdosa;
                    saadudMurdosa.append(lisatavTäisarv);
                    murdosa = murdosa - lisatavTäisarv;
                }
            }
        }

        else if (tulemuseTüüp == 16) {
            // Täisosa lisamine
            for (; täisosa > 0; täisosa /= tulemuseTüüp) {
                int jääk = täisosa % tulemuseTüüp;
                if (jääk < 10) {
                    saadudTäisosa.append(jääk);
                }
                else {
                    saadudTäisosa.append(tähed.charAt(jääk-10));
                }
            }

            // Murdosa lisamine
            if (onKomakohtadega) {
                for (int i = 0; i < 15; i++) { // piirame lõpmatuid arve 15 komakohaga
                    if (murdosa==0.0)
                        break;
                    murdosa *= tulemuseTüüp;
                    int lisatavTäisarv = (int)murdosa;
                    if (lisatavTäisarv < 10)
                        saadudMurdosa.append(lisatavTäisarv);
                    else
                        saadudMurdosa.append(tähed.charAt(lisatavTäisarv-10));
                    murdosa = murdosa - lisatavTäisarv;
                }
            }
        }

        if (saadudTäisosa.isEmpty() && saadudMurdosa.isEmpty()) return "0.0";
        return saadudTäisosa.reverse().toString() + "." + saadudMurdosa;
    }
}