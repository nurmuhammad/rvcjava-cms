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
public class Vote extends Model {

    public Long id() {
        return (Long) get("id");
    }

    public void id(Long id) {
        set("id", id);
    }

    public Long nodeId() {
        return (Long) get("node_id");
    }

    public void nodeId(Long nodeId) {
        set("node_id", nodeId);
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

    public Double value() {
        return (Double)get("value");
    }

    public void value(Double value) {
        set("value", value);
    }

    public String voteSource() {
        return (String)get("vote_source");
    }

    public void voteSource(String voteSource) {
        set("vote_source", voteSource);
    }

    public Integer timestamp() {
        return (Integer) get("timestamp");
    }

    public void timestamp(int timestamp) {
        set("timestamp", timestamp);
    }
}
