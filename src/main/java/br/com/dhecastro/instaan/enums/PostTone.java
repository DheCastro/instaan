package br.com.dhecastro.instaan.enums;

public enum PostTone {
	
	HAPPY(1, "de felicidade"), 
	EMPATHETIC(2, "de empatia"),
	AUTHORITARIAN(3, "de autoridade"),
	MOTIVATIONAL(4, "de motivação");

	private Integer code;
	private String description;

	PostTone(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static PostTone getByCode(Integer code) {

		switch (code) {
		case 1:
			return PostTone.HAPPY;
		case 2:
			return PostTone.EMPATHETIC;
		case 3:
			return PostTone.AUTHORITARIAN;
		case 4:
			return PostTone.MOTIVATIONAL;	
		default:
			return PostTone.HAPPY;
		}
	}
}
