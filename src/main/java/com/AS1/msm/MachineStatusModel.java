package com.AS1.msm;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface MachineStatusModel {
	@WebMethod
	public void getMachineStatus();
	@WebMethod
	public void dealStatusMessage();
	@WebMethod
	public String sendStatusMessage();
}
