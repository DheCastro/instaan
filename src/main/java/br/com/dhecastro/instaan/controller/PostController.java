package br.com.dhecastro.instaan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.dhecastro.instaan.config.OpenAICompletionsConfig;
import br.com.dhecastro.instaan.models.ChatRequest;
import br.com.dhecastro.instaan.models.ChatResponse;
import br.com.dhecastro.instaan.vo.PostRequest;

@RestController
public class PostController {
	@Autowired
	private final OpenAICompletionsConfig openAi;
	
	//@Autowired
	//private AssuntoService assuntoService;
	
	public PostController(OpenAICompletionsConfig openAi) {
		this.openAi = openAi;
	}
	
	private String model = "gpt-3.5-turbo-16k";

	@GetMapping("/chat")
	public String chat(@RequestBody PostRequest request) {
		
		//AssuntoVO assunto = assuntoService.getById(request.getIdAssunto());
		
		// Carrousel
		/*ChatRequest chatRequest = new ChatRequest(model, "Desenvolva um post para Instagram com o tema: como organizar um quarto de bebê"
													   + " - O tom do post deve ser: de autoridade "
													   + " - O post deve ser do tipo: carrousel "
													   + " - A sequência do carrousel deve ter exatamente 4 slides "
													   + " - Defina Headline , sempre uma opção que tenha o melhor engajamento possível "
													   + " - Defina Legenda do post "
													   + " - Defina o texto que deve ter em cada slide "
													   + " - Defina a imagem que deve ter em cada slide "
													   + " - Sugira 4 hashtags que podem agregar engajamento");*/
		
		//Imagem única
		/*ChatRequest chatRequest = new ChatRequest(model, "Desenvolva um post para Instagram com o tema: como organizar um quarto de bebê"
													   + " - O tom do post deve ser: de autoridade "
													   + " - Defina Headline , sempre uma opção que tenha o melhor engajamento possível "
													   + " - Defina Legenda do post "
													   + " - Importante: lembre-se que qualquer dica ou passo-a-passo deve fazer parte da legenda do post "
													   + " - O post deve ser do tipo: post de imagem única "
													   + " - Defina a imagem do post "
													   + " - Sugira 4 hashtags que podem agregar engajamento");*/
		
		//Reels
		/*ChatRequest chatRequest = new ChatRequest(model, "Desenvolva um post para Instagram com o tema: como organizar um quarto de bebê"
													   + " - O tom do post deve ser: de autoridade "
													   + " - O post deve ser do tipo: reels "
													   + " - Defina Headline , sempre uma opção que tenha o melhor engajamento possível "
													   + " - Defina Legenda do post "
													   + " - Sugira as cenas, se é uma pessoa falando ou cenas gravadas com um narrador "
													   + " - Sugira o roteiro com o que deve ser dito em cada cena sugerida e o tempo de cada cena "
													   + " - Ao sugerir o roteiro e cenas, considere que o tempo total, somando todas as cenas, deve ser no máximo de quarenta e cinco segundos "
													   + " - Sugira 4 hashtags que podem agregar engajamento");*/
		
		//Video longo
		/*ChatRequest chatRequest = new ChatRequest(model, "Desenvolva um post para Instagram com o tema: como organizar um quarto de bebê"
													   + " - O tom do post deve ser: de autoridade "
													   + " - O post deve ser do tipo: vídeo longo "
													   + " - Defina Headline , sempre uma opção que tenha o melhor engajamento possível "
													   + " - Defina Legenda do post "
													   + " - Sugira o roteiro com o que deve ser dito "
													   + " - Sugira as cenas, se é uma pessoa falando ou cenas gravadas com um narrador "
													   + " - Ao sugerir o roteiro e cenas, considere que o vídeo deve ter no mínimo um minuto e meio "
													   + " - Sugira 4 hashtags que podem agregar engajamento");*/
		
		//Story
		ChatRequest chatRequest = new ChatRequest(model, "Desenvolva uma sequência de stories para Instagram com o tema: como organizar um quarto de bebê"
													   + " - O tom dos stories deve ser: de autoridade "
													   + " - O post deve ser do tipo: story "
													   + " - A sequência dos stories deve ter exatamente 4 slides "
													   + " - Defina Headline , sempre uma opção que tenha o melhor engajamento possível "
													   + " - Defina o texto que deve ter em cada slide "
													   + " - Defina a imagem que deve ter em cada slide "
													   + " - Sugira uma chamada de ação");
		
		// set one response by default
		chatRequest.setN(1);

		// call the API
		ChatResponse response = openAi.gerenateAnswer(chatRequest);

		if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
			return "No response";
		}

		// return the first response
		return response.getChoices().get(0).getMessage().getContent();
	}
}
