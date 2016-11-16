package rvc.cms.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.io.UnsupportedEncodingException;

/**
 * @author nurmuhammad
 */

@Entity
@Table(name = "lob_values")
@DynamicInsert
@DynamicUpdate
public class LobValue extends aModel {
    private static final Logger logger = LoggerFactory.getLogger(LobValue.class);

    byte[] value;

    @Lob
    @Column(name = "value")
    public byte[] getValue() {
        return value;
    }

    public void setValue(byte[] value) {
        this.value = value;
    }

    @Transient
    public String string(String... params) {
        if (value == null) return null;
        try {
            if (params.length > 0) {
                String charset = params[0];
                return new String(value, charset);
            }
            return new String(value);
        } catch (UnsupportedEncodingException e) {
            logger.error("UnsupportedEncodingException", e);
        }
        return null;
    }

}
