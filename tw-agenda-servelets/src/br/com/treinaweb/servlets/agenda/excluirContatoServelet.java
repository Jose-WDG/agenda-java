package br.com.treinaweb.servlets.agenda;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.treinaweb.agenda.entidades.Contato;
import br.com.treinaweb.agenda.repositorios.impl.ContatoRepositorioJdbc;
import br.com.treinaweb.agenda.repositorios.interfaces.AgendaRepositorio;
@WebServlet(urlPatterns = {"/agenda/dell"})
public class excluirContatoServelet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3874532360606409782L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int idContato = Integer.parseInt(req.getParameter("id"));
		Contato contatoExcluido = new Contato();
		contatoExcluido.setId(idContato);
		AgendaRepositorio<Contato> agendaRepositorio = new ContatoRepositorioJdbc();
		try {
			agendaRepositorio.excluir(contatoExcluido);
		} catch (SQLException e) {
			req.getSession().setAttribute("mensagemErro", e.getMessage());
		}
		
		resp.sendRedirect(req.getContextPath()+"/agenda/Lista");
		
	}

}
