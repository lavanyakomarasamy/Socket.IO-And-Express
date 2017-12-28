package com.avaya.controller;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.avaya.exception.CRMBusinessException;
import com.avaya.model.Adapter;
import com.avaya.model.AgentHandleLoginState;
import com.avaya.model.AgentLogoutReason;
import com.avaya.model.ModelApiResponse;
import com.avaya.service.AdapterService;
import com.avaya.threading.RestThreadExecuter;
import com.avaya.util.AvayaDataMocker;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin
@RequestMapping(value="/api")
@Api(value="Controller Service")
public class AdapterController
{
	@Autowired
	AdapterService adapterSvc;

	@Autowired
	AvayaDataMocker adapterdtmocker;

	static Logger log = Logger.getLogger("com.avaya");

	/**
	 * This method will create an Adapter session and return a adapter id
	 * 
	 * @author prabhakaranm
	 * @return String
	 * 
	 * @throws JsonProcessingException
	 */

	@ApiOperation(value = "Create adapters", notes = "Returns Response Of adapters Creation. SLA:500", response = String.class)
	 @ApiResponses(value = {
	   @ApiResponse(code = 200, message = "Successfully Created adapters", response = String.class),
	   @ApiResponse(code = 404, message = "Creation Of adapters Failed"),
	   @ApiResponse(code = 400, message = "Invalid Input Provided") })
	@RequestMapping(value = "/adapters", method = RequestMethod.POST, produces = "application/json")
	@CrossOrigin(origins = { "*" })
	public @ResponseBody String createAdapterSession() {
		try{
			log.info("Inside create Adapter Session");
		
			ObjectMapper mapper = new ObjectMapper();
			Adapter adapter = adapterSvc.getMockedAdapter();
			String jsonInString = mapper.writeValueAsString(adapter);
			log.info("Exit create Adapter Session");
			// instantiate the pool
			ExecutorService pool=Executors.newSingleThreadExecutor();
			// run the task to execute in parallel, specificying the subset of rows
			pool.execute(new RestThreadExecuter(200,"Success"));
			return jsonInString;
		}catch (Exception e){
			log.severe("Exception Inside create Adapter Session");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * This method is used by an agent to login
	 * 
	 * @param loginState
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "agents/{agentId}/loginCommand", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody String loginCommand(@PathVariable("agentId") String agentId,@RequestBody AgentHandleLoginState loginStateParam)
			 {
		try {
			log.info("Inside login command" +agentId);
			log.info(loginStateParam.getLoginState());
			ObjectMapper mapper = new ObjectMapper();
			ModelApiResponse modApiResp = adapterSvc.agentLoginCommand();
			String responseJson = convertObjtoJson(modApiResp);
			log.info("Exit login command");
			return responseJson;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * This method is used by an agent to delete adapter
	 * 
	 * @param loginState
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "adapters/{adapterId}", method = RequestMethod.DELETE, produces = "text/xml")
	public @ResponseBody String deleteAdapter(@PathVariable("adapterId") String adapterId)
			 {
		try {
			log.info("Inside Delete Adapter command" +adapterId);
			String response = adapterSvc.deleteAdapter();
			log.info("Exit Delete Adapter command");
			return response;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * This method is used to fetch reason codes for an adapter
	 * 
	 * @param adapterId
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/adapters/{adapterId}/reasonCodes", method = RequestMethod.GET, produces = "text/xml")
	public String fetchReasonCodes(@PathVariable("adapterId") String adapterId)
			 {
		try {
			log.info("Inside fetch Reason Codes" +adapterId);
			String response = adapterSvc.fetchReasonCodes();
			log.info("Exit fetch Reason Codes");
			return response;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * This method is used by an agent to logout
	 * 
	 * @param myParams
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "agents/{agentId}/logoutCommand", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody String logoutCommand(@PathVariable("agentId") String agentId,@ModelAttribute AgentLogoutReason myParams)
		 {
		try {
			log.info("Inside logout command");
			log.info(myParams.getLogoutReason());
			log.info(agentId);
			ModelApiResponse modApiResp = adapterSvc.agentLogoutCommand();
			String responseJson = convertObjtoJson(modApiResp);
			log.info("Exit logout command");
			return responseJson;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * This method is used to start a adapter connection
	 * 
	 * @param myParams
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/adapters/{adapterId}/startCommand", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody String startCommand(@PathVariable("adapterId") String adapterId)
		 {
		try {
			log.info("Inside start command");
			log.info(adapterId);
			ModelApiResponse modApiResp = adapterSvc.adapterStartCommand();
			String responseJson = convertObjtoJson(modApiResp);
			log.info("Exit logout command");
			return responseJson;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * This method is used to stop a adapter connection
	 * 
	 * @param adapterId
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/adapters/{adapterId}/stopCommand", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody String stopCommand(@PathVariable("adapterId") String adapterId)
			{
		try {
			log.info("Inside stop adapter command");
			log.info(adapterId);
			ModelApiResponse modApiResp = adapterSvc.adapterStopCommand();
			String responseJson = convertObjtoJson(modApiResp);
			log.info("Exit stop adapter command");
			return responseJson;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * This method is used to convert Object to JSON\
	 * 
	 * @param Object
	 * @return String
	 * @throws JsonProcessingException
	 */
	private String convertObjtoJson(Object modelObj){
		String jsonInString=null;
		try{
		ObjectMapper mapper = new ObjectMapper();
		jsonInString = mapper.writeValueAsString(modelObj);
		return jsonInString;}
		catch(Exception e){
			log.severe("Error in convertObjtoJson with model-"+jsonInString);
			return null;
		}

	}
}
