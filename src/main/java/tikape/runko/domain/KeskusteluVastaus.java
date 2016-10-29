/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.runko.domain;
import java.sql.*;
import java.util.*;
/**
 *
 * @author ruatuu
 */
public class KeskusteluVastaus {
   
    private Integer Viesti_id;
    private String Lahettaja;
    private String Viesti;
    private Timestamp LahetysAika;
    private KeskusteluAvaus avaus_id;

    public KeskusteluVastaus(Integer id, String Lahettaja, Timestamp Lahetysaika, String Viesti) {
        this.Viesti_id = id;
        this.Lahettaja = Lahettaja;
        this.Viesti = Viesti;
        this.LahetysAika = Lahetysaika;
        this.avaus_id = avaus_id;
    }

    public Integer getId() {
        return Viesti_id;
    }

    public void setId(Integer id) {
        this.Viesti_id = id;
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
