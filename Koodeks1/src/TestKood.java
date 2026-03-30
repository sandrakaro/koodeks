public class TestKood {
    static void main(String[] args) throws Exception {
        Kood bin1 = new Kood("0011", 2); // 3
        Kood bin2 = new Kood("010101",2); // 21
        Kood bin3 = new Kood("111111",2); // 63
        Kood bin4 = new Kood("000000",2); // 0

        Kood oct1 = new Kood("653",8); // 427
        Kood oct2 = new Kood("123",8); // 83
        Kood oct3 = new Kood("0",8); // 0
        Kood oct4 = new Kood("1",8); // 1

        Kood hex1 = new Kood("2C9B",16); // 11419
        Kood hex2 = new Kood("1B7E",16); // 7038
        Kood hex3 = new Kood("1AB",16); // 427
        Kood hex4 = new Kood("0",16); // 0

        Kood[] kahendkoodideMassiiv = {bin1,bin2,bin3,bin4};
        Kood[] kaheksandkoodideMassiiv = {oct1,oct2,oct3,oct4};
        Kood[] heksakoodideMassiiv = {hex1,hex2,hex3,hex4};

        int[] teisendaMilleks = {2,8,10,16};

        for (Kood kood : heksakoodideMassiiv) {
            System.out.println(kood.teisendaKümnendkoodi());
        }
        Kood test1 = new Kood(String.valueOf(oct1.teisendaKümnendkoodi()),10);
        System.out.println(test1.teisenda(16));
    }
}