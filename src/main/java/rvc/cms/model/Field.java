package rvc.cms.model;

import java.util.ArrayList;

/**
 * @author nurmuhammad
 */

public class Field {

    public long id;
    public long userId;
    public long nodeId;
    public String fieldName;
    public String type;
    public String group;
    public String settings;
    public int sort;
    public int created;
    public int changed;
    public boolean status;

    public ArrayList<FieldValue> values;

}
