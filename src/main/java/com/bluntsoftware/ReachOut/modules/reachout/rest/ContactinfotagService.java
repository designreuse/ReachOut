package com.bluntsoftware.ReachOut.modules.reachout.rest;



import com.bluntsoftware.ReachOut.modules.reachout.domain.Contactinfo;
import com.bluntsoftware.ReachOut.modules.reachout.domain.Tag;
import com.genx.framework.jpa.repository.GenericRepository;
import com.bluntsoftware.ReachOut.modules.reachout.domain.Contactinfotag;
import com.bluntsoftware.ReachOut.modules.reachout.repository.ContactinfotagRepository;
import com.genx.framework.jpa.repository.support.HqlBuilder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller("reachoutContactinfotagService")
@RequestMapping(value = "/reachout/contactinfotag")
@Transactional
@Qualifier("reachout")

public class ContactinfotagService extends CustomService<Contactinfotag,Integer, ContactinfotagRepository> {


    public Contactinfotag getOrCreate(Contactinfo contactInfo, Tag tag) {
        HqlBuilder hql = new HqlBuilder(Contactinfotag.class);
        hql.eq("tag",tag).eq("contactInfo",contactInfo);

        Contactinfotag infotag = repository.findOne(hql);

        if(infotag == null){
            infotag = new Contactinfotag();
            infotag.setContactInfo(contactInfo);
            infotag.setTag(tag);
            infotag = repository.saveAndUpdate(infotag);
        }
        return infotag;
    }

    public void removeTags(Contactinfo contactInfo) {
        HqlBuilder hql = new HqlBuilder(Contactinfotag.class);
        hql.eq("contactInfo",contactInfo);
        List<Contactinfotag> infotags = repository.findAll(hql);
        for(Contactinfotag infoTag:infotags){
            repository.delete(infoTag);
        }
    }
}
