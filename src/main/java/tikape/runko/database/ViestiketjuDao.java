
package tikape.runko.database;
import java.sql.*;
import java.util.*;
import tikape.runko.domain.Keskustelualue;
import tikape.runko.domain.Viestiketju;


public class ViestiketjuDao implements Dao<Viestiketju, Integer> {

    private Database database;
    private Dao<Keskustelualue, Integer> keskustelualueDao;

    public ViestiketjuDao(Database database) {
        this.database = database;
    }
     @Override
    public Viestiketju findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Viestiketju WHERE id = ?"); 
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");
        String nimi = rs.getString("nimi");

        Viestiketju v = new Viestiketju(id, nimi);

        rs.close();
        stmt.close();
        connection.close();

        return v;
    }

    
    public List<Viestiketju> findAll() throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Viestiketju");
        

        ResultSet rs = stmt.executeQuery();
        List<Viestiketju> viestiketjut = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String nimi = rs.getString("nimi");
            

            viestiketjut.add(new Viestiketju(id, nimi));
        }

        rs.close();
        stmt.close();
        connection.close();

        return viestiketjut;
    }
    
    public List<Viestiketju> findAllIn(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Viestiketju WHERE keskustelualue = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        List<Viestiketju> viestiketjut = new ArrayList<>();
        
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String nimi = rs.getString("nimi");
            

            viestiketjut.add(new Viestiketju(id, nimi));
        }

        rs.close();
        stmt.close();
        connection.close();

        return viestiketjut;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        // ei toteutettu
    }

}