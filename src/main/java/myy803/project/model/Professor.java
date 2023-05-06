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
@Table(name="professors")
public class Professor{
	
	@Id 
	@Column(name="id", nullable=false)
	private int id;
	
	@OneToOne
	@PrimaryKeyJoinColumn(name="id", referencedColumnName="id")
	private User user;
	
	@Column(name="full_name", length=64)
	private String fullName;
	
	@Column(name="specialty", length=32)
	private String specialty;
	
	@OneToMany(mappedBy="professor")
	private List<Subject> subjectList;
	
	@OneToMany(mappedBy="professor")
	private List<Thesis> thesisList;
	
	public Professor(int id, String fullname) {
		this.id = id;
		this.fullName = fullname;
	}
	
	public Professor() {}
	
	public void setFullName(String full_name) {
		this.fullName = full_name;
	}
	
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	
	public String getFullName() {
		return this.fullName;
	}
	
	public String getSpecialty() {
		return this.specialty;
	}
	
	
	@Override
	public String toString() {
		return "Professor [full_name=" + fullName + ", specialty=" + specialty + "]";
	}
}
