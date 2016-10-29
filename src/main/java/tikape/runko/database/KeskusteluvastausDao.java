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

public class KeskusteluvastausDao implements Dao<KeskusteluVastaus, Integer> {

    private Database database;

    public KeskusteluvastausDao(Database database) {
        this.database = database;
    }

    @Override
    public KeskusteluVastaus findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Keskusteluvastaus WHERE viesti_id = ?");//vai id tähän
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer viesti_id = rs.getInt("viesti_id");
        String lahettaja = rs.getString("lahettaja");
        Timestamp LahetysAika = rs.getTimestamp("LahetysAika");
        String Viesti = rs.getString("Viesti");

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
            Integer viesti_id = rs.getInt("viesti_id");
            String lahettaja = rs.getString("lahettaja");
            Timestamp LahetysAika = rs.getTimestamp("LahetysAika");
            String Viesti = rs.getString("Viesti");

            keskusteluvastaukset.add(new KeskusteluVastaus(viesti_id, lahettaja, LahetysAika, Viesti));
        }

        rs.close();
        stmt.close();
        connection.close();

        return keskusteluvastaukset;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        // ei toteutettu
    }

}