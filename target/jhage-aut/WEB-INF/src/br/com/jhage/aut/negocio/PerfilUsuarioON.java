package br.com.jhage.aut.negocio;

import java.util.Date;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.jhage.aut.dao.UserDao;
import br.com.jhage.aut.excecao.AutException;
import br.com.jhage.aut.excecao.AutExceptionCode;
import br.com.jhage.aut.helper.FormatDateHelper;
import br.com.jhage.aut.helper.SecurityHelper;
import br.com.jhage.aut.helper.ValidarHelper;
import br.com.jhage.aut.modelo.User;
import br.com.jhage.aut.modelo.response.MensagemResponse;
import br.com.jhage.aut.modelo.response.SuccessResponse;

/**
 * 
 * @author Alexsander Melo
 *
 */
@Component
public class PerfilUsuarioON {

	@Autowired
	private UserDao userDAO;

	public Response loadById(Long id, String token) {
		try {
			User user = userDAO.obterPorID(id);
			if (ValidarHelper.naoENuloENaoEVazio(token) && !ValidarHelper.enulo(user) && validarToken(user, token)) {

				if (sessaoValida(user)) {

					return Response.ok(new SuccessResponse(user), MediaType.APPLICATION_JSON).build();
				} else {

					return Response.status(Status.CONFLICT)
							.entity(new MensagemResponse(AutExceptionCode.SESSAO_INVALIDA.value())).build();
				}
			} else {

				return Response.status(Status.UNAUTHORIZED).build();
			}
		} catch (Exception e) {

			throw new WebApplicationException(e.getMessage(), Status.INTERNAL_SERVER_ERROR);
		}

	}

	private boolean validarToken(User user, String token) {
		boolean result = false;
		try {

			result = token.equals(SecurityHelper.criptografar(user.gettoken()));
		} catch (AutException e) {

			return false;
		}
		return result;
	}

	private boolean sessaoValida(User user) {

		try {
			return FormatDateHelper.isData1MenorQueData2(new Date(), FormatDateHelper.acrescenterMinutos(user.lastLogin(), 30));
		} catch (AutException e) {

			return false;
		}
	}

}
