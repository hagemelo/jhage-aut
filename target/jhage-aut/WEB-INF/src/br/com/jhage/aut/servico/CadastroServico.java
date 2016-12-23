package br.com.jhage.aut.servico;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.jhage.aut.constante.UTF8;
import br.com.jhage.aut.dto.DefaultDto;
import br.com.jhage.aut.dto.MensagemDto;
import br.com.jhage.aut.dto.SuccessDto;
import br.com.jhage.aut.dto.UserDto;
import br.com.jhage.aut.excecao.AutException;
import br.com.jhage.aut.negocio.CadastroON;

/**
 * 
 * @author Alexsander Melo
 * @since 18/12/2016
 *
 */

@Path("cadastro")
@Component @Transactional
public class CadastroServico {
	
	@Autowired
	private CadastroON on;
	
	@POST
	@Consumes(UTF8.JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public DefaultDto saveUsuario(UserDto user){
		
		try {
			return (DefaultDto) new SuccessDto(on.cadastrarUser(user.extractUser()));
		} catch (AutException e) {
			
			return new MensagemDto(e.getCode().value());
		}
	}

}
