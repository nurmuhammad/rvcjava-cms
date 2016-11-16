package rvc.cms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author nurmuhammad
 */

@Entity
@Table(name = "node_types")
public class NodeType extends SettingsModel {

    String type;
    String name;
    String description;
    String domain;
    Boolean translate;

    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description", length = 1024)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "domain")
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Column(name = "translate")
    public Boolean getTranslate() {
        return translate;
    }

    public void setTranslate(Boolean translate) {
        this.translate = translate;
    }
}