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
import java.util.List;
import java.util.*;
import java.sql.*;
import tikape.runko.domain.KeskusteluAvaus;
import tikape.runko.domain.Viestiketju;

public class KeskusteluavausDao implements Dao<KeskusteluAvaus, Integer> {

    private Database database;

    public KeskusteluavausDao(Database database) {
        this.database = database;
    }

    @Override
    public KeskusteluAvaus findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Keskusteluavaus WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");
        String lahettaja = rs.getString("lahettaja");
        Timestamp lahetysaika = rs.getTimestamp("lahetysaika");
        String viesti = rs.getString("viesti");

        KeskusteluAvaus ka = new KeskusteluAvaus(id, lahettaja, lahetysaika, viesti);

        rs.close();
        stmt.close();
        connection.close();

        return ka;
    }

    @Override
    public List<KeskusteluAvaus> findAll() throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Keskusteluavaus");

        ResultSet rs = stmt.executeQuery();
        List<KeskusteluAvaus> Keskusteluavaukset = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String lahettaja = rs.getString("lahettaja");
            Timestamp lahetysaika = rs.getTimestamp("lahetysaika");
            String viesti = rs.getString("viesti");

            KeskusteluAvaus ka = new KeskusteluAvaus(id, lahettaja, lahetysaika, viesti);
        }

        rs.close();
        stmt.close();
        connection.close();

        return Keskusteluavaukset;
    }
    
    public List<KeskusteluAvaus> findAllIn(Collection<Integer> keys) throws SQLException {
        if (keys.isEmpty()) {
            return new ArrayList<>();
        }
        
        StringBuilder muuttujat = new StringBuilder("?");
        for (int i = 1; i < keys.size(); i++) {
            muuttujat.append(", ?");
        }
        
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Keskusteluavaus WHERE id IN (" + muuttujat + ")");
        int laskuri = 1;
        for (Integer key : keys) {
            stmt.setObject(laskuri, key);
            laskuri++;
        }
        
        ResultSet rs = stmt.executeQuery();
        List<KeskusteluAvaus> keskusteluavaukset = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String lahettaja = rs.getString("lahettaja");
            Timestamp lahetysaika = rs.getTimestamp("lahetysaika");
            String viesti = rs.getString("viesti");
            
            
            keskusteluavaukset.add(new KeskusteluAvaus(id, lahettaja, lahetysaika, viesti));
        }
        
        return keskusteluavaukset;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        // ei toteutettu
    }

}