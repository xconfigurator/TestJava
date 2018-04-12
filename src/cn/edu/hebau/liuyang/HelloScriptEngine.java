package cn.edu.hebau.liuyang;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class HelloScriptEngine {

	public static void main(String[] args) throws ScriptException {
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
		
	}

}
