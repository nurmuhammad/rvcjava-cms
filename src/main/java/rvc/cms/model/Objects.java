package rvc.cms.model;

import javax.persistence.*;

/**
 * @author nurmuhammad
 */

@Entity
@Table(name = "objects")
public class Objects extends SettingsModel {

    String name;
    String description;
    String type;
    String javaCode;
    Objects beforeRunObject;
    Objects afterRunObject;

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Lob
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Lob
    @Column(name = "java_code")
    public String getJavaCode() {
        return javaCode;
    }

    public void setJavaCode(String javaCode) {
        this.javaCode = javaCode;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "before_run_object_id")
    public Objects getBeforeRunObject() {
        return beforeRunObject;
    }

    public void setBeforeRunObject(Objects beforeRunObject) {
        this.beforeRunObject = beforeRunObject;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "after_run_object_id")
    public Objects getAfterRunObject() {
        return afterRunObject;
    }

    public void setAfterRunObject(Objects afterRunObject) {
        this.afterRunObject = afterRunObject;
    }
}