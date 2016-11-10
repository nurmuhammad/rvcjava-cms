package rvc.cms.model;

import javax.persistence.*;

/**
 * @author nurmuhammad
 */

@Entity
@Table(name = "node_types")
@Access(AccessType.FIELD)
public class NodeType extends SettingsModel {

    @Column(name = "type")
    public String type;

    @Column(name = "name")
    public String name;

    @Lob
    @Column(name="description")
    public String description;

    @Column(name = "domain")
    public String domain;

    @Column(name = "translate")
    public Boolean translate;
}