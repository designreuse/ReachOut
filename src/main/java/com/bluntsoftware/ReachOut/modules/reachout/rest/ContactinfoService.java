package com.bluntsoftware.ReachOut.modules.reachout.rest;



import com.bluntsoftware.ReachOut.modules.reachout.domain.*;
import com.genx.framework.jpa.repository.GenericRepository;
import com.bluntsoftware.ReachOut.modules.reachout.repository.ContactinfoRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller("reachoutContactinfoService")
@RequestMapping(value = "/reachout/contactinfo")
@Transactional
@Qualifier("reachout")

public class ContactinfoService extends CustomService<Contactinfo,Integer, ContactinfoRepository> {

    @Autowired
    AddressService addressService;
    @Autowired
    ContactphoneService contactphoneService;
    @Autowired
    ContactemailService contactemailService;
    @Autowired
    ContactwebService contactwebService;
    @Autowired
    ContactinfotagService contactinfotagService;
    @Autowired
    TagService tagService;
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
        if(object.containsKey("phones")){
            List<Map<String, Object>> phones = (List<Map<String, Object>>)object.get("phones");
            for(Map<String, Object> phone:phones){
               Contactphone cphone =  contactphoneService.saveUpdate(null,phone);
                cphone.setContactInfo(contactInfo);
            }
        }
        if(object.containsKey("emails")){
            List<Map<String, Object>> emails = (List<Map<String, Object>>)object.get("emails");
            for(Map<String, Object> email:emails){
                Contactemail cemail =  contactemailService.saveUpdate(null,email);
                cemail.setContactInfo(contactInfo);
            }
        }
        if(object.containsKey("urls")){
            List<Map<String, Object>> urls = (List<Map<String, Object>>)object.get("urls");
            for(Map<String, Object> url:urls){
                Contactweb cweb =  contactwebService.saveUpdate(null,url);
                cweb.setContactInfo(contactInfo);
            }
        }
        if(object.containsKey("tags")){
            List<Map<String, Object>> tags = (List<Map<String, Object>>)object.get("tags");

            contactinfotagService.removeTags(contactInfo);

            for(Map<String, Object> tag:tags){
                Tag dtag = tagService.getOrCreateTag((String)tag.get("text"));
                Contactinfotag infoTag = contactinfotagService.getOrCreate(contactInfo,dtag);
            }
        }
        return contactInfo;
    }

    public void delete(Contactinfo contactInfo) {
        contactemailService.deleteByContactinfo(contactInfo);
        contactphoneService.deleteByContactinfo(contactInfo);
        contactwebService.deleteByContactinfo(contactInfo);

        Address billing = contactInfo.getBilling();
        if(billing != null){
            addressService.getRepository().delete(billing);
        }

        Address shipping = contactInfo.getShipping();
        if(shipping != null){
            addressService.getRepository().delete(shipping);
        }


        repository.delete(contactInfo);
    }
}
