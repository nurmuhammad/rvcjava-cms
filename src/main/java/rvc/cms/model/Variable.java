package rvc.cms.model;

import javax.persistence.*;

/**
 * @author nurmuhammad
 */

@Entity
@Table(name = "variables")
@Access(AccessType.FIELD)
public class Variable extends SettingsModel {

    @Column(name = "name")
    public String name;

    @Lob
    @Column(name = "value")
    public byte[] value;

    @Column(name = "type")
    public String type;

}
