package rvc.cms.model;

import org.javalite.activejdbc.Model;

/**
 * @author nurmuhammad
 */
public class Log extends Model {

    public Long id() {
        return (Long) get("id");
    }

    public void id(Long id) {
        set("id", id);
    }

    public String user() {
        return (String) get("user");
    }

    public void user(String user) {
        set("user", user);
    }

    public String type() {
        return (String) get("type");
    }

    public void type(String type) {
        set("type", type);
    }

    public String message() {
        return (String) get("message");
    }

    public void message(String message) {
        set("message", message);
    }

    public String settings() {
        return (String) get("settings");
    }

    public void settings(String settings) {
        set("settings", settings);
    }

    public String hostname() {
        return (String) get("hostname");
    }

    public void hostname(String hostname) {
        set("hostname", hostname);
    }

    public Integer timestamp() {
        return (Integer) get("timestamp");
    }

    public void timestamp(int timestamp) {
        set("timestamp", timestamp);
    }
}