/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.runko.database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import tikape.runko.domain.Keskustelualue;
import tikape.runko.domain.Viestiketju;

/**
 *
 * @author juyl
 */
public class KeskustelualueDao implements Dao<Keskustelualue, Integer> {

    private Database database;

    public KeskustelualueDao(Database database) {
        this.database = database;
    }
     @Override
    public Keskustelualue findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Keskustelualue WHERE alue_id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");
        String nimi = rs.getString("nimi");

        Keskustelualue k = new Keskustelualue(id, nimi);

        rs.close();
        stmt.close();
        connection.close();

        return k;
    }

    @Override
    public List<Keskustelualue> findAll() throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Keskustelualue");

        ResultSet rs = stmt.executeQuery();
        List<Keskustelualue> Keskustelualueet = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String nimi = rs.getString("nimi");

            Keskustelualueet.add(new Keskustelualue(id, nimi));
        }

        rs.close();
        stmt.close();
        connection.close();

        return Keskustelualueet;
    }
    
    public List<Keskustelualue> findAllIn(Collection<Integer> keys) throws SQLException {
        if (keys.isEmpty()) {
            return new ArrayList<>();
        }
        
        StringBuilder muuttujat = new StringBuilder("?");
        for (int i = 1; i < keys.size(); i++) {
            muuttujat.append(", ?");
        }
        
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Keskustelualue WHERE id IN (" + muuttujat + ")");
        int laskuri = 1;
        for (Integer key : keys) {
            stmt.setObject(laskuri, key);
            laskuri++;
        }
        
        ResultSet rs = stmt.executeQuery();
        List<Keskustelualue> keskustelualueet = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String nimi = rs.getString("nimi");
            
            keskustelualueet.add(new Keskustelualue(id, nimi));
        }
        
        return keskustelualueet;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        // ei toteutettu
    }

}
