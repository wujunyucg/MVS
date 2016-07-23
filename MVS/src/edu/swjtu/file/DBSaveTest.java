package edu.swjtu.file;

import java.io.IOException;

import org.junit.Test;

public class DBSaveTest {
	@Test
	public void test() throws InterruptedException, IOException{
		//new DBSave().backupDatebase("localhost", "root", "root", "car_factory", "d://z.sql");
		//new DBSave().backupDatebase("192.168.1.136", "root", "root", "car_factory", "d://y.sql");
		//System.out.println(new DBSave().loadDataBase("d://y.sql"));
		//new DBSave().backupDatebase("d://x.sql");
		DBSave db = new DBSave();
		db.setProValue("lastIxTime", "first");
		System.out.println(db.getProValue("lastIxTime"));
	}
}
