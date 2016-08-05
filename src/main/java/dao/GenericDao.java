package dao;

import java.io.Serializable;
import java.util.Collection;

public interface GenericDao<E, K extends Serializable> {

	E consultar(final K id){
		E livro = null;
		try (Connection conexao = FabricaConexao.getConexao();
				PreparedStatement consulta = conexao.prepareStatement(OBTER_POR_ID_SQL)
				)
	}

	void alterar(final E Serializable);

	void salvar(final E Serializable);

	void remover(final E Serializable);

	Collection<E> listar();

}