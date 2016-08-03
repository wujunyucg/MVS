package edu.swjtu.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.swjtu.impl.CarDaoImpl;
import edu.swjtu.impl.DayRecordDaoImpl;
import edu.swjtu.impl.SiteDaoImpl;
import edu.swjtu.impl.StaffDaoImpl;
import edu.swjtu.model.Car;
import edu.swjtu.model.DayRecord;
import edu.swjtu.model.Lines;
import edu.swjtu.model.Site;
import edu.swjtu.model.Staff;
import edu.swjtu.util.DBUtil;
import edu.swjtu.util.DateUtil;

public class APPLocationServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");

		String type = request.getParameter("type");
		String name = request.getParameter("name");
		String location = request.getParameter("location");
		System.out.println("name=" + name);

		DBUtil db = new DBUtil();
		Connection con = null;
		PrintWriter pw = response.getWriter();

		ServletContext application = this.getServletContext();

		if (null == type) {
			pw.write("no");
			return;
		}
		try {
			con = db.getCon();
			SiteDaoImpl sdi = new SiteDaoImpl();
			StaffDaoImpl fdi = new StaffDaoImpl();
			// 员工
			if (type.equals("1")) {
				String s = "";
				if (null != application.getAttribute("driver")) {
					String loca = application.getAttribute("driver").toString();
					s = "30.965207,103.089339";
				} else {
					s = "no";
				}
				if (null == name) {
					name = "not a number";
					System.out.println(name);
				}
				Staff sf = fdi.getStaffByNumberS(name, con);
				if (null != sf) {
					System.out.println(name);
					Site site = sdi.getSiteById(sf.getSiteId(), con);
					if (null != site) {
						s += "," + site.getLatitude() + ","
								+ site.getLongitude();
					} else {
						s += ",no";
					}
				} else {
					s += ",no";
				}
				pw.write(s);

			} else if (type.equals("2")) {
				// CarDaoImpl cdi = new CarDaoImpl();
				// Car car = cdi.getCarByDriver(name, con);
				application.setAttribute("driver", location);
				pw.write(location);
			} else if (type.equals("3")) {
				// 员工签到认证

				Staff sf = fdi.getStaffByNumberS(name, con);
				String coord = request.getParameter("coordi");
				if (coord == null) {
					coord = "30,110";
				}
				if (null != sf) {
					Site site = sdi.getSiteById(sf.getSiteId(), con);
					if (null != site) {

						String[] mylo = coord.split(",");

						double dis = Lines.Distance(
								Double.parseDouble(mylo[0]),
								Double.parseDouble(mylo[1]),
								site.getLatitude(), site.getLongitude());
						System.out.println("dis=" + dis);
						if (dis < 50) {
							// 签到成功后存入每日记录表
							DayRecord dr = new DayRecord();
							dr.setDay_date(DateUtil.getDate());
							dr.setDay_siteId(site.getSiteId());
							dr.setDay_staffNumber(sf.getNumber());

							DayRecordDaoImpl drd = new DayRecordDaoImpl();
							int r = drd.addOneRecord(con, dr);
							if (r > 0) {
								pw.write(dis + "");
							} else {
								pw.write("no");
							}
						} else {
							pw.write("no");
						}
					} else {
						pw.write("no");
					}
				} else {
					pw.write("no");
				}
			}
			pw.close();
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
