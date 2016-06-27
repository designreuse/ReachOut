package com.bluntsoftware.ReachOut.modules.user_manager.domain;

import com.genx.framework.jpa.domain.Domain;
import java.io.Serializable;

public interface CustomDomain<T> extends Domain,Serializable,Cloneable,Comparable<T> {

}
