package myy803.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Subjects")
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

}
