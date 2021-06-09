package dev.husein.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ComposeEndPoint
 */
@WebServlet("/ComposeEndPoint")
public class ComposeEndPoint extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComposeEndPoint() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		request.getRequestDispatcher("header.html").include(request, response);
		request.getRequestDispatcher("link.html").include(request, response);
		
		HttpSession session=request.getSession(false);
		if(session==null){
			response.sendRedirect("index.html");
		}else{
			String email=(String)session.getAttribute("email");
			out.print("<span style='float:right'>Hi, "+email+"</span>");
			
			String msg=(String)request.getAttribute("msg");
			if(msg!=null){
				out.print("<p>"+msg+"</p>");
			}
		request.getRequestDispatcher("composeform.html").include(request, response);
		}
		
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}
}
