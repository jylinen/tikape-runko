package tikape.runko.domain;
import java.util.*;

public class Keskustelualue {

    private Integer id;
    private String nimi;
    private List<Avaus> avaukset;

    public Keskustelualue(Integer id, String nimi) {
        this.id = id;
        this.nimi = nimi;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNimi() {
        return this.nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    
    public List getAvaukset() {
        return this.avaukset;
    }
    
    public void addToAvaukset(Avaus avaus) {
        this.avaukset.add(avaus);
    }

}