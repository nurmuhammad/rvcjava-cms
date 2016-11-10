package rvc.cms.model;

import javax.persistence.*;

/**
 * @author nurmuhammad
 */

@Entity
@Table(name = "translates")
@Access(AccessType.FIELD)
public class Translate extends aModel {

    @Lob
    @Column(name = "source")
    public String source;

    @Lob
    @Column(name = "translate")
    public String translate;

    @Column(name = "language")
    public String language;

    @Column(name = "location")
    public String location;
}