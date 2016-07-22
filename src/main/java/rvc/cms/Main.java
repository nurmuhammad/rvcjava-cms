package rvc.cms;

import org.h2.tools.Server;
import rvc.ModelAndView;
import rvc.RvcServer;
import rvc.ann.Template;
import rvc.cms.init.Config;
import rvc.cms.model.User;
import rvc.http.Request;
import rvc.http.Response;
import rvc.http.Session;

/**
 * @author nurmuhammad
 */

public class Main {

    public static void main(String[] args) throws Exception {

        Database.backupSql();

        RvcServer rvcServer = new RvcServer()
                .addTemplate(Template.TemplateEngine.PEBBLE, Pebble.instance);

        rvcServer.port(Config.get("server.port", 4567));

        rvcServer.init();
        rvcServer.classes(UserController.class);

        /* rvcServer.before("administer, administer/*", () -> {
            User user = Session.get().attribute("user");
            if (user == null) {
                Response.get().redirect("/login");
            }
        });

        rvcServer.get("login", () -> {
            ModelAndView modelAndView = new ModelAndView(null, "login.html");
            return modelAndView;
        }, Pebble.instance);

        rvcServer.post("login", () -> {
            String email = Request.get().queryParams("email");
            String password = Request.get().queryParams("password");
            Database.open();
            User user = User.findFirst("email=?", email);
            Database.close();
            if (user != null) {
                if ($.matches(password, user.password()) && user.status()) {
                    Session.get().attribute("user", user);
                    Response.get().redirect("/administer");
                }
            } else {
                Response.get().redirect("/login");
            }
            return null;
        });*/

        new Thread(() -> rvcServer.start()).start();

        // h2 db-manager server
        if (Application.debug()) {
            Server webServer = Server.createWebServer("-webAllowOthers", "-webPort", "8083").start();
        }

    }

}