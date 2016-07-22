package rvc.cms.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.BelongsTo;
import org.javalite.activejdbc.annotations.BelongsToParents;
import org.javalite.activejdbc.annotations.Table;

/**
 * @author nurmuhammad
 */

@Table("node_node")
@BelongsToParents({
        @BelongsTo(parent = Node.class, foreignKeyName = "node_id"),
        @BelongsTo(parent = Node.class, foreignKeyName = "releated_node_id")
})
public class Node2Node extends Settings {

    public Long nodeId() {
        return (Long) get("node_id");
    }

    public void nodeId(Long nodeId) {
        set("node_id", nodeId);
    }

    public Long releatedNodeId() {
        return (Long) get("node_releated_id");
    }

    public void releatedNodeId(Long releatedNodeId) {
        set("node_releated_id", releatedNodeId);
    }

    public Integer value() {
        return (Integer) get("value");
    }

    public void value(Integer value) {
        set("value", value);
    }

    public Integer sort() {
        return (Integer) get("sort");
    }

    public void sort(Integer sort) {
        set("sort", sort);
    }
}
