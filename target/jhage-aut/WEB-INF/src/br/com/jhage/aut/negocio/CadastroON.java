package br.com.jhage.aut.negocio;

import javax.ws.rs.core.Response;

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
			User user = userRececiver.extractUser();
			Session session = new Session(user);
			user.getSessions().add(session);

			user = userDAO.salvarRetornar(user);

			result = Response.ok(new SuccessResponse(user)).build();
			return result;
		} catch (AutException e) {

			result = Response.ok(new MensagemResponse(AutExceptionCode.EMAIL_JA_EXISTENTE.value())).build();
			return result;
		}
	}

}
