package rvc.cms.model;

import javax.persistence.*;

/**
 * @author nurmuhammad
 */

@Entity
@Table(name = "field_types")
@Access(AccessType.FIELD)
public class FieldType extends SettingsModel {

    @Column(name = "node_type_id")
    public Long nodeTypeId;

    @Column(name = "label")
    public String label;

    @Column(name = "name")
    public String name;

    @Column(name = "widget")
    public String widget;

    @Column(name = "type")
    public String type;

    @Column(name = "required")
    public Boolean required;

    @Column(name = "sort")
    public Integer sort;

    @Lob
    @Column(name="render_code")
    public String renderCode;

    @Lob
    @Column(name = "validation_code")
    public String validationCode;

    @Lob
    @Column(name = "return_code")
    public String returnCode;


    public NodeType nodeType(){
        return null;
    }
}