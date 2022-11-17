package core.config;

import org.springframework.beans.factory.annotation.Autowired;

public class College {

	@Autowired
	private Student student;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	
}
