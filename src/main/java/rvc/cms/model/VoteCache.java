package rvc.cms.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * @author nurmuhammad
 */

@Entity
@Table(name = "votes_cache")
@Access(AccessType.FIELD)
@DynamicInsert @DynamicUpdate
public class VoteCache extends aModel {

//    @Column(name = "node_id")
//    public Long nodeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "node_id")
    public Node node;

    @Column(name = "value")
    public Double value;

    @Column(name = "type", length = 32)
    public String type;

    @Column(name = "function", length = 16)
    public String function;

}
