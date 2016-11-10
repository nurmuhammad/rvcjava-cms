package rvc.cms.model;

import javax.persistence.*;

/**
 * @author nurmuhammad
 */

@Entity
@Table(name = "node_node")
@Access(AccessType.FIELD)
public class Node2Node extends SettingsModel {

    @Column(name = "node_id")
    public Long nodeId;

    @Column(name = "node_releated_id")
    public Long releatedNodeId;

    @Column(name = "value")
    public Integer value;

    @Column(name = "sort")
    public Integer sort;

}
