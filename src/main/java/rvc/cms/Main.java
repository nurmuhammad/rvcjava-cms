package rvc.cms;

import org.h2.tools.Server;
import rvc.RvcServer;
import rvc.ann.Template;
import rvc.cms.init.Config;

/**
 * @author nurmuhammad
 */

public class Main {

    public static void main(String[] args) throws Exception {

        Database.backupSql();

        RvcServer rvcServer = new RvcServer()
                .addTemplate(Template.TemplateEngine.PEBBLE, Pebble.instance)
                .suffix(".html");

        rvcServer.port(Config.get("server.port", 4567));

        rvcServer.init();

        rvcServer.classes(LoginController.class);

        new Thread(() -> rvcServer.start()).start();

        // h2 db-manager server
        if (Application.debug()) {
            Server webServer = Server.createWebServer("-webAllowOthers", "-webPort", "8083").start();
        }

    }

}