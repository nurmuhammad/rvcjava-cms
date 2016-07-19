package rvc.cms.model;

/**
 * @author nurmuhammad
 */

public class FieldValue extends aModel {
    public long id;
    public long userId;
    public long fieldId;
    public long nodeIdValue;
    public String valueText;
    public long valueInt;
    public double valueDouble;
    public byte[] valueBlob;
    public String language;
    public String settings;
    public int sort;
}