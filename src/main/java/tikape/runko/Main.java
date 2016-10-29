package tikape.runko;

import java.util.HashMap;
import spark.ModelAndView;
import static spark.Spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.runko.database.Database;
import tikape.runko.database.KeskustelualueDao;
import tikape.runko.database.ViestiketjuDao;

public class Main {

    public static void main(String[] args) throws Exception {
        Database database = new Database("jdbc:sqlite:keskustelu.db");
        database.init();

        KeskustelualueDao keskustelualueDao = new KeskustelualueDao(database);
        ViestiketjuDao viestiketjuDao = new ViestiketjuDao(database);

        get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("viesti", "tervehdys");

            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

        get("/keskustelualueet", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("keskustelualueet", keskustelualueDao.findAll());

            return new ModelAndView(map, "keskustelualueet");
        }, new ThymeleafTemplateEngine());

        get("/keskustelualueet/:id", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("Viestiketjut", viestiketjuDao.findAll());

            return new ModelAndView(map, "Viestiketjut");
        }, new ThymeleafTemplateEngine());
    }
}
