package br.com.jhage.aut.negocio;

import javax.persistence.PersistenceException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.jhage.aut.dao.UserDao;
import br.com.jhage.aut.excecao.AutException;
import br.com.jhage.aut.excecao.AutExceptionCode;
import br.com.jhage.aut.modelo.Session;
import br.com.jhage.aut.modelo.User;
import br.com.jhage.aut.modelo.receiver.UserReceiver;
import br.com.jhage.aut.modelo.response.MensagemResponse;
import br.com.jhage.aut.modelo.response.SuccessResponse;

@Component
public class CadastroON {

	@Autowired
	private UserDao userDAO;

	public Response cadastrarUser(UserReceiver userRececiver) {
		Response result;
		try {
			if (userDAO.isNotExistEmail(userRececiver.getEmail())){
				User user = userRececiver.extractUser();
				Session session = new Session(user);
				user.getSessions().add(session);
				System.out.println("Token Salvo:: " + user.gettoken());
				user = userDAO.salvarRetornar(user);
	
				result =  Response.ok(new SuccessResponse(user)).build();
			}else{
				return Response.status(Status.CONFLICT).entity(new MensagemResponse(AutExceptionCode.EMAIL_JA_EXISTENTE.value())). build();
			}
			return result;
		} catch (PersistenceException | AutException | IllegalStateException e) {

			throw new WebApplicationException(e.getMessage(), Status.INTERNAL_SERVER_ERROR);
		}
	}

}
