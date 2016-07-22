package rvc.cms.model;

import org.javalite.activejdbc.annotations.Table;

/**
 * @author nurmuhammad
 */

@Table("objects")
public class Objects extends Settings {

    public Long id() {
        return (Long) get("id");
    }

    public void id(Long id) {
        set("id", id);
    }

    public String name() {
        return (String) get("name");
    }

    public void name(String name) {
        set("name", name);
    }

    public String description() {
        return getString("description");
    }

    public void description(String description) {
        set("description", description);
    }

    public String type() {
        return (String) get("type");
    }

    public void type(String type) {
        set("type", type);
    }

    public String javaCode() {
        return getString("java_code");
    }

    public void javaCode(String javaCode) {
        set("java_code", javaCode);
    }
}