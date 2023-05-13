package myy803.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

@Entity
@Table(name="thesis")
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
	
	@ManyToOne(targetEntity=Professor.class)
	private Professor professor;
	
	@Column(name="imp_grade")
	private float implementationGrade;
	
	@Column(name="rep_grade")
	private float reportGrade;
	
	@Column(name="pres_grade")
	private float presentationGrade;
	
	@Formula("0.7*imp_grade + 0.15*rep_grade + 0.15*pres_grade")
	private float totalGrade;
	
	public float getTotalGrade() {
		return totalGrade;
	}
}
