package com.bluntsoftware.ReachOut.modules.reachout.rest;



import com.bluntsoftware.ReachOut.modules.reachout.domain.Contactinfo;
import com.genx.framework.jpa.repository.GenericRepository;
import com.bluntsoftware.ReachOut.modules.reachout.domain.Contactphone;
import com.bluntsoftware.ReachOut.modules.reachout.repository.ContactphoneRepository;
import com.genx.framework.jpa.repository.support.HqlBuilder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller("reachoutContactphoneService")
@RequestMapping(value = "/reachout/contactphone")
@Transactional
@Qualifier("reachout")

public class ContactphoneService extends CustomService<Contactphone,Integer, ContactphoneRepository> {


    public void addPhone(String number,String type,Contactinfo contactinfo){
        if(number != null && !number.equalsIgnoreCase("")){
            Contactphone phone = new Contactphone();
            phone.setContactInfo(contactinfo);
            phone.setPhone(number);
            phone.setType(type);
            repository.saveAndUpdate(phone);
        }

    }

    public void deleteByContactinfo(Contactinfo contactInfo) {
        HqlBuilder builder = new HqlBuilder(Contactphone.class);
        builder.eq("contactInfo",contactInfo);

        List<Contactphone> phones = repository.findAll(builder);

        for(Contactphone phone:phones){
            repository.delete(phone);
        }

    }
}
