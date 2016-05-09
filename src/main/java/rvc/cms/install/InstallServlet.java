package rvc.cms.install;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;
import rvc.cms.admin.AdminUI;

@VaadinServletConfiguration(ui = InstallUI.class, productionMode = true)
public class InstallServlet extends VaadinServlet {

}