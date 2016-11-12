package rvc.cms.model;

import javax.persistence.*;

/**
 * @author nurmuhammad
 */
@Entity
@Table(name = "details")
@Access(AccessType.FIELD)
public class Details extends aModel {

    @Column(name = "status_comment", length = 255)
    public String comment;

    @Column(name = "status_username", length = 64)
    public String username;

    @Column(name = "status_id", length = 16)
    public String statusId;

}
