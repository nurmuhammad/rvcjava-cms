package rvc.cms.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.BelongsTo;

/**
 * @author nurmuhammad
 */

@BelongsTo(parent = User.class, foreignKeyName = "user_id")
public class Node extends Model {

    public Long id() {
        return (Long) get("id");
    }

    public void id(Long id) {
        set("id", id);
    }

    public Long userId() {
        return (Long) get("user_id");
    }

    public void userId(Long userId) {
        set("user_id", userId);
    }

    public String type() {
        return (String) get("type");
    }

    public void type(String type) {
        set("type", type);
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

    public void changed(Long changed) {
        set("changed", changed);
    }

    public boolean status() {
        return Boolean.TRUE.equals(get("status"));
    }

    public void status(boolean status) {
        set("status", status);
    }

    public String settings() {
        return (String) get("settings");
    }

    public void settings(String settings) {
        set("settings", settings);
    }

    public String domen() {
        return (String) get("domen");
    }

    public void domen(String domen) {
        set("domen", domen);
    }
}