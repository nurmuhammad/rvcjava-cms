package rvc.cms.model;

/**
 * @author nurmuhammad
 */

public class Vote extends aModel {
    public long id;
    public long nodeId;
    public long userId;
    public String type;
    public double value;
    public String voteSource;
    public long timestamp;
}
