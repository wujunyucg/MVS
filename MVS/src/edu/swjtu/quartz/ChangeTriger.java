package edu.swjtu.quartz;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ChangeTriger {
	public int changeTime(String st,String xmlPath){
		xmlPath = xmlPath + "WEB-INF/classes/quartz_job.xml";
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        dbf.setIgnoringElementContentWhitespace(true);
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(xmlPath); // 使用dom解析xml文件
            NodeList sonlist = doc.getElementsByTagName("cron"); 
            int flag = 0;
            Element son = null;
            for (int i = 0; i < sonlist.getLength(); i++) // 循环处理对象
            {
                son = (Element)sonlist.item(i);;
                
                for (Node node = son.getFirstChild(); node != null; node = node.getNextSibling()){  
                    if (node.getNodeType() == Node.ELEMENT_NODE ){  
                        String name = node.getNodeName();  
                        String value = node.getFirstChild().getNodeValue();
                        if(name.equals("name") && value.equals("triger1"))
                        {
                        	flag = 1;
                        	break;
                        }
                    }  
                }  
               if(flag == 1)
            	   break;
            }
            if(flag==1){
            	if(st.equals("day"))
            		son.getElementsByTagName("cron-expression").item(0).setTextContent("0 5 0 * * ?");
            	else if(st.equals("week"))
            		son.getElementsByTagName("cron-expression").item(0).setTextContent("0 5 0 ? * MON");//"0 5 0 ? * MON"
            	else if(st.equals("month"))
            		son.getElementsByTagName("cron-expression").item(0).setTextContent("0 5 0 1 * ?");//"0 5 0 1 * ?"
            	TransformerFactory factory = TransformerFactory.newInstance();
                Transformer former = factory.newTransformer();
                former.transform(new DOMSource(doc), new StreamResult(new File(xmlPath)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return 0;


		
	}
}
