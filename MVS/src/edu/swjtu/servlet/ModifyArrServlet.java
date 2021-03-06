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

import edu.swjtu.excel.ExportArrExcel;
import edu.swjtu.impl.ArrangeDaoImpl;
import edu.swjtu.impl.CarDaoImpl;
import edu.swjtu.impl.LineDaoImpl;
import edu.swjtu.model.ArrCarLine;
import edu.swjtu.model.Arrange;
import edu.swjtu.model.Car;
import edu.swjtu.model.Line;
import edu.swjtu.util.DBUtil;

public class ModifyArrServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// type==1:delete,==2:modify,==3:export,4:multi-del
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
			} else if (type.equals("2")) {
				// 修改
				PrintWriter pw = response.getWriter();
				int id = Integer.parseInt(request.getParameter("id"));
				String name = request.getParameter("name");
				String date = request.getParameter("date");
				String time = request.getParameter("time");

				Arrange arr = adi.getArrById(id, con);

				if (null != arr) {
					arr.setName(name);
					arr.setDate(date);
					arr.setTime(time);
					int re = adi.updateArr(con, arr);
					if (re > 0) {
						pw.write("yes");
					} else {
						pw.write("no");
					}
				} else {
					pw.write("no");
				}
				pw.close();
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
					Car c = cdi.getCarById(arr.getCarId(), con);
					if (null == c) {
						c = new Car();
						c.setLicensePlate("未知");
						c.setDriver("未知");
					}
					Line l = ldi.getLineById(con, arr.getLineId());
					if (null == l) {
						l = new Line();
						l.setName("线路");
					}
					data.add(new ArrCarLine(arr, c, l));
				}
				// export
				OutputStream out = response.getOutputStream();
				response.reset();
				// 设置导出Excel报表的导出形式
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition",
						"attachment;filename=" + date + ".xls");
				ExportArrExcel<ArrCarLine> ex = new ExportArrExcel<ArrCarLine>();
				String[] headers = { "arrid", "班次名称", "日期", "发车时间", "线路名称",
						"车牌号", "司机" };
				ex.export("班次表", headers, data, out);
				out.flush();
				out.close();
			} else if (type.equals("4")) {
				PrintWriter pw = response.getWriter();
				String[] ids = request.getParameter("multiDelIds").split(",");
				System.out.println(ids.length);
				int cnt = 0;
				for (int i = 0; i < ids.length; i++) {
					cnt += adi.delArrById(con, Integer.parseInt(ids[i]));
				}
				pw.write(cnt + "");
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
