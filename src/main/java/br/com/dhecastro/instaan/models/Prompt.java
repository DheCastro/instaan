package br.com.dhecastro.instaan.models;

import javax.validation.constraints.NotNull;

import br.com.dhecastro.instaan.enums.PromptType;
import lombok.Data;

@Data
//@Entity
//@Table(name = "instaan_post")
public class Prompt {

	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	//@Enumerated(EnumType.ORDINAL)
	private PromptType type;
	
	//commons
	private String description;
}
