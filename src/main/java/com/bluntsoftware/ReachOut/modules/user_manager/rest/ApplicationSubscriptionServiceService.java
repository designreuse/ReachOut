package com.bluntsoftware.ReachOut.modules.user_manager.rest;



import com.genx.framework.jpa.repository.GenericRepository;
import com.bluntsoftware.ReachOut.modules.user_manager.domain.ApplicationSubscriptionService;
import com.bluntsoftware.ReachOut.modules.user_manager.repository.ApplicationSubscriptionServiceRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
@Controller("user_managerApplicationSubscriptionServiceService")
@RequestMapping(value = "/user_manager/applicationSubscriptionService")
@Transactional
@Qualifier("user_manager")

public class ApplicationSubscriptionServiceService extends CustomService<ApplicationSubscriptionService,Integer, ApplicationSubscriptionServiceRepository> {


}
