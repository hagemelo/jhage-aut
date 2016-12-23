package br.com.jhage.aut.servico;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.jhage.aut.dao.UserDao;
import br.com.jhage.aut.dto.DefaultDto;
import br.com.jhage.aut.dto.SuccessDto;

/**
 * 
 * @author Alexsander Melo
 * @since 18/12/2016
 *
 */

@Path("perfilusuario")
@Component @Transactional
public class PerfilUsuarioServico {
	
	@Autowired
	private UserDao userDAO;
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public DefaultDto aut(@PathParam("id") Long id){
		
		return new SuccessDto(userDAO.obterPorID(id));
	}

}
