package rvc.cms.model;

import org.javalite.activejdbc.annotations.Table;

/**
 * @author nurmuhammad
 */

@Table("node_node")
public class Node2Node extends aModel {
    public long nodeId;
    public long releatedNodeId;
    public String settings;
    public int value;
    public int sort;
}
