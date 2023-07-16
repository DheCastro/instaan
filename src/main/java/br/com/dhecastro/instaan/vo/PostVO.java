package br.com.dhecastro.instaan.vo;

import lombok.Data;

@Data
public class PostVO {

	private String headline;
	private String subtitle;
	private String hashtag;
	
	//unique post
	private String imageSuggest;
	
	//carrousel
	private Integer slidesQtd;
	private String slideText;
	private String slideImage;
	
	//long video or reels
	private String script;
	private String scenesSuggest;
}
