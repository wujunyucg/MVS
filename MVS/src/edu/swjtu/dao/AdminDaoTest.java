package edu.swjtu.dao;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.swjtu.impl.AdminDaoImpl;
import edu.swjtu.impl.CarDaoImpl;
import edu.swjtu.impl.SiteDaoImpl;
import edu.swjtu.impl.StaffDaoImpl;
import edu.swjtu.model.Admin;
import edu.swjtu.model.Car;
import edu.swjtu.model.Site;
import edu.swjtu.model.Staff;
import edu.swjtu.util.DBUtil;

public class AdminDaoTest {

	@Test
	public void test() throws ClassNotFoundException, SQLException {
		
		/*Car car = new Car();
		car.setArrangeId("1,2,3");
		car.setBrand("宝马");
		car.setCarId(0);
		car.setDriver("吴俊宇");
		car.setDrivingLicense("sdfgsad");
		car.setInsuranceDate( new Date(999999999));
		car.setLicense("sdrtgsdra");
		car.setLicensePlate("fth5475d");
		car.setNumber(60);
		car.setRegistrationDate(new Date(999999999));
		CarDaoImpl cdi = new CarDaoImpl();
		ArrayList<Car> carList = new ArrayList<Car>();
		for(int i=5;i<8;i++)
		{
			Car car1 = new Car();
			car1.setCarId(i);
			carList.add(car1);
		}
		
		//cdi.addOneCar(car, new DBUtil().getCon());
		//cdi.addListCar(carList, new DBUtil().getCon());
		//car.setCarId(4);
		//cdi.deleteOneCar(car, new DBUtil().getCon());
		//cdi.deleteListCar(carList, new DBUtil().getCon());
		//car.setCarId(8);
		//car.setDriver("承鲁");
		//cdi.updateCar(car, new DBUtil().getCon());
		//car=cdi.getCarByArrangeId(2, new DBUtil().getCon());
		
		//carList = cdi.getCarByBrand("宝马", new DBUtil().getCon());
		/*for(Car c : carList)
				System.out.println(c.getCarId());
		//car=cdi.getCarById(2, new DBUtil().getCon());
		//car=cdi.getCarByLicensePlate("fth5475d", new DBUtil().getCon());
		//car=cdi.getCarByLicense("sdrtgsdra", new DBUtil().getCon());
		//car=cdi.getCarByDriver("吴俊宇", new DBUtil().getCon());
		int num=cdi.getCarNumberByCarId(8, new DBUtil().getCon());
		System.out.println(num);*/
		
		
		Site site = new Site();
		site.setAddress("四川");
		site.setDelay(5);
		site.setLatitude("199,,656,");
		site.setLineId(1);
		site.setLongitude("465456");
		site.setName("一号战带你");
		site.setOrder(1);
		site.setPeoNum(55);
		site.setSiteId(0);
		SiteDaoImpl sdi = new SiteDaoImpl();
		sdi.addOneSite(site, new DBUtil().getCon());
		}
		
}

