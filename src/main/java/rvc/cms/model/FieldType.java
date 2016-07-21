package rvc.cms.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.BelongsTo;
import org.javalite.activejdbc.annotations.BelongsToParents;

import java.util.Map;

/**
 * @author nurmuhammad
 */
@BelongsToParents({
        @BelongsTo(parent = NodeType.class, foreignKeyName = "node_type_id")
})
public class FieldType extends Model {

    public Long id() {
        return (Long) get("id");
    }

    public void id(Long id) {
        set("id", id);
    }

    public long nodeTypeId() {
        return (Long) get("node_type_id");
    }

    public void nodeTypeId(long nodeTypeId) {
        set("node_type_id", nodeTypeId);
    }

    public String label() {
        return (String) get("label");
    }

    public void label(String label) {
        set("label", label);
    }

    public String name() {
        return (String) get("name");
    }

    public void name(String name) {
        set("name", name);
    }

    public String widget() {
        return (String) get("widget");
    }

    public void widget(String widget) {
        set("widget", widget);
    }

    public String type() {
        return (String) get("type");
    }

    public void type(String type) {
        set("type", type);
    }

    public boolean required() {
        return (Boolean) get("required");
    }

    public void required(boolean required) {
        set("required", required);
    }

    public Integer sort() {
        return (Integer) get("sort");
    }

    public void sort(Integer sort) {
        set("sort", sort);
    }

    public String settings() {
        return (String) get("settings");
    }

    public void settings(String settings) {
        set("settings", settings);
    }

    public String renderCode() {
        return (String) get("render_code");
    }

    public void renderCode(String renderCode) {
        set("render_code", renderCode);
    }

    public String validationCode() {
        return (String) get("validation_code");
    }

    public void validationCode(String validationCode) {
        set("validation_code", validationCode);
    }

    public String returnCode() {
        return (String) get("return_code");
    }

    public void returnCode(String returnCode) {
        set("return_code", returnCode);
    }
}