package com.bluntsoftware.ReachOut.modules.reachout.rest;



import com.genx.framework.jpa.repository.GenericRepository;
import com.bluntsoftware.ReachOut.modules.reachout.domain.Contactinfotag;
import com.bluntsoftware.ReachOut.modules.reachout.repository.ContactinfotagRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
@Controller("reachoutContactinfotagService")
@RequestMapping(value = "/reachout/contactinfotag")
@Transactional
@Qualifier("reachout")

public class ContactinfotagService extends CustomService<Contactinfotag,Integer, ContactinfotagRepository> {


}
