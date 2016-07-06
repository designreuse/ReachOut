package com.bluntsoftware.ReachOut.modules.reachout.rest;

import com.bluntsoftware.ReachOut.modules.reachout.domain.Contactinfo;
import com.bluntsoftware.ReachOut.modules.reachout.domain.Contactinfotag;
import com.bluntsoftware.ReachOut.modules.reachout.domain.Tag;
import com.bluntsoftware.ReachOut.modules.user_manager.domain.ApplicationUser;
import com.bluntsoftware.ReachOut.security.AccountService;
import com.bluntsoftware.ReachOut.security.SecurityUtils;
import com.genx.framework.jpa.domain.Domain;
import com.genx.framework.jpa.repository.GenericRepository;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.genx.framework.jpa.repository.support.HqlBuilder;
import com.genx.framework.jpa.repository.support.QueryBuilder;
import com.genx.framework.jpa.rest.impl.JpaCRUDRestControllerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

public abstract class CustomService <T extends Domain,ID extends Serializable, X extends GenericRepository<T,ID>> extends JpaCRUDRestControllerImpl<T,ID,X> {

    @Autowired
    AccountService accountService;

    public T getById(String id){
        HqlBuilder sp = new HqlBuilder(this.repository.getDomainClass());
        sp.eq("id",Integer.parseInt(id));
        return repository.findOne( sp);
    }
    UserSecurity<T> getUserSecurity(){
        ApplicationUser user = accountService.getByLogin(SecurityUtils.getCurrentLogin());
        List<String> roles = accountService.getUserRoles(user);
        return new UserSecurity<T>(user,roles);
    }


    @Override
    public T saveUpdate(HttpServletRequest request, @RequestBody Map<String, Object> object) throws Exception {
        if(isUserManaged()){

            UserSecurity<T> security = getUserSecurity();

            if(object.containsKey("id") &&
                    object.get("id") != null &&
                    !object.get("id").toString().equalsIgnoreCase("") &&
                    !object.get("id").toString().equalsIgnoreCase("null")){
                T entity = getById(object.get("id").toString());
                if(entity != null && security.canUpdate(entity)){
                    object.put(UserSecurity.SECURITY_FIELD,security.getSecurityFieldValue(entity));
                    return super.saveUpdate(request, object);
                }
            }else{
                if(security.canCreate()){
                    object.put(UserSecurity.SECURITY_FIELD,security.getUser().getLogin());
                    object.put("id",null);
                    return super.saveUpdate(request, object);
                }
            }
        }else{
            return super.saveUpdate(request, object);
        }
        return null;
    }

    public Boolean canRemove(@PathVariable("id") ID id){
        if(isUserManaged()){
            UserSecurity<T> security = getUserSecurity();
            T entity =  repository.findOne(id);
            if(security.canDelete(entity)){
                return true;
            }
        }else {
            return true;
        }

        return false;
    }

    @Override
    public Object delete(@PathVariable("id") ID id) throws Exception {
        if(canRemove(id)) {
            return super.delete(id);
        }
        return null;
    }




    @Override
    public HashMap<String,Object> findAll(HttpServletRequest request) throws Exception {
        ApplicationUser user = accountService.getByLogin(SecurityUtils.getCurrentLogin());
        List<String> roles = accountService.getUserRoles(user);

        if(roles.contains("ROLE_ADMIN") || !isUserManaged()){
            return super.findAll(request);
        }else{

            QueryBuilder sp = new QueryBuilder(this.repository.getDomainClass());
            sp.eq(UserSecurity.SECURITY_FIELD,user.getLogin());
            String hql = sp.getHql(request);
            PageRequest pageRequest = sp.getPageRequest(request);
            List parameters = sp.getParameters();
            org.springframework.data.domain.Page items = this.repository.findAll(hql, parameters, pageRequest);
            return QueryBuilder.result(request, items);
        }

    }

    @Override
    public T findOne(@PathVariable("id") ID id) throws Exception {
        if(isUserManaged()){
            T entity = super.findOne(id);
            UserSecurity<T> security = getUserSecurity();
            if(security.canRead(entity)){
                return super.findOne(id);
            }
        }else{
            return super.findOne(id);
        }
        return null;
    }

    public Boolean isUserManaged(){return false;};
    public Boolean isGroupManaged(){return false;};


}
