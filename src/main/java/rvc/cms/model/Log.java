package rvc.cms.model;

/**
 * @author nurmuhammad
 */
public class Log extends SettingsModel {

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
        return (String) getString("message");
    }

    public void message(String message) {
        set("message", message);
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