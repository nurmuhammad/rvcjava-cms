package rvc.cms.controllers;

import rvc.ann.Controller;
import rvc.ann.GET;
import rvc.ann.Template;

/**
 * @author nurmuhammad
 */

@Controller
public class AdminController {

    @GET
    @Template(viewName = "admin/index.html")
    Object administer(){
        return "";
    }

}