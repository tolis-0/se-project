package myy803.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
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
	private float totalGrade;
	
	public Thesis() {}
	
	public Thesis(int id, Student student) {
		this.id = id;
		this.student = student;
	}
	
	@PostLoad
	private void calculateTotalGrade() {
		this.totalGrade = 	(float) (
							0.70 * implementationGrade + 
							0.15 * reportGrade + 
							0.15 * presentationGrade);
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
	
	public Student getStudent() {
		return student;
	}
	
	public float getImplementationGrade() {
		return  implementationGrade;
	}
	
	public float getReportGrade() {
		return  reportGrade;
	}
	
	public float getPresentationGrade() {
		return  presentationGrade;
	}
	
	public Professor getProfessor() {
		return professor;
	}
	
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	public void setImplementationGrade(float grade) {
		this.implementationGrade = grade;
	}
	
	public void setReportGrade(float grade) {
		this.reportGrade = grade;
	}
	
	public void setPresentationGrade(float grade) {
		this.presentationGrade = grade;
	}
	
	@Override
	public String toString() {
		return "[id=" + id + ", student=" + student.getId()  + ", impGrade=" + implementationGrade + 
				", repGrade=" + reportGrade + ", presGrade=" + presentationGrade +  
				", totalGrade=" + totalGrade + "]";
	}
	
}
