
package tikape.runko.domain;
import java.sql.*;


public class Vastaus {
   
    private Integer id;
    private String lahettaja;
    private Timestamp lahetysaika;
    private String viesti;
    private Avaus avaus;

    public Vastaus(Integer id, String lahettaja, Timestamp lahetysaika, String viesti) {
        this.id = id;
        this.lahettaja = lahettaja;
        this.lahetysaika = lahetysaika;
        this.viesti = viesti;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLahettaja() {
        return this.lahettaja;
    }

    public void setLahettaja(String lahettaja) {
        this.lahettaja = lahettaja;
    }
    public Timestamp getLahetysAika() {
        return this.lahetysaika;
    }

    public void setLahetysAika(Timestamp lahetysaika) {
        this.lahetysaika = lahetysaika;
    }
    
    public String getViesti() {
        return this.viesti;
    }

    public void setViesti(String viesti) {
        this.viesti = viesti;
    }

}
