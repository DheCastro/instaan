package br.com.dhecastro.instaan.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	
	@Bean
    public ModelMapper modelMapper() {
		ModelMapper mm = new ModelMapper();
		mm.getConfiguration().setAmbiguityIgnored(true); 
		mm.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        
		return mm;
    }
}

