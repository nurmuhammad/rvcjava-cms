package rvc.cms.model;

import org.javalite.activejdbc.annotations.BelongsTo;

/**
 * @author nurmuhammad
 */
@BelongsTo(parent = User.class, foreignKeyName = "user_id")
public class Log extends aModel {
    public long id;
    public long userId;
    public String type;
    public String message;
    public String setting;
    public String hostname;
    public long timestamp;
}