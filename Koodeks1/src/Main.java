import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    /* Täisarvulised tähised:
    1 - ASCII
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

            try {
                int algneKodeering = Integer.valueOf(sc.nextLine());

                System.out.println("\nSisesta tulemuse soovitud kodeering:");
                int tulemuseKodeering = Integer.valueOf(sc.nextLine());

                Kood kood = new Kood(arv, algneKodeering);
                System.out.println("\nSee arv " + kodeeringudSõnaga.get(tulemuseKodeering) + ":\n" + kood.teisenda(tulemuseKodeering));
            }
            catch (Exception e) {
                System.out.println("Sisestasid ebasobiva väärtuse. Programm lõpetab töö.");
                sc.close();
                break;
            }


            /*
            // praegu teeb kümnendkoodiks, sest muid funktsioone veel pole
            System.out.println("\nSee arv kümnendkoodis:\n" + kood.teisendaKümnendkoodi());
            */
        }

    }
}