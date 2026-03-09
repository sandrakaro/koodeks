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

    public Kood(String kood, int tüüp) {
        this.kood = kood;
        this.tüüp = tüüp;
    }

    /**
     * Meetod teisendab etteantud koodi kümnendkoodi.
     * Teisendatava kodeering sisaldub klassi isendis ja on kasutaja ette antud.
     * @param kood Antud kood, mida teisendada, sõnena
     * @param tüüp Antud koodi tüüp täisarvulise tähisena. Valikud on eespool kommenteeritud
     * @return Teisendamisel saadud kümnendkoodi arv
     */
    public int teisendaKümnendkoodi() {
        // peaklassis on kood ja tüüp vaja enne klassi isendiks tegemist ja
        // meetodite kasutamist trimmida ja õigeks tüübiks ja kujule teisendada

        int arvKümnendkoodis = 0;

        // Teisendamine ASCII-koodist kümnendkoodi
        if (tüüp == 1) {
            // kood
        }

        // Teisendamine kahendkoodist kümnendkoodi
        else if (tüüp == 2) {
            int kaheAste = 0;

            for (int i = kood.length()-1; i >=0; i--) {
                int bitt = Integer.parseInt(String.valueOf(kood.charAt(i)));
                arvKümnendkoodis += bitt * Math.pow(2,kaheAste);
                kaheAste++;
            }
        }

        // Teisendamine kaheksandkoodist kümnendkoodi
        else if (tüüp == 8) {
            int kaheksaAste = 0;

            for (int i = kood.length()-1; i >=0; i--) {
                int bitt = Integer.parseInt(String.valueOf(kood.charAt(i)));
                arvKümnendkoodis += bitt * Math.pow(8,kaheksaAste);
                kaheksaAste++;
            }
        }

        // Teisendamine kuueteistkümnendkoodist kümnendkoodi
        else if (tüüp == 16) {
            int kuueteistkümneAste = 0;

            // !!!!!!! SEE PEAB MUJAL OLEMA, tegelen mingi hetk
            // muidu läheb iga kord for-tsüklisse kui heksakood on
            HashMap<Character,Integer> tähtedeVasted= new HashMap<Character,Integer>();
            char[] tähed = {'A','B','C','D','E','F'};
            int vaste = 10;
            for (int i = 0; i < tähed.length; i++) {
                tähtedeVasted.put(tähed[i],vaste);
                vaste++;
            }

            for (int i = kood.length()-1; i >=0; i--) {
                /*
                A = 10
                B = 11
                C = 12
                D = 13
                E = 14
                F = 15
                 */
                // See on laisk lahendus praegu, vaatab kas muudba paremaks
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

        // Probleemide püüdmiseks debugimisel
        else {
            System.out.println("Sellist kodeeringut ei ole");
        }

        return arvKümnendkoodis;
    }
}