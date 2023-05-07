package myy803.project.dto;

public class StudentDTO {
	
	private String fullName;
	private int remCourses;
	private int year;
	private float avgGrade;

	public StudentDTO(String fullname, int rem_courses, int year, float average_grade) {
		this.fullName = fullname;
		this.remCourses = rem_courses;
		this.year = year;
		this.avgGrade = average_grade;
	}

	//GETTERS
	public String getFullName() {
		return fullName;
	}
	
	public int getRemCourses() {
		return remCourses;
	}
	
	public int getYear() {
		return year;
	}
	
	public float getAvgGrade() {
		return avgGrade;
	}

	//SETTERS
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setRemCourses(int rem_courses) {
		this.remCourses = rem_courses;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public void setAvgGrade(float avg_grade) {
		this.avgGrade = avg_grade;
	}
}