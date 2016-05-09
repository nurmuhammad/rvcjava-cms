package rvc.cms;

import com.google.gson.Gson;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import rvc.ResponseTransformer;
import rvc.RvcHandler;
import rvc.RvcServer;

public class Main {

    public static void main(String[] args) {

        RvcServer rvcServer = new RvcServer();

        RvcHandler rvcHandler = rvcServer.init();

        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        handler.setContextPath("/");

        ServletHolder sh = new ServletHolder(new RvcServlet());

        rvcHandler.addServlet(sh, "/administer");
        rvcHandler.addServlet(sh, "/administer/*");
        rvcHandler.addServlet(sh, "/VAADIN/*");
//        rvcHandler.setInitParameter("ui", AdminUI.class.getCanonicalName());
//        rvcHandler.setInitParameter("productionMode", "false");
//        rvcHandler.setInitParameter("theme", "admin");
//        rvcHandler.setInitParameter("widgetset", "rvc.cms.Widgetset");

        rvcServer.start();

    }

    static class JsonTransformer implements ResponseTransformer {

        private Gson gson = new Gson();

        @Override
        public String transform(Object object) throws Exception {
            return gson.toJson(object);
        }
    }

}