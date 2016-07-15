package edu.swjtu.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
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

import edu.swjtu.file.ExportArrExcel;
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
		// type==1:delete,==2:modify,==3:export
		String type = request.getParameter("deltype");

		DBUtil db = new DBUtil();
		Connection con = null;

		try {
			con = db.getCon();
			ArrangeDaoImpl adi = new ArrangeDaoImpl();
			if (type.equals("1")) {
				PrintWriter pw = response.getWriter();
				int id = Integer.parseInt(request.getParameter("arrId"));
				int re = adi.delArrById(con, id);
				if (1 == re) {
					pw.write("yes");
				} else {
					pw.write("no");
				}
			} else if (type.equals("3")) {// 导出
				String dt = request.getParameter("arr_date");
				String date = request.getSession().getAttribute("arr_date")
						.toString();
				System.out.println("export-date:" + date + " dt:" + dt);
				int total = 0;
				ArrayList<ArrCarLine> data = new ArrayList<ArrCarLine>();
				ArrayList<Arrange> arrList = null;
				if (date.equals("all")) {
					// 导出所有
					arrList = adi.getAllArrange(con);
				} else {
					// 导出一个月的班次
					arrList = adi.getAllMonthArr(con, date);
				}
				// 合成数据
				CarDaoImpl cdi = new CarDaoImpl();
				LineDaoImpl ldi = new LineDaoImpl();
				for (Arrange arr : arrList) {
					 data.add(new
					 ArrCarLine(arr,cdi.getCarById(arr.getCarId(), con),
					 ldi.getLineById(con, arr.getLineId())));
				}
				// export
				OutputStream out = response.getOutputStream();
				response.reset();
				//设置导出Excel报表的导出形式
			    response.setContentType("application/vnd.ms-excel");
			    response.setHeader("Content-Disposition","attachment;filename="+date+".xls");
				ExportArrExcel<ArrCarLine> ex = new ExportArrExcel<ArrCarLine>();
				String[] headers = { "arrid", "班次名称", "日期", "发车时间", "线路名称",
						"车牌号", "司机" };
				ex.export("班次表", headers, data, out);
				out.flush();
				out.close();
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
