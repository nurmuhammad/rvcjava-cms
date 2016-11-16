package rvc.cms;

import rvc.cms.init.Config;

/**
 * @author nurmuhammad
 */

public class Application {

    boolean debug;

    static Application instalce = new Application();

    Application() {
        debug = Boolean.valueOf(Config.get("debug.mode", "false"));

//        Database.open();
//        User user = User.findFirst("email=?", "admin");
//        if (user == null) {
//            user = new User();
//            user.set(
//                    "email", "admin",
//                    "password", $.encode("1"),
//                    "roles", "admin",
//                    "created", $.timestamp(),
//                    "changed", $.timestamp(),
//                    "status", true,
//                    "domen", "*"
//            );
//            user.saveIt();
//        }
//        Database.close();
    }

    public static boolean debug() {
        return instalce.debug;
    }

}
