package br.com.dhecastro.instaan.config;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.dhecastro.instaan.models.ChatRequest;
import br.com.dhecastro.instaan.models.ChatResponse;

@Component
public class OpenAICompletionsConfig {
	private static final String OPENAI_URL = "https://api.openai.com/v1/chat/completions";

	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private final String apiKey = "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";
	private final RestTemplate restTemplate = new RestTemplate();

	public ChatResponse gerenateAnswer(ChatRequest chatrequest) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + apiKey);

		// We are including only some of the parameters to the json request
		String requestJson = "{\"messages\":" + gson.toJson(chatrequest.getMessages()) + ", \"model\":\"" + chatrequest.getModel() + "\",\"n\":" + 1 + "}";

		HttpEntity<String> request = new HttpEntity<>(requestJson, headers);
		ResponseEntity<ChatResponse> response = restTemplate.postForEntity(OPENAI_URL, request, ChatResponse.class);
		return response.getBody();
	}
}
