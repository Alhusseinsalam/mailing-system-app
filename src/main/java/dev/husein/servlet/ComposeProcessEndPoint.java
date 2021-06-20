package dev.husein.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.husein.model.Message;
import dev.husein.persistence.MessagePersistenceService;
import dev.husein.util.DateFormatter;

@WebServlet("/ComposeProcessEndPoint")
public class ComposeProcessEndPoint extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	MessagePersistenceService mps;
	
    public ComposeProcessEndPoint() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			response.setContentType("text/html");
			request.getRequestDispatcher("header.html").include(request, response);

			String receiver = request.getParameter("to");
			String subject = request.getParameter("subject");
			String messageBody = request.getParameter("message");
			messageBody = messageBody.replaceAll("\n", "<br/>");
			String sender = (String) request.getSession(false).getAttribute("email");

			Message messageObj = new Message(sender, receiver, subject, messageBody, DateFormatter.getCurrentDate());

			// persist the message
			mps.addMessage(messageObj);
			
			request.setAttribute("msg", "message successfully sent");
			request.getRequestDispatcher("ComposeEndPoint").forward(request, response);

		} catch (Exception e) {
			request.setAttribute("msg", "message sending failed");
			request.getRequestDispatcher("ComposeEndPoint").forward(request, response);
		}
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();

	}
	
}
