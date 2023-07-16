package br.com.dhecastro.instaan.enums;

public enum PromptType {

	POST(1, "para post");

	private Integer code;
	private String description;

	PromptType(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static PromptType getByCode(Integer code) {

		switch (code) {
		case 1:
			return PromptType.POST;	
		default:
			return PromptType.POST;
		}
	}
}
