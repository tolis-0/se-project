package myy803.project.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="Students")
public class Student{

	@Id 
	@Column(name="id", nullable=false)
	private int id;
	
	@OneToOne
	@PrimaryKeyJoinColumn(name="id", referencedColumnName="id")
	private User user;
	
	@Column(name="full_name", length=64)
	private String fullName;
	
	@Column(name="rem_courses")
	private int remainingCourses;
	
	@Column(name="year")
	private int year;
	
	@Column(name="avg_grade")
	private float averageGrade;
	
	@OneToMany(mappedBy="student")
	private List<Application> applications;
	
	public Student(String full_name, int rem_courses, int year, float avg_grade) {
		this.fullName = full_name;
		this.remainingCourses = rem_courses;
		this.year = year;
		this.averageGrade = avg_grade;
	}
	
	public Student() {
		
	}
	
	public void setFull_name(String full_name) {
		this.fullName = full_name;
	}
	
	public void setRem_courses(int rem_courses) {
		this.remainingCourses = rem_courses;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public void setAvg_grade(float avg_grade) {
		this.averageGrade = avg_grade;
	}
	
	public String getFull_name() {
		return this.fullName;
	}
	
	public int getRem_courses() {
		return this.remainingCourses;
	}
	
	public int getYear() {
		return this.year;
	}
	
	public float getAvg_grades() {
		return this.averageGrade;
	}
	
	@Override
	public String toString() {
		return "Student [full_name=" + fullName + ", rem_courses=" + remainingCourses + ", year=" + year + ", avg_grade=" + averageGrade + "]";
	}
}
