package com.bluntsoftware.ReachOut.modules.reachout.rest;



import com.bluntsoftware.ReachOut.modules.reachout.domain.Contactinfo;
import com.bluntsoftware.ReachOut.modules.reachout.domain.Contactphone;
import com.genx.framework.jpa.repository.GenericRepository;
import com.bluntsoftware.ReachOut.modules.reachout.domain.Contactemail;
import com.bluntsoftware.ReachOut.modules.reachout.repository.ContactemailRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
@Controller("reachoutContactemailService")
@RequestMapping(value = "/reachout/contactemail")
@Transactional
@Qualifier("reachout")

public class ContactemailService extends CustomService<Contactemail,Integer, ContactemailRepository> {
    public void addEmail(String email,String type,Contactinfo contactinfo){
        Contactemail cemail = new Contactemail();
        cemail.setContactInfo(contactinfo);
        cemail.setEmail(email);
        cemail.setType(type);
        repository.saveAndUpdate(cemail);
    }

}
