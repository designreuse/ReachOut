package com.bluntsoftware.ReachOut.modules.reachout.rest;



import com.genx.framework.jpa.repository.GenericRepository;
import com.bluntsoftware.ReachOut.modules.reachout.domain.Contactinfo;
import com.bluntsoftware.ReachOut.modules.reachout.repository.ContactinfoRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
@Controller("reachoutContactinfoService")
@RequestMapping(value = "/reachout/contactinfo")
@Transactional
@Qualifier("reachout")

public class ContactinfoService extends CustomService<Contactinfo,Integer, ContactinfoRepository> {


}
