package com.bluntsoftware.ReachOut.modules.user_manager.rest;

import com.genx.framework.jpa.domain.Domain;
import com.genx.framework.jpa.repository.GenericRepository;
import java.io.Serializable;
import com.genx.framework.jpa.rest.impl.JpaCRUDRestControllerImpl;
public abstract class CustomService <T extends Domain,ID extends Serializable, X extends GenericRepository<T,ID>> extends JpaCRUDRestControllerImpl<T,ID,X> {



}
