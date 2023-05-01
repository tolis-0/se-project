package myy803.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="Thesis")
public class Thesis {

	@Id
	@Column(name="id", nullable=false)
	private int id;
	
	@OneToOne
	@PrimaryKeyJoinColumn(name="id", referencedColumnName="id")
	private Subject subject;
	
	@OneToOne
	@JoinColumn(name="student")
	private Student student;
	
	@Column(name="imp_grade")
	private float ImplementationGrade;
	
	@Column(name="rep_grade")
	private float ReportGrade;
	
	@Column(name="pres_grade")
	private float PresentationGrade;
	
}
