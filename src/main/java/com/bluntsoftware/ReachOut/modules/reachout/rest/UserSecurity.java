package com.bluntsoftware.ReachOut.modules.reachout.rest;

import com.bluntsoftware.ReachOut.modules.user_manager.domain.ApplicationUser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Alexander on 11/30/2015.
 */
public class UserSecurity<T> {
    static String SECURITY_FIELD = "owner";
    ApplicationUser user = null;
    List<String> roles =null;

    UserSecurity(ApplicationUser user, List<String> roles){

        this.user = user;
        this.roles = roles;
    }

    public Boolean canCreate(){
        return roles.size() > 0;
    }


    public String getSecurityFieldValue(T entity){
        Map<String, Object> map = getMap(entity);
        if(map.containsKey(SECURITY_FIELD)){
            return map.get(SECURITY_FIELD).toString();
        }
        return null;
    };

    Boolean isOwner(T entity){
        String owner =  getSecurityFieldValue(entity);
        return owner != null && !owner.equalsIgnoreCase("") && owner.equalsIgnoreCase(user.getLogin());

    }
    Boolean isAdmin(){
        return roles.contains("ROLE_ADMIN");
    }

    public Boolean canRead(T entity){
        return isAdmin() || isOwner(entity);
    }
    public Boolean canUpdate(T entity){
        return isAdmin() || isOwner(entity);
    }
    public Boolean canDelete(T entity){
        return isAdmin() || isOwner(entity);
    }
    public Map<String, Object> getMap(T entity){
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(entity, Map.class);
    }

    public ApplicationUser getUser() {
        return user;
    }

    public List<String> getRoles() {
        return roles;
    }
}


