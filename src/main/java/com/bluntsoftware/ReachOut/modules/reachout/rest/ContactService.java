package com.bluntsoftware.ReachOut.modules.reachout.rest;



import com.bluntsoftware.ReachOut.modules.reachout.domain.Company;
import com.bluntsoftware.ReachOut.modules.reachout.domain.Contactinfo;
import com.genx.framework.jpa.repository.GenericRepository;
import com.bluntsoftware.ReachOut.modules.reachout.domain.Contact;
import com.bluntsoftware.ReachOut.modules.reachout.repository.ContactRepository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller("reachoutContactService")
@RequestMapping(value = "/reachout/contact")
@Transactional
@Qualifier("reachout")

public class ContactService extends CustomService<Contact,Integer, ContactRepository> {
     @Autowired
    ContactinfoService contactinfoService;
    @Autowired
    CompanyService companyService;
    @Override
    public Contact saveUpdate(HttpServletRequest request, @RequestBody Map<String, Object> object) throws Exception {
         Contact contact =  super.saveUpdate(request, object);
         if(object.containsKey("contactInfo")){
             Map<String, Object> contactInfo = (Map<String, Object>)object.get("contactInfo");
             Contactinfo contactinfo =  contactinfoService.saveUpdate(null,contactInfo);
             contact.setContactInfo(contactinfo);
         }
        if(object.containsKey("company")){
            Map<String, Object> companyMap = (Map<String, Object>)object.get("company");
            Company company =  companyService.saveUpdate(null,companyMap);
            contact.setCompany(company);
        }
         return contact;
    }


}
