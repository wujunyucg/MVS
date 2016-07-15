package edu.swjtu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.swjtu.impl.AdminDaoImpl;
import edu.swjtu.impl.ArrangeDaoImpl;
import edu.swjtu.impl.CarDaoImpl;
import edu.swjtu.impl.LineDaoImpl;
import edu.swjtu.model.Admin;
import edu.swjtu.model.Arrange;
import edu.swjtu.model.Car;
import edu.swjtu.model.ArrCarLine;
import edu.swjtu.model.Line;
import edu.swjtu.model.Powers;
import edu.swjtu.util.DBUtil;

public class ManageArrangeServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DBUtil db = new DBUtil();

		Connection con = null;

		int startPage = 1;
		int pageNum = 10;

		//type==1查看所有班次，==2按月查看
		String sp = request.getParameter("arrStartPage");
		String type = request.getParameter("type");

		if (null != sp) {
			startPage = Integer.parseInt(sp);
		}
		if (null == type) {
			type = "1";
		}
		int total = 0;
		int total_page = 1;

		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).substring(0, 7);
		try {
			HttpSession session = request.getSession();
			con = db.getCon();
			ArrangeDaoImpl adi = new ArrangeDaoImpl();
			ArrayList<Arrange> arrList = null;
			if (type.equals("1")) {
				date = "all";
				 arrList = adi.getPageArrange(con, startPage,
						pageNum);
				total = adi.getTotal(con);
			}else if(type.equals("2")){
				date = request.getParameter("date");
				//Calendar c = Calendar.getInstance();
				//int day = c.get(Calendar.DAY_OF_MONTH);
				//String d = date+"-"+day;
				arrList = adi.getPageMonthArr(con, startPage, pageNum, date);
				total = adi.getTotalByMonth(con, date);
				System.out.println("date="+date+" arr size:"+arrList.size()+" total="+total);
			}
			if (0 < total) {
				total_page = (total - 1) / pageNum + 1;// 总共的页数
			}
			ArrayList<ArrCarLine> arrData = new ArrayList<ArrCarLine>();
			CarDaoImpl cdi = new CarDaoImpl();
			LineDaoImpl ldi = new LineDaoImpl();
			for (Arrange arr : arrList) {
				arrData.add(new ArrCarLine(arr,cdi.getCarById(arr.getCarId(), con),
								ldi.getLineById(con, arr.getLineId())));
			}
			session.setAttribute("arr_date", date);
			session.setAttribute("arr_type", type);
			session.setAttribute("arr_data", arrData);
			session.setAttribute("arr_total", total_page);
			session.setAttribute("arrStartPage", startPage);
			response.sendRedirect("../jsp_user/manage_arrange.jsp");
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
