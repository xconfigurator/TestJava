package cn.edu.hebau.liuyang.annotation;

@Entity("student")
public class Student {
	@Field(columnName = "id", type = "int", length = 10)
	private int id;
	@Field(columnName = "student_name", type = "varchar", length = 40)
	private String studentName;
	@Field(columnName = "age", type = "int", length = 10)
	private int age;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
