package rvc.cms.model;

import org.javalite.activejdbc.Model;

/**
 * @author nurmuhammad
 */

public class Translate extends aModel {

    public Long id() {
        return (Long) get("id");
    }

    public void id(Long id) {
        set("id", id);
    }

    public String source() {
        return getString("source");
    }

    public void source(String source) {
        set("source", source);
    }

    public String translate() {
        return getString("translate");
    }

    public void translate(String translate) {
        set("translate", translate);
    }

    public String language() {
        return (String) get("language");
    }

    public void language(String language) {
        set("language", language);
    }

    public String location() {
        return (String) get("location");
    }

    public void location(String location) {
        set("location", location);
    }
}