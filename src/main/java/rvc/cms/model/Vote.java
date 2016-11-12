package rvc.cms.model;


import javax.persistence.*;

/**
 * @author nurmuhammad
 */

@Entity
@Table(name = "votes")
@Access(AccessType.FIELD)
public class Vote extends aModel {

//    @Column(name = "node_id")
//    public Long nodeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "node_id")
    public Node node;

//    @Column(name = "user_id")
//    public Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;

    @Column(name = "type", length = 32)
    public String type;

    @Column(name = "value")
    public Double value;

    @Column(name = "vote_source", length = 64)
    public String voteSource;

}
