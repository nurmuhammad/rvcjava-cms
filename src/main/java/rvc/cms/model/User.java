package rvc.cms.model;

import org.javalite.activejdbc.Model;

/**
 * @author nurmuhammad
 */

public class User extends Model {
    public long id;
    public String roles;
    public String email;
    public String password;

    public long created;
    public long chaged;

    public int lastLogin;
    public String lastIp;
    public boolean status;

    public long profileNodeId;

    public String settings;
    public String domen;
}