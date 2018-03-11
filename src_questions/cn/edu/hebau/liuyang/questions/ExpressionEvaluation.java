package cn.edu.hebau.liuyang.questions;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 表达式求值<br>
 * 参考：耿宇航
 * 
 * @author liuyang
 * @since 2017/11/28
 * 
 */
public class ExpressionEvaluation {

	private static final String EXP = "3*(7-2*3)+5";// 假设没有空格
	private static final Map<Character, Integer> PRIV = new HashMap<>(); // 优先级
	private static final Stack<Character> OP = new Stack<>();// 算符栈
	private static final Stack<Integer> DATA = new Stack<>();// 数据栈

	static {
		// 初始化优先级
		PRIV.put('(', 0);// 放入栈中的左括号应认为其优先级最低
		PRIV.put('+', 1);
		PRIV.put('-', 1);
		PRIV.put('*', 2);
	}

	private static void calc() {
		int b = DATA.pop(); // 先弹出的是在右边
		int a = DATA.pop(); //
		char op = OP.pop(); //
		int result = 0;
		switch (op) {
		case '+':
			result = a + b;
			break;
		case '-':
			result = a - b;
			break;
		case '*':
			result = a * b;
			break;
		}
		DATA.push(result);
	}

	public static void main(String[] args) {
		// 这里假设操作数是1位，实际可以先词法分析，支持更复杂的操作数。
		// 这里假设只有乘法、加法、减法，突出核心问题

		// 1. 词法分析
		char[] cc = EXP.toCharArray();

		// 2. 对每个字符进行处理
		for (int i = 0; i < cc.length; i++) {

			if (cc[i] >= '0' && cc[i] <= '9') { // 数字
				DATA.push(cc[i] - '0');
			} else if (cc[i] == '(') { // 左括号
				OP.push(cc[i]);
			} else if (cc[i] == ')') { // 右括号：遇到')'则开始进行计算过程
				while (OP.peek() != '(') { // 计算：在遇到'('之前进行计算
					calc();
				}
				OP.pop(); // '('出栈
			} else { // 操作符
				while (!OP.empty() && PRIV.get(cc[i]) <= PRIV.get(OP.peek())) {
					// 操作符栈不为空，且当前算符优先级小于算符栈栈顶元素优先级时。
					// 则算符栈栈顶算符应进行计算。
					calc();
				}
				// 此时算符栈已空，或者算符栈中高优先级的算符已计算完毕
				OP.push(cc[i]);// 当前算符入栈。
			}

		} // end of for

		// 当运算符栈不空则进行计算
		while (!OP.empty()) {// 计算
			calc();
		}

		// 当运算符栈空的时候，运算数栈中剩下的即为所求结果
		System.out.print("表达式" + EXP + " = ");
		System.out.println(DATA.pop());
	}

}
