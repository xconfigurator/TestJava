package cn.edu.hebau.liuyang.io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TestWirter {
	
	private static final String FILE_PATH = "F:/temp/Random.txt";
	private static final int RANGE = 10000;// 随机产生的数字的范围
	private static final int RECORDS_TOTAL = 10; // 总记录条数 
	
	
	public static void main(String[] args) throws IOException {
		PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH));

		for (int i = 0; i < RECORDS_TOTAL; ++i) {
			//System.out.println(Math.random() * RANGE);
			writer.println(Math.random() * RANGE);
		}
		
		
		writer.close();
	}

}
