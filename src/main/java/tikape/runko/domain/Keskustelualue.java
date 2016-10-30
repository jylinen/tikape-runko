package tikape.runko.domain;
import java.util.*;

public class Keskustelualue {

    private Integer id;
    private String nimi;
    private List<Viestiketju> viestiketjut;

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
    
    public List getViestiketjut() {
        return this.viestiketjut;
    }
    
    public void addToViestiketjut(Viestiketju viestiketju) {
        this.viestiketjut.add(viestiketju);
    }

}