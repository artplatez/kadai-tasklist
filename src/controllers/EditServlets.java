package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;

/**
 * Servlet implementation class EditServlets
 */
@WebServlet("/edit")
public class EditServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlets() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = DBUtil.createEntityManager();


		Task t = em.find(Task.class, Integer.parseInt(request.getParameter("id")));

		em.close();

		request.setAttribute("task", t);
		request.setAttribute("_token", request.getSession().getId());

		//메세지아이디를 세션스코프에 등록
		if(t != null) {
		request.getSession().setAttribute("task_id", t.getId());
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/edit.jsp");
		rd.forward(request, response);

	}
}
