package com.bluntsoftware.ReachOut.modules.reachout.domain;

import com.genx.framework.jpa.domain.Domain;
import java.io.Serializable;

public interface CustomDomain<T> extends Domain,Serializable,Cloneable,Comparable<T> {

}
