
package tikape.runko.domain;
import java.sql.*;
import java.util.*;


public class KeskusteluVastaus {
   
    private Integer id;
    private String lahettaja;
    private String viesti;
    private Timestamp lahetysaika;
    private KeskusteluAvaus keskusteluAvaus;

    public KeskusteluVastaus(Integer id, String lahettaja, Timestamp lahetysaika, String viesti) {
        this.id = id;
        this.lahettaja = lahettaja;
        this.viesti = viesti;
        this.lahetysaika = lahetysaika;
        this.keskusteluAvaus = keskusteluAvaus;
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

    public void setLahettaja(String Lahettaja) {
        this.lahettaja = Lahettaja;
    }
    public Timestamp getLahetysAika() {
        return lahetysaika;
    }

    public void setLahetysAika(Timestamp LahetysAika) {
        this.lahetysaika = LahetysAika;
    }
    public String getViesti() {
        return viesti;
    }

    public void setViesti(String Viesti) {
        this.viesti = Viesti;
    }

}
