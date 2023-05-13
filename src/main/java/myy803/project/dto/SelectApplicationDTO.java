package myy803.project.dto;

public class SelectApplicationDTO {
	
	enum SelectStrategy {
	      RANDOM, AVERAGE_GRADE, REMAINING_COURSES, THRESHOLD
	}
	
	private SelectStrategy selectStrategy;
	
	public SelectApplicationDTO () {}

	public SelectStrategy getSelectMethod() {
		return selectStrategy;
	}
	
	public void setSelectStrategy(SelectStrategy selectStrategy) {
		this.selectStrategy = selectStrategy;
	}
	
}
