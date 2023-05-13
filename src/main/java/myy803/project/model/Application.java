package myy803.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="applications")
@IdClass(ApplicationId.class)
public class Application {
	
	@Id
	@Column(name="subject", nullable=false)
	private int subjectId;
	
	@Id
	@Column(name="student", nullable=false)
	private int studentId;
	
	@Transient
	private Student student;
	
	@Column(name="message")
	private String message;
	
	public Application() {}
	
	public Application(int subjectId, int studentId, String message) {
		this.subjectId = subjectId;
		this.studentId = studentId;
		this.message = message;
	}
	
	public int getSubjectId() {
		return subjectId;
	}

	public int getStudentId() {
		return studentId;
	}

	public Student getStudent() {
		return student;
	}
	
	public void setStudent(Student student) {
		this.student = student;
	}
	
	public String getMessage() {
		return message;
	}

}

