package edu.swjtu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.swjtu.impl.ArrangeDaoImpl;
import edu.swjtu.impl.CarDaoImpl;
import edu.swjtu.impl.LineDaoImpl;
import edu.swjtu.model.ArrCarLine;
import edu.swjtu.model.Arrange;
import edu.swjtu.util.DBUtil;

public class ModifyArrServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//type==1:delete,==2:modify
		String type = request.getParameter("type");
		
		DBUtil db = new DBUtil();
		Connection con = null;

		try {
			PrintWriter pw = response.getWriter();
			con = db.getCon();
			ArrangeDaoImpl adi = new ArrangeDaoImpl();
			if(type.equals("1")){
				int id = Integer.parseInt(request.getParameter("arrId"));
				int re = adi.delArrById(con, id);
				if(1==re){
					pw.write("yes");
				}else{
					pw.write("no");
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != con) {
				try {
					db.closeCon(con);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
