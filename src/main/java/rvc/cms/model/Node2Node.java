package rvc.cms.model;

import javax.persistence.*;

/**
 * @author nurmuhammad
 */

@Entity
@Table(name = "node_node")
public class Node2Node extends SettingsModel {

    Node node;
    Node releatedNode;
    String type;
    Integer value;
    Integer sort;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "node_id")
    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "node_releated_id")
    public Node getReleatedNode() {
        return releatedNode;
    }

    public void setReleatedNode(Node releatedNode) {
        this.releatedNode = releatedNode;
    }
    @Column(name = "type", length = 32)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Column(name = "value")
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
    @Column(name = "sort")
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
