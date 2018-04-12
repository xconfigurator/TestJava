package cn.edu.hebau.liuyang;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class HelloScriptEngine {

	public static void main(String[] args) throws ScriptException, NoSuchMethodException, IOException {
		// 获取脚本引擎对象
		ScriptEngineManager sem = new ScriptEngineManager();
		ScriptEngine engine = sem.getEngineByName("javascript");
		
		
		System.out.println("#简单使用###############################################");
		// 在引擎上下文中定义变量，Java中和JavaScript中均可获取
		engine.put("msg", "hello, world");
		StringBuilder sb = new StringBuilder();
		sb.append("var user = {name:'liuyang', age:35, schools:['四中', '河北农业大学']};");
		sb.append("print(user.name);");
		sb.append("msg = '在JavaScript中修改msg内容';");	// 在JavaScript中修改msg内容
		
		// 执行脚本
		engine.eval(sb.toString());
		
		// 获取msg
		System.out.println(engine.get("msg")); 			// 在Java中获得msg值
		
		
		System.out.println("#定义函数###############################################");
		// 在JavaScript中定义函数，在Java中使用
		engine.eval("function add(a,b) {var sum = a + b; return sum;}");
		Invocable jsInvoke = (Invocable) engine;
		Object result1 = jsInvoke.invokeFunction("add", 2, 2);
		System.out.println(result1);
		
		
		System.out.println("#神奇的###############################################");
		// 这段有问题
		//engine.eval("importPackage(java.util); var list = Arrays.asList([\"四中\", \"河北农业大学\"]);");
		System.out.println(engine.get("msg"));// msg还在
		
		
		System.out.println("#执行js###############################################");
		URL url = HelloScriptEngine.class.getClassLoader().getResource("HelloScriptEngine.js");
		FileReader reader = new FileReader(url.getPath());
		engine.eval(reader);
		reader.close();
	}

}
