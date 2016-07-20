package rvc.cms.model;

import org.javalite.activejdbc.annotations.Table;

/**
 * @author nurmuhammad
 */

@Table("objects")
public class Objects extends aModel {
    public long id;
    public String name;
    public String description;
    public String type;
    public String javaCode;
    public String settings;
}