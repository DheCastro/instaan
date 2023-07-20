package br.com.dhecastro.instaan.vo;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * 
 * @author Dhellano
 *
 */
@Data
public class GeneralRequest {
	
	@NotNull(message = "O campo term (termo) é obrigatório.")
	private String term;
	
}
