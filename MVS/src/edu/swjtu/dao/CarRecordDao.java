package edu.swjtu.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.swjtu.model.CarRecord;
import edu.swjtu.model.User;

/**
 * 
 * 2016年7月9日下午1:14:05
 * @author mischief
 * TODO	车的记录
 */

public interface CarRecordDao {
	
	public int addCarRecord(CarRecord carrecord,Connection con) throws SQLException;
	
	public int deleteCarRecord(int carrecordId,Connection con) throws SQLException;
	
	public int updateCarRecord(CarRecord carrecord,Connection con) throws SQLException;
	
	public CarRecord getCarRecordById(int carrecordId,Connection con) throws SQLException;
	
	public ArrayList getAllCarRecord(Connection con) throws SQLException;
}
