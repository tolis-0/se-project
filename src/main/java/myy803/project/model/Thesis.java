package myy803.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

@Entity
@Table(name="thesis")
public class Thesis {

	@Id
	@Column(name="id", nullable=false)
	private int id;
	
	@Transient
	private Subject subject;
	
	@OneToOne
	@JoinColumn(name="student", unique=true)
	private Student student;
	
	@Transient
	private Professor professor;
	
	@Column(name="imp_grade")
	private float implementationGrade;
	
	@Column(name="rep_grade")
	private float reportGrade;
	
	@Column(name="pres_grade")
	private float presentationGrade;
	
	@Transient
	@Formula("0.7*imp_grade + 0.15*rep_grade + 0.15*pres_grade")
	private float totalGrade;
	
	public Thesis() {}
	
	public Thesis(int id, Student student) {
		this.id = id;
		this.student = student;
	}
	
	public int getId() {
		return id;
	}
	
	public float getTotalGrade() {
		return totalGrade;
	}
	
	public Subject getSubject() {
		return subject;
	}
	
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
}
