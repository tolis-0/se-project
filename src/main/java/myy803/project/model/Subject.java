package myy803.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="subjects")
public class Subject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable=false)
	private int id;
	
	@Column(name="professor", nullable=false)
	private int professorId;
	
	@ManyToOne
	@JoinColumn(name="professor", insertable=false, updatable=false)
	private Professor professor;
	
	@Column(name="name", nullable = false)
	private String name;
	
	@Column(name="objectives", nullable = false)
	private String objectives;
	
	@Column(name="assigned", columnDefinition = "boolean default false")
	private boolean assigned;
	
	public Subject() {}
	
	public Subject(Professor professor, String name, String objectives) {
		this.professor = professor;
		this.professorId = professor.getId();
		this.name = name;
		this.objectives = objectives;
		this.assigned = false;
	}
	
	public int getId() {
		return id;
	}
	
	public int getProfessorId() {
		return professorId;
	}
	
	public String getName() {
		return name;
	}
	
	public String getObjectives() {
		return objectives;
	}
	
	public Professor getProfessor() {
		return professor;
	}
	
	public boolean isAssigned() {
		return assigned;
	}

	public void setName(String name) {
		this.name= name ;
	}
	
	public void setObjectives(String objectives) {
		this.objectives = objectives ;
	}
	
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	public void assign() {
		this.assigned = true;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", professor=" + professorId  + ", name=" + name + ", objectives=" + objectives + "]";
	}
	
}
