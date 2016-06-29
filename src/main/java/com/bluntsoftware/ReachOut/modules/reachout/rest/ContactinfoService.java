package com.bluntsoftware.ReachOut.modules.reachout.rest;



import com.bluntsoftware.ReachOut.modules.reachout.domain.Address;
import com.genx.framework.jpa.repository.GenericRepository;
import com.bluntsoftware.ReachOut.modules.reachout.domain.Contactinfo;
import com.bluntsoftware.ReachOut.modules.reachout.repository.ContactinfoRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller("reachoutContactinfoService")
@RequestMapping(value = "/reachout/contactinfo")
@Transactional
@Qualifier("reachout")

public class ContactinfoService extends CustomService<Contactinfo,Integer, ContactinfoRepository> {

    @Autowired
    AddressService addressService;

    @Override
    public Contactinfo saveUpdate(HttpServletRequest request, @RequestBody Map<String, Object> object) throws Exception {

        Contactinfo contactInfo =  super.saveUpdate(request, object);
        if(object.containsKey("shipping")){
            Map<String, Object> shipping = (Map<String, Object>)object.get("shipping");
            Address address =  addressService.saveUpdate(null,shipping);
            contactInfo.setShipping(address);
        }
        if(object.containsKey("billing")){
            Map<String, Object> shipping = (Map<String, Object>)object.get("billing");
            Address address =  addressService.saveUpdate(null,shipping);
            contactInfo.setBilling(address);
        }
        return contactInfo;


    }
}
