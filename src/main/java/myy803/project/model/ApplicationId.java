package myy803.project.model;

import java.io.Serializable;

public class ApplicationId implements Serializable {
    private static final long serialVersionUID = 1L;
    
	private Long userIdFrom;
    private Long userIdTo;
    
	public Long getUserIdTo() {
		return userIdTo;
	}
	
	public void setUserIdTo(Long userIdTo) {
		this.userIdTo = userIdTo;
	}
	
	public Long getUserIdFrom() {
		return userIdFrom;
	}
	
	public void setUserIdFrom(Long userIdFrom) {
		this.userIdFrom = userIdFrom;
	}
}