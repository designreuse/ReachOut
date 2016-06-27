package com.bluntsoftware.ReachOut.modules.reachout.rest;



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


}
