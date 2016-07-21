package rvc.cms.model;

import org.javalite.activejdbc.Model;

/**
 * @author nurmuhammad
 */

public class Variable extends Model {

    public Long id() {
        return (Long) get("id");
    }

    public void id(Long id) {
        set("id", id);
    }

    public String key() {
        return (String) get("key");
    }

    public void key(String key) {
        set("key", key);
    }

    public Object value() {
        return get("value");
    }

    public void value(Object value) {
        set("value", value);
    }

    public String type() {
        return (String) get("type");
    }

    public void type(String type) {
        set("type", type);
    }

    public String settings() {
        return (String) get("settings");
    }

    public void settings(String settings) {
        set("settings", settings);
    }

    public Integer created() {
        return (Integer) get("created");
    }

    public void created(Integer created) {
        set("created", created);
    }

    public Integer changed() {
        return (Integer) get("changed");
    }

    public void changed(Integer changed) {
        set("changed", changed);
    }
}
