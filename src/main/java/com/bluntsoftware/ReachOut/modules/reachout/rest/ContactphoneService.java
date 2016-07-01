package com.bluntsoftware.ReachOut.modules.reachout.rest;



import com.bluntsoftware.ReachOut.modules.reachout.domain.Contactinfo;
import com.genx.framework.jpa.repository.GenericRepository;
import com.bluntsoftware.ReachOut.modules.reachout.domain.Contactphone;
import com.bluntsoftware.ReachOut.modules.reachout.repository.ContactphoneRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
@Controller("reachoutContactphoneService")
@RequestMapping(value = "/reachout/contactphone")
@Transactional
@Qualifier("reachout")

public class ContactphoneService extends CustomService<Contactphone,Integer, ContactphoneRepository> {

    public void addPhone(String number,String type,Contactinfo contactinfo){
        Contactphone phone = new Contactphone();
        phone.setContactInfo(contactinfo);
        phone.setPhone(number);
        phone.setType(type);
        repository.saveAndUpdate(phone);
    }
}
