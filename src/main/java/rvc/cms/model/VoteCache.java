package rvc.cms.model;

import javax.persistence.*;

/**
 * @author nurmuhammad
 */

@Entity
@Table(name = "votes_cache")
@Access(AccessType.FIELD)
public class VoteCache extends aModel {

    @Column(name = "node_id")
    public Long nodeId;

    @Column(name = "value")
    public Double value;

    @Column(name = "type", length = 32)
    public String type;

    @Column(name = "function", length = 16)
    public String function;

    @Column(name = "timestamp")
    public Long timestamp;

}
