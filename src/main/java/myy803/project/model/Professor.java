package myy803.project.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="professors")
public class Professor{
	
	private String full_name;
	private String specialty;
	
	public Professor(String full_name, String specialty) {
		this.full_name = full_name;
		this.specialty = specialty;
	}
	
	public Professor() {
		
	}
	
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	
	public String getFull_name() {
		return this.full_name;
	}
	
	public String getSpecialty() {
		return this.specialty;
	}
	
	
	@Override
	public String toString() {
		return "Professor [full_name=" + full_name + ", specialty=" + specialty + "]";
	}
}
