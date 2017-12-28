package com.avaya.util;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.avaya.model.*;

@Service("adapterdtmocker")
public class AvayaDataMocker {
	
	public Adapter getMockedAdapter(){
		Adapter adapter=new Adapter();
		adapter.setId("Newtin1190");
		adapter.setEventsUrl("www.newtglobal.com");
		return adapter;
	}
	

}
