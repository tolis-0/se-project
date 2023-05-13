package myy803.project.dto;

public class SelectApplicationDTO {
	
	public enum SelectStrategy {
	      RANDOM, AVERAGE_GRADE, REMAINING_COURSES, MANUALLY
	}
	
	Integer th1;
	Integer th2;
	private SelectStrategy strategy;
	
	public SelectApplicationDTO() {}
	
	public Integer getTh1() {
		return th1;
	}

	public void setTh1(int th1) {
		this.th1 = th1;
	}
	
	public Integer getTh2() {
		return th2;
	}

	public void setTh2(int th2) {
		this.th2 = th2;
	}
	

	public SelectStrategy getStrategy() {
		return strategy;
	}
	
	public void setStrategy(SelectStrategy strategy) {
		this.strategy = strategy;
	}
	
}
