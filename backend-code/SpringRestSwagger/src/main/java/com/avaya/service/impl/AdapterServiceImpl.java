package com.avaya.service.impl;

import org.springframework.stereotype.Service;

import com.avaya.model.*;
import com.avaya.service.AdapterService;

/**
 * @author prabhakaranm
 *
 */
@Service("adapterSvc")
public class AdapterServiceImpl implements AdapterService{
	
	/* This service class is used to create Adapter mockup
	 * 
	 * (non-Javadoc)
	 * @see com.avaya.service.AdapterService#getMockedAdapter()
	 */
	@Override
	public Adapter getMockedAdapter(){
		Adapter adapter=new Adapter();
		adapter.setId("Newtin1190");
		adapter.setEventsUrl("www.newtglobal.com");
		return adapter;
	}
	
	/* 
	 * This service class is used by agent to login
	 * (non-Javadoc)
	 * @see com.avaya.service.AdapterService#agentLoginCommand()
	 */
	@Override
	public ModelApiResponse agentLoginCommand(){
		ModelApiResponse apiResp=new ModelApiResponse();
		apiResp.setCode(1);
		apiResp.setMessage("Login Successfull");
		apiResp.setType("LoginMessage");
		return apiResp;
	}
	
	/* 
	 * This service class is used to delete adapter
	 * (non-Javadoc)
	 * @see com.avaya.service.AdapterService#deleteAdapter()
	 */
	@Override
	public String deleteAdapter(){
		
		return "200";
	}
	
	/* 
	 * This service class is used to fetch reason codes
	 * (non-Javadoc)
	 * @see com.avaya.service.AdapterService#fetchReason codes()
	 */
	@Override
	public String fetchReasonCodes(){
		
		return "{ 200 }";
	}
	
	/* 
	 * This service class is used to start adapter
	 * (non-Javadoc)
	 * @see com.avaya.service.AdapterService#adapterstartcommand()
	 */
	@Override
	public ModelApiResponse adapterStartCommand(){
		ModelApiResponse apiResp=new ModelApiResponse();
		apiResp.setCode(1);
		apiResp.setMessage("Adapter Started");
		apiResp.setType("StartAdapter");
		return apiResp;
	}
	
	/* 
	 * This service class is used to stop adapter
	 * (non-Javadoc)
	 * @see com.avaya.service.AdapterService#adapterstopcommand()
	 */
	@Override
	public ModelApiResponse adapterStopCommand(){
		ModelApiResponse apiResp=new ModelApiResponse();
		apiResp.setCode(1);
		apiResp.setMessage("Adapter Sopped");
		apiResp.setType("StopAdapter");
		return apiResp;
	}
	/* This service class is used by agent to logout
	 * 
	 * (non-Javadoc)
	 * @see com.avaya.service.AdapterService#agentLogoutCommand()
	 */
	@Override
	public ModelApiResponse agentLogoutCommand(){
		ModelApiResponse apiResp=new ModelApiResponse();
		apiResp.setCode(1);
		apiResp.setMessage("Logout Successfull");
		apiResp.setType("LogoutMessage");
		return apiResp;
	}
}
