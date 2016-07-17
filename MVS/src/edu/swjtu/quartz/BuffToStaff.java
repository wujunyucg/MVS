package edu.swjtu.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class BuffToStaff implements Job {
	public void execute(JobExecutionContext context) throws JobExecutionException {     
       System.out.print("开始了");
}  
}
