package dev.husein.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.husein.persistence.UserPersistenceService;

@WebServlet("/LoginEndPoint")
public class LoginEndPoint extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	UserPersistenceService ups;

	public LoginEndPoint() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("header.html").include(request, response);

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		if (ups.checkLoginCredentials(email, password)) {
			request.getSession().setAttribute("login", "true");
			request.getSession().setAttribute("email", email);
			response.sendRedirect("InboxEndPoint");
			out.print("you are successfully logged in!");

		} else {
			out.print("<p>Sorry, username or password error</p>");
			request.getRequestDispatcher("index.html").include(request, response);
		}

		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}

}
