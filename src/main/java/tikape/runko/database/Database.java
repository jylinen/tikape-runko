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
        ArrayList<String> taulut = new ArrayList<>();

        // tietokantataulujen luomiseen tarvittavat komennot suoritusjärjestyksessä
        //taulut.add("DROP TABLE Keskustelualue;");
        taulut.add("CREATE TABLE Keskustelualue (id integer PRIMARY KEY, nimi varchar(255) UNIQUE);");
        taulut.add("INSERT INTO Keskustelualue (nimi) VALUES ('Ohjelmointi');");
        taulut.add("INSERT INTO Keskustelualue (nimi) VALUES ('Pelit');");
        taulut.add("INSERT INTO Keskustelualue (nimi) VALUES ('Koneet');");
        
        taulut.add("CREATE TABLE Avaus (id integer PRIMARY KEY, otsikko varchar(20) NOT NULL UNIQUE, " 
                + "keskustelualue integer NOT NULL, FOREIGN KEY(keskustelualue) REFERENCES Viestiketju(id));");
        taulut.add("INSERT INTO Avaus (otsikko, keskustelualue) VALUES ('testi', 1);");
        taulut.add("INSERT INTO Avaus (otsikko, keskustelualue) VALUES ('testi2', 1);");
        
        taulut.add("CREATE TABLE Vastaus (id integer PRIMARY KEY, lahettaja varchar(64) NOT NULL, lahetysaika timestamp, " 
                + "viesti varchar(3000) NOT NULL, avaus integer NOT NULL, FOREIGN KEY(avaus) REFERENCES Avaus(id));");
        taulut.add("INSERT INTO Vastaus (lahettaja, viesti, avaus) VALUES ('aatu', 'terve!', 1);");

        return taulut;
        
    }
}