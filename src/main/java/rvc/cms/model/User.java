package rvc.cms.model;

import javax.persistence.*;

/**
 * @author nurmuhammad
 */

@Entity
@Table(name = "users")
public class User extends SettingsModel {

    String roles;
    String email;
    String password;
    Boolean status;
    Long lastLogin;
    String lastIp;
    Node profileNode;
    String domain;

    @Column(name = "roles")
    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "status")
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Column(name = "last_login")
    public Long getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Long lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Column(name = "last_ip")
    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_node_id")
    public Node getProfileNode() {
        return profileNode;
    }

    public void setProfileNode(Node profileNode) {
        this.profileNode = profileNode;
    }

    @Column(name = "domain")
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}