package rvc.cms.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.BelongsTo;
import org.javalite.activejdbc.annotations.BelongsToParents;
import org.javalite.activejdbc.annotations.Table;

/**
 * @author nurmuhammad
 */

@Table("scheduler")
@BelongsToParents({
        @BelongsTo(parent = Node.class, foreignKeyName = "node_id"),
        @BelongsTo(parent = Objects.class, foreignKeyName = "object_id")
})
public class Scheduler extends Model {

    public Long id() {
        return (Long) get("id");
    }

    public void id(Long id) {
        set("id", id);
    }

    public Integer runAt() {
        return (Integer) get("run_at");
    }

    public void runAt(Integer runAt) {
        set("run_at", runAt);
    }

    public Integer lastEndAt() {
        return (Integer) get("last_end_at");
    }

    public void lastEndAt(Integer lastEndAt) {
        set("last_end_at", lastEndAt);
    }

    public String text() {
        return (String) get("text");
    }

    public void text(String text) {
        set("text", text);
    }

    public String type() {
        return (String) get("type");
    }

    public void type(String type) {
        set("type", type);
    }

    public Long nodeId() {
        return (Long) get("node_id");
    }

    public void nodeId(Long nodeId) {
        set("node_id", nodeId);
    }

    public Long objectId() {
        return (Long) get("object_id");
    }

    public void objectId(Long objectId) {
        set("object_id", objectId);
    }

    public String settings() {
        return (String) get("settings");
    }

    public void settings(String settings) {
        set("settings", settings);
    }
}