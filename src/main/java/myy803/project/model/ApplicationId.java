package myy803.project.model;

import java.io.Serializable;

public class ApplicationId implements Serializable {
    private static final long serialVersionUID = 1L;
    
	private int subject;
    private int student;
    
	public int getSubject() {
		return subject;
	}
	
	public void setSubject(int subject) {
		this.subject = subject;
	}
	
	public int getStudent() {
		return student;
	}
	
	public void setStudent(int student) {
		this.student = student;
	}
}
