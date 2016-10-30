
package tikape.runko.domain;
import java.util.*;
import java.sql.*;


public class Avaus {
    
    private Integer id;
    private String lahettaja;
    private Timestamp lahetysaika;
    private String viesti;
    private Viestiketju viestiketju;
    private List<Vastaus> keskusteluvastaukset;

    public Avaus(Integer id, String lahettaja, Timestamp lahetysaika, String viesti) {
        this.id = id;
        this.lahettaja = lahettaja;
        this.lahetysaika = lahetysaika;
        this.viesti = viesti;
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
