package rvc.cms.controllers;

import rvc.ann.*;
import rvc.cms.$;
import rvc.cms.Database;
import rvc.cms.model.User;
import rvc.http.Request;
import rvc.http.Response;
import rvc.http.Session;

/**
 * @author nurmuhammad
 */

@Controller
public class LoginController {

    @GET
    @Template
    public Object login() {
        return "";
    }

    @POST("login")
    public Object loginPost() {
//        String email = Request.get().queryParams("email");
//        String password = Request.get().queryParams("password");
//        Database.open();
//        User user = User.findFirst("email=?", email);
//
//        if (user != null) {
//            if ($.matches(password, user.password()) && user.status()) {
//                Session.get().attribute("user", user);
//                user.lastLogin($.timestamp());
//                user.lastIp(Request.get().ip());
//                user.saveIt();
//                Response.get().redirect("/administer");
//            }
//        } else {
//            Response.get().redirect("/login");
//        }
//        Database.close();
        return null;

    }

    @Before("administer, administer/*")
    public void before() {
        User user = Session.get().attribute("user");
        if (user == null) {
            Response.get().redirect("/login");
        }
    }

    @GET
    Object json() throws Exception {
//        Database.open();
//        Object result = User.findAll().toJson(true);
//        User user = User.findFirst("email=?", "admin");
//        user.settings("");
//        user.saveIt();
//        Database.close();
//        return result;
        return null;
    }

}