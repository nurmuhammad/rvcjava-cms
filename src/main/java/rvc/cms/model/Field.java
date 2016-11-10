package rvc.cms.model;

import javax.persistence.*;
import java.util.List;

/**
 * @author nurmuhammad
 */

@Entity
@Table(name = "fields")
@Access(AccessType.FIELD)
public class Field extends SettingsModel {

    @Column(name = "user_id")
    public Long userId;

    @Column(name = "node_id")
    public Long nodeId;

    @Column(name = "field_name", length = 255)
    public String fieldName;

    @Column(name = "field_type_id")
    public Long fieldTypeId;

    @Column(name = "field_group", length = 32)
    public String group;

    @Column(name = "sort")
    public Integer sort;

    @Column(name = "status")
    public Boolean status;

    public Node node(){
        return null;
    }

    public FieldType fieldType(){
        return null;
    }

    public FieldValue fieldValue(){
        return null;
    }

    public List<FieldValue> fieldValues(){
        return null;
    }

}
