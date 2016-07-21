package rvc.cms.model;

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
public class Scheduler extends aModel {
    public long id;
    public int runAt;
    public int lastEndAt;
    public String text;
    public String type;
    public long nodeId;
    public long objectId;
    public String settings;
}