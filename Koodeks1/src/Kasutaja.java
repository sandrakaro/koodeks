//natuke hiljem lisan ka viimatise teisenduse salvestamine ning põhalikumad ja korrektsed kommentaarid

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Kasutaja {
    private String kasutajaNimi;
    private String parooliRasi;

    public Kasutaja(String kasutajaNimi, String parool) {
        this.kasutajaNimi = kasutajaNimi;
        this.parooliRasi = looRasi(parool);
    }

    public String getKasutajaNimi() {
        return kasutajaNimi;
    }

    public String getParooliRasi() {
        return parooliRasi;
    }

    //tagastab sisestatud parooli räsi
    public String looRasi(String sisestatudParool) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] digest = messageDigest.digest(sisestatudParool.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < digest.length; i++) {
                sb.append(String.format("%02x", digest[i]));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    //kasutajanime ja parooli kontroll
    public boolean sisseloogimine(String sisestatudNimi, String sisestatudParool) {
        if(this.kasutajaNimi.equals(sisestatudNimi) && looRasi(sisestatudParool).equals(this.parooliRasi)) {
            return true;
        }
        else {
            return false;
        }
    }

    //viimatise teisenduse salvestamine

}