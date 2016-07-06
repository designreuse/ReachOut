package com.bluntsoftware.ReachOut.modules.reachout.rest;



import com.genx.framework.jpa.repository.GenericRepository;
import com.bluntsoftware.ReachOut.modules.reachout.domain.Tag;
import com.bluntsoftware.ReachOut.modules.reachout.repository.TagRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
@Controller("reachoutTagService")
@RequestMapping(value = "/reachout/tag")
@Transactional
@Qualifier("reachout")

public class TagService extends CustomService<Tag,Integer, TagRepository> {


}
