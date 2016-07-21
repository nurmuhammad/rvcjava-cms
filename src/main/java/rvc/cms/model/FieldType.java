package rvc.cms.model;

import org.javalite.activejdbc.annotations.BelongsTo;
import org.javalite.activejdbc.annotations.BelongsToParents;

import java.util.Map;

/**
 * @author nurmuhammad
 */
@BelongsToParents({
        @BelongsTo(parent = NodeType.class, foreignKeyName = "node_type_id")
})
public class FieldType extends aModel {
    public long id;
    public long nodeTypeId;

    public String label;
    public String name;
    public String widget;
    public String type;

    public boolean required;

    public Map<String, String> options;
    public int sort;
    public String settings;

    public Object value;

    public String renderCode;
    public String validationCode;
    public String returnCode;

}