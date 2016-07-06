package com.bluntsoftware.ReachOut.modules.reachout.rest;



import com.bluntsoftware.ReachOut.modules.reachout.domain.Contactinfo;
import com.genx.framework.jpa.repository.GenericRepository;
import com.bluntsoftware.ReachOut.modules.reachout.domain.Company;
import com.bluntsoftware.ReachOut.modules.reachout.repository.CompanyRepository;
import com.genx.framework.jpa.repository.support.HqlBuilder;
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
        if(object.containsKey("contactInfo") && company != null){
            Map<String, Object> contactInfo = (Map<String, Object>)object.get("contactInfo");
            Contactinfo contactinfo =  contactinfoService.saveUpdate(null,contactInfo);
            company.setContactInfo(contactinfo);
        }

        return company;
    }

    @Override
    public Boolean isGroupManaged() {
        return true;
    }

    public Company importCompanyCsv(Map<String, String> rowAsMap) {
        String companyName = rowAsMap.get("Company") ;
        if(companyName != null && !companyName.equalsIgnoreCase("")){
            //Lets find the company by name
            HqlBuilder hql = new HqlBuilder(Company.class);
            hql.eq("name",companyName);

            Company company = repository.findOne(hql);
            if(company == null ){
                company = new Company();
                company.setName(companyName);
                company = repository.saveAndUpdate(company);
            }
            return company;
        }
        return null;
    }
}
