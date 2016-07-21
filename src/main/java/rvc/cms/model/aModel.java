package rvc.cms.model;

import org.javalite.activejdbc.Model;

import java.io.Serializable;

/**
 * @author nurmuhammad
 */

public abstract class aModel extends Model implements Serializable {
    public static int timestamp() {
        return (int) (System.currentTimeMillis() / 1000);
    }
}
