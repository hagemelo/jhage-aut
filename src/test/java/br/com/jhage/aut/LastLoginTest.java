package br.com.jhage.aut;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import br.com.jhage.aut.excecao.AutException;
import br.com.jhage.aut.helper.FormatDateHelper;
import br.com.jhage.aut.modelo.Session;
import br.com.jhage.aut.modelo.User;

/**
 * 
 * @author Alexsander Melo
 *
 */
public class LastLoginTest {
	
	@Test
	public void validarUltimoLogin() throws AutException{
		
		Date hoje = new Date();
		User user  = new Dados().usuarioComSessao;
		Session s = new Session(user);
		s.setCreated(hoje);
		user.getSessions().add(s);
		
		System.out.println("Last Login ::" + FormatDateHelper.converterDataParaCaracter(user.lastLogin()));
		System.out.println("Hoje ::" + FormatDateHelper.converterDataParaCaracter(hoje));
		Assert.assertTrue(FormatDateHelper.isMesmadata(user.lastLogin(), hoje));
	}

}
