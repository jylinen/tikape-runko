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
        taulut.add("CREATE TABLE Keskustelualue (id integer PRIMARY KEY, nimi varchar(255));");
        taulut.add("INSERT INTO Keskustelualue (nimi) VALUES ('Ohjelmointi');");
        taulut.add("INSERT INTO Keskustelualue (nimi) VALUES ('Pelit');");
        taulut.add("INSERT INTO Keskustelualue (nimi) VALUES ('Koneet');");

        // tietokantataulujen luomiseen tarvittavat komennot suoritusjärjestyksessä
        //taulut.add("DROP TABLE Viestiketju;");
        taulut.add("CREATE TABLE Viestiketju (id integer PRIMARY KEY, nimi varchar(255), keskustelualue integer, "
                + "FOREIGN KEY(Keskustelualue) REFERENCES Keskustelualue(id));");
        taulut.add("INSERT INTO Viestiketju (nimi, keskustelualue) VALUES ('Testi', 1);");
        taulut.add("INSERT INTO Viestiketju (nimi, keskustelualue) VALUES ('Testi2', 2);");
        taulut.add("INSERT INTO Viestiketju (nimi, keskustelualue) VALUES ('TestiEri', 1);");
        
        taulut.add("CREATE TABLE KeskusteluAvaus (id integer PRIMARY KEY, lahettaja varchar(64) NOT NULL, lahetysaika timestamp NOT NULL, " 
                + "viesti varchar(3000) NOT NULL, viestiketju integer NOT NULL, FOREIGN KEY(viestiketju) REFERENCES Viestiketju(id));");
        taulut.add("INSERT INTO KeskusteluAvaus (lahettaja, viesti, viestiketju) VALUES ('jussi', 'terve vaan!', 1);");
        
        taulut.add("CREATE TABLE KeskusteluVastaus (id integer NOT NULL UNIQUE, lahettaja varchar(64) NOT NULL, lahetysaika timestamp NOT NULL, " 
                + "viesti varchar(3000) NOT NULL, keskusteluavaus integer NOT NULL, FOREIGN KEY(keskusteluavaus) REFERENCES KeskusteluAvaus(id));");
        taulut.add("INSERT INTO KeskusteluVastaus (lahettaja, viesti, keskusteluavaus) VALUES ('aatu', 'terve!', 1);");

        return taulut;
        
    }
}