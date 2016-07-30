package edu.swjtu.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import edu.swjtu.impl.CarDaoImpl;
import edu.swjtu.impl.PowerDaoImpl;
import edu.swjtu.impl.UserDaoImpl;
import edu.swjtu.model.Car;
import edu.swjtu.model.Power;
import edu.swjtu.model.User;
import edu.swjtu.util.DBUtil;

/**
 * 
 * 2016年7月9日上午11:39:56
 * @author mischief
 * TODO Test PowerDao 
 */

public class PowerDaoTest {
	
	@Test
	public void testAddPower() throws ClassNotFoundException, SQLException{
		Power power = new Power();
		power.setContent("1-2-3-4-5");
		int u = new PowerDaoImpl().addPower(power, new DBUtil().getCon());
		System.out.println(u);//1
	}
	
	@Test
	public void testDeletePower() throws SQLException, ClassNotFoundException{
		PowerDaoImpl testdelete = new PowerDaoImpl();
		int u = testdelete.deletePower(2, new DBUtil().getCon());
		System.out.println(u);
	}
	
	@Test
	public void testUpdataPower() throws ClassNotFoundException, SQLException{
		Power power = new Power();
		PowerDaoImpl powerDao = new PowerDaoImpl();
		
		power.setPowerId(3);
		power.setContent("带去我的");
		
		int u = new PowerDaoImpl().updatePower(power, new DBUtil().getCon());
		System.out.println(u);//1
	}
	
	@Test
	public void testFindAllPower() throws ClassNotFoundException, SQLException{
		 ArrayList<Power> list = new PowerDaoImpl().getAllPower(new DBUtil().getCon());
		 for(int j = 0 ; j < list.size() ; j++)
	                System.out.print(list.get(j).getContent() + '\n'); 
	}
	
	@Test
	public void testFindPower() throws ClassNotFoundException, SQLException{
		Power power = new PowerDaoImpl().getPowerById(3, new DBUtil().getCon());
		 System.out.println(power.getContent());
	}
	

	@Test
	public void testFindCar() throws ClassNotFoundException, SQLException{
		ArrayList<Car> carList = new CarDaoImpl().getCarByLicensePlate_V("13",new DBUtil().getCon()); 
		for(int i=0;i<carList.size();i++)
			System.out.println(carList.get(i).getCarId());
	}
}
