
package tikape.runko.domain;
import java.util.*;
import java.sql.*;


public class Viestiketju {

    private Integer id;
    private String nimi;
    private Keskustelualue keskustelualue; 
    private List<KeskusteluAvaus> keskustelunavaukset;

    public Viestiketju(Integer id, String nimi) {
        this.id = id;
        this.nimi = nimi;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

}
