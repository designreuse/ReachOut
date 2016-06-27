package com.bluntsoftware.ReachOut.modules.reachout.rest;



import com.genx.framework.jpa.repository.GenericRepository;
import com.bluntsoftware.ReachOut.modules.reachout.domain.Address;
import com.bluntsoftware.ReachOut.modules.reachout.repository.AddressRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
@Controller("reachoutAddressService")
@RequestMapping(value = "/reachout/address")
@Transactional
@Qualifier("reachout")

public class AddressService extends CustomService<Address,Integer, AddressRepository> {


}
