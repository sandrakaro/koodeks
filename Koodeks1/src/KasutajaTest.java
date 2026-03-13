public class KasutajaTest {
    static void main(String[] args) {
        Kasutaja test = new Kasutaja("Nimi", "parool");
        String testRasi = test.looRasi("parool");
        if (testRasi.length() == 64) {
            System.out.println(true);;
        }
        else {
            System.out.println(false);;
        }
        //System.out.println(testRasi);
    }
}