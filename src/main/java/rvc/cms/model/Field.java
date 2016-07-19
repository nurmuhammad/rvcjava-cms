package rvc.cms.model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author nurmuhammad
 */

public class Field extends aModel {

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

    static String t = "eeee";

    public static void main(String[] args) throws Exception {
        java.lang.reflect.Field f = Field.class.getDeclaredField("t");
        System.out.println(f.get(null));
    }
}
