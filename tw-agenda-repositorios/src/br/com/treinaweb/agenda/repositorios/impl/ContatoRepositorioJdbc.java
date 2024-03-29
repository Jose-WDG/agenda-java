package br.com.treinaweb.agenda.repositorios.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.treinaweb.agenda.entidades.Contato;
import br.com.treinaweb.agenda.fabricas.FabricaConexaoJdbc;
import br.com.treinaweb.agenda.repositorios.interfaces.AgendaRepositorio;

public class ContatoRepositorioJdbc implements AgendaRepositorio<Contato> {

	@Override
	public List<Contato> selecionar() throws SQLException, IOException {
		List<Contato> contatos = new ArrayList<Contato>();
		try (Connection conexao = FabricaConexaoJdbc.criarConexao()) {
			Statement comando = conexao.createStatement();
			ResultSet rs = comando.executeQuery("SELECT * FROM usuarios");
			while (rs.next()) {
				Contato contato = new Contato();
				contato.setId(rs.getInt("id"));
				contato.setIdade(rs.getInt("idade"));
				contato.setNome(rs.getString("nome"));
				contato.setTell(rs.getString("tell"));
				contatos.add(contato);
			}
		}
		return contatos;
	}

	@Override
	public void inserir(Contato entidade) throws IOException, SQLException {
		Connection conexao = null;
		try {
			conexao = FabricaConexaoJdbc.criarConexao();
			PreparedStatement comando = conexao.prepareStatement("INSERT INTO usuarios (nome, idade, tell) " + 
															     " VALUES (?, ?, ?)");
			comando.setString(1, entidade.getNome());
			comando.setInt(2, entidade.getIdade());
			comando.setString(3, entidade.getTell());
			comando.execute();
		} finally {
			if (conexao != null) {
				conexao.close();
			}
		}

	}

	@Override
	public void atualizar(Contato entidade)  throws IOException, SQLException {
		try (Connection conexao = FabricaConexaoJdbc.criarConexao()){
			PreparedStatement comando = conexao.prepareStatement("UPDATE usuarios SET nome = ?, idade = ?, tell = ? "+ 
																 " WHERE id = ?");
			comando.setString(1, entidade.getNome());
			comando.setInt(2, entidade.getIdade());
			comando.setString(3, entidade.getTell());
			comando.setInt(4, entidade.getId());
			comando.execute();
		}

	}

	@Override
	public void excluir(Contato entidade)  throws IOException, SQLException {
		try (Connection conexao = FabricaConexaoJdbc.criarConexao()){
			PreparedStatement comando = conexao.prepareStatement("DELETE FROM usuarios WHERE id = ?");
			comando.setInt(1, entidade.getId());
			comando.execute();
		}

	}

}
