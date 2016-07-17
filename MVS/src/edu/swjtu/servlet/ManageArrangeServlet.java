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

import edu.swjtu.excel.InportArrExcel;
import edu.swjtu.file.FileUpload;
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

		//type==1查看所有班次，==2按月查看,==3代表导入的文件解析结果
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

		System.out.println("type="+type);
		boolean isUpload = false;//判断是否是文件上传的数据
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).substring(0, 7);
		try {
			HttpSession session = request.getSession();
			con = db.getCon();
			ArrangeDaoImpl adi = new ArrangeDaoImpl();
			CarDaoImpl cdi = new CarDaoImpl();
			LineDaoImpl ldi = new LineDaoImpl();
			ArrayList<Arrange> arrList = null;
			if(type.equals("3")){
				FileUpload fl = new FileUpload();
				int re = fl.upload(request);
				String path = fl.getAllPath();
				System.out.println("path:"+path);
				ArrayList<ArrCarLine> al = new InportArrExcel().inport(path);
				System.out.println(al.size()+"=size");
				
				///解析并存到数据库
				//////////////////////////////
				
				for(ArrCarLine acl : al){
					
					Arrange arr = new Arrange();
					arr.setDate(acl.getDate());
					arr.setTime(acl.getTime());
					arr.setName(acl.getArrName());
					Car car = cdi.getCarByLicensePlate(acl.getLicensePlate(), con);
					if(null==car){
						arr.setCarId(1);
					}else{
						arr.setCarId(car.getCarId());
					}
					Line line = ldi.getLineByName(con, acl.getLineName());
					if(null==line){
						arr.setLineId(1);
					}else{
						arr.setLineId(line.getLineId());
					}
					adi.addArr(arr, con);
				}
				//解析完删除文件
				fl.delOneFile(path);
				response.getWriter().write("yes");
			}else{
				
				if (type.equals("1")) {
					date = "all";
					arrList = adi.getPageArrange(con, startPage, pageNum);
					total = adi.getTotal(con);
				} else if (type.equals("2")) {
					date = request.getParameter("date");
					arrList = adi
							.getPageMonthArr(con, startPage, pageNum, date);
					total = adi.getTotalByMonth(con, date);
					System.out.println("date=" + date + " arr size:"
							+ arrList.size() + " total=" + total);
				}
				if (0 < total) {
					total_page = (total - 1) / pageNum + 1;// 总共的页数
				}
				ArrayList<ArrCarLine> arrData = new ArrayList<ArrCarLine>();
				for (Arrange arr : arrList) {
					arrData.add(new ArrCarLine(arr, cdi.getCarById(
							arr.getCarId(), con), ldi.getLineById(con,
							arr.getLineId())));
				}
				session.setAttribute("arr_date", date);
				session.setAttribute("arr_type", type);
				session.setAttribute("arr_data", arrData);
				session.setAttribute("arr_total", total_page);
				session.setAttribute("arrStartPage", startPage);
				request.getRequestDispatcher("../jsp_user/manage_arrange.jsp")
						.forward(request, response);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
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
