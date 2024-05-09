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

	private static final String PROMPT_BEGIN = "Você é um profissional renomado de arquitetura e decoração de interiores, e também é especializado em marketing nessa área. "
			+ "Desenvolva um post para Instagram de um escritório de arquitetura com as seguintes características: ";
	
	private static final String PROMPT_END = "Sempre que você mencionar a palavra 'dicas', liste as dicas explicitamente na resposta. E não saia do assunto arquitetura e decoração de interiores.";
	
	
	private static final String HEADLINE = " - Defina Headline , sempre uma opção que tenha o melhor engajamento possível ";
	private static final String LEGENDA = " - Descreva a Legenda completa para o post, mas que seja uma legenda que não mencione o termo 'dicas'. ";
	private static final String IMAGEM_POST = " - Descreva com um texto o que deve estar contido na imagem que deve estar no post, a imagem do slide, e essa descrição deve ter relação com a legenda do post ";
	private static final String TEXTO_SLIDE = " - Defina o texto que deve ter em cada slide ";
	private static final String IMAGEM_SLIDE = " - Descreva com um texto o que deve estar contido em cada imagem do carrousel do post, as imagens dos slides do carrousel, e as descrições devem ter relação com a legenda do post ";
	private static final String HASHTAGS = " - Sugira 4 hashtags que podem agregar engajamento ";
	private static final String SEQUENCIA_CARROUSEL = " - A sequência do carrousel deve ter exatamente ";
	private static final String SEQUENCIA_STORIES = " - A sequência dos stories deve ter exatamente ";
	private static final String AVISO = " - Importante: caso você mencione dicas ou passo-a-passo, essas dicas ou passo-a-passo devem estar contidas na legenda do post ";
	
	//Reels and Long Video
	private static final String SCENES_SUGGEST = " - Sugira as cenas, se é uma pessoa falando ou cenas gravadas com um narrador ";
	private static final String SCRIPT_SUGGEST = " - Sugira o roteiro com o que deve ser dito em cada cena sugerida e o tempo de cada cena ";
	private static final String SCRIPT_TIME_MIN = " - Ao sugerir o roteiro e cenas, considere que o vídeo deve ter no mínimo ";
	private static final String SCRIPT_TIME_MAX = " - Ao sugerir o roteiro e cenas, considere que o tempo total, somando todas as cenas, deve ser no máximo de ";
	
	private static final String JSON_FORMAT = "{ headline:, legenda:, tipo:, imagem_slide:, hashtags: } ";
	private static final String JSON_ATTS = " headline, legenda, tipo, imagem_slide, hashtags ";
	
	private static final String JSON_FORMAT_SCENE = "{ headline:, legenda:, tipo:, scene_suggest:, script_suggest:, scene_time:, hashtags: } ";
	private static final String JSON_ATTS_SCENE = " headline, legenda, scene_suggest, script_suggest, scene_time, hashtags ";
	
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
			return PROMPT_BEGIN
					   + " - O tema do post deve ser: " + this.theme
					   + " - O tom do post deve ser: " + PostTone.getByCode(this.codeTone).getDescription()
					   + HEADLINE + LEGENDA + AVISO
					   + " - O post deve ser do tipo: " + PostType.getByCode(this.codeType).getDescription()
					   + IMAGEM_POST + HASHTAGS
					   + " - e a resposta gerada deve vir sempre em um objeto json "
					   + " no formato " + JSON_FORMAT + " e com os atributos " + JSON_ATTS;
		
		}else if(this.getCodeType().equals(PostType.CARROUSEL.getCode())) {
			return PROMPT_BEGIN
					   + " - O tema do post deve ser: " + this.theme
					   + " - O tom do post deve ser: " + PostTone.getByCode(this.codeTone).getDescription()
					   + HEADLINE + LEGENDA
					   + " - O post deve ser do tipo: " + PostType.getByCode(this.codeType).getDescription()
					   + SEQUENCIA_CARROUSEL + this.slidesQtd + " slides "
					   + TEXTO_SLIDE + IMAGEM_SLIDE + HASHTAGS
					   + " - e a resposta gerada deve vir sempre em uma lista de objetos json "
					   + " no formato " + JSON_FORMAT + " e cada objeto json deve conter os atributos " + JSON_ATTS;
		
		}else if(this.getCodeType().equals(PostType.STORY.getCode())) {
			return "Desenvolva uma sequência de stories para Instagram de um escritório de arquitetura com as seguintes características: "
					   + " - O tema do stories deve ser: " + this.theme  
					   + " - O tom do post deve ser: " + PostTone.getByCode(this.codeTone).getDescription()
					   + HEADLINE + LEGENDA
					   + " - O post deve ser do tipo: " + PostType.getByCode(this.codeType).getDescription()
					   + SEQUENCIA_STORIES + this.slidesQtd + " slides "
					   + TEXTO_SLIDE + IMAGEM_SLIDE + HASHTAGS
					   //+ " - Sugira uma chamada de ação "
					   + " - e a resposta gerada deve vir sempre em uma lista de objetos json "
			           + " no formato " + JSON_FORMAT + " e cada objeto json deve conter os atributos " + JSON_ATTS
			           + " o nó mais externo do json deve ter um atributo chamada cta e nele deve estar contida uma chamada de ação baseada no post ";
			
		}else if(this.getCodeType().equals(PostType.REELS.getCode())) {
			return PROMPT_BEGIN
					   + " - O tema do post deve ser: " + this.theme
					   + " - O tom do post deve ser: " + PostTone.getByCode(this.codeTone).getDescription()
					   + HEADLINE + LEGENDA
					   + " - O post deve ser do tipo: " + PostType.getByCode(this.codeType).getDescription()
					   + SCENES_SUGGEST + SCRIPT_SUGGEST + SCRIPT_TIME_MAX + " quarenta e cinco segundos " + HASHTAGS
					   + " - e a resposta gerada deve vir sempre em um objeto json "
					   + " no formato " + JSON_FORMAT_SCENE + " e com os atributos " + JSON_ATTS_SCENE;
		}
		
		return PROMPT_BEGIN
				+ " - O tema do post deve ser: " + this.theme
				   + " - O tom do post deve ser: " + PostTone.getByCode(this.codeTone).getDescription()
				   + HEADLINE + LEGENDA
				   + " - O post deve ser do tipo: " + PostType.getByCode(this.codeType).getDescription()
				   + SCENES_SUGGEST + SCRIPT_SUGGEST + SCRIPT_TIME_MIN + " um minuto e meio " + HASHTAGS
				   + " - e a resposta gerada deve vir sempre em um objeto json "
				   + " no formato " + JSON_FORMAT_SCENE + " e com os atributos " + JSON_ATTS_SCENE;
	}
}
