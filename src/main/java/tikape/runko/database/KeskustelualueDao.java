
package tikape.runko.database;
import java.sql.*;
import java.util.*;
import tikape.runko.domain.Avaus;
import tikape.runko.domain.Keskustelualue;
import tikape.runko.domain.Viestiketju;


public class KeskustelualueDao implements Dao<Keskustelualue, Integer> {

    private Database database;
    private Dao<Viestiketju, Integer> viestiketjuDao;

    public KeskustelualueDao(Database database) {
        this.database = database;
        this.viestiketjuDao = viestiketjuDao;
    }
    
     @Override
    public Keskustelualue findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Keskustelualue WHERE id = ?");
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
        
        List<Keskustelualue> keskustelualueet = new ArrayList<>();
        
        while (rs.next()) {
            
            Integer id = rs.getInt("id");
            String nimi = rs.getString("nimi");
            
            keskustelualueet.add(new Keskustelualue(id , nimi));
        }    
                    
        return keskustelualueet;
    }
    
    public List<Keskustelualue> findAllIn(Integer key) throws SQLException {
        //ei toteutettu
        return null;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        // ei toteutettu
    }

}
