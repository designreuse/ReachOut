package com.bluntsoftware.ReachOut.modules.reachout.rest;



import com.genx.framework.jpa.repository.GenericRepository;
import com.bluntsoftware.ReachOut.modules.reachout.domain.Contactweb;
import com.bluntsoftware.ReachOut.modules.reachout.repository.ContactwebRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
@Controller("reachoutContactwebService")
@RequestMapping(value = "/reachout/contactweb")
@Transactional
@Qualifier("reachout")

public class ContactwebService extends CustomService<Contactweb,Integer, ContactwebRepository> {


}
