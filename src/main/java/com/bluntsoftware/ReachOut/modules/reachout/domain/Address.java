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
@Table(name = "\"Address\"")
public class Address implements CustomDomain<Address> {

    private static final Map< Serializable, Integer > SAVED_HASHES = Collections.synchronizedMap(new WeakHashMap< Serializable, Integer >());
    private volatile Integer hashCode;
    private Integer id = null;
    private String city;
    private String state;
    private String zip;
    private String countryCode;
    private String address;

    public Address() { }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Address_id_seq")
    @SequenceGenerator(name = "Address_id_seq", allocationSize = 1, sequenceName = "Address_id_seq", initialValue = 1)
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

    @Column(name = "\"city\"", length = 255)
    public String getCity() {
        return city;
    }
    public void setCity(String city){
        this.city = city;
    }

    @Column(name = "\"state\"", length = 255)
    public String getState() {
        return state;
    }
    public void setState(String state){
        this.state = state;
    }

    @Column(name = "\"zip\"", length = 255)
    public String getZip() {
        return zip;
    }
    public void setZip(String zip){
        this.zip = zip;
    }

    @Column(name = "\"countryCode\"", length = 255)
    public String getCountryCode() {
        return countryCode;
    }
    public void setCountryCode(String countryCode){
        this.countryCode = countryCode;
    }

    @Column(name = "\"address\"", length = 2048)
    public String getAddress() {
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }

    @Transient
    public Class<?> getClassType() {
        return Address.class;
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

    public int compareTo(Address address) {
        return 0;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Address entity = (Address)super.clone();
        entity.setId(null);
        return entity;
    }
}