package rvc.cms.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * @author nurmuhammad
 */
@Entity
@Table(name = "field_values")
@DynamicInsert
@DynamicUpdate
public class FieldValue extends SettingsModel {

    User user;
    Field field;
    Node nodeValue;
    String valueString;
    Long valueInt;
    Double valueDouble;
    LobValue valueLob;
    String language;
    Integer sort;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "field_id")
    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "node_id_value")
    public Node getNodeValue() {
        return nodeValue;
    }

    public void setNodeValue(Node nodeValue) {
        this.nodeValue = nodeValue;
    }

    @Column(name = "value_string", length = 1024)
    public String getValueString() {
        return valueString;
    }

    public void setValueString(String valueString) {
        this.valueString = valueString;
    }

    @Column(name = "value_int")
    public Long getValueInt() {
        return valueInt;
    }

    public void setValueInt(Long valueInt) {
        this.valueInt = valueInt;
    }

    @Column(name = "value_double")
    public Double getValueDouble() {
        return valueDouble;
    }

    public void setValueDouble(Double valueDouble) {
        this.valueDouble = valueDouble;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lob_id")
    public LobValue getValueLob() {
        return valueLob;
    }

    public void setValueLob(LobValue valueLob) {
        this.valueLob = valueLob;
    }

    @Column(name = "language")
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Column(name = "sort")
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}