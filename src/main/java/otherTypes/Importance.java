package otherTypes;

public enum Importance {
	High(1),
	Medium(2),
	Low(3);
	
	public final int code;
	
	Importance(int code) {
		this.code = code;
	}
	public int GetCode() {
		return code;
	}
	
	public static Importance fromCode(int code) {
        for (Importance status : values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }
}

