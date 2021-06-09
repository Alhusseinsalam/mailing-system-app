package dev.husein.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import dev.husein.util.ConnectionProvider;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DeleteMailEndPoint
 */
@WebServlet("/DeleteMailEndPoint")
public class DeleteMailEndPoint extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMailEndPoint() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			
			int id=Integer.parseInt(request.getParameter("id"));
			
			try{
				Connection con=ConnectionProvider.get();
				PreparedStatement ps=con.prepareStatement("update company_mailer_message set trash=? where id=?");
				ps.setString(1,"yes");
				ps.setInt(2,id);
				int i=ps.executeUpdate();
				if(i>0){
					request.setAttribute("msg","Mail successfully deleted!");
					request.getRequestDispatcher("InboxServlet").forward(request, response);
				}
				con.close();
			}catch(Exception e){out.print(e);}
		}
		
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}
}
