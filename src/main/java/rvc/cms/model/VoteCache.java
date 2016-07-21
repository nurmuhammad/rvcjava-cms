package rvc.cms.model;

import org.javalite.activejdbc.annotations.BelongsTo;
import org.javalite.activejdbc.annotations.Table;

/**
 * @author nurmuhammad
 */

@Table("votes_cache")
@BelongsTo(parent = Node.class, foreignKeyName = "node_id")
public class VoteCache extends aModel {
    public long id;
    public long nodeId;
    public double value;
    public String type;
    public String function;
    public long timestamp;
}
