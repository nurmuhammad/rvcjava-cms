package rvc.cms.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.BelongsTo;
import org.javalite.activejdbc.annotations.Table;

/**
 * @author nurmuhammad
 */

@Table("votes_cache")
@BelongsTo(parent = Node.class, foreignKeyName = "node_id")
public class VoteCache extends aModel {

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

    public Double value() {
        return (Double)get("value");
    }

    public void value(Double value) {
        set("value", value);
    }

    public String type() {
        return (String) get("type");
    }

    public void type(String type) {
        set("type", type);
    }

    public String function() {
        return (String) get("function");
    }

    public void function(String function) {
        set("function", function);
    }

    public Integer timestamp() {
        return (Integer) get("timestamp");
    }

    public void timestamp(int timestamp) {
        set("timestamp", timestamp);
    }
}
