package rvc.cms.model;

import rvc.cms.$;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author nurmuhammad
 */

@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class SettingsModel extends aModel {

//    @Basic(fetch = FetchType.LAZY)
    @OneToOne (fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "settings_id")
    private Settings settings;

    public Settings getSettings() {
        if((id==null || id==0L) && settings == null ){
            settings = new Settings();
            return settings;
        }
        return settings;// = lazy(settings, schema);
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public Map<String, String> map() {
        return getSettings().map();
    }

    public String setting(String key) {
        return getSettings().setting(key);
    }

    public void setting(String key, String value) {
        getSettings().setting(key, value);
    }
}
