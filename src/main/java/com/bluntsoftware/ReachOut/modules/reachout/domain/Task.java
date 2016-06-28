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
@Table(name = "\"Task\"")
public class Task implements CustomDomain<Task> {

    private static final Map< Serializable, Integer > SAVED_HASHES = Collections.synchronizedMap(new WeakHashMap< Serializable, Integer >());
    private volatile Integer hashCode;
    private Integer id = null;
    private Date completedOn;
    private String description;
    private Contact contact;
    private String owner;
    private String status;

    public Task() { }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Task_id_seq")
    @SequenceGenerator(name = "Task_id_seq", allocationSize = 1, sequenceName = "Task_id_seq", initialValue = 1)
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

    @JsonSerialize(using = com.genx.framework.jpa.serializers.CustomTimestampSerializer.class, include=JsonSerialize.Inclusion.NON_NULL)
    @Column(name = "\"completedOn\"")
    public Date getCompletedOn() {
        return completedOn;
    }
    public void setCompletedOn(Date completedOn){
        this.completedOn = completedOn;
    }

    @Column(name = "\"description\"", length = 2048)
    public String getDescription() {
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "\"contact\"", nullable = true )
    public Contact getContact() {
        return contact;
    }
    public void setContact(Contact contact){
        this.contact = contact;
    }

    @Column(name = "\"owner\"", length = 255)
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner){
        this.owner = owner;
    }

    @Column(name = "\"status\"", length = 255)
    public String getStatus() {
        return status;
    }
    public void setStatus(String status){
        this.status = status;
    }

    @Transient
    public Class<?> getClassType() {
        return Task.class;
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

    public int compareTo(Task task) {
        return 0;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Task entity = (Task)super.clone();
        entity.setId(null);
        return entity;
    }
}