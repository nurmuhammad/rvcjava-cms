package rvc.cms.model;

import org.javalite.activejdbc.annotations.BelongsTo;
import org.javalite.activejdbc.annotations.BelongsToParents;

/**
 * @author nurmuhammad
 */
@BelongsToParents({
        @BelongsTo(parent = User.class, foreignKeyName = "user_id"),
        @BelongsTo(parent = Field.class, foreignKeyName = "field_id"),
        @BelongsTo(parent = Node.class, foreignKeyName = "node_id_value")
})
public class FieldValue extends aModel {
    public long id;
    public long userId;
    public long fieldId;
    public long nodeIdValue;
    public String valueText;
    public long valueInt;
    public double valueDouble;
    public byte[] valueBlob;
    public String language;
    public String settings;
    public int sort;
}