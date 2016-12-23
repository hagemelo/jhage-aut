package br.com.jhage.aut.negocio;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author Alexsander Melo
 *
 */

@Configuration
public class NegocioConfig {

	@Bean
	public CadastroON cadastroON() {
		return new CadastroON();
	}

}
