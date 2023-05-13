package myy803.project.dto;

public class SelectApplicationDTO {
	
	public enum SelectStrategy {
	      RANDOM, AVERAGE_GRADE, REMAINING_COURSES, MANUALLY
	}
	
	float th1 = 0;
	int th2 = 46;
	private SelectStrategy strategy;
	
	public SelectApplicationDTO() {}
	
	public float getTh1() {
		return th1;
	}

	public void setTh1(float th1) {
		this.th1 = th1;
	}
	
	public int getTh2() {
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
