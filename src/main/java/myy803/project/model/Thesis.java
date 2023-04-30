package myy803.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Thesis")
public class Thesis {

	@Id
	@Column(name="id", nullable=false)
	private Subject subject;		// TODO int
	
	private Professor professor;
	
	@Column(name="student", nullable=false)
	private Student student;		// TODO int
	
	@Column(name="imp_grade")
	private float ImplementationGrade;
	
	@Column(name="rep_grade")
	private float ReportGrade;
	
	@Column(name="pres_grade")
	private float PresentationGrade;
	
}
