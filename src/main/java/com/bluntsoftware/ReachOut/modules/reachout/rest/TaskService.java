package com.bluntsoftware.ReachOut.modules.reachout.rest;



import com.genx.framework.jpa.repository.GenericRepository;
import com.bluntsoftware.ReachOut.modules.reachout.domain.Task;
import com.bluntsoftware.ReachOut.modules.reachout.repository.TaskRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
@Controller("reachoutTaskService")
@RequestMapping(value = "/reachout/task")
@Transactional
@Qualifier("reachout")

public class TaskService extends CustomService<Task,Integer, TaskRepository> {


}
