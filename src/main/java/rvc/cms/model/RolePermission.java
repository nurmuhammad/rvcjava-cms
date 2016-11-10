package rvc.cms.model;

import javax.persistence.*;

/**
 * @author nurmuhammad
 */

@Entity
@Table(name = "role_permissions")
@Access(AccessType.FIELD)
public class RolePermission extends SettingsModel {

    @Column(name = "role")
    String role;

    /* permissions separated by comma(,) symbol */
    @Lob
    @Column(name = "permissions")
    String permissions;
}
