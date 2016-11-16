package rvc.cms.model;

import javax.persistence.*;

/**
 * @author nurmuhammad
 */

@Entity
@Table(name = "scheduler")
public class Scheduler extends SettingsModel {

    String name;
    Long runAt;
    Long lastEndAt;
    String action;
    String type;
    Node node;
    Objects object;

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "run_at")
    public Long getRunAt() {
        return runAt;
    }

    public void setRunAt(Long runAt) {
        this.runAt = runAt;
    }

    @Column(name = "last_end_at")
    public Long getLastEndAt() {
        return lastEndAt;
    }

    public void setLastEndAt(Long lastEndAt) {
        this.lastEndAt = lastEndAt;
    }

    @Lob
    @Column(name = "action")
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "node_id")
    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "object_id")
    public Objects getObject() {
        return object;
    }

    public void setObject(Objects object) {
        this.object = object;
    }
}