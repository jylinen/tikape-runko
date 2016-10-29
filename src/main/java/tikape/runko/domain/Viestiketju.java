/*
 * To change this license header, choose License Headers in Project Properties.
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
public class Viestiketju {

    private Integer ketju_id;
    private String nimi;
    private Keskustelualue alue_id; 
    private List<KeskusteluAvaus>Keskustelunavaukset;

    public Viestiketju(Integer id, String nimi) {
        this.ketju_id = id;
        this.nimi = nimi;
    }

    public Integer getId() {
        return ketju_id;
    }

    public void setId(Integer id) {
        this.ketju_id = id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

}
