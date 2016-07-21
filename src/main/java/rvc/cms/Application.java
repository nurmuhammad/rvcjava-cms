package rvc.cms;

import crypt.BCryptPasswordEncoder;
import org.slf4j.bridge.SLF4JBridgeHandler;
import rvc.cms.init.Config;
import rvc.cms.model.User;

/**
 * @author nurmuhammad
 */

public class Application {

    static {
        SLF4JBridgeHandler.install();
    }

    boolean debug;

    static Application instalce = new Application();

    Application() {
        debug = Boolean.valueOf(Config.get("debug.mode", "false"));

        Database.open();
        User user = User.findFirst("email=?", "admin");
        if (user == null) {
            user = new User();
            user.set(
                    "email", "admin",
                    "password", $.encode("1"),
                    "roles", "admin",
                    "created", $.timestamp(),
                    "changed", $.timestamp(),
                    "status", true,
                    "domen", "*"
            );
            user.saveIt();
        }
        Database.close();
    }

    public static boolean debug() {
        return instalce.debug;
    }

}
