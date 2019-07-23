package br.com.treinaweb.servlets.agenda;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.treinaweb.agenda.entidades.Contato;
import br.com.treinaweb.agenda.repositorios.impl.ContatoRepositorioJdbc;
import br.com.treinaweb.agenda.repositorios.interfaces.AgendaRepositorio;


public class ListaContatosServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4224205316837151342L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AgendaRepositorio<Contato> agendaRepositorio = new ContatoRepositorioJdbc();
		try {
			List<Contato> contatos = agendaRepositorio.selecionar();
			req.setAttribute("listaContatos", contatos);			
		} catch (SQLException e) {
			req.setAttribute("mensagemErro", e.getMessage());
		}
		Object mensagemErro = req.getSession().getAttribute("mensagemErro");
		if (mensagemErro != null) {
			req.setAttribute("mensagemErro", mensagemErro.toString());
			req.getSession().removeAttribute("mensagemErro");
		}
		RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/Views/agenda/ListaContatos.jsp");
		dispatcher.forward(req, resp);
		
	}
	

}
