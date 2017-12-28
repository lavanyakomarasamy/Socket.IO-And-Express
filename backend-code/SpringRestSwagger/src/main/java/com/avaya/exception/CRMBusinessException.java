package com.avaya.exception;

public class CRMBusinessException extends Exception{

	String message;
	
	String svcName;

	CRMBusinessException(String message) {
		this.message=message;
	   }
	public CRMBusinessException(String message, String svcName) {
		this.message=message;
		this.svcName=svcName;
	   }
	   public String toString(){ 
		return ("Exception occurred in "+svcName+"-"+message) ;
	   }
}
