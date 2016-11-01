package tikape.runko;

import java.util.HashMap;
import spark.ModelAndView;
import static spark.Spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.runko.database.*;
import tikape.runko.domain.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Database database = new Database("jdbc:sqlite:keskustelu.db");
        database.init();

        KeskustelualueDao keskustelualueDao = new KeskustelualueDao(database);
        AvausDao avausDao = new AvausDao(database);
        VastausDao vastausDao = new VastausDao(database);

        get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("keskustelualueet", keskustelualueDao.findAll());

            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

        post("/", (req, res) -> {
            String nimi = req.queryParams("nimi");
            keskustelualueDao.addNew(null, nimi, "");
            
            res.redirect("/");
            return "";
        });

        get("/keskustelualue/:id", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("keskustelualue", keskustelualueDao.findOne(Integer.parseInt(req.params("id"))));
            
            map.put("avaukset", avausDao.findAllIn(Integer.parseInt(req.params("id"))));

            return new ModelAndView(map, "keskustelualue");
        }, new ThymeleafTemplateEngine());
        
        post("/keskustelualue/:id", (req, res) -> {
            String otsikko = req.queryParams("otsikko");
            String lahettaja = req.queryParams("lahettaja");
            String viesti = req.queryParams("viesti");
            
            Keskustelualue k = keskustelualueDao.findOne(Integer.parseInt(req.params("id")));
            Integer keyK = k.getId();
            
            avausDao.addNew(keyK, otsikko, "");
            
            Avaus a = avausDao.findOne(keyK);
            Integer keyA = a.getId();
            vastausDao.addNew(keyA, lahettaja, viesti);
            
            res.redirect("/keskustelualue/:keyK");
            return "";
        });

        get("/keskustelualue/:id/avaus/:id", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("avaus", avausDao.findOne(Integer.parseInt(req.params("id"))));

            map.put("vastaukset", vastausDao.findAllIn(Integer.parseInt(req.params("id"))));

            return new ModelAndView(map, "avaus");
        }, new ThymeleafTemplateEngine());

    }
}
