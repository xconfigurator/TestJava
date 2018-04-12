package cn.edu.hebau.liuyang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 * 动态编译 sinc JDK 6
 * @author liuyang
 *
 */
public class HelloDynaCompiler {

	private static final String SOURCEFILE = "f:/temp/Hello.java";
	private static final String INVOKE_CMD = "java -cp f:/temp Hello";
	
	public static void main(String[] args) throws IOException {
		compile();
		invoke();
	}
	
	// 编译
	private static void compile() {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		int result = compiler.run(System.in, System.out, System.err, SOURCEFILE);
		System.out.println(result == 0 ? "编译成功":"编译失败");
	}
	
	// 调用
	private static void invoke() throws IOException {
		Runtime rt = Runtime.getRuntime();
		Process process = rt.exec(INVOKE_CMD);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String info = null;
		while ((info = reader.readLine()) != null) {
			System.out.println(info);
		}
		
		reader.close();
	}
	
}
