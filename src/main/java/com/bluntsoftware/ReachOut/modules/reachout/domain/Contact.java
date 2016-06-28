package com.bluntsoftware.ReachOut.modules.reachout.domain;


import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Date;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.WeakHashMap;
import java.sql.Time;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.proxy.HibernateProxy;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.*;
                                                        
@Entity
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Table(name = "\"Contact\"")
public class Contact implements CustomDomain<Contact> {

    private static final Map< Serializable, Integer > SAVED_HASHES = Collections.synchronizedMap(new WeakHashMap< Serializable, Integer >());
    private volatile Integer hashCode;
    private Integer id = null;
    private String status;
    private String firstName;
    private String lastName;
    private String photoUrl;
    private String jobTitle;
    private String owner;
    private Date createdAt;
    private Date modifiedAt;
    private Company company;
    private Contactinfo contactInfo;
    private String companyName;
    private String note;

    public Contact() { }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Contact_id_seq")
    @SequenceGenerator(name = "Contact_id_seq", allocationSize = 1, sequenceName = "Contact_id_seq", initialValue = 1)
    @Column(name = "\"id\"")
    public Integer getId() {
        return id;
    }
    public void setId(Integer id){
            if ((this.id == null || this.id == 0) && id != null && hashCode != null) {
        SAVED_HASHES.put(id, hashCode);
        }
        this.id = id;
    }

    @Column(name = "\"status\"", length = 255)
    public String getStatus() {
        return status;
    }
    public void setStatus(String status){
        this.status = status;
    }

    @Column(name = "\"firstName\"", length = 255)
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    @Column(name = "\"lastName\"", length = 255)
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    @Column(name = "\"photoUrl\"", length = 255)
    public String getPhotoUrl() {
        return photoUrl;
    }
    public void setPhotoUrl(String photoUrl){
        this.photoUrl = photoUrl;
    }

    @Column(name = "\"jobTitle\"", length = 255)
    public String getJobTitle() {
        return jobTitle;
    }
    public void setJobTitle(String jobTitle){
        this.jobTitle = jobTitle;
    }

    @Column(name = "\"owner\"", length = 255)
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner){
        this.owner = owner;
    }

    @JsonSerialize(using = com.genx.framework.jpa.serializers.CustomTimestampSerializer.class, include=JsonSerialize.Inclusion.NON_NULL)
    @Column(name = "\"createdAt\"")
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt){
        this.createdAt = createdAt;
    }

    @JsonSerialize(using = com.genx.framework.jpa.serializers.CustomTimestampSerializer.class, include=JsonSerialize.Inclusion.NON_NULL)
    @Column(name = "\"modifiedAt\"")
    public Date getModifiedAt() {
        return modifiedAt;
    }
    public void setModifiedAt(Date modifiedAt){
        this.modifiedAt = modifiedAt;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "\"company\"", nullable = true )
    public Company getCompany() {
        return company;
    }
    public void setCompany(Company company){
        this.company = company;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "\"contactInfo\"", nullable = true )
    public Contactinfo getContactInfo() {
        return contactInfo;
    }
    public void setContactInfo(Contactinfo contactInfo){
        this.contactInfo = contactInfo;
    }

    @Column(name = "\"companyName\"", length = 255)
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }

    @Column(name = "\"note\"", length = 4098)
    public String getNote() {
        return note;
    }
    public void setNote(String note){
        this.note = note;
    }

    @Transient
    public Class<?> getClassType() {
        return Contact.class;
    }

    @Override
    public int hashCode() {
          if (hashCode == null) {
            synchronized (this) {
                if (hashCode == null) {
                    if (getId() != null) {
                        hashCode = SAVED_HASHES.get(getId());
                    }
                    if (hashCode == null) {
                        if ( getId() != null && getId() != 0) {
                            hashCode = new Integer(getId().hashCode());
                        } else {
                            hashCode = new Integer(super.hashCode());
                        }
                    }
                }
            }
        }
        return hashCode;
    }

    public int compareTo(Contact contact) {
        return 0;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Contact entity = (Contact)super.clone();
        entity.setId(null);
        return entity;
    }
}