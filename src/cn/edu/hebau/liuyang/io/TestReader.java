package cn.edu.hebau.liuyang.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestReader {
	
	private static final String FILE_PATH = "F:/temp/USA.txt";
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
		
		String str = null;
		while ((str = br.readLine()) != null) {
			System.out.println(str);
		}
		
		br.close();
	}
}
