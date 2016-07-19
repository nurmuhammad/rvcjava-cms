package rvc.cms.model;

/**
 * @author nurmuhammad
 */

public class Scheduler extends aModel {
    public long id;
    public int runAt;
    public int lastEndAt;
    public String text;
    public String type;
    public long nodeId;
    public long objectId;
    public String settings;


}