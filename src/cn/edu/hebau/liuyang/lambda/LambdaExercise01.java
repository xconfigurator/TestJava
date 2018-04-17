package cn.edu.hebau.liuyang.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 练习1： 自定义排序规则
 * 
 * @author liuyang
 *
 */
public class LambdaExercise01 {

	public static void main(String[] args) {
		List<Employee> emps = Arrays.asList(
				new Employee(101, "张三", 18, 9999.99),
				new Employee(102, "李四", 59, 6666.66),
				new Employee(103, "王五", 28, 3333.33),
				new Employee(104, "赵六", 28, 7777.77),
				new Employee(105, "田七", 38, 5555.55)
		);
		
		Collections.sort(emps, (e1, e2) -> {
			if (e1.getAge() == e2.getAge()) {// 2. 若年龄相同则按收入排序（升序，默认）
				return Double.compare(e1.getSalary(), e2.getSalary());
			} else {// 1. 按年龄排序（倒序，即降序）
				return -Integer.compare(e1.getAge(), e2.getAge());
			}
		});
		
		emps.forEach(System.out::println);
	}

	static class Employee {
		private int id;
		private int age;
		private String name;
		private double salary;

		public Employee(int id, String name,int age, double salary) {
			super();
			this.id = id;
			this.age = age;
			this.name = name;
			this.salary = salary;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public double getSalary() {
			return salary;
		}

		public void setSalary(double salary) {
			this.salary = salary;
		}

		@Override
		public String toString() {
			return "Employee [id=" + id + ", age=" + age + ", name=" + name + ", salary=" + salary + "]";
		}

	}
}
