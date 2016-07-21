package rvc.cms.model;

import org.javalite.activejdbc.annotations.BelongsTo;

/**
 * @author nurmuhammad
 */

@BelongsTo(parent = User.class, foreignKeyName = "user_id")
public class Node extends aModel {

    public long id;
    public long userId;
    public String type;
    public long created;
    public long chaged;
    public boolean status;

    public String settings;
    public String domen;
}