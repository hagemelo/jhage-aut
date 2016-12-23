package br.com.jhage.aut.servico;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.jhage.aut.constante.UTF8;
import br.com.jhage.aut.dto.DefaultDto;
import br.com.jhage.aut.dto.LoginDto;
import br.com.jhage.aut.dto.MensagemDto;
import br.com.jhage.aut.dto.SuccessDto;
import br.com.jhage.aut.excecao.AutException;
import br.com.jhage.aut.negocio.LoginON;

/**
 * 
 * @author Alexsander Melo
 * @since 18/12/2016
 *
 */

@Path("login")
@Component @Transactional
public class LoginServico {
	
	@Autowired
	private LoginON on;

	@POST
	@Consumes(UTF8.JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response  login(LoginDto loginDto){
		
		DefaultDto result;
		try {
			result  = new SuccessDto(on.login(loginDto));
			return Response.ok(result, MediaType.APPLICATION_JSON).build();
			
		} catch (AutException e) {
			
			switch (e.getCode()) {
			case USER_NAO_AUTORIZADO:
				
				throw new WebApplicationException(401);
			default:
				
				return Response.ok(new MensagemDto(e.getCode().value()), MediaType.APPLICATION_JSON).build();
			}
			
		}
	}

}
