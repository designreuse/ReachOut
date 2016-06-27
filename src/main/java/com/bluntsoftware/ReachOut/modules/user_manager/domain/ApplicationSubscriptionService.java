package com.bluntsoftware.ReachOut.modules.user_manager.domain;


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
@Table(name = "\"APPLICATION_SUBSCRIPTION_SERVICE\"")
public class ApplicationSubscriptionService implements CustomDomain<ApplicationSubscriptionService> {

    private static final Map< Serializable, Integer > SAVED_HASHES = Collections.synchronizedMap(new WeakHashMap< Serializable, Integer >());
    private volatile Integer hashCode;
    private Integer id = null;
    private String name;
    private String type;
    private String host;
    private String user;
    private String password;
    private String token;
    private String merchantId;
    private String publicKey;
    private String privateKey;

    public ApplicationSubscriptionService() { }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Column(name = "\"name\"", length = 50)
    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    @Column(name = "\"type\"", length = 50)
    public String getType() {
        return type;
    }
    public void setType(String type){
        this.type = type;
    }

    @Column(name = "\"host\"", length = 50)
    public String getHost() {
        return host;
    }
    public void setHost(String host){
        this.host = host;
    }

    @Column(name = "\"user\"", length = 50)
    public String getUser() {
        return user;
    }
    public void setUser(String user){
        this.user = user;
    }

    @Column(name = "\"password\"", length = 100)
    public String getPassword() {
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    @Column(name = "\"token\"", length = 100)
    public String getToken() {
        return token;
    }
    public void setToken(String token){
        this.token = token;
    }

    @Column(name = "\"merchant_id\"", length = 100)
    public String getMerchantId() {
        return merchantId;
    }
    public void setMerchantId(String merchantId){
        this.merchantId = merchantId;
    }

    @Column(name = "\"public_key\"", length = 100)
    public String getPublicKey() {
        return publicKey;
    }
    public void setPublicKey(String publicKey){
        this.publicKey = publicKey;
    }

    @Column(name = "\"private_key\"", length = 100)
    public String getPrivateKey() {
        return privateKey;
    }
    public void setPrivateKey(String privateKey){
        this.privateKey = privateKey;
    }

    @Transient
    public Class<?> getClassType() {
        return ApplicationSubscriptionService.class;
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

    public int compareTo(ApplicationSubscriptionService applicationSubscriptionService) {
        return 0;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        ApplicationSubscriptionService entity = (ApplicationSubscriptionService)super.clone();
        entity.setId(null);
        return entity;
    }
}