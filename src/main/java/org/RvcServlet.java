package org;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;
import rvc.cms.admin.AdminUI;

import javax.servlet.annotation.WebServlet;

/**
 * @author nurmuhammad
 */

@WebServlet(urlPatterns = "/administer", name = "RvcServlet", asyncSupported = true)
@VaadinServletConfiguration(ui = AdminUI.class, productionMode = true)
public class RvcServlet extends VaadinServlet {

}