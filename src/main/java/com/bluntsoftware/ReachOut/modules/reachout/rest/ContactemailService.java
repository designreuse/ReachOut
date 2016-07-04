package com.bluntsoftware.ReachOut.modules.reachout.rest;



import com.bluntsoftware.ReachOut.modules.reachout.domain.Contactinfo;
import com.bluntsoftware.ReachOut.modules.reachout.domain.Contactphone;
import com.bluntsoftware.ReachOut.modules.reachout.repository.ContactphoneRepository;
import com.genx.framework.jpa.repository.GenericRepository;
import com.bluntsoftware.ReachOut.modules.reachout.domain.Contactemail;
import com.bluntsoftware.ReachOut.modules.reachout.repository.ContactemailRepository;
import com.genx.framework.jpa.repository.support.HqlBuilder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller("reachoutContactemailService")
@RequestMapping(value = "/reachout/contactemail")
@Transactional
@Qualifier("reachout")

public class ContactemailService extends CustomService<Contactemail,Integer, ContactemailRepository> {


    public void addEmail(String email,String type,Contactinfo contactinfo){

        if(email != null && !email.equalsIgnoreCase("")){
            Contactemail cemail = new Contactemail();
            cemail.setContactInfo(contactinfo);
            cemail.setEmail(email);
            cemail.setType(type);
            repository.saveAndUpdate(cemail);
        }
    }

    public void deleteByContactinfo(Contactinfo contactInfo) {

        HqlBuilder builder = new HqlBuilder(Contactemail.class);
        builder.eq("contactInfo",contactInfo);

        List<Contactemail> emails = repository.findAll(builder);

        for(Contactemail email:emails){
            repository.delete(email);
        }

    }
}
