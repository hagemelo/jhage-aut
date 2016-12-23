package br.com.jhage.aut.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.jhage.aut.dao.UserDao;
import br.com.jhage.aut.excecao.AutException;
import br.com.jhage.aut.excecao.AutExceptionCode;
import br.com.jhage.aut.helper.SecurityHelper;
import br.com.jhage.aut.modelo.User;
import br.com.jhage.aut.modelo.receiver.LoginReceiver;

/**
 * 
 * @author Alexsander Melo
 *
 */
@Component
public class LoginON {
	
	@Autowired
	private UserDao userDAO;
	
	
	public User login(LoginReceiver login) throws AutException{
		
		User result = userDAO.login(login.getEmail());
		
		if(SecurityHelper.criptografar(login.getPassword()).equals(result.getPassword())){
			
			return result;
		}else{
			
			throw new AutException(AutExceptionCode.USER_NAO_AUTORIZADO);
		}
	}

}
