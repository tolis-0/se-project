package myy803.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="Applications")
@IdClass(ApplicationId.class)
public class Application {
	
	@Id
	@Column(name="subject", nullable=false)
	private Subject subject;		// TODO int
	
	@Id
	@Column(name="subject", nullable=false)
	private Student student;		// TODO int
	
	@Column(name="message")
	private String message;

}

