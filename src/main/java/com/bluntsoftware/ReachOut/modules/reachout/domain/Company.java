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
@Table(name = "\"Company\"")
public class Company implements CustomDomain<Company> {

    private static final Map< Serializable, Integer > SAVED_HASHES = Collections.synchronizedMap(new WeakHashMap< Serializable, Integer >());
    private volatile Integer hashCode;
    private Integer id = null;
    private String name;
    private Address billing;
    private Address shipping;
    private Company parentCompany;
    private Contactinfo contactInfo;

    public Company() { }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Company_id_seq")
    @SequenceGenerator(name = "Company_id_seq", allocationSize = 1, sequenceName = "Company_id_seq", initialValue = 1)
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

    @Column(name = "\"name\"", length = 255)
    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "\"billing\"", nullable = true )
    public Address getBilling() {
        return billing;
    }
    public void setBilling(Address billing){
        this.billing = billing;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "\"shipping\"", nullable = true )
    public Address getShipping() {
        return shipping;
    }
    public void setShipping(Address shipping){
        this.shipping = shipping;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "\"parentCompany\"", nullable = true )
    public Company getParentCompany() {
        return parentCompany;
    }
    public void setParentCompany(Company parentCompany){
        this.parentCompany = parentCompany;
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

    @Transient
    public Class<?> getClassType() {
        return Company.class;
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

    public int compareTo(Company company) {
        return 0;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Company entity = (Company)super.clone();
        entity.setId(null);
        return entity;
    }
}