package cn.edu.hebau.liuyang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HelloInvoker {
	
	private static final String CMD = "java -cp F:/workspaces/workspace_oxygen/TestJava/bin cn.edu.hebau.liuyang.Hello";

	public static void main(String[] args) throws IOException {
		Runtime rt = Runtime.getRuntime();
		Process process = rt.exec(CMD);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String info = null;
		while ((info = reader.readLine()) != null) {
			System.out.println(info);
		}
		
		reader.close();
	}

}
