package com.bluntsoftware.ReachOut.modules.reachout.rest;



import com.bluntsoftware.ReachOut.modules.reachout.domain.Contactinfo;
import com.genx.framework.jpa.repository.GenericRepository;
import com.bluntsoftware.ReachOut.modules.reachout.domain.Company;
import com.bluntsoftware.ReachOut.modules.reachout.repository.CompanyRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller("reachoutCompanyService")
@RequestMapping(value = "/reachout/company")
@Transactional
@Qualifier("reachout")

public class CompanyService extends CustomService<Company,Integer, CompanyRepository> {
    @Autowired
    ContactinfoService contactinfoService;

    @Override
    public Company saveUpdate(HttpServletRequest request, @RequestBody Map<String, Object> object) throws Exception {

        Company company = super.saveUpdate(request, object);
        if(object.containsKey("contactInfo")){
            Map<String, Object> contactInfo = (Map<String, Object>)object.get("contactInfo");
            Contactinfo contactinfo =  contactinfoService.saveUpdate(null,contactInfo);
            company.setContactInfo(contactinfo);
        }

        return company;
    }
}
