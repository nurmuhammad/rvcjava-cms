package rvc.cms.model;

/**
 * @author nurmuhammad
 */

public class User extends aModel {
    public long id;
    public String roles;
    public String email;
    public String password;

    public int created;
    public int chaged;

    public int lastLogin;
    public String lastIp;
    public boolean status;

    public long profileNodeId;

    public String settings;
    public String domen;
}