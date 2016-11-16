package rvc.cms.model;

import rvc.cms.$;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.util.HashMap;
import java.util.Map;

/**
 * @author nurmuhammad
 */

@MappedSuperclass
public abstract class SettingsModel extends aModel {

    String settings;

    @Transient
    private transient Map<String, String> settingsMap;

    @Column(name = "settings", length = 1024)
    public String getSettings() {
        return settings;
    }

    public void setSettings(String settings) {
        this.settings = settings;
    }

    @Transient
    public Map<String, String> map() {
        if (settingsMap == null) {
            settingsMap = new HashMap<>($.settings2map(settings));
        }
        return settingsMap;
    }

    @Transient
    private void map2settings(Map map) {
        settings = $.map2settings(map);
    }

    @Transient
    public String setting(String key) {
        return map().get(key);
    }

    @Transient
    public void setting(String key, String value) {
        map().put(key, value);
        map2settings(map());
    }
}
