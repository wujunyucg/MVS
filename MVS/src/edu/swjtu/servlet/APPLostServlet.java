package edu.swjtu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import edu.swjtu.impl.CarDaoImpl;
import edu.swjtu.impl.LostDaoImpl;
import edu.swjtu.impl.StaffDaoImpl;
import edu.swjtu.model.Car;
import edu.swjtu.model.Lost;
import edu.swjtu.model.Staff;
import edu.swjtu.util.DBUtil;
import edu.swjtu.util.DateUtil;

public class APPLostServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		
		String type = request.getParameter("type");
		
		DBUtil db = new DBUtil();
		Connection con = null;
		PrintWriter pw = response.getWriter();
		
		if(null==type){
			pw.write("no");
			return;
		}
		try {
			con = db.getCon();
			LostDaoImpl ldi = new LostDaoImpl();
			//添加lost
			if(type.equals("1")){
				String name = request.getParameter("name");
				String content = request.getParameter("content");
				String contact = request.getParameter("contact");
				String ltype = request.getParameter("ltype");
				String date = DateUtil.getDateTime();
				Lost lost = new Lost();
				lost.setContent(content);
				lost.setName(name);
				lost.setDate(date);
				lost.setContact(contact);
				lost.setType(ltype);
				int rs = ldi.addOneLost(con, lost);
				if(rs>0){
					pw.write("yes");
				}else{
					pw.write("no");
				}
			}else if(type.equals("2")){
				String start = request.getParameter("startPage");
				int pageNum = 10;
				int sp = Integer.parseInt(start);
				ArrayList<Lost> list = ldi.getPageLost(con, sp, pageNum);
				if(list.size()==0){
					pw.write("nomore");
				}else{
					JSONArray array = new JSONArray();
					for(Lost l:list){
						JSONObject ob = new JSONObject();
						ob.put("name", l.getName());
						ob.put("content", l.getContent());
						ob.put("date", l.getDate());
						ob.put("contact",l.getContact());
						array.add(ob);
					}
					//System.out.println(array.toString());
					pw.write(array.toString());
				}
			}else if(type.equals("3")){
				//失物招领
				String start = request.getParameter("startPage");
				int pageNum = 10;
				int sp = Integer.parseInt(start);
				ArrayList<Lost> list = ldi.getPageLost(con, sp, pageNum,"0");
				if(list.size()==0){
					pw.write("nomore");
				}else{
					JSONArray array = new JSONArray();
					for(Lost l:list){
						JSONObject ob = new JSONObject();
						ob.put("name", l.getName());
						ob.put("content", l.getContent());
						ob.put("date", l.getDate());
						ob.put("contact",l.getContact());
						array.add(ob);
					}
					//System.out.println(array.toString());
					pw.write(array.toString());
				}
			}else if(type.equals("4")){
				//寻物启事
				String start = request.getParameter("startPage");
				int pageNum = 10;
				int sp = Integer.parseInt(start);
				ArrayList<Lost> list = ldi.getPageLost(con, sp, pageNum,"1");
				if(list.size()==0){
					pw.write("nomore");
				}else{
					JSONArray array = new JSONArray();
					for(Lost l:list){
						JSONObject ob = new JSONObject();
						ob.put("name", l.getName());
						ob.put("content", l.getContent());
						ob.put("date", l.getDate());
						ob.put("contact",l.getContact());
						array.add(ob);
					}
					//System.out.println(array.toString());
					pw.write(array.toString());
				}
			}
			pw.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally{
			if(null!=con){
				try {
					db.closeCon(con);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
