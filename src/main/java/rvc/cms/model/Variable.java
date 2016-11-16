package rvc.cms.model;

import javax.persistence.*;

/**
 * @author nurmuhammad
 */

@Entity
@Table(name = "variables")
public class Variable extends SettingsModel {

    String name;
    String value;
    LobValue valueLob;
    String type;

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "value", length = 1024)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lob_id")
    public LobValue getValueLob() {
        return valueLob;
    }

    public void setValueLob(LobValue valueLob) {
        this.valueLob = valueLob;
    }

    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
