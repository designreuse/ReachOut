package com.bluntsoftware.ReachOut.modules.user_manager.rest;



import com.genx.framework.jpa.repository.GenericRepository;
import com.bluntsoftware.ReachOut.modules.user_manager.domain.ApplicationMailServer;
import com.bluntsoftware.ReachOut.modules.user_manager.repository.ApplicationMailServerRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
@Controller("user_managerApplicationMailServerService")
@RequestMapping(value = "/user_manager/applicationMailServer")
@Transactional
@Qualifier("user_manager")

public class ApplicationMailServerService extends CustomService<ApplicationMailServer,Integer, ApplicationMailServerRepository> {


}
