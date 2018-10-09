package com.AS1.msmimpl;


import javax.jws.WebService;

import org.json.JSONObject;

import com.AS1.componentmanager.ComponentManager;
import com.AS1.msm.MachineStatusModel;
import com.AS1.staticparam.StaticParamModel;
import com.AS1.staticparam.StaticParamModelI;

@WebService(endpointInterface = "com.AS1.msm.MachineStatusModel")
public class MachineStatusModelImpl implements MachineStatusModel {

	String tempr; 
	String hum;
	String press;
	String status;
	public String getTempr() {
		return tempr;
	}

	public void setTempr(String tempr) {
		this.tempr = tempr;
	}

	public String getHum() {
		return hum;
	}

	public void setHum(String hum) {
		this.hum = hum;
	}

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public void getMachineStatus() {
		// TODO Auto-generated method stub
		this.tempr=String.valueOf((int)(Math.random()*100)); 
		this.hum=String.valueOf((int)(Math.random()*100));
		this.press=String.valueOf((int)(Math.random()*100));
	}

	public void dealStatusMessage() {
		// TODO Auto-generated method stub
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("tempr",this.tempr)
					.put("hum",this.hum)
						.put("press",this.press);
		StaticParamModelI spmi=new StaticParamModel();
	 String url=ComponentManager.class.getClassLoader().getResource("file/result.xml").getPath();
		String result=spmi.getResult(url);
		JSONObject jsonObject1 = new JSONObject(result);
		for(String key:jsonObject1.keySet()) {
			String value=(String) jsonObject1.get(key);
			jsonObject.put(key, value);
		}
		this.status=jsonObject.toString();
		System.out.println(this.status);
	}
	public String sendStatusMessage() {
		// TODO Auto-generated method stub
		this.getMachineStatus();
		this.dealStatusMessage();
		//StaticParamModelI spmi=new StaticParamModel();
		return this.status;
	}

}
