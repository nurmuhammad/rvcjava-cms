package rvc.cms.model;


import javax.persistence.*;

/**
 * @author nurmuhammad
 */

@Entity
@Table(name = "votes")
public class Vote extends aModel {

    Node node;
    User user;
    String type;
    Double value;
    String voteSource;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "node_id")
    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "type", length = 32)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "value")
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Column(name = "vote_source", length = 64)
    public String getVoteSource() {
        return voteSource;
    }

    public void setVoteSource(String voteSource) {
        this.voteSource = voteSource;
    }
}
