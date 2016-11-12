package rvc.cms.model;

import javax.persistence.*;

/**
 * @author nurmuhammad
 */

@Entity
@Table(name = "objects")
@Access(AccessType.FIELD)
public class Objects extends SettingsModel {

    @Column(name = "name")
    public String name;

    @Lob
    @Column(name = "description")
    public String description;

    @Column(name = "type")
    public String type;

    @Lob
    @Column(name = "java_code")
    public String javaCode;

//    @Column(name = "before_run_object_id")
//    public Long beforeRunObjectId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "before_run_object_id")
    public Objects beforeRunObject;

//    @Column(name = "after_run_object_id")
//    public Long afterRunObjectId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "after_run_object_id")
    public Objects afterRunObject;

}