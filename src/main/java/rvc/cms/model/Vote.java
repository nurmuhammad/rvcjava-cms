package rvc.cms.model;

import org.javalite.activejdbc.annotations.BelongsTo;
import org.javalite.activejdbc.annotations.BelongsToParents;

/**
 * @author nurmuhammad
 */

@BelongsToParents({
        @BelongsTo(parent = User.class, foreignKeyName = "user_id"),
        @BelongsTo(parent = Node.class, foreignKeyName = "node_id")
})
public class Vote extends aModel {
    public long id;
    public long nodeId;
    public long userId;
    public String type;
    public double value;
    public String voteSource;
    public long timestamp;
}
