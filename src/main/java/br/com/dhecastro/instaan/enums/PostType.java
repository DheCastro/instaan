package br.com.dhecastro.instaan.enums;

public enum PostType {

	UNIQUEIMAGE(1, "Post de Imagem Única"), 
	CARROUSEL(2, "Carrousel"),
	LONGVIDEO(3, "Vídeo Longo"),
	REELS(4, "Reels"),
	STORY(5, "Story");

	private Integer code;
	private String description;

	PostType(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static PostType getByCode(Integer code) {

		switch (code) {
		case 1:
			return PostType.UNIQUEIMAGE;
		case 2:
			return PostType.CARROUSEL;
		case 3:
			return PostType.LONGVIDEO;
		case 4:
			return PostType.REELS;
		case 5:
			return PostType.STORY;	
		default:
			return PostType.UNIQUEIMAGE;
		}
	}
}
