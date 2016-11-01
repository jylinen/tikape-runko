
package tikape.runko.database;

import java.util.*;
import java.sql.*;
import tikape.runko.domain.Avaus;

public class AvausDao implements Dao<Avaus, Integer, String, String> {

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
        String otsikko = rs.getString("otsikko");

        Avaus ka = new Avaus(id, otsikko);

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
            String otsikko = rs.getString("otsikko");

            avaukset.add(new Avaus(id, otsikko));
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
            String otsikko = rs.getString("otsikko");

            avaukset.add(new Avaus(id, otsikko));
        }

        rs.close();
        stmt.close();
        connection.close();

        return avaukset;
    }

    public void addNew(Integer key, String name, String message) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Vastaus (lahettaja, viesti) VALUES (?, ?)");
        stmt.setObject(1, name);
        stmt.setObject(2, message);

        stmt.execute();

        stmt.close();
        connection.close();
    }

    @Override
    public void delete(Integer key) throws SQLException {
        // ei toteutettu
    }

}
