package myy803.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="students")
public class Student{

	@Id 
	@Column(name="id", nullable=false)
	private int id;
	
	@Transient
	private User user;
	
	@Column(name="full_name", length=64)
	private String fullName;
	
	@Column(name="rem_courses")
	private int remainingCourses;
	
	@Column(name="year")
	private int year;
	
	@Column(name="average_grade")
	private float averageGrade;
	
	@Transient
	private Thesis thesis;
	
	public Student(User _user, String fullname) {
		this.user = _user;
		this.id = _user.getId();
		this.fullName = fullname;
	}
	
	public Student() {}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public void setFullName(String full_name) {
		this.fullName = full_name;
	}
	
	public void setRemainingCourses(int rem_courses) {
		this.remainingCourses = rem_courses;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public void setAverageGrade(float avg_grade) {
		this.averageGrade = avg_grade;
	}
	
	public int getId() {
		return id;
	}
	
	public String getFullName() {
		return this.fullName;
	}
	
	public int getRemainingCourses() {
		return this.remainingCourses;
	}
	
	public int getYear() {
		return this.year;
	}
	
	public float getAverageGrade() {
		return this.averageGrade;
	}
	
	@Override
	public String toString() {
		return "[id=" + id + ", full_name=" + fullName + ", rem_courses=" + 
				remainingCourses + ", year=" + year + ", avg_grade=" + averageGrade + "]";
	}
	
}
