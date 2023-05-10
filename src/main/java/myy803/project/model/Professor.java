package myy803.project.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="professors")
public class Professor{
	
	@Id 
	@Column(name="id", nullable=false)
	private int id;
	
	@Transient
	private User user;
	
	@Column(name="full_name", length=64)
	private String fullName;
	
	@Column(name="specialty", length=32)
	private String specialty;
	
	@OneToMany(mappedBy="professor")
	private List<Subject> subjectList;
	
	@OneToMany(mappedBy="professor")
	private List<Thesis> thesisList;
	
	
	public Professor(User _user, String fullname) {
		this.user = _user;
		this.id = _user.getId();
		this.fullName = fullname;
	}
	
	public Professor() {}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public void setFullName(String full_name) {
		this.fullName = full_name;
	}
	
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	
	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}
	
	public int getId() {
		return id;
	}
	
	public User getUser() {
		return user;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public String getSpecialty() {
		return specialty;
	}
	
	public List<Subject> getSubjectList() {
		return subjectList;
	}
	
	@Override
	public String toString() {
		return "Professor [full_name=" + fullName + ", specialty=" + specialty + "]";
	}
}
