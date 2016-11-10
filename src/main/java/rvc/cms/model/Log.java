package rvc.cms.model;

import javax.persistence.*;

/**
 * @author nurmuhammad
 */

@Entity
@Table(name = "logs")
@Access(AccessType.FIELD)
public class Log extends SettingsModel {

    @Column(name = "user")
    public String user;

    @Column(name = "type")
    public String type;

    @Lob
    @Column(name = "message")
    public String message;

    @Column(name = "hostname")
    public String hostname;

    @Column(name = "timestamp")
    public Long timestamp;

}