package com.bluntsoftware.ReachOut.modules.reachout.rest;



import com.bluntsoftware.ReachOut.modules.reachout.domain.Company;
import com.bluntsoftware.ReachOut.modules.reachout.domain.Contactinfo;
import com.bluntsoftware.ReachOut.modules.reachout.domain.Contactphone;
import com.bluntsoftware.ReachOut.modules.reachout.repository.ContactphoneRepository;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.genx.framework.jpa.repository.GenericRepository;
import com.bluntsoftware.ReachOut.modules.reachout.domain.Contact;
import com.bluntsoftware.ReachOut.modules.reachout.repository.ContactRepository;


import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

@Controller("reachoutContactService")
@RequestMapping(value = "/reachout/contact")
@Transactional
@Qualifier("reachout")

public class ContactService extends CustomService<Contact,Integer, ContactRepository> {
     @Autowired
    ContactinfoService contactinfoService;
    @Autowired
    CompanyService companyService;
    @Autowired
    ContactphoneService contactphoneService;
    @Autowired
    ContactemailService contactemailService;
    @Autowired
    ContactwebService contactwebService;

    @Override
    public Contact saveUpdate(HttpServletRequest request, @RequestBody Map<String, Object> object) throws Exception {
         Contact contact =  super.saveUpdate(request, object);
         if(object.containsKey("contactInfo")){
             Map<String, Object> contactInfo = (Map<String, Object>)object.get("contactInfo");
             Contactinfo contactinfo =  contactinfoService.saveUpdate(null,contactInfo);
             contact.setContactInfo(contactinfo);
         }
        if(object.containsKey("company")){
            Map<String, Object> companyMap = (Map<String, Object>)object.get("company");
            Company company =  companyService.saveUpdate(null,companyMap);
            contact.setCompany(company);
        }
         return contact;
    }
    @RequestMapping(value = "/importCsv", method = RequestMethod.GET)
    @ResponseBody
    public String uploadFile( ){
        return "POST Files to this URL for Upload";
    }

    @Transactional(readOnly = false)
    @RequestMapping(value = "/importCsv", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile file) throws Exception {

        String filename = file.getOriginalFilename();
        String filenameNoExtension = filename.replaceFirst("[.][^.]+$", "");
        String extension = null;
        int i = filename.lastIndexOf('.');
        if (i > 0) {
            extension = filename.substring(i+1);
        }

        if(extension == null || !extension.equalsIgnoreCase("csv")){
            throw new Exception("We can only upload war files");
        }
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader(); // use first row as header; otherwise defaults are fine
        MappingIterator<Map<String,String>> it = null;
        try {
            it = mapper.reader(Map.class)
                    .with(schema)
                    .readValues(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (it.hasNext()) {
            Map<String,String> rowAsMap = it.next();

            Contact contact = new Contact();

            if(rowAsMap.containsKey("First Name")){contact.setFirstName(rowAsMap.get("First Name"));}
            if(rowAsMap.containsKey("Last Name")){contact.setLastName(rowAsMap.get("Last Name"));}
            if(rowAsMap.containsKey("Job Title")){contact.setJobTitle(rowAsMap.get("Job Title"));}

            Company company = new Company();
            contact.setCompany(company);
            if(rowAsMap.containsKey("Company")){
                contact.setCompanyName(rowAsMap.get("Company"));
                company.setName(rowAsMap.get("Company"));
            }


            if(rowAsMap.containsKey("Notes")){contact.setNote(rowAsMap.get("Notes"));}
            Contactinfo  contactInfo = new Contactinfo();
            contact.setContactInfo(contactInfo);
            contactInfo.setOwner("Alex");
            contact = repository.saveAndUpdate(contact);
            //Phones
            if(rowAsMap.containsKey("Mobile Phone")){
                contactphoneService.addPhone(rowAsMap.get("Mobile Phone"),"Mobile",contact.getContactInfo());
            }
            if(rowAsMap.containsKey("Business Phone")){
                contactphoneService.addPhone(rowAsMap.get("Business Phone"),"Office",contact.getContactInfo());
            }
            if(rowAsMap.containsKey("Business Fax")){
                contactphoneService.addPhone(rowAsMap.get("Business Fax"),"Fax",contact.getContactInfo());
            }
            if(rowAsMap.containsKey("Home Phone")){
                contactphoneService.addPhone(rowAsMap.get("Home Phone"),"Home",contact.getContactInfo());
            }
            //Emails
            if(rowAsMap.containsKey("E-mail Address")){
                contactemailService.addEmail(rowAsMap.get("E-mail Address"),"Office",contact.getContactInfo());
            }
            if(rowAsMap.containsKey("E-mail 2 Address")){
                contactemailService.addEmail(rowAsMap.get("E-mail 2 Address"),"Other",contact.getContactInfo());
            }
            if(rowAsMap.containsKey("E-mail 3 Address")){
                contactemailService.addEmail(rowAsMap.get("E-mail 3 Address"),"Other",contact.getContactInfo());
            }





        }


        return "SUCCESS";
    }

    @Override
    public Object delete(@PathVariable("id") Integer integer) throws Exception {
        Contact contact = repository.getOne(integer);

        contactinfoService.delete(contact.getContactInfo());

        return super.delete(integer);
    }
}
