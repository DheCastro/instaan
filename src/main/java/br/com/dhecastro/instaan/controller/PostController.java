package br.com.dhecastro.instaan.controller;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.dhecastro.instaan.config.OpenAICompletionsConfig;
import br.com.dhecastro.instaan.enums.PostType;
import br.com.dhecastro.instaan.exceptions.InvalidRequestException;
import br.com.dhecastro.instaan.models.ChatRequest;
import br.com.dhecastro.instaan.models.ChatResponse;
import br.com.dhecastro.instaan.vo.PostRequest;

@RestController
//@CrossOrigin(origins = "http://instaan-s3.s3-website-sa-east-1.amazonaws.com/")
@CrossOrigin(origins = "http://localhost:4200")
public class PostController {
	
	@Autowired
	private final OpenAICompletionsConfig openAi;
	
	public PostController(OpenAICompletionsConfig openAi) {
		this.openAi = openAi;
	}
	
	private String model = "gpt-3.5-turbo-16k";

	@PostMapping("/chat")
	public String chat(@RequestBody @Valid PostRequest request) {
		
		validateFieldsByPostType(request);
		
		System.out.println("PROMPT:");
		System.out.println(request.buildBody());
		
		ChatRequest chatRequest = new ChatRequest(model, request.buildBody());
		
		// set one response by default
		chatRequest.setN(1);
		ChatResponse response = openAi.gerenateAnswer(chatRequest);

		if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
			return null;
		}
		
		return response.getChoices().get(0).getMessage().getContent();
	}
	
	public void validateFieldsByPostType(PostRequest request) {
		
		if(PostType.getByCode(request.getCodeType()).equals(PostType.CARROUSEL) || PostType.getByCode(request.getCodeType()).equals(PostType.STORY)) {
			if(Objects.isNull(request.getSlidesQtd())) {
				throw new InvalidRequestException("Para os tipos [" + PostType.CARROUSEL.getDescription() + "," + PostType.STORY.getDescription() + "], o campo slidesQtd (quantidade de slides) é obrigatório.");
			}
		}
	}
}
