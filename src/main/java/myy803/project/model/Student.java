package myy803.project.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="students")
public class Student{

	private String full_name;
	private int rem_courses;
	private int year;
	private float avg_grade;
	
	public Student(String full_name, int rem_courses, int year, float avg_grade) {
		this.full_name = full_name;
		this.rem_courses = rem_courses;
		this.year = year;
		this.avg_grade = avg_grade;
	}
	
	public Student() {
		
	}
	
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	
	public void setRem_courses(int rem_courses) {
		this.rem_courses = rem_courses;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public void setAvg_grade(float avg_grade) {
		this.avg_grade = avg_grade;
	}
	
	public String getFull_name() {
		return this.full_name;
	}
	
	public int getRem_courses() {
		return this.rem_courses;
	}
	
	public int getYear() {
		return this.year;
	}
	
	public float getAvg_grades() {
		return this.avg_grade;
	}
	
	@Override
	public String toString() {
		return "Student [full_name=" + full_name + ", rem_courses=" + rem_courses + ", year=" + year + ", avg_grade=" + avg_grade + "]";
	}
}
