package rvc.cms.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

/**
 * @author nurmuhammad
 */

@Table("objects")
public class Objects extends Model {

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
        return (String) get("description");
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
        return (String) get("java_code");
    }

    public void javaCode(String javaCode) {
        set("java_code", javaCode);
    }

    public String settings() {
        return (String) get("settings");
    }

    public void settings(String settings) {
        set("settings", settings);
    }
}