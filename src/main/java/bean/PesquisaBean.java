package bean;

import java.util.ArrayList;
import java.util.List;
import model.Livro;

import dao.LivroDao;

public class PesquisaBean {

	private String titulo;
	private Livro livro;
	private List<Livro> livros = new ArrayList<Livro>();

	public String pesquisar() {
		if (titulo == null) {
			titulo = "";
		}
		System.out.println("Pesquisa: " + titulo);
		LivroDao dao = new LivroDao();
		livros = dao.consultar(titulo);
		if (livros.size() <= 0) {
			System.out.println("Livro não foi localizado");
		}
		return "Resultado";
	}

	public String verLivro(Integer codigo) {
		LivroDao dao = new LivroDao();
		livro = dao.consultar(codigo);
		return "Livro";
	}
}
