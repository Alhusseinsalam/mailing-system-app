package dev.husein.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dev.husein.persistence.MessagePersistenceService;

@WebServlet("/DeleteMailEndPoint")
public class DeleteMailEndPoint extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	MessagePersistenceService mps;

	public DeleteMailEndPoint() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("header.html").include(request, response);
		request.getRequestDispatcher("links-ref.html").include(request, response);

		HttpSession session = request.getSession(false);
		
		if (session == null) {
			response.sendRedirect("index.html");
		} else {
			String email = (String) session.getAttribute("email");
			out.print("<span style='float:right'>Hi, " + email + "</span>");

			long id = Long.parseLong(request.getParameter("id"));

			try {

				mps.putMessageToTrashById(id);
				request.setAttribute("msg", "Mail successfully deleted!");
				request.getRequestDispatcher("InboxEndPoint").forward(request, response);

			} catch (Exception e) {
				request.setAttribute("msg", "Mail deletion failed!");
				request.getRequestDispatcher("InboxEndPoint").forward(request, response);

			}
		}

		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}
}
