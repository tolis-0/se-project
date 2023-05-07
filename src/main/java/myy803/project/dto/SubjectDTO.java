package myy803.project.dto;

public class SubjectDTO {
	
	private String name;
	private String objectives;
	
	public SubjectDTO(String name, String objectives) {
		this.name = name;
		this.objectives = objectives;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getObjectives() {
		return objectives;
	}
	
	public void setObjectives(String objectives) {
		this.objectives = objectives;
	}
	
}