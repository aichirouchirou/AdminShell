
package com.AS1.dynamicParam;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.AS1.dynamicParamModel.MyDynamicParams;


@WebService
@SOAPBinding(style = Style.RPC)
public interface DynamicParam {
	

	@WebMethod
	boolean dynamicParamModel(MyDynamicParams params);
	

	@WebMethod
	boolean dynamicParamFileModel(String inputStream,String fileExtName);
	
	@WebMethod
	String  dynamicParamHandle();
	
	

	@WebMethod
	String  dynamicParamdistribute();
}





