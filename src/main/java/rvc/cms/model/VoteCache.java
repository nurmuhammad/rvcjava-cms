package rvc.cms.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * @author nurmuhammad
 */

@Entity
@Table(name = "votes_cache")
@DynamicInsert
@DynamicUpdate
public class VoteCache extends aModel {
    Node node;
    Double value;
    String type;
    String function;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "node_id")
    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    @Column(name = "value")
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Column(name = "type", length = 32)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "function", length = 16)
    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }
}
