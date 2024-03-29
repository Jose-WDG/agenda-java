package br.com.treinaweb.servlets.agenda;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.treinaweb.agenda.entidades.Contato;
import br.com.treinaweb.agenda.repositorios.impl.ContatoRepositorioJdbc;
import br.com.treinaweb.agenda.repositorios.interfaces.AgendaRepositorio;
@WebServlet(urlPatterns = {"/agenda/editar"} )
public class alterarContatoServelet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3104481951582377118L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		AgendaRepositorio<Contato> agendaRepositorio = new ContatoRepositorioJdbc();
		try {
			List<Contato> contatos = agendaRepositorio.selecionar();
			var contatoSelecionado = contatos.stream().filter(c -> c.getId() == id).findFirst();
			if(contatoSelecionado.isPresent()) {
				req.setAttribute("contato", contatoSelecionado.get());
			} else {
				req.setAttribute("mensagemErro", "Esse contato n�o existe.");
				resp.sendRedirect("/agenda/Lista");
			}
		} catch (SQLException e) {
			req.getSession().setAttribute("mensagemErro", e.getMessage());
			resp.sendRedirect("/agenda/listar");
		}
		RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/Views/agenda/alterarContato.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Contato contatoAlterado = new Contato();
		contatoAlterado.setNome(req.getParameter("nome"));
		contatoAlterado.setIdade(Integer.parseInt(req.getParameter("idade")));
		contatoAlterado.setTell(req.getParameter("tell"));
		contatoAlterado.setId(Integer.parseInt(req.getParameter("id")));
		AgendaRepositorio<Contato> agendaRepositorio = new ContatoRepositorioJdbc();
		try {
			agendaRepositorio.atualizar(contatoAlterado);
		} catch (SQLException e) {
			req.getSession().setAttribute("mensagemErro", e.getMessage());
		}
			resp.sendRedirect(req.getContextPath()+ "/agenda/Lista");
	}

}
