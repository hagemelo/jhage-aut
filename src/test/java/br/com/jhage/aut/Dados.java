package br.com.jhage.aut;

import java.util.HashSet;
import java.util.Set;

import br.com.jhage.aut.excecao.AutException;
import br.com.jhage.aut.helper.FormatDateHelper;
import br.com.jhage.aut.modelo.Phone;
import br.com.jhage.aut.modelo.Session;
import br.com.jhage.aut.modelo.User;

public class Dados {

	protected final Set<Phone> phones = new HashSet<Phone>();
	protected User usuarioComSessao;

	public Dados() {

		this.popularPhones();
		this.pupularUsuarioComSessao();
	}

	private void popularPhones(){
		
		Phone p = new Phone("67654323","091");
		phones.add(p);
		p = new Phone("44444444","011");
		phones.add(p);
		p = new Phone("23232323","011");
		phones.add(p);
		p = new Phone("65454554","041");
		phones.add(p);
		p = new Phone("77766776","091");
		phones.add(p);
		p = new Phone("11111111","091");
		phones.add(p);
		p = new Phone("88888888","021");
		phones.add(p);
	}
	
	private void pupularUsuarioComSessao(){
		
		this.usuarioComSessao = new User("Usuario com Sessao", "teste123");
		this.usuarioComSessao.setId(0);
		this.usuarioComSessao.setEmail("usuario@teste.com");
		this.usuarioComSessao.getPhone().add(phones.iterator().next());
		
		Session s1 = new Session(this.usuarioComSessao);
		Session s2 = new Session(this.usuarioComSessao);
		try {
			s1.setCreated(FormatDateHelper.formatarDataParaPadrao("08/12/2016", "dd/mm/yyyy"));
			s2.setCreated(FormatDateHelper.formatarDataParaPadrao("14/12/2016", "dd/mm/yyyy"));
		} catch (AutException e) {
			e.printStackTrace();
		}
		this.usuarioComSessao.getSessions().add(s1);
		this.usuarioComSessao.getSessions().add(s2);
	}

}
