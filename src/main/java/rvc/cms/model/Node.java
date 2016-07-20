package rvc.cms.model;

import java.util.ArrayList;

/**
 * @author nurmuhammad
 */

public class Node extends aModel {

    public long id;
    public long userId;
    public String type;
    public long created;
    public long chaged;
    public boolean status;

    public String settings;
    public String domen;

    public ArrayList<Field> fields;
}