package myy803.project.dto;

public class ProfessorDTO {
	
	private String fullName;
	private String specialty;

	public ProfessorDTO(String fullname, String specialty) {
		this.fullName = fullname;
		this.specialty = specialty;
	}

	public String getFullName() {
		return fullName;
	}
	
	public String getSpecialty() {
		return specialty;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
}
