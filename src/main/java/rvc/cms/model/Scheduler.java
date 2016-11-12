package rvc.cms.model;

import javax.persistence.*;

/**
 * @author nurmuhammad
 */

@Entity
@Table(name = "scheduler")
@Access(AccessType.FIELD)
public class Scheduler extends SettingsModel {

    @Column(name = "name")
    public String name;

    @Column(name = "run_at")
    public Long runAt;

    @Column(name = "last_end_at")
    public Long lastEndAt;

    @Lob
    @Column(name = "action")
    public String action;

    @Column(name = "type")
    public String type;

//    @Column(name = "node_id")
//    public Long nodeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "node_id")
    public Node node;

//    @Column(name = "object_id")
//    public Long objectId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "object_id")
    public Objects object;

}