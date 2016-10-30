
package tikape.runko.domain;
import java.sql.*;


public class Vastaus {
   
    private Integer id;
    private String lahettaja;
    private String viesti;
    private Timestamp lahetysaika;
    private Integer avaus;

    public Vastaus(Integer id, String lahettaja, String viesti, Timestamp lahetysaika) {
        this.id = id;
        this.lahettaja = lahettaja;
        this.viesti = viesti;
        this.lahetysaika = lahetysaika;
        this.avaus = avaus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLahettaja() {
        return lahettaja;
    }

    public void setLahettaja(String lahettaja) {
        this.lahettaja = lahettaja;
    }
    public Timestamp getLahetysAika() {
        return lahetysaika;
    }

    public void setLahetysAika(Timestamp lahetysaika) {
        this.lahetysaika = lahetysaika;
    }
    public String getViesti() {
        return viesti;
    }

    public void setViesti(String viesti) {
        this.viesti = viesti;
    }

}
