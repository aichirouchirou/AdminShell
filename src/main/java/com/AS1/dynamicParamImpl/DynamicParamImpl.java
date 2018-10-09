
package com.AS1.dynamicParamImpl;

import java.io.IOException;

import javax.jws.WebService;

import com.AS1.dynamicParam.DynamicParam;
import com.AS1.dynamicParamModel.MyDynamicParams;
import com.AS1.util.InfoSave;


@WebService(endpointInterface = "com.AS1.dynamicParam.DynamicParam")
public class DynamicParamImpl implements DynamicParam{

		//预热温度
		public String param1;
		//温度控制范围
		public String param2;
		//波峰1浸锡时间
		public String param3;
		//波峰2浸锡时间
		public String param4;
		//夹角速度
		public String param5;
		//夹角倾度
		public String param6;
		//助锡剂喷雾压力
		public String param7;
		//针阀压力
		public String param8;
	
	
	public boolean dynamicParamModel(MyDynamicParams params) {
		boolean saveParameters=InfoSave.saveParametersInfo(params.getParam1(), params.getParam2(), params.getParam3(), 
				params.getParam4(), params.getParam5(), params.getParam6(), params.getParam7(), params.getParam6());
		
		if(saveParameters){
			System.out.println("参数保存成功");
			return true;
		}else{
			System.out.println("参数保存失败");
			return false;
		}
	}

	
	public boolean dynamicParamFileModel(String inputStream, String fileExtName) {
		boolean result = false;
		try {
			result = InfoSave.saveFileInfo(inputStream,fileExtName);
			System.out.println("保存文件的状态"+result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;	
	}


	
	public String dynamicParamHandle() {
		//上传参数处理
		return "WebService处理了参数";
	}


	
	public String dynamicParamdistribute() {
		
		return "下发操作";
	}

}
