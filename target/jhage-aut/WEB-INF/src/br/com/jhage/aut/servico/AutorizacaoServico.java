package br.com.jhage.aut.servico;
/**
 * 
 * @author Alexsander Melo
 * @since 18/12/2016
 *
 */

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.jhage.aut.constante.UTF8;
import br.com.jhage.aut.dao.UserDao;
import br.com.jhage.aut.excecao.AutException;
import br.com.jhage.aut.modelo.receiver.LoginReceiver;
import br.com.jhage.aut.modelo.receiver.UserReceiver;
import br.com.jhage.aut.modelo.response.MensagemResponse;
import br.com.jhage.aut.modelo.response.SuccessResponse;
import br.com.jhage.aut.negocio.CadastroON;
import br.com.jhage.aut.negocio.LoginON;

@Path("autorizador")
@Component @Transactional
public class AutorizacaoServico {
	
	@Autowired
	private CadastroON cadon;
	
	@Autowired
	private LoginON loginon;
	
	@Autowired
	private UserDao userDAO;
	
	
	@Path("cadastro")
	@POST
	@Consumes(UTF8.JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveUsuario(UserReceiver userReceiver){
		
		Response result = cadon.cadastrarUser(userReceiver);
		return result;
	}

	@Path("login")
	@POST
	@Consumes(UTF8.JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response  login(LoginReceiver loginReceiver){
		
		try {
			return Response.ok(new SuccessResponse(loginon.login(loginReceiver)), MediaType.APPLICATION_JSON).build();
			
		} catch (AutException e) {
			
			switch (e.getCode()) {
			case USER_NAO_AUTORIZADO:
				
				throw new WebApplicationException(401);
			default:
				
				return Response.ok(new MensagemResponse(e.getCode().value()), MediaType.APPLICATION_JSON).build();
			}
			
		}
	}
	
	
	@GET
	@Path("/perfilusuario/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response aut(@PathParam("id") Long id, @HeaderParam("token") String token){
		
		System.out.println("Token:: " + token);
		return Response.ok(new SuccessResponse(userDAO.obterPorID(id)), MediaType.APPLICATION_JSON).build();
	}
}
