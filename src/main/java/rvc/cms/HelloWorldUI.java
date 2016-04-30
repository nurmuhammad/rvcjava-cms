package rvc.cms;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;

import java.util.Date;

@Title("Light Vaadin")
@Theme("valo")
public class HelloWorldUI extends UI {

    private static final long serialVersionUID = 1L;
    private CssLayout layout;

    protected void init(VaadinRequest request) {
        layout = new CssLayout();
        setContent(layout);
        layout.addComponent(new Label("Hello world"));
        layout.addComponent(new Button("Click me", (Button.ClickListener) event -> {
            Notification.show("Hello at " + new Date());
        }));
    }
}
