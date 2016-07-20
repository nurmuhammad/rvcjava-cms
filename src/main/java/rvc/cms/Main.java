package rvc.cms;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.javalite.activejdbc.Base;
import rvc.*;
import rvc.cms.admin.AdminServlet;
import rvc.cms.admin.AdminUI;
import rvc.cms.init.Config;
import rvc.cms.model.User;
import rvc.http.Response;
import rvc.http.Session;

import java.util.Date;

/**
 * @author nurmuhammad
 */

public class Main {

    public static void main(String[] args) {

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
        rvcHandler.setInitParameter("productionMode", "false");
        rvcHandler.setInitParameter("theme", "admin");
        rvcHandler.setInitParameter("widgetset", "rvc.cms.Widgetset");

        rvcServer.before("administer, administer/*", () -> {
            Object o = Session.get().attribute("user");
            Response.get().redirect("/login");
        });

        rvcServer.get("login", () -> {

            Database.open();

            User user = new User();
            user.set("email", "email@email.com", "roles", "admin, moderate", "created", new Date().getTime()/1000);
            user.saveIt();

            Database.close();

            ModelAndView modelAndView = new ModelAndView(null, "home.html");
            return modelAndView;
        }, Pebble.instance);

        rvcServer.start();

    }

}