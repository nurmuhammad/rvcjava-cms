package rvc.cms.model;

import javax.persistence.*;

/**
 * @author nurmuhammad
 */
@Entity
@Table(name = "field_values")
@Access(AccessType.FIELD)
public class FieldValue extends SettingsModel {

    @Column(name = "user_id")
    public Long userId;

    @Column(name = "field_id")
    public Long fieldId;

    @Column(name = "node_id_value")
    public Long nodeIdValue;

    @Lob
    @Column(name = "value_text")
    public String valueText;

    @Column(name = "value_int")
    public Long valueInt;

    @Column(name = "value_double")
    public Double valueDouble;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "value_blob")
    public byte[] valueBlob;

    @Column(name = "language")
    public String language;

    @Column(name = "sort")
    public Integer sort;


    public Node nodeValue(){
        return null;
    }

}