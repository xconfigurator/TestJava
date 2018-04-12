package test;

import java.io.IOException;

public class Hello {
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	public static void main(String[] args) throws IOException {
		
		// 环境变量
		System.out.println(System.getenv());
		
		// 系统属性
		System.out.println(System.getProperties());
		System.out.println(System.getProperty("os.name"));					 // Windows 10
		System.out.println(System.getProperty("os.arch"));					 // amd64
		System.out.println(System.getProperty("os.version"));				 // 10
		System.out.println(System.getProperty("java.class.path"));			 // F:\workspaces\workspace_oxygen\TestJava\bin
		System.out.println(System.getProperty("java.specification.version"));//	1.8

	}

}
