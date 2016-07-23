package edu.swjtu.intelligent;

import java.util.ArrayList;

import edu.swjtu.model.Site;

public class SearchRoute {
	public double weight;
	public ArrayList<Integer> pass_path;
	public ArrayList<Integer> notpass_path;
	
	public SearchRoute () {
		weight = 0.0;
		this.pass_path = new ArrayList<Integer>();
		this.notpass_path = new ArrayList<Integer>();
	}
	
//	public SearchRoute (double wei) {
//		weight = wei;
//		this.pass_path = new ArrayList<Integer>();
//	}
}
