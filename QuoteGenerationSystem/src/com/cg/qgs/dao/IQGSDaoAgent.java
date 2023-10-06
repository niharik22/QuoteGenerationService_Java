package com.cg.qgs.dao;

import java.util.List;

import com.cg.qgs.exception.QGSException;
import com.cg.qgs.model.Policy;

public interface IQGSDaoAgent {

	List<Policy> quickViewPolicyAgent(String username) throws QGSException;

}
