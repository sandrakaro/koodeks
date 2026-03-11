import java.util.Scanner;

public class Koodeks {
    /* Täisarvulised tähised:
    1 - ASCII
    2 - binaarkood
    8 - kaheksandkood
    10 - kümnendkood
    16 - kuueteistkümnendkood
     */
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
            int kodeering = Integer.valueOf(sc.nextLine());

            Kood kood = new Kood(arv,kodeering);
            // praegu teeb kümnendkoodiks, sest muid funktsioone veel pole
            System.out.println("\nSee arv kümnendkoodis:\n" + kood.teisendaKümnendkoodi());
        }

    }
}