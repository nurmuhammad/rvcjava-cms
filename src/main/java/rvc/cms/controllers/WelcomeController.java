package rvc.cms.controllers;

import rvc.ann.*;

@Controller
public class WelcomeController {

    @GET
    Object index(){
        return "hello world";
    }

    @GET
    @Json
    Object toJson(){
        return "hello, world".split(",");
    }

}
