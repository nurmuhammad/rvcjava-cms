package rvc.cms.admin;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import rvc.Context;
import rvc.cms.$;
import rvc.cms.Database;
import rvc.cms.model.User;
import rvc.http.Request;
import rvc.http.Session;

import java.util.Date;

/**
 * @author nurmuhammad
 */

@Title("Administer")
@Theme("admin")
@Widgetset("rvc.cms.Widgetset")
public class AdminUI extends UI {

    private static final long serialVersionUID = 1L;
    private CssLayout layout;

    protected void init(VaadinRequest request) {
        User user = Session.get().attribute("user");
        if (!user.status()) {
            Notification.show("User is not activated.");
            Session.get().attribute("user", null);
            Context.set(User.class, null);
            return;
        }
        Context.set(User.class, user);
        user.lastLogin($.timestamp());
        user.lastIp(Request.get().host());
        Database.open();
        user.saveIt();
        Database.close();

        layout = new CssLayout();
        setContent(layout);
        layout.addComponent(new Label("Hello world"));
        layout.addComponent(new Button("Click me", (Button.ClickListener) event -> {
            Notification.show("Hello at " + new Date());
        }));

        MenuBar sample = new MenuBar();
        sample.setWidth(100.0f, Unit.PERCENTAGE);

        MenuBar.MenuItem child = sample.addItem("Test", (MenuBar.Command) menuItem -> {
        });
        layout.addComponent(sample);

    }
}
