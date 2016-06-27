package com.bluntsoftware.ReachOut.modules.reachout.rest;



import com.genx.framework.jpa.repository.GenericRepository;
import com.bluntsoftware.ReachOut.modules.reachout.domain.Company;
import com.bluntsoftware.ReachOut.modules.reachout.repository.CompanyRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
@Controller("reachoutCompanyService")
@RequestMapping(value = "/reachout/company")
@Transactional
@Qualifier("reachout")

public class CompanyService extends CustomService<Company,Integer, CompanyRepository> {


}
