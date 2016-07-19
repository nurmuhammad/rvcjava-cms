package rvc.cms.model;

import java.util.ArrayList;

/**
 * @author nurmuhammad
 */

public class Node extends aModel {

    public long id;
    public long userId;
    public String type;
    public int created;
    public int chaged;
    public boolean status;

    public String settings;
    public String domen;

    public ArrayList<Field> fields;
}