 /* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.runko.domain;
import java.util.*;
import java.sql.*;
/**
 *
 * @author ruatuu
 */
public class KeskusteluAvaus {
    
    private Integer Avaus_id;
    private String Lahettaja;
    private Timestamp LahetysAika;
    private String Viesti;
    private Viestiketju ketju_id;
    private List<KeskusteluVastaus>KeskustelunVastaukset;

    public KeskusteluAvaus(Integer id, String Lahettaja, Timestamp LahetysAika, String Viesti) {
        this.Avaus_id = id;
        this.Lahettaja = Lahettaja;
        this.LahetysAika = LahetysAika;
        this.Viesti = Viesti;
    }

    public Integer getId() {
        return Avaus_id;
    }

    public void setId(Integer id) {
        this.Avaus_id = id;
    }

    public String getLahettaja() {
        return Lahettaja;
    }

    public void setLahettaja(String Lahettaja) {
        this.Lahettaja = Lahettaja;
    }
    
    public Timestamp getLahetysAika() {
        return LahetysAika;
    }

    public void setLahetysAika(Timestamp LahetysAika) {
        this.LahetysAika = LahetysAika;
    }
    public String getViesti() {
        return Viesti;
    }

    public void setViesti(String Viesti) {
        this.Viesti = Viesti;
    }

}
