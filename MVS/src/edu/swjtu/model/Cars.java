package edu.swjtu.model;

import java.util.Comparator;

public class Cars implements Comparator<Object> {

		 public int compare(Object o1, Object o2) {
			  Car c1 = (Car) o1;
			  Car c2 = (Car) o2;
			  if (c1.getNumber() > c2.getNumber()){
				  return 1;
			  }else{
				  return -1;
			  }
	}
}
