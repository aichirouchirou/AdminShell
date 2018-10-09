package com.AS1.staticparam;

import java.io.IOException;

import javax.jws.WebService;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@WebService(endpointInterface = "com.AS1.staticparam.StaticParamModelI")
public class StaticParamModel implements StaticParamModelI{

	public String setParam(int id, String name, String norm_Status, String work_status, double tempera, String ip,
			String fileName){
		// TODO Auto-generated method stub
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document document=db.newDocument();
			document.setXmlStandalone(true);
			Element root=document.createElement("Admin_Shell"),
					ID=document.createElement("id"),
					Name=document.createElement("name"),
					Norm_Status=document.createElement("size"),
					Work_status=document.createElement("vendor"),
					Tempera=document.createElement("weight"),
					Ip=document.createElement("IP");
			Name.setTextContent(name);
			ID.setTextContent(""+id);
			Norm_Status.setTextContent(norm_Status);
			Work_status.setTextContent(work_status);
			Tempera.setTextContent(""+tempera);
			Ip.setTextContent(ip);
			
			root.appendChild(ID);
			root.appendChild(Name);
			root.appendChild(Norm_Status);
			root.appendChild(Work_status);
			root.appendChild(Tempera);
			root.appendChild(Ip);
			document.appendChild(root);
			TransformerFactory tff=TransformerFactory.newInstance();
			Transformer tf;
			try {
				tf = tff.newTransformer();
				try {
					tf.transform(new DOMSource(document), new StreamResult(fileName));
				} catch (TransformerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (TransformerConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "Save Parameter Successful!!";
	}

	public String getResult(String fileName) {
		// TODO Auto-generated method stub
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		JSONObject jsonObject = new JSONObject();
		try {
			db = dbf.newDocumentBuilder();
			Document document;
			try {
					document = db.parse(fileName);
					NodeList adminList=document.getElementsByTagName("Admin_Shell");
					
					for(int i=0;i<adminList.getLength();i++)
					{
						//获取第i个结点
						Node node=adminList.item(i);
						//获取第i个结点的所有属性
						//NamedNodeMap namedNodeMap=node.getAttributes();
						//获取admin_shell的子节点
						NodeList cList=node.getChildNodes();
						for(int j=0;j<cList.getLength();j++)
						{
							Node cNode=cList.item(j);
							String name=cNode.getNodeName();
							String content=cNode.getFirstChild().getTextContent();
							jsonObject.put(name, content);
						}
	
					}
				 
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String result=jsonObject.toString();
		return result;
	}


}
