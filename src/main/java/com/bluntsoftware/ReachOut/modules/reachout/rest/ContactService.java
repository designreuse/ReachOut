package com.bluntsoftware.ReachOut.modules.reachout.rest;



import com.genx.framework.jpa.repository.GenericRepository;
import com.bluntsoftware.ReachOut.modules.reachout.domain.Contact;
import com.bluntsoftware.ReachOut.modules.reachout.repository.ContactRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
@Controller("reachoutContactService")
@RequestMapping(value = "/reachout/contact")
@Transactional
@Qualifier("reachout")

public class ContactService extends CustomService<Contact,Integer, ContactRepository> {


}
