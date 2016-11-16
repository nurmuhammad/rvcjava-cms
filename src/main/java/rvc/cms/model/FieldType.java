package rvc.cms.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * @author nurmuhammad
 */

@Entity
@Table(name = "field_types")
@DynamicInsert
@DynamicUpdate
public class FieldType extends SettingsModel {

    NodeType nodeType;
    String label;
    String name;
    String widget;
    String type;
    Boolean required;
    Integer sort;
    Objects renderCode;
    Objects validationCode;
    Objects returnCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "node_type_id")
    public NodeType getNodeType() {
        return nodeType;
    }

    public void setNodeType(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    @Column(name = "label")
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "widget")
    public String getWidget() {
        return widget;
    }

    public void setWidget(String widget) {
        this.widget = widget;
    }

    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "required")
    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    @Column(name = "sort")
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "render_code_object_id")
    public Objects getRenderCode() {
        return renderCode;
    }

    public void setRenderCode(Objects renderCode) {
        this.renderCode = renderCode;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "validation_code_object_id")
    public Objects getValidationCode() {
        return validationCode;
    }

    public void setValidationCode(Objects validationCode) {
        this.validationCode = validationCode;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "return_code_object_id")
    public Objects getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(Objects returnCode) {
        this.returnCode = returnCode;
    }
}