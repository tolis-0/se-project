package myy803.project.dto;

public class ThesisDTO {
	
	private float impGrade;
	private float repGrade;
	private float presGrade;
	
	public ThesisDTO() {}
	
	public ThesisDTO(float impGrade, float repGrade, float presGrade) {
		this.impGrade = impGrade;
		this.repGrade = repGrade;
		this.presGrade = presGrade;
	}
	
	public float getImpGrade() {
		return impGrade;
	}
	
	public void setImpGrade(float impGrade) {
		this.impGrade = impGrade;
	}
	
	public float getPresGrade() {
		return presGrade;
	}
	
	public void setPresGrade(float presGrade) {
		this.presGrade = presGrade;
	}

	public float getRepGrade() {
		return repGrade;
	}

	public void setRepGrade(float repGrade) {
		this.repGrade = repGrade;
	}
	
}
