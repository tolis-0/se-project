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
	
	@ManyToOne
	@JoinColumn(name = "professor", nullable=false)
	private Professor professor;
	
	@Column(name="name", nullable = false)
	private String name;
	
	@Column(name="objectives", nullable = false)
	private String objectives;
	
	@Column(name="assigned", columnDefinition = "boolean default false")
	private boolean assigned;
	
	@OneToMany(mappedBy="subject")
	private List<Application> applications;
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

}
