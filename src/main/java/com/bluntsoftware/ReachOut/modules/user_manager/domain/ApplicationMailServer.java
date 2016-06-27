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
@Table(name = "\"APPLICATION_MAIL_SERVER\"")
public class ApplicationMailServer implements CustomDomain<ApplicationMailServer> {

    private static final Map< Serializable, Integer > SAVED_HASHES = Collections.synchronizedMap(new WeakHashMap< Serializable, Integer >());
    private volatile Integer hashCode;
    private Integer id = null;
    private String name;
    private String host;
    private String port;
    private String user;
    private String password;
    private Boolean tls;
    private Boolean auth;
    private String protocol;
    private String from;

    public ApplicationMailServer() { }

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

    @Column(name = "\"host\"", length = 50)
    public String getHost() {
        return host;
    }
    public void setHost(String host){
        this.host = host;
    }

    @Column(name = "\"port\"", length = 50)
    public String getPort() {
        return port;
    }
    public void setPort(String port){
        this.port = port;
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

    @JsonSerialize(using = com.genx.framework.jpa.serializers.CustomBooleanSerializer.class, include=JsonSerialize.Inclusion.NON_NULL)
    @Column(name = "\"tls\"")
    public Boolean getTls() {
        return tls;
    }
    public void setTls(Boolean tls){
        this.tls = tls;
    }

    @JsonSerialize(using = com.genx.framework.jpa.serializers.CustomBooleanSerializer.class, include=JsonSerialize.Inclusion.NON_NULL)
    @Column(name = "\"auth\"")
    public Boolean getAuth() {
        return auth;
    }
    public void setAuth(Boolean auth){
        this.auth = auth;
    }

    @Column(name = "\"protocol\"", length = 50)
    public String getProtocol() {
        return protocol;
    }
    public void setProtocol(String protocol){
        this.protocol = protocol;
    }

    @Column(name = "\"from\"", length = 50)
    public String getFrom() {
        return from;
    }
    public void setFrom(String from){
        this.from = from;
    }

    @Transient
    public Class<?> getClassType() {
        return ApplicationMailServer.class;
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

    public int compareTo(ApplicationMailServer applicationMailServer) {
        return 0;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        ApplicationMailServer entity = (ApplicationMailServer)super.clone();
        entity.setId(null);
        return entity;
    }
}