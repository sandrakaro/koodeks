import java.util.HashMap;

public class Kood {
    // võibolla vaja lisada getter ja setter?
    // kas lisada komaga arvude funktsionaalsus???
    // äkki osad teisendused saab kokku võtta sest valem sisuliselt sama
    // äkki oleks lihtsam kui lähtekoodi tüüp oleks meetodi parameeter?
    // see teeks testimise ja koodi muutmise lihtsamaks

    /* Täisarvulised tähised:
    1 - ASCII
    2 - binaarkood
    8 - kaheksandkood
    10 - kümnendkood
    16 - kuueteistkümnendkood
     */

    public String kood;
    public int tüüp;

    private static final String tähed = "ABCDEF"; // Tähed heksakoodi f-nides kasutamiseks
    private static final HashMap<Character,Integer> tähtedeVasted = new HashMap<Character,Integer>();

    // Täidame hashmapi heksakoodi tähe-numbri vastetega
    static {
    char[] tähed = {'A','B','C','D','E','F','a','b','c','d','e','f'};
    int vaste = 10;
    for (int i = 0; i < tähed.length; i++) {
        tähtedeVasted.put(tähed[i],vaste);
        vaste++;
        if (tähed[i] == 'F')
            vaste = 10;
    }
    }

    public Kood(String kood, int tüüp) {
        this.kood = kood;
        this.tüüp = tüüp;
    }

    /**
     * Meetod teisendab etteantud koodi kümnendkoodi.
     * Teisendatava kodeering sisaldub klassi isendis ja on kasutaja ette antud.
     * @return Teisendamisel saadud kümnendkoodi arv
     */
    public int teisendaKümnendkoodi() {
        // peaklassis on kood ja tüüp vaja enne klassi isendiks tegemist ja
        // meetodite kasutamist trimmida ja õigeks tüübiks ja kujule teisendada
        // input validation maini vaja lisada

        int arvKümnendkoodis = 0;

        // Teisendamine ASCII-koodist kümnendkoodi
        // kinda arvan et seda pole vaja tegelt
        /*if (tüüp == 1) {
            System.out.println("Seda funktsionaalsust pole veel lisatud");
        }*/

        // Teisendamine kahendkoodist kümnendkoodi
        // Teisendamine kaheksandkoodist kümnendkoodi
        if (tüüp == 2 || tüüp == 8) {
            int aste = 0;

            for (int i = kood.length()-1; i >=0; i--) {
                int bitt = Integer.parseInt(String.valueOf(kood.charAt(i)));
                arvKümnendkoodis += bitt * Math.pow(tüüp,aste);
                aste++;
            }
        }

        // Teisendamine kuueteistkümnendkoodist kümnendkoodi
        else if (tüüp == 16) {
            int kuueteistkümneAste = 0;

            for (int i = kood.length()-1; i >=0; i--) {
                // A = 10, ..., F = 15
                // See on laisk lahendus praegu, vaatab kas muudab paremaks
                int bitt = 0;
                try {
                    bitt = Integer.parseInt(String.valueOf(kood.charAt(i)));
                } catch (NumberFormatException e) {
                    bitt = tähtedeVasted.get(kood.charAt(i));
                }
                arvKümnendkoodis += bitt * Math.pow(16,kuueteistkümneAste);
                kuueteistkümneAste++;
            }
        }

        // Probleemide püüdmiseks debugimisel, muidu peaks olema input validation mainis
        else {
            System.out.println("Sellist kodeeringut ei ole");
        }

        return arvKümnendkoodis;
    }

    /**
     * Meetod teisendab antud kümnendkoodi kasutaja soovitud kodeeringusse.
     * @param tulemuseTüüp Soovitud kodeering, mis antakse kasutaja sisendist funktsioonile ette
     * @return Teisendamisel saadud arv vastavas kodeeringus
     */
    public String teisendaKümnendkoodist(int tulemuseTüüp) {
        // tulemuseTüübiks on samad valikud, mis koodi algseks tüübiks
        StringBuilder saadudKood = new StringBuilder();
        int koodInt = Integer.parseInt(kood);

        if (tulemuseTüüp == 2 || tulemuseTüüp == 8) {
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
        return saadudKood.reverse().toString();
    }
}