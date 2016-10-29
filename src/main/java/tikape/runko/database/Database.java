package tikape.runko.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private String databaseAddress;

    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }

    public void init() {
        List<String> lauseet = sqliteLauseet();

        // "try with resources" sulkee resurssin automaattisesti lopuksi
        try (Connection conn = getConnection()) {
            Statement st = conn.createStatement();

            // suoritetaan komennot
            for (String lause : lauseet) {
                System.out.println("Running command >> " + lause);
                st.executeUpdate(lause);
            }

        } catch (Throwable t) {
            // jos tietokantataulu on jo olemassa, ei komentoja suoriteta
            System.out.println("Error >> " + t.getMessage());
        }
    }

    private List<String> sqliteLauseet() {
        ArrayList<String> taulut = new ArrayList<>();// lisää vielä keskusteluvastaus ja keskusteluavaus taulut.sekä insert intolla loput tiedot

        // tietokantataulujen luomiseen tarvittavat komennot suoritusjärjestyksessä
        taulut.add("CREATE TABLE KeskusteluAlue (alue_id integer PRIMARY KEY, nimi varchar(255));");
        taulut.add("INSERT INTO KeskusteluAlue (nimi) VALUES ('Traktorit');");
        taulut.add("INSERT INTO KeskusteluAlue (nimi) VALUES ('Koneet');");
        taulut.add("INSERT INTO KeskusteluAlue (nimi) VALUES ('Paskakärryt');");

        // tietokantataulujen luomiseen tarvittavat komennot suoritusjärjestyksessä
        taulut.add("CREATE TABLE Viestiketju (ketju_id integer PRIMARY KEY, nimi varchar(255), KeskusteluAlue integer, "
                + "FOREIGN KEY(KeskusteluAlue) REFERENCES KeskusteluAlue(alue_id));");
        taulut.add("INSERT INTO Viestiketju (nimi) VALUES ('Platon2');");
        taulut.add("INSERT INTO Viestiketju (nimi) VALUES ('Aristoteles2');");
        taulut.add("INSERT INTO Viestiketju (nimi) VALUES ('Homeros2');");

        return taulut;
        
    }
}