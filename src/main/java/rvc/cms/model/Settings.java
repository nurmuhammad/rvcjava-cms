package rvc.cms.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import rvc.cms.$;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author nurmuhammad
 */

@Entity
@Table(name = "settings")
@Access(AccessType.FIELD)
@DynamicInsert @DynamicUpdate
public final class Settings extends aModel {

    @Lob
    @Column(name ="settings")
    private String settings;

    @Transient
    private transient Map<String, String> settingsMap;

    public Map<String, String> map() {
        if (settingsMap == null) {
            settingsMap = new HashMap<>($.settings2map(settings));
        }
        return settingsMap;
    }

    private void map2settings(Map map) {
        settings = $.map2settings(map);
    }

    public String setting(String key) {
        return map().get(key);
    }

    public void setting(String key, String value) {
        map().put(key, value);
        map2settings(map());
    }
}
