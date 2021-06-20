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

import dev.husein.model.Message;
import dev.husein.persistence.MessagePersistenceService;

@WebServlet("/ViewMailEndPoint")
public class ViewMailEndPoint extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	MessagePersistenceService mps;

	public ViewMailEndPoint() {
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
				Message message = mps.getMessageById(id);

				out.print("<h1>" + message.getSubject() + "</h1><hr/>");
				out.print("<p><b>Message:</b><br/> " + message.getMessageBody() + " <br/> <b>By: " + message.getSender()
						+ "</b></p>");
				out.print("<p><a href='DeleteMailEndPoint?id=" + message.getId() + "'>Delete Mail</a></p>");

			} catch (Exception e) {
				out.print(e);
			}
		}

		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
