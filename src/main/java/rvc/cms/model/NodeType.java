package rvc.cms.model;

/**
 * @author nurmuhammad
 */

public class NodeType extends Settings {

    public Long id() {
        return (Long) get("id");
    }

    public void id(Long id) {
        set("id", id);
    }

    public String type() {
        return (String) get("type");
    }

    public void type(String type) {
        set("type", type);
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

    public String domen() {
        return (String) get("domen");
    }

    public void domen(String domen) {
        set("domen", domen);
    }

    public boolean translate() {
        return Boolean.TRUE.equals(get("translate"));
    }

    public void translate(boolean translate) {
        set("translate", translate);
    }
}