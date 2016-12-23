package br.com.jhage.aut;

import org.junit.Assert;
import org.junit.Test;

import br.com.jhage.aut.excecao.AutException;
import br.com.jhage.aut.modelo.receiver.UserReceiver;

public class TransformarCollectionsTest {
	
	
	@Test
	public void validarTransformePhonetoDto() throws AutException{
		
		Dados dados = new Dados();
		
		Assert.assertNotNull(new UserReceiver(dados.usuarioComSessao).getPhones());
		Assert.assertTrue(new UserReceiver(dados.usuarioComSessao).getPhones().size()>0);
		
	}

}
