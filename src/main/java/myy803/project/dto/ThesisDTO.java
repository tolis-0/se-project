package myy803.project.dto;

public class ThesisDTO {
	
	private float impl_Grade;
	private float rep_Grade;
	private float pres_Grade;
	
	public ThesisDTO() {}
	
	public ThesisDTO(float impl_Grade, float rep_Grade, float pres_Grade) {
		this.impl_Grade = impl_Grade;
		this.rep_Grade = rep_Grade;
		this.pres_Grade = pres_Grade;
	}
	
	public float getImplGrade() {
		return impl_Grade;
	}
	
	public float getRepGrade() {
		return rep_Grade;
	}
	
	public float getPresGrade() {
		return pres_Grade;
	}
	
	public void setImplGrade(float impl_Grade) {
		this.impl_Grade = impl_Grade;
	}
	
	public void setRepGrade(float rep_Grade) {
		this.rep_Grade = rep_Grade;
	}
	
	public void setPresGrade(float pres_Grade) {
		this.pres_Grade = pres_Grade;
	}	
}