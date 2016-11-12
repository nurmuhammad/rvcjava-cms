package rvc.cms.model;


import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * @author nurmuhammad
 */

@Entity
@Table(name = "nodes")
@Access(AccessType.FIELD)
@DynamicInsert @DynamicUpdate
public class Node extends SettingsModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "node_type_id")
    public NodeType nodeType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "detail_id")
    public Details details;

    @Column(name = "status")
    public Boolean status;

    @Column(name = "domain")
    public String domain;

}