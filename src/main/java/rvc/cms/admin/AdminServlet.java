package rvc.cms.admin;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;

import javax.servlet.annotation.WebServlet;

@VaadinServletConfiguration(ui = AdminUI.class, productionMode = true)
public class AdminServlet extends VaadinServlet {

}