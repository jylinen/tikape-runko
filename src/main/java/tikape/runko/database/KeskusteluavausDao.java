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

public class KeskusteluavausDao implements Dao<KeskusteluAvaus, Integer> {

    private Database database;

    public KeskusteluavausDao(Database database) {
        this.database = database;
    }

    @Override
    public KeskusteluAvaus findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Keskusteluavaus WHERE avaus_id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer avaus_id = rs.getInt("avaus_id");
        String Lahettaja = rs.getString("Lahettaja");
        Timestamp LahetysAika = rs.getTimestamp("lahetysAika");
        String Viesti = rs.getString("Viesti");

        KeskusteluAvaus ka = new KeskusteluAvaus(avaus_id, Lahettaja, LahetysAika, Viesti);

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
            Integer avaus_id = rs.getInt("avaus_id");
            String Lahettaja = rs.getString("Lahettaja");
            Timestamp LahetysAika = rs.getTimestamp("lahetysAika");
            String Viesti = rs.getString("Viesti");

            Keskusteluavaukset.add(new KeskusteluAvaus(avaus_id, Lahettaja, LahetysAika, Viesti));
        }

        rs.close();
        stmt.close();
        connection.close();

        return Keskusteluavaukset;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        // ei toteutettu
    }

}