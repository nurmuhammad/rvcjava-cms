package rvc.cms.model;

import javax.persistence.*;

/**
 * @author nurmuhammad
 */

@Entity
@Table(name = "node_node")
@Access(AccessType.FIELD)
public class Node2Node extends SettingsModel {

//    @Column(name = "node_id")
//    public Long nodeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "node_id")
    public Node node;

//    @Column(name = "node_releated_id")
//    public Long releatedNodeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "node_releated_id")
    public Node releatedNode;

    @Column(name = "value")
    public Integer value;

    @Column(name = "sort")
    public Integer sort;

}
