package dev.husein.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dev.husein.model.Message;
import dev.husein.persistence.MessagePersistenceService;

@WebServlet("/TrashEndPoint")
public class TrashEndPoint extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Inject
	MessagePersistenceService mps;
	
    public TrashEndPoint() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		request.getRequestDispatcher("header.html").include(request, response);
		request.getRequestDispatcher("links-ref.html").include(request, response);
		
		HttpSession session=request.getSession(false);
		if(session==null){
			response.sendRedirect("index.html");
		}else{
			String email=(String)session.getAttribute("email");
			out.print("<span style='float:right'>Hi, "+email+"</span>");
			out.print("<h1>Trash</h1>");
			
			String msg=(String)request.getAttribute("msg");
			if(msg!=null){
				out.print("<p>"+msg+"</p>");
			}
			
			try{
				
				List<Message> messages = mps.getTrash();

				
				out.print("<table border='1' style='width:700px;'>");
				out.print("<tr style='background-color:grey;color:white'><td>Sender</td><td>Subject</td><td></td></tr>");
				for (Message message : messages){
					out.print("<tr><td>"+message.getSender()+"</td><td>"+message.getSubject()+"</td><td>" + "<a href='ShredMailEndPoint?id=" + message.getId() + "'>Shred Message</a></td></tr>");
				}
				out.print("</table>");
				
			}catch(Exception e){out.print(e);}
		}
		
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}

}
