package com.AS1.staticparam;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface StaticParamModelI {
	
	@WebMethod
	String setParam(int id,String name,String norm_Status,
			String work_status,double tempera,String ip,String fileName);
	@WebMethod
	String getResult(String fileName);
}
