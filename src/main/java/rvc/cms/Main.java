package rvc.cms;

import com.google.gson.Gson;
import com.vaadin.server.VaadinServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import rvc.HaltException;
import rvc.ResponseTransformer;
import rvc.RvcHandler;
import rvc.RvcServer;
import rvc.an.HelloWorldUI;
import rvc.http.Request;
import rvc.http.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {

        RvcServer rvcServer = new RvcServer()
                .folder("b:\\12")
                .before(() -> {
                    System.out.println(Request.get().path());
                    Response.get().gzip();
                })
                .before("/hello", "gg2.uz", () -> {
                    if (1 == 1)
                        throw new HaltException(200);
                    Request.get().forward("hello1");
                })
                .get("/hello", "*.gg.uz", () -> "hello, world - " + Request.get().raw().getServerName())
                .get("/hello1", () -> {
                    System.out.println(UUID.randomUUID());
                    return "1111";
                }, 5000)
                .get("/smart", "baltontrading.lc", () -> {
                    String xml = "items";
                    System.out.println(xml);
                    return xml.toUpperCase();
                }, 10000)
                .get("/json", () -> {
                    List list = new ArrayList();
                    list.add("json");
                    list.add("key");
                    list.add("java");
                    return list;
                }, new JsonTransformer());


        RvcHandler rvcHandler = rvcServer.init();

        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        handler.setContextPath("/");

        ServletHolder sh = new ServletHolder(new VaadinServlet());

        rvcHandler.addServlet(sh, "/vaadin");
        rvcHandler.addServlet(sh, "/vaadin/*");
        rvcHandler.addServlet(sh, "/VAADIN/*");
        rvcHandler.setInitParameter("ui", HelloWorldUI.class.getCanonicalName());

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