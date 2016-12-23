package br.com.jhage.aut.dao;

import java.util.Collection;

import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;

import br.com.jhage.aut.modelo.JhageEntidade;

public interface Dao<E extends JhageEntidade<E>> {
	
	void salvar(final E object) throws EntityExistsException, PersistenceException, IllegalStateException;
	E salvarRetornar(final E object) throws EntityExistsException, PersistenceException, IllegalStateException;
	void remove(final E object) throws IllegalArgumentException, TransactionRequiredException;
	E obterPorID(final Long id) throws IllegalArgumentException, TransactionRequiredException;
	public Collection<E> buscarTodos() throws IllegalStateException,TransactionRequiredException, PersistenceException;
	
}
