package edu.swjtu.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

public class QuartzApplicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public QuartzApplicServlet() {
        super();
       
    }
    public void init() {
    	  ServletContext application = this.getServletContext();
    	String  xmlPath ="F:/MyEclipse 2015/apache-tomcat-8.0.33/webapps/MVS/WEB-INF/classes/quartz_job.xml";
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
            	  application.setAttribute("quartz_type", son.getElementsByTagName("cron-expression").item(0).getTextContent());
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
    	 

    	 }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
