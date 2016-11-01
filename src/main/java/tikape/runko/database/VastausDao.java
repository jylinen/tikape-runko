
package tikape.runko.database;

import java.util.*;
import java.sql.*;
import tikape.runko.domain.Vastaus;

public class VastausDao implements Dao<Vastaus, Integer, String, String> {

    private Database database;

    public VastausDao(Database database) {
        this.database = database;
    }

    @Override
    public Vastaus findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Vastaus WHERE id = ?");//vai id tähän
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

        Vastaus kv = new Vastaus(id, lahettaja, lahetysaika, viesti);

        rs.close();
        stmt.close();
        connection.close();

        return kv;
    }

    @Override
    public List<Vastaus> findAll() throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Vastaus");

        ResultSet rs = stmt.executeQuery();
        List<Vastaus> vastaukset = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String lahettaja = rs.getString("lahettaja");
            Timestamp lahetysaika = rs.getTimestamp("lahetysaika");
            String viesti = rs.getString("viesti");

            vastaukset.add(new Vastaus(id, lahettaja, lahetysaika, viesti));
        }

        rs.close();
        stmt.close();
        connection.close();

        return vastaukset;
    }

    public List<Vastaus> findAllIn(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Vastaus WHERE avaus = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        List<Vastaus> vastaukset = new ArrayList<>();

        while (rs.next()) {
            Integer id = rs.getInt("id");
            String lahettaja = rs.getString("lahettaja");
            Timestamp lahetysaika = rs.getTimestamp("lahetysaika");
            String viesti = rs.getString("viesti");

            vastaukset.add(new Vastaus(id, lahettaja, lahetysaika, viesti));
        }

        rs.close();
        stmt.close();
        connection.close();

        return vastaukset;
    }
    
    
    public void addNew(Integer key, String name, String message) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Vastaus (lahettaja, viesti, avaus) VALUES (?, ?, ?)");
        stmt.setObject(1, name);
        stmt.setObject(2, message);
        stmt.setObject(3, key);
        
        stmt.execute();

        stmt.close();
        connection.close();
    }

    @Override
    public void delete(Integer key) throws SQLException {
        // ei toteutettu
    }

}
