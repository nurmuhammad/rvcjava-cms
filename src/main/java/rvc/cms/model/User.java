package rvc.cms.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.BelongsTo;

/**
 * @author nurmuhammad
 */

@BelongsTo(parent = Node.class, foreignKeyName = "profile_node_id")
public class User extends Model {
    public long id;
    public String roles;
    public String email;
    public String password;

    public long created;
    public long changed;

    public int lastLogin;
    public String lastIp;
    public boolean status;

    public long profileNodeId;

    public String settings;
    public String domen;
}