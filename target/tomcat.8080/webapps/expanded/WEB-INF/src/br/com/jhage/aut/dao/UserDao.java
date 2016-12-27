package br.com.jhage.aut.dao;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.springframework.stereotype.Repository;

import br.com.jhage.aut.excecao.AutException;
import br.com.jhage.aut.excecao.AutExceptionCode;
import br.com.jhage.aut.modelo.User;

@Repository
public class UserDao extends BaseDao<User>{
	
	public boolean isNotExistEmail(String email) throws AutException{
		
		return  login(email) ==null;
	}

	public User login(String email) throws AutException {

		try {
			return (User) this.getEntityManager().createQuery(USERBYEMAIL).setParameter("email", email)
					.getSingleResult();
		} catch (NoResultException e) {

			return null;
		} catch (PersistenceException ee) {

			throw new AutException(AutExceptionCode.ERRRO_ACAO_ABRUPTA);
		}
	}
	
	
	private static final String USERBYEMAIL="select distinct u from User as u "
			+ " where u.email like :email ";
}
