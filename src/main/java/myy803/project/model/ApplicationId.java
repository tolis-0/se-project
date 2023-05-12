package myy803.project.model;

import java.io.Serializable;

public class ApplicationId implements Serializable {
    private static final long serialVersionUID = 1L;
    
	private int subjectId;
    private int studentId;
    
	public int getSubjectId() {
		return subjectId;
	}
	
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	
	public int getStudentId() {
		return studentId;
	}
	
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
}
