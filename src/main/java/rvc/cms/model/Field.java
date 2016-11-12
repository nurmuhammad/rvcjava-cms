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

//    @Column(name = "user_id")
//    public Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;

//    @Column(name = "node_id")
//    public Long nodeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "node_id")
    public Node node;

    @Column(name = "field_name", length = 255)
    public String fieldName;

//    @Column(name = "field_type_id")
//    public Long fieldTypeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "field_type_id")
    public FieldType fieldType;

    @Column(name = "field_group", length = 32)
    public String group;

    @Column(name = "sort")
    public Integer sort;

    @Column(name = "status")
    public Boolean status;

}
