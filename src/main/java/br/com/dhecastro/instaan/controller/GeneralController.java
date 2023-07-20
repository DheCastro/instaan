package br.com.dhecastro.instaan.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.dhecastro.instaan.config.OpenAICompletionsConfig;
import br.com.dhecastro.instaan.models.ChatRequest;
import br.com.dhecastro.instaan.models.ChatResponse;
import br.com.dhecastro.instaan.vo.GeneralRequest;
import br.com.dhecastro.instaan.vo.GeneralResponse;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class GeneralController {
	
	@Autowired
	private final OpenAICompletionsConfig openAi;
	private static final String JSON_FORMAT = "{ response:, content: } ";
	private static final String JSON_ATTS = " response, content ";
	
	public GeneralController(OpenAICompletionsConfig openAi) {
		this.openAi = openAi;
	}
	
	private String model = "gpt-3.5-turbo-16k";

	@PostMapping("/general")
	public GeneralResponse chat(@RequestBody @Valid GeneralRequest request) {
		
		ChatRequest chatRequest = new ChatRequest(model, request.getTerm());
		
		//+ " - a resposta gerada deve vir sempre em um objeto json válido "
			//	   + " no formato " + JSON_FORMAT + " e com os atributos " + JSON_ATTS + ". E qualquer complemento da resposta deve vir no atributo content. Reforçando"
				//   		+ " que o JSON da resposta deve ser válido");
		
		// set one response by default
		chatRequest.setN(1);
		ChatResponse response = openAi.gerenateAnswer(chatRequest);

		if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
			return null;
		}
		
		GeneralResponse gr = new GeneralResponse();
		gr.setResponse(response.getChoices().get(0).getMessage().getContent());
		
		return gr;
	}
}
