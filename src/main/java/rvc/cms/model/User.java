package rvc.cms.model;

import javax.persistence.*;

/**
 * @author nurmuhammad
 */

@Entity
@Table(name = "users")
@Access(AccessType.FIELD)
public class User extends SettingsModel {

    @Column(name = "roles")
    public String roles;

    @Column(name = "email")
    public String email;

    @Column(name = "password")
    public String password;

    @Column(name = "status")
    public Boolean status;

    @Column(name = "last_login")
    public Long lastLogin;

    @Column(name = "last_ip")
    public String lastIp;

    @Column(name = "profile_node_id")
    public Long profileNodeId;

    @Column(name = "domain")
    public String domain;
}