package com.AS1.componentmanager;

import java.io.File;
import java.io.FileOutputStream;
//import java.io.IOException;
import java.io.IOException;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.jws.WebService;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
//import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


@WebService(endpointInterface = "com.AS1.componentmanager.ComponentManagerI")
public class ComponentManager implements ComponentManagerI {
  
	public String findModel() {
		// TODO Auto-generated method stub
		String res="";
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		JsonObjectBuilder objectbuilder=Json.createObjectBuilder();
		try {
			db = dbf.newDocumentBuilder();
			Document document;
			try {
					String url=ComponentManager.class.getClassLoader().getResource("file/manifest.xml").getPath();
					if(url!=null)
					{
						document = db.parse(url);
						NodeList adminList=document.getElementsByTagName("sub_model");
						
						for(int i=0;i<adminList.getLength();i++)
						{
							
							Node node=adminList.item(i);
							NodeList cList=node.getChildNodes();
							for(int j=0;j<cList.getLength();j++)
							{
								Node cNode=cList.item(j);
								if(cNode instanceof Element)
								{
									String name=cNode.getNodeName();
									//String content=cNode.getFirstChild().getTextContent();
									String content=cNode.getTextContent();
									objectbuilder.add(name, content);
								}
							}
							res+=objectbuilder.build().toString();
							res+="\n";
		
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
		
		
		//String result=objectbuilder.build().toString();
		return res;
	}

	public String addModel(int id, String name, String URL) {
		// TODO Auto-generated method stub
		String url=ComponentManager.class.getClassLoader().getResource("file/manifest.xml").getPath();
		File file = new File(url);
		//db = dbf.newDocumentBuilder();
		//document = db.parse(file);
		//Document document=db.newDocument();
		//document.setXmlStandalone(true);
		if(file.exists()) {
			try {
				DOMAdd(file,id,name,URL);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else
			try {
				DOMcreate(file,id,name,URL);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
	      return "generate successful!";
	}
public  void DOMcreate(File file,int id, String name2, String uRL)throws Exception{
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		db = dbf.newDocumentBuilder();
		Document document=db.newDocument();
		//Document document=db.parse(file);
		document.setXmlStandalone(true);
        Element root=document.createElement("submodel_list");
           Element submodel=document.createElement("sub_model");
            Element ID=document.createElement("id"),
                    name=document.createElement("name"),
                    URL=document.createElement("URL");
            //submodel.setAttribute("id", "1");
                //name.setTextContent("��"+"1");
                //age.setTextContent(""+5);
               // grade.setTextContent(""+20);
            ID.setTextContent(""+id);
            name.setTextContent(name2);
            URL.setTextContent(uRL);
            submodel.appendChild(ID);
            submodel.appendChild(name);
            submodel.appendChild(URL);
            root.appendChild(submodel);
        document.appendChild(root);     
        TransformerFactory tff=TransformerFactory.newInstance();
        Transformer tf=tff.newTransformer();
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        tf.transform(new DOMSource(document), new StreamResult(file));
    }
	
public void DOMAdd(File file,int id, String name2, String uRL)throws Exception {
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		db = dbf.newDocumentBuilder();
		//Document document=db.newDocument();
		Document document=db.parse(file);
		document.setXmlStandalone(true);
	try {
        	 //Element root=document.createElement("submodel_list");
             Element submodel=document.createElement("sub_model");
              Element ID=document.createElement("id"),
                      name=document.createElement("name"),
                      URL=document.createElement("URL");
              ID.setTextContent(""+id);
              name.setTextContent(name2);
              URL.setTextContent(uRL);
           /* student.setAttribute("id", "215");
            name.setTextContent("��"+"211");
            age.setTextContent(""+10);
            grade.setTextContent(""+30);
            student.appendChild(name);
            student.appendChild(age);
            student.appendChild(grade);*/
            submodel.appendChild(ID);
            submodel.appendChild(name);
            submodel.appendChild(URL);
            document.getDocumentElement().appendChild(submodel);    
            TransformerFactory tff=TransformerFactory.newInstance();
            Transformer tf=tff.newTransformer();
            DOMSource domSource = new DOMSource(document);
            tf.setOutputProperty(OutputKeys.ENCODING, "utf-8");
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            StreamResult result = new StreamResult(new FileOutputStream(file));
            tf.transform(domSource, result);
	}catch(Exception e){
		e.printStackTrace();
	}
	}

	public String removeModel(int id) {
		// TODO Auto-generated method stub
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document document;
			try {
				    String url=ComponentManager.class.getClassLoader().getResource("file/manifest.xml").getPath();
					document = db.parse(url);
					NodeList adminList=document.getElementsByTagName("sub_model");
					
					Element elink=(Element)adminList.item(id);
					elink.removeChild(elink.getElementsByTagName("id").item(0));
					elink.removeChild(elink.getElementsByTagName("name").item(0));
					elink.removeChild(elink.getElementsByTagName("URL").item(0));
					document.getFirstChild().removeChild(elink);
					
					/*for(int i=0;i<adminList.getLength();i++)
					{
						Node node=adminList.item(i);
						NodeList cList=node.getChildNodes();
						Node cNode=cList.item(0);
					    if(cNode.getTextContent()==id)
						{
					    	Element elink=(Element)adminList.item(i);
							elink.removeChild(elink.getElementsByTagName("id").item(0));
							elink.removeChild(elink.getElementsByTagName("name").item(0));
							elink.removeChild(elink.getElementsByTagName("URL").item(0));
							document.getFirstChild().removeChild(elink);
						}
						
					}*/
					 TransformerFactory tff=TransformerFactory.newInstance();
			         Transformer tf;
						try {
							tf = tff.newTransformer();
							 DOMSource domSource = new DOMSource(document);
					            tf.setOutputProperty(OutputKeys.ENCODING, "utf-8");
					            tf.setOutputProperty(OutputKeys.INDENT, "yes");
					            StreamResult result = new StreamResult(new FileOutputStream(url));
					            try {
									tf.transform(domSource, result);
								} catch (TransformerException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
						} catch (TransformerConfigurationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
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
		
		return "remove successful!";
	}
	
	public String findModelByName(String name) {
		// TODO Auto-generated method stub
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		String res="";
		try {
			db = dbf.newDocumentBuilder();
			Document document;
			try {
				String url=ComponentManager.class.getClassLoader().getResource("file/manifest.xml").getPath();
				document = db.parse(url);
				NodeList adminList=document.getElementsByTagName("sub_model");
				for(int i=0;i<adminList.getLength();i++)
				{
					Node node=adminList.item(i);
					NodeList cList=node.getChildNodes();
					if(cList.item(3).getTextContent().equalsIgnoreCase(name))
					{
						res+=cList.item(5).getTextContent();
					}
					/*for(int j=0;j<cList.getLength();j++)
					{
						Node cNode=cList.item(j);
						if(cNode instanceof Element)
						{
							String name1=cNode.getNodeName();
							if(name1.equals("URL"))
						//String content=cNode.getFirstChild().getTextContent();
							{
								String content=cNode.getTextContent();
								res+=content;
								res+=";";
							}
						}
					}*/
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
		/*String ress[]=res.split("\\;");
		String str="";
		for(String s:ress)
		{
			if(s.contains(name))
				return s;
		}*/
		return res;
	}

}
