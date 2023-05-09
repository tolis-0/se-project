package myy803.project.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="subjects")
public class Subject {
	
	@Id
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
	
	@OneToMany(mappedBy="subject")
	private List<Application> applications;
	
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

	public void setName(String name) {
		this.name= name ;
	}
	
	public void setObjectives(String objectives) {
		this.objectives = objectives ;
	}

}
