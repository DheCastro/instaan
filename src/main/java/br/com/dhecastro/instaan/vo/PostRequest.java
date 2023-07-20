package br.com.dhecastro.instaan.vo;

import javax.validation.constraints.NotNull;

import br.com.dhecastro.instaan.enums.PostTone;
import br.com.dhecastro.instaan.enums.PostType;
import lombok.Data;

/**
 * Podem ser posts de imagem única, carrousel, vídeo longo, reels ou stories
 * 
 * @author Dhellano
 *
 */
@Data
public class PostRequest {

	private static final String PROMPT_BEGIN = "Desenvolva um post para Instagram de um escritório de arquitetura com as seguintes características: ";
	
	private static final String HEADLINE = " - Defina Headline , sempre uma opção que tenha o melhor engajamento possível ";
	private static final String LEGENDA = " - Defina Legenda do post ";
	private static final String IMAGEM_POST = " - Descreva com um texto o que deve estar contido na imagem que deve estar no post, a imagem do slide, e essa descrição deve ter relação com a legenda do post ";
	private static final String TEXTO_SLIDE = " - Defina o texto que deve ter em cada slide ";
	private static final String IMAGEM_SLIDE = " - Defina a imagem que deve ter em cada slide ";
	private static final String HASHTAGS = " - Sugira 4 hashtags que podem agregar engajamento ";
	private static final String SEQUENCIA_CARROUSEL = " - A sequência do carrousel deve ter exatamente ";
	private static final String SEQUENCIA_STORIES = " - A sequência dos stories deve ter exatamente ";
	private static final String AVISO = " - Importante: lembre-se que qualquer dica ou passo-a-passo deve fazer parte da legenda do post ";
	
	//Reels and Long Video
	private static final String SCENES_SUGGEST = " - Sugira as cenas, se é uma pessoa falando ou cenas gravadas com um narrador ";
	private static final String SCRIPT_SUGGEST = " - Sugira o roteiro com o que deve ser dito em cada cena sugerida e o tempo de cada cena ";
	private static final String SCRIPT_TIME_MIN = " - Ao sugerir o roteiro e cenas, considere que o vídeo deve ter no mínimo ";
	private static final String SCRIPT_TIME_MAX = " - Ao sugerir o roteiro e cenas, considere que o tempo total, somando todas as cenas, deve ser no máximo de ";
	
	private static final String JSON_FORMAT = "{ headline:, legenda:, tipo:, imagem_slide:, hashtags: } ";
	private static final String JSON_ATTS = " headline, legenda, tipo, imagem_slide, hashtags ";
	
	//commons and mandatory fields
	@NotNull(message = "O campo codeTone (código do tom) é obrigatório.")
	private Integer codeTone;
	
	@NotNull(message = "O campo theme (tema) é obrigatório.")
	private String theme;
	
	@NotNull(message = "O campo codeType (código do tipo) é obrigatório.")
	private Integer codeType;
	
	//just carrousel and story types
	private Integer slidesQtd;
	
	public String buildBody() {
		
		if(this.getCodeType().equals(PostType.UNIQUEIMAGE.getCode())) {
			return PROMPT_BEGIN + this.theme
					   + " - O tema do post deve ser: " + this.theme
					   + " - O tom do post deve ser: " + PostTone.getByCode(this.codeTone).getDescription()
					   + HEADLINE + LEGENDA + AVISO
					   + " - O post deve ser do tipo: " + PostType.getByCode(this.codeType).getDescription()
					   + IMAGEM_POST + HASHTAGS
					   + " - e a resposta gerada deve vir sempre em um objeto json "
					   + " no formato " + JSON_FORMAT + " e com os atributos " + JSON_ATTS;
		
		}else if(this.getCodeType().equals(PostType.CARROUSEL.getCode())) {
			return PROMPT_BEGIN + this.theme
					   + " - O tema do post deve ser: " + this.theme
					   + " - O tom do post deve ser: " + PostTone.getByCode(this.codeTone).getDescription()
					   + " - O tom do post deve ser: " + PostTone.getByCode(this.codeTone).getDescription()
					   + " - O post deve ser do tipo: " + PostType.getByCode(this.codeType).getDescription()
					   + SEQUENCIA_CARROUSEL + this.slidesQtd + " slides "
					   + HEADLINE + LEGENDA + TEXTO_SLIDE + IMAGEM_SLIDE + HASHTAGS;
		
		}else if(this.getCodeType().equals(PostType.STORY.getCode())) {
			return "Desenvolva uma sequência de stories para Instagram de um escritório de arquitetura com as seguintes características: " + this.theme
					   + " - O tema do stories deve ser: " + this.theme  
					   + " - O tom do post deve ser: " + PostTone.getByCode(this.codeTone).getDescription()
					   + " - O post deve ser do tipo: " + PostType.getByCode(this.codeType).getDescription()
					   + SEQUENCIA_STORIES + this.slidesQtd + " slides "
					   + HEADLINE + TEXTO_SLIDE + IMAGEM_SLIDE
					   + " - Sugira uma chamada de ação";
			
		}else if(this.getCodeType().equals(PostType.REELS.getCode())) {
			return PROMPT_BEGIN + this.theme
					   + " - O tema do post deve ser: " + this.theme
					   + " - O tom do post deve ser: " + PostTone.getByCode(this.codeTone).getDescription()
					   + " - O tom do post deve ser: " + PostTone.getByCode(this.codeTone).getDescription()
					   + " - O post deve ser do tipo: " + PostType.getByCode(this.codeType).getDescription()
					   + HEADLINE + LEGENDA + SCENES_SUGGEST + SCRIPT_SUGGEST + SCRIPT_TIME_MAX + " quarenta e cinco segundos "
					   + HASHTAGS;
		}
		
		return PROMPT_BEGIN + this.theme
				   + " - O tema do post deve ser: " + this.theme
				   + " - O tom do post deve ser: " + PostTone.getByCode(this.codeTone).getDescription()
				   + " - O tom do post deve ser: " + PostTone.getByCode(this.codeTone).getDescription()
				   + " - O post deve ser do tipo: " + PostType.getByCode(this.codeType).getDescription()
				   + HEADLINE + LEGENDA + SCENES_SUGGEST + SCRIPT_SUGGEST + SCRIPT_TIME_MIN + " um minuto e meio "
				   + HASHTAGS;
	}
}
