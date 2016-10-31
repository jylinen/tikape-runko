/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.runko.database;
import java.util.*;
import java.sql.*;
import tikape.runko.domain.Avaus;

public class AvausDao implements Dao<Avaus, Integer, String> {

    private Database database;

    public AvausDao(Database database) {
        this.database = database;
    }

    @Override
    public Avaus findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Avaus WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");
        String lahettaja = rs.getString("lahettaja");
        String otsikko = rs.getString("otsikko");
        Timestamp lahetysaika = rs.getTimestamp("lahetysaika");
        String viesti = rs.getString("viesti");

        Avaus ka = new Avaus(id, lahettaja, otsikko, lahetysaika, viesti);

        rs.close();
        stmt.close();
        connection.close();

        return ka;
    }

    @Override
    public List<Avaus> findAll() throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Avaus");

        ResultSet rs = stmt.executeQuery();
        List<Avaus> avaukset = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String lahettaja = rs.getString("lahettaja");
            String otsikko = rs.getString("otsikko");
            Timestamp lahetysaika = rs.getTimestamp("lahetysaika");
            String viesti = rs.getString("viesti");

            Avaus ka = new Avaus(id, lahettaja, otsikko, lahetysaika, viesti);
        }

        rs.close();
        stmt.close();
        connection.close();

        return avaukset;
    }
    
    public List<Avaus> findAllIn(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Avaus WHERE keskustelualue = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        List<Avaus> avaukset = new ArrayList<>();
        
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String lahettaja = rs.getString("lahettaja");
            String otsikko = rs.getString("otsikko");
            Timestamp lahetysaika = rs.getTimestamp("lahetysaika");
            String viesti = rs.getString("viesti");

            avaukset.add(new Avaus(id, lahettaja, otsikko, lahetysaika, viesti));
        }

        rs.close();
        stmt.close();
        connection.close();

        return avaukset;
    }
    
    
    public void addNew(String name) throws SQLException {
        
    }

    @Override
    public void delete(Integer key) throws SQLException {
        // ei toteutettu
    }

}