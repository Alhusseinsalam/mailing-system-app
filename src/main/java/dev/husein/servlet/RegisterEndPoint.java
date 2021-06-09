package dev.husein.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.husein.dao.Register;

/**
 * Servlet implementation class RegisterEndPoint
 */
@WebServlet("/RegisterEndPoint")
public class RegisterEndPoint extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterEndPoint() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		request.getRequestDispatcher("header.html").include(request, response);
		
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		String gender=request.getParameter("gender");
		String dob=request.getParameter("dob");
		String addressLine=request.getParameter("addressLine");
		String city=request.getParameter("city");
		String state=request.getParameter("state");
		String country=request.getParameter("country");
		String contact=request.getParameter("contact");
		
		int status=Register.save(name, email+"@cmailer.com", password, gender, dob, addressLine, city, state, country, contact);
		if(status>0){
			out.print("<p>You are successfully registered!</p>");
			request.getRequestDispatcher("login.html").include(request, response);
			
		}
		
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}

}
