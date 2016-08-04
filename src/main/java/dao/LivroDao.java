package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import util.FabricaConexao;
import model.Livro;

public class LivroDao implements Dao<Livro, Integer> {
	Logger LOG = Logger.getGlobal();
	private static final String OBTER_POR_ID_SQL = "SELECT AUTOR, TITULO, COD_LIVRO, IMAGEM,"
			+ " PRECO, DESCRICAO FROM ESTOQUE WHERE COD_LIVRO = ?";
	private static final String CONSULTAR_SQL = "SELECT COD_LIVRO, TITULO, AUTOR, PRECO,"
			+ " IMAGEM, DESCRICAO FROM ESTOQUE WHERE TITULO LIKE ?";
	private static final String ALTERAR_SQL = "UPDATE estoque SET TITULO = ?, AUTOR = ?, PRECO = ?,"
			+ " IMAGEM = ?, DESCRICAO = ? WHERE COD_LIVRO = ?";
	private static final String REMOVER_SQL = "DELETE FROM estoque WHERE COD_LIVRO = ?";
	private static final String INCLUIR_SQL = "INSERT INTO estoque COD_LIVRO = ?, TITULO = ?, AUTOR = ?, PRECO = ?,"
			+ " IMAGEM = ?, DESCRICAO = ? WHERE COD_LIVRO = ?";

	@Override
	public Livro consultar(Integer codigo) {
		Livro livro = null;
		try (Connection conexao = FabricaConexao.getConexao();
				PreparedStatement consulta = conexao.prepareStatement(OBTER_POR_ID_SQL);) {
			consulta.setInt(1, codigo);
			ResultSet resultado = consulta.executeQuery();
			if (resultado.next()) {
				livro = new Livro();
				livro.setAutor(resultado.getString("AUTOR"));
				livro.setCodigo(resultado.getInt("COD_LIVRO"));
				livro.setImagem(resultado.getString("IMAGEM"));
				livro.setPreco(resultado.getDouble("PRECO"));
				livro.setTitulo(resultado.getString("TITULO"));
				livro.setDescricao(resultado.getString("DESCRICAO"));
			}
			resultado.close();
		} catch (SQLException e) {
			LOG.severe(e.toString());
		}
		return livro;
	}

	public List<Livro> consultar(String titulo) {
		ArrayList<Livro> lista = new ArrayList<Livro>();
		try (Connection conexao = FabricaConexao.getConexao();
				PreparedStatement consulta = conexao.prepareStatement(CONSULTAR_SQL);) {
			consulta.setString(1, "%" + titulo.toUpperCase() + "%");
			ResultSet resultado = consulta.executeQuery();
			while (resultado.next()) {
				Livro livro = new Livro();
				livro.setAutor(resultado.getString("AUTOR"));
				livro.setCodigo(resultado.getInt("COD_LIVRO"));
				livro.setImagem(resultado.getString("IMAGEM"));
				livro.setPreco(resultado.getDouble("PRECO"));
				livro.setTitulo(resultado.getString("TITULO"));
				livro.setDescricao(resultado.getString("DESCRICAO"));
				lista.add(livro);
			}
			resultado.close();
		} catch (SQLException e) {
			LOG.severe(e.toString());
		}
		return lista;
	}

	// Faça o restante do CRUD }

	public void alterar(Livro livro) {
		// Livro livro = null;
		try (Connection conexao = FabricaConexao.getConexao();
				PreparedStatement consulta = conexao.prepareStatement(ALTERAR_SQL);) {
			// TITULO = ?, AUTOR = ?, PRECO = ?," + " IMAGEM = ?, DESCRICAO = ?
			// WHERE COD_LIVRO = ?";
			consulta.setString(1, livro.getTitulo());
			consulta.setString(2, livro.getAutor());
			consulta.setDouble(3, livro.getPreco());
			consulta.setString(4, livro.getImagem());
			consulta.setString(5, livro.getDescricao());
			consulta.setInt(6, livro.getCodigo());
			consulta.executeUpdate();
		} catch (SQLException e) {
			LOG.severe(e.toString());
		}
	}

	public Livro remover(Integer codigo) {
		Livro livro = null;
		try (Connection conexao = FabricaConexao.getConexao();
				PreparedStatement consulta = conexao.prepareStatement(REMOVER_SQL);) {
			consulta.setInt(1, codigo);
			consulta.executeUpdate();
		} catch (SQLException e) {
			LOG.severe(e.toString());
		}
		return livro;
	}

	public void incluir(Livro livro) {
		// Livro livro = null;
		try (Connection conexao = FabricaConexao.getConexao();
				PreparedStatement consulta = conexao.prepareStatement(INCLUIR_SQL);) {
// COD_LIVRO, TITULO, AUTOR, PRECO, IMAGEM, DESCRICAO
			consulta.setInt(1, livro.getCodigo());
			consulta.setString(2, livro.getTitulo());
			consulta.setString(3, livro.getAutor());
			consulta.setDouble(4, livro.getPreco());
			consulta.setString(5, livro.getImagem());
			consulta.setString(6, livro.getDescricao());
			consulta.setInt(7, livro.getCodigo());
			// * * *** * * *** ver abaixo com o River se é executeQuery ou update
			consulta.executeUpdate();
		} catch (SQLException e) {
			LOG.severe(e.toString());
		}
	}
	
}
