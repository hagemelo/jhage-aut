package br.com.jhage.aut.modelo;

import java.io.Serializable;

/**
 * 
 * @author Alexsander Melo
 * @since 06/11/2014
 *
 * @param <E>
 */

public interface JhageEntidade<E> extends Serializable{

	public Long getId();
	
}
