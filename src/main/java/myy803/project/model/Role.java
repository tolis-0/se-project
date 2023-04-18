package myy803.project.model;

public enum Role {
	STUDENT("Student"),
	PROFESSOR("Professor"),
    ADMIN("Admin");

    private final String value;

    private Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
