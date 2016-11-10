package rvc.cms.model;


import javax.persistence.*;

/**
 * @author nurmuhammad
 */

@Entity
@Table(name = "nodes")
@Access(AccessType.FIELD)
public class Node extends SettingsModel {

    @Column(name = "user_id")
    public Long userId;

    @Column(name = "node_type_id")
    public Long nodeTypeId;

    @Column(name = "status")
    public Boolean status;

    @Column(name = "domain")
    public String domain;

    public Field field(String fieldName){
        return null;
    }

    public FieldValue fieldValue(String fieldName){
        return null;
    }

    public NodeType nodeType(){
        return null;
    }


}