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
import tikape.runko.domain.KeskusteluVastaus;
import tikape.runko.domain.Viestiketju;

public class KeskusteluvastausDao implements Dao<KeskusteluVastaus, Integer> {

    private Database database;

    public KeskusteluvastausDao(Database database) {
        this.database = database;
    }

    @Override
    public KeskusteluVastaus findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Keskusteluvastaus WHERE id = ?");//vai id tähän
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer viesti_id = rs.getInt("id");
        String lahettaja = rs.getString("lahettaja");
        Timestamp LahetysAika = rs.getTimestamp("lahetysaika");
        String Viesti = rs.getString("viesti");

        KeskusteluVastaus kv = new KeskusteluVastaus(viesti_id, lahettaja, LahetysAika, Viesti);

        rs.close();
        stmt.close();
        connection.close();

        return kv;
    }

    @Override
    public List<KeskusteluVastaus> findAll() throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Keskusteluvastaus");

        ResultSet rs = stmt.executeQuery();
        List<KeskusteluVastaus> keskusteluvastaukset = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String lahettaja = rs.getString("lahettaja");
            Timestamp lahetysaika = rs.getTimestamp("lahetysaika");
            String viesti = rs.getString("viesti");

            keskusteluvastaukset.add(new KeskusteluVastaus(id, lahettaja, lahetysaika, viesti));
        }

        rs.close();
        stmt.close();
        connection.close();

        return keskusteluvastaukset;
    }

    public List<KeskusteluVastaus> findAllIn(Collection<Integer> keys) throws SQLException {
        if (keys.isEmpty()) {
            return new ArrayList<>();
        }

        StringBuilder muuttujat = new StringBuilder("?");
        for (int i = 1; i < keys.size(); i++) {
            muuttujat.append(", ?");
        }

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM KeskusteluVastaus WHERE id IN (" + muuttujat + ")");
        int laskuri = 1;
        for (Integer key : keys) {
            stmt.setObject(laskuri, key);
            laskuri++;
        }

        ResultSet rs = stmt.executeQuery();
        List<KeskusteluVastaus> keskusteluvastaukset = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String lahettaja = rs.getString("lahettaja");
            Timestamp lahetysaika = rs.getTimestamp("lahetysaika");
            String viesti = rs.getString("viesti");

            keskusteluvastaukset.add(new KeskusteluVastaus(id, lahettaja, lahetysaika, viesti));
        }

        return keskusteluvastaukset;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        // ei toteutettu
    }

}
