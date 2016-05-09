package rvc.cms;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/administer", name = "RvcServlet", asyncSupported = true)
@VaadinServletConfiguration(ui = AdminUI.class, productionMode = true)
public class RvcServlet extends VaadinServlet {

}