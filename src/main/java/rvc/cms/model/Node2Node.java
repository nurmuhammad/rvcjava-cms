package rvc.cms.model;

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
public class Node2Node extends aModel {
    public long nodeId;
    public long releatedNodeId;
    public String settings;
    public int value;
    public int sort;
}
