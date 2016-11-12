package rvc.cms.model;

import javax.persistence.*;

/**
 * @author nurmuhammad
 */
@Entity
@Table(name = "field_values")
@Access(AccessType.FIELD)
public class FieldValue extends SettingsModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "field_id")
    public Field field;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "node_id_value")
    public Node nodeValue;

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

}