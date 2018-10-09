package com.AS1.componentmanager;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface ComponentManagerI {
	
	@WebMethod
	String findModel();
	
	@WebMethod
	String addModel(int id,String name,String URL);
	
	@WebMethod
	String removeModel(int id);
	@WebMethod
	String findModelByName(String name);

}
