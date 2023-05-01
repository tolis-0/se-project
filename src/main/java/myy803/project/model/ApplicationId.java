package myy803.project.model;

import java.io.Serializable;

public class ApplicationId implements Serializable {
    private static final long serialVersionUID = 1L;
    
	private Subject subject;
    private Student student;
    
	public Subject getSubject() {
		return subject;
	}
	
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	public Student getStudent() {
		return student;
	}
	
	public void setStudent(Student student) {
		this.student = student;
	}
}
