package cn.edu.hebau.liuyang;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class HelloRandomData {

	// https://www.cnblogs.com/xwdreamer/archive/2012/06/13/2547426.html
	
	private static final String FILE_PATH = "F:/temp/test_algorithms_sort.txt";
	private static final int TOTAL_NUM = 10; // 产生随机数数量
	private static final int BOUND = 10000; // 产生随机数范围
	
	/**
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH));
		
		Random r = new Random();
		for (int i = 0; i < TOTAL_NUM; ++i ) {
			//System.out.println(r.nextInt(BOUND));	
			pw.println(r.nextInt(BOUND));
		}
		
		pw.close();
	}

}
