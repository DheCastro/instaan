package br.com.dhecastro.instaan.models;

import javax.validation.constraints.NotNull;

import br.com.dhecastro.instaan.enums.PostTone;
import br.com.dhecastro.instaan.enums.PostType;
import lombok.Data;

@Data
//@Entity
//@Table(name = "instaan_post")
public class Post {

	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//commons
	private PostTone tone;
	private String theme;
	@NotNull
	//@Enumerated(EnumType.ORDINAL)
	private PostType postType;
	
	
	//unique post
    //private String imageSuggest;
	
	//carrousel
	private Integer slidesQtd;
	//private String slideText;
	//private String slideImage;
	
	//long video or reels
	//private String script;
	//private String scenesSuggest;
}
