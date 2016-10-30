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
        
        ViestiketjuDao viestiketjuDao = new ViestiketjuDao(database);
        KeskustelualueDao keskustelualueDao = new KeskustelualueDao(database);
        

        get("/", (req, res) -> {            
            HashMap map = new HashMap<>();
            map.put("keskustelualueet", keskustelualueDao.findAll());
            
            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

        get("/keskustelualue/:id", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("keskustelualue", keskustelualueDao.findOne(Integer.parseInt(req.params("id"))));
            
            map.put("viestiketjut", viestiketjuDao.findAllIn(Integer.parseInt(req.params("id"))));

            return new ModelAndView(map, "keskustelualue");
        }, new ThymeleafTemplateEngine());
        
    }
}
