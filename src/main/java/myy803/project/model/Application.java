package myy803.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="applications")
@IdClass(ApplicationId.class)
public class Application {
	
	@Id
	@Column(name="subject", nullable=false)
	private int subject;
	
	@Id
	@Column(name="student", nullable=false)
	private int student;
	
	@Column(name="message")
	private String message;
	
	public Application() {}
	
	public Application(int subject, int student, String message) {
		this.subject = subject;
		this.student = student;
		this.message = message;
	}

}

