package dao;

import java.io.Serializable;
import java.util.Collection;

public interface Dao<E, K extends Serializable> {
	/**
	 * * Busca a Serializable pelo seu identificador. * * @param identificador
	 * da Serializable * * @return Serializable pesquisada
	 */
	E consultar(final K id);

	/** * Altera a Serializable. * * @param Serializable */
	void alterar(final E Serializable);

	/** * Insere a Serializable. * * @param Serializable */
	void salvar(final E Serializable);

	/** * Remove a Serializable. * * @param Serializable */
	void remover(final E Serializable);

	/** * Lista todos os objetos da Serializable. * * @return Collection<E> */
	Collection<E> listar();
}