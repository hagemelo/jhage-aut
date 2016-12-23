package br.com.jhage.aut.dao;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;

import br.com.jhage.aut.excecao.AutException;
import br.com.jhage.aut.excecao.AutExceptionCode;
import br.com.jhage.aut.modelo.JhageEntidade;

public abstract class BaseDao<E extends JhageEntidade<E>>{


	@PersistenceContext
	protected EntityManager entityManager;

	private Class<E> clazz = null;
	
	public EntityManager getEntityManager() {

		return this.entityManager;
	}
	
	public void salvar(final E object) throws EntityExistsException, PersistenceException, IllegalStateException {

		this.getEntityManager().merge(object);
		this.getEntityManager().flush();
	}
	
	public E salvarRetornar(final E object) throws AutException {

		try {
			E e = this.getEntityManager().merge(object);
			this.getEntityManager().flush();
			return e;
		}catch(PersistenceException| IllegalStateException e ){
			
			if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
				this.getEntityManager().getTransaction().rollback();
				throw new AutException(AutExceptionCode.EMAIL_JA_EXISTENTE);
			}else{
				throw new AutException(AutExceptionCode.ERRRO_ACAO_ABRUPTA);
			}
		}
		
	}

	public void remove(final E object) throws IllegalArgumentException, TransactionRequiredException {

		this.getEntityManager().remove(object);
		this.getEntityManager().flush();
	}

	public E obterPorID(final Long id) throws IllegalArgumentException, TransactionRequiredException {

		return (E) this.getEntityManager().find(this.getClazz(), id);
	}
	
	@SuppressWarnings("unchecked")
	public Collection<E> buscarTodos() throws IllegalStateException,
	TransactionRequiredException, PersistenceException {

		return this.getEntityManager().createQuery("from " + this.getClazz().getSimpleName()).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	protected Class<E> getClazz() {

		if (this.clazz == null) {
			this.clazz = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		}

		return this.clazz;
	}
}
