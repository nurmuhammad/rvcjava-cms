package rvc.cms.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.BelongsTo;
import org.javalite.activejdbc.annotations.BelongsToParents;

/**
 * @author nurmuhammad
 */

@BelongsToParents({
        @BelongsTo(parent = User.class, foreignKeyName = "user_id"),
        @BelongsTo(parent = Node.class, foreignKeyName = "node_id")
})
public class Field extends Model {

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

    public Long nodeId() {
        return (Long) get("node_id");
    }

    public void nodeId(Long nodeId) {
        set("node_id", nodeId);
    }

    public String fieldName() {
        return (String) get("field_name");
    }

    public void fieldName(String fieldName) {
        set("field_name", fieldName);
    }

    public String type() {
        return (String) get("type");
    }

    public void type(String type) {
        set("type", type);
    }

    public String group() {
        return (String) get("group");
    }

    public void group(String group) {
        set("group", group);
    }

    public String settings() {
        return (String) get("settings");
    }

    public void settings(String settings) {
        set("settings", settings);
    }

    public Integer sort() {
        return (Integer) get("sort");
    }

    public void sort(Integer sort) {
        set("sort", sort);
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

    public boolean status() {
        return Boolean.TRUE.equals(get("status"));
    }

    public void status(boolean status) {
        set("status", status);
    }

}
