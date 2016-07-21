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
public class Field extends aModel {

    public long id;
    public long userId;
    public long nodeId;
    public String fieldName;
    public String type;
    public String group;
    public String settings;
    public int sort;
    public long created;
    public long changed;
    public boolean status;

}
