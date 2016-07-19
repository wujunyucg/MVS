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

import edu.swjtu.quartz.ChangeTriger;

public class QuartzApplicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public QuartzApplicServlet() {
        super();
       
    }
    public void init() {
    	 ServletContext application = this.getServletContext();
    	ChangeTriger ct = new ChangeTriger();
    	String type = ct.getTime();
       application.setAttribute("quartz_type", type);    

    	 }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
