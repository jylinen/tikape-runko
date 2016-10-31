
package tikape.runko.domain;
import java.util.*;
import java.sql.*;


public class Avaus {
    
    private Integer id;
    private String lahettaja;
    private String otsikko;
    private Timestamp lahetysaika;
    private String viesti;
    private Keskustelualue keskustelualue;
    private List<Vastaus> vastaukset;

    public Avaus(Integer id, String lahettaja, String otsikko, Timestamp lahetysaika, String viesti) {
        this.id = id;
        this.lahettaja = lahettaja;
        this.otsikko = otsikko;
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
    
    
    public String getOtsikko() {
        return this.otsikko;
    }

    public void setOtsikko(String otsikko) {
        this.otsikko = otsikko;
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
