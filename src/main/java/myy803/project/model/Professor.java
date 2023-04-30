package myy803.project.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Professors")
public class Professor{
	
	@Id
	@Column(name="id", nullable=false)
	private User user; 			// TODO int
	
	@Column(name="full_name", length=64)
	private String full_name;
	
	@Column(name="specialty", length=32)
	private String specialty;
	
	@OneToMany(mappedBy="professor")
	private List<Subject> SubjectList;
	
	@OneToMany(mappedBy="professor")
	private List<Thesis> ThesisList;
	
	public Professor(String full_name, String specialty) {
		this.full_name = full_name;
		this.specialty = specialty;
	}
	
	public Professor() {
		
	}
	
	public void setFullName(String full_name) {
		this.full_name = full_name;
	}
	
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	
	public String getFullName() {
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
