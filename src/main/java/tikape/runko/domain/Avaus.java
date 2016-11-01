
package tikape.runko.domain;
import java.util.*;
import java.sql.*;


public class Avaus {
    
    private Integer id;
    private String otsikko;
    private Keskustelualue keskustelualue;
    private List<Vastaus> vastaukset;

    public Avaus(Integer id, String otsikko) {
        this.id = id;
        this.otsikko = otsikko;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
      
    public String getOtsikko() {
        return this.otsikko;
    }

    public void setOtsikko(String otsikko) {
        this.otsikko = otsikko;
    }
    

}
