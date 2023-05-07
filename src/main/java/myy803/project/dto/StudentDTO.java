package myy803.project.dto;

public class StudentDTO {
	
	private String fullName;
	private int remainingCourses;
	private int year;
	private float averageGrade;

	public StudentDTO(String fullname, int rem_courses, int year, float average_grade) {
		this.fullName = fullname;
		this.remainingCourses = rem_courses;
		this.year = year;
		this.averageGrade = average_grade;
	}

	//GETTERS
	public String getFullName() {
		return fullName;
	}
	
	public int getRemCourses() {
		return remainingCourses;
	}
	
	public int getYear() {
		return year;
	}
	
	public float getAvgGrade() {
		return averageGrade;
	}

	//SETTERS
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setSpecialty(int remcourses) {
		this.remainingCourses = remcourses;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public void setAvgGrade(int avg_grade) {
		this.averageGrade = avg_grade;
	}
}