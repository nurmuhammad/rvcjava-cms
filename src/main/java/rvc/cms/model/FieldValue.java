package rvc.cms.model;

import org.javalite.activejdbc.Model;
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
public class FieldValue extends Model {

    public Long id() {
        return (Long) get("id");
    }

    public void id(Long id) {
        set("id", id);
    }

    public Long userId() {
        return (Long) get("user_id");
    }

    public void userId(Long userId) {
        set("user_id", userId);
    }

    public Long fieldId() {
        return (Long) get("field_id");
    }

    public void fieldId(long fieldId) {
        set("field_id", fieldId);
    }

    public Long nodeIdValue() {
        return (Long) get("node_id_value");
    }

    public void nodeIdValue(long nodeIdValue) {
        set("node_id_value", nodeIdValue);
    }

    public String valueText() {
        return (String) get("value_text");
    }

    public void valueText(String valueText) {
        set("value_text", valueText);
    }

    public Long valueInt() {
        return (Long) get("value_int");
    }

    public void valueInt(long valueInt) {
        set("value_int", valueInt);
    }

    public Double valueDouble() {
        return (Double) get("value_double");
    }

    public void valueDouble(double valueDouble) {
        set("value_double", valueDouble);
    }

    public Object valueBlob() {
        return get("value_blob");
    }

    public void valueBlob(Object valueBlob) {
        set("value_blob", valueBlob);
    }

    public String language() {
        return (String) get("language");
    }

    public void language(String language) {
        set("language", language);
    }

    public String settings() {
        return (String) get("settings");
    }

    public void settings(String settings) {
        set("settings", settings);
    }

    public Integer sort() {
        return (Integer) get("sort");
    }

    public void sort(Integer sort) {
        set("sort", sort);
    }
}