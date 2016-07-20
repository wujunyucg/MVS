package edu.swjtu.util;

import java.util.ArrayList;
import java.util.Random;

import edu.swjtu.model.Staff;

public class KMeans {
	private int k;// 分成多少簇
	private int m;// 迭代次数
	private int dataSetLength;// 数据集元素个数，即数据集的长度
	private ArrayList<Staff> dataSet;// 数据集链表
	private ArrayList<double[]> center;// 中心链表
	private ArrayList<ArrayList<Staff>> cluster; // 簇
	private ArrayList<Double> jc;// 误差平方和，k越接近dataSetLength，误差越小
	private Random random;
	/**
	 * 设置需分组的原始数据集
	 * 
	 * @param dataSet
	 */

	public void setDataSet(ArrayList<Staff> dataSet) {
		this.dataSet = dataSet;
	}

	/**
	 * 获取结果分组
	 * 
	 * @return 结果集
	 */

	public ArrayList<ArrayList<Staff>> getCluster() {
		return cluster;
	}
	
	public ArrayList<double[]> getCenter() {
		return center;
	}

	public void setCenter(ArrayList<double[]> center) {
		this.center = center;
	}

	/**
	 * 构造函数，传入需要分成的簇数量
	 * 
	 * @param k
	 *            簇数量,若k<=0时，设置为1，若k大于数据源的长度时，置为数据源的长度
	 * @return 
	 */
	public  KMeans(int k) {
		if (k <= 0) {
			k = 1;
		}
		this.k = k;
	}

	/**
	 * 初始化
	 */
	private void init() {
		m = 0;
		random = new Random();
		if (dataSet == null || dataSet.size() == 0) {
			initDataSet();
		}
		dataSetLength = dataSet.size();
		if (k > dataSetLength) {
			k = dataSetLength;
		}
		center = initCenters();
		cluster = initCluster();
		jc = new ArrayList<Double>();
	}

	/**
	 * 如果调用者未初始化数据集，则采用内部测试数据集
	 */
	private void initDataSet() {
		/*dataSet = new ArrayList<double[]>();
		// 其中{6,3}是一样的，所以长度为15的数据集分成14簇和15簇的误差都为0
		double[][] dataSetArray = new double[][] { { 8, 2 }, { 3, 4 }, { 2, 5 },
				{ 4, 2 }, { 7, 3 }, { 6, 2 }, { 4, 7 }, { 6, 3 }, { 5, 3 },
				{ 6, 3 }, { 6, 9 }, { 1, 6 }, { 3, 9 }, { 4, 1 }, { 8, 6 } };

		for (int i = 0; i < dataSetArray.length; i++) {
			dataSet.add(dataSetArray[i]);
		}*/
	}

	/**
	 * 初始化中心数据链表，分成多少簇就有多少个中心点
	 * 
	 * @return 中心点集
	 */
	private ArrayList<double[]> initCenters() {
		ArrayList<double[]> center = new ArrayList<double[]>();
		int[] randoms = new int[k];
		boolean flag;
		int temp = random.nextInt(dataSetLength);
		randoms[0] = temp;
		for (int i = 1; i < k; i++) {
			flag = true;
			while (flag) {
				temp = random.nextInt(dataSetLength);
				int j = 0;
				while (j < i) {
					if (temp == randoms[j]) {
						break;
					}
					j++;
				}
				if (j == i) {
					flag = false;
				}
			}
			randoms[i] = temp;
		}
		for (int i = 0; i < k; i++) {
			center.add(new double[]{dataSet.get(randoms[i]).getLati(),dataSet.get(randoms[i]).getLongti()});// 生成初始化中心链表
		}
		return center;
	}

	/**
	 * 初始化簇集合
	 * 
	 * @return 一个分为k簇的空数据的簇集合
	 */
	private ArrayList<ArrayList<Staff>> initCluster() {
		ArrayList<ArrayList<Staff>> cluster = new ArrayList<ArrayList<Staff>>();
		for (int i = 0; i < k; i++) {
			cluster.add(new ArrayList<Staff>());
		}

		return cluster;
	}

	/**
	 * 计算两个点之间的距离
	 * 
	 * @param element
	 *            点1
	 * @param center
	 *            点2
	 * @return 距离
	 */
	private double distance(double[] element, double[] center) {
		double distance = 0.0;
		double x = element[0] - center[0];
		double y = element[1] - center[1];
		double z = x * x + y * y;
		distance = (double) Math.sqrt(z);

		return distance;
	}

	/**
	 * 获取距离集合中最小距离的位置
	 * 
	 * @param distance
	 *            距离数组
	 * @return 最小距离在距离数组中的位置
	 */
	private int minDistance(double[] distance) {
		double minDistance = distance[0];
		int minLocation = 0;
		for (int i = 1; i < distance.length; i++) {
			if (distance[i] < minDistance) {
				minDistance = distance[i];
				minLocation = i;
			} else if (distance[i] == minDistance) // 如果相等，随机返回一个位置
			{
				if (random.nextInt(10) < 5) {
					minLocation = i;
				}
			}
		}

		return minLocation;
	}

	/**
	 * 核心，将当前元素放到最小距离中心相关的簇中
	 */
	private void clusterSet() {
		double[] distance = new double[k];
		for (int i = 0; i < dataSetLength; i++) {
			for (int j = 0; j < k; j++) {
				distance[j] = distance(new double[]{dataSet.get(i).getLati(),dataSet.get(i).getLongti()}, center.get(j));
			}
			int minLocation = minDistance(distance);
			cluster.get(minLocation).add(dataSet.get(i));// 核心，将当前元素放到最小距离中心相关的簇中

		}
	}

	/**
	 * 求两点误差平方的方法
	 * 
	 * @param element
	 *            点1
	 * @param center
	 *            点2
	 * @return 误差平方
	 */
	private double errorSquare(double[] element, double[] center) {
		double x = element[0] - center[0];
		double y = element[1] - center[1];

		double errSquare = x * x + y * y;

		return errSquare;
	}

	/**
	 * 计算误差平方和准则函数方法
	 */
	private void countRule() {
		double jcF = 0;
		for (int i = 0; i < cluster.size(); i++) {
			for (int j = 0; j < cluster.get(i).size(); j++) {
				jcF += errorSquare(new double[]{cluster.get(i).get(j).getLati(),cluster.get(i).get(j).getLongti()}, center.get(i));

			}
		}
		jc.add(jcF);
	}

	/**
	 * 设置新的簇中心方法
	 */
	private void setNewCenter() {
		for (int i = 0; i < k; i++) {
			int n = cluster.get(i).size();
			if (n != 0) {
				double[] newCenter = { 0, 0 };
				for (int j = 0; j < n; j++) {
					newCenter[0] += cluster.get(i).get(j).getLati();
					newCenter[1] += cluster.get(i).get(j).getLongti();
				}
				// 设置一个平均值
				newCenter[0] = newCenter[0] / n;
				newCenter[1] = newCenter[1] / n;
				center.set(i, newCenter);
			}
		}
	}

	/**
	 * 打印数据，测试用
	 * 
	 * @param dataArray
	 *            数据集
	 * @param dataArrayName
	 *            数据集名称
	 */
	public void printDataArray(ArrayList<double[]> dataArray,
			String dataArrayName) {
		for (int i = 0; i < dataArray.size(); i++) {
			System.out.println("print:" + dataArrayName + "[" + i + "]={"
					+ dataArray.get(i)[0] + "," + dataArray.get(i)[1] + "}");
		}
		System.out.println("===================================");
	}

	/**
	 * Kmeans算法核心过程方法
	 */
	private void kmeans() {
		init();
		while (true) {
			clusterSet();
			countRule();
			if (m != 0) {
				if (jc.get(m) - jc.get(m - 1) == 0) {
					break;
				}
			}
			setNewCenter();
			m++;
			cluster.clear();
			cluster = initCluster();
		}
	}

	/**
	 * 执行算法
	 */
	public void execute() {
	//	long startTime = System.currentTimeMillis();
		//System.out.println("kmeans begins");
		kmeans();
		//long endTime = System.currentTimeMillis();
		//System.out.println("kmeans running time=" + (endTime - startTime)
		//		+ "ms");
		//System.out.println("kmeans ends");
		//System.out.println();
	}
	
	private  static double EARTH_RADIUS = 6378.137;//地球半径
	 private final double R = 6371229; 
	private static double rad(double d)
	{
	   return d * Math.PI / 180.0;
	} 

	public double GetDistance(double lat1, double lng1, double lat2, double lng2)
	{
	   double radLat1 = rad(lat1);
	   double radLat2 = rad(lat2);
	   double a = radLat1 - radLat2;
	   double b = rad(lng1) - rad(lng2);

	   double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
	    Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
	   s = s * EARTH_RADIUS;
	   s = Math.round(s * 10000) / 10000;
	   return s;
	}

	 public double getDistance(double longt1, double lat1, double longt2, double lat2){
	        double x,y, distance;
	        x=(longt2-longt1)*Math.PI*R*Math.cos( ((lat1+lat2)/2)*Math.PI/180)/180;
	        y=(lat2-lat1)*Math.PI*R/180;
	        distance=Math.hypot(x,y);
	        return distance;
	    }

	public int getK() {
		return k;
	}

	public void setK(int k) {
		this.k = k;
	}
}
