package rvc.cms;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.h2.tools.Server;
import rvc.*;
import rvc.cms.admin.AdminServlet;
import rvc.cms.admin.AdminUI;
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

        RvcServer rvcServer = new RvcServer();

        rvcServer.port(Config.get("server.port", 4567));

        RvcHandler rvcHandler = rvcServer.init();

        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        handler.setContextPath("/");

        ServletHolder sh = new ServletHolder(new AdminServlet());

        rvcHandler.addServlet(sh, "/administer");
        rvcHandler.addServlet(sh, "/administer/*");
        rvcHandler.addServlet(sh, "/VAADIN/*");
        rvcHandler.setInitParameter("ui", AdminUI.class.getCanonicalName());
        rvcHandler.setInitParameter("productionMode", "" + (!Application.debug()));
        rvcHandler.setInitParameter("theme", "admin");
        rvcHandler.setInitParameter("widgetset", "rvc.cms.Widgetset");

        rvcServer.before("administer, administer/*", () -> {
            User user = Session.get().attribute("user");

            if (user == null) {
                Response.get().redirect("/login");
            }
        });

        new Thread(() -> rvcServer.start()).start();

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
        });


        // h2 db-manager server
        if (Application.debug()) {
            Server webServer = Server.createWebServer("-webAllowOthers", "-webPort", "8083").start();
        }

    }

}