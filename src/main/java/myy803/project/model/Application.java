package myy803.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="applications")
@IdClass(ApplicationId.class)
public class Application {
	
	@Id
	@ManyToOne
	@JoinColumn(name="subject", nullable=false)
	private Subject subject;
	
	@Id
	@ManyToOne
	@JoinColumn(name="student", nullable=false)
	private Student student;
	
	@Column(name="message")
	private String message;

}

