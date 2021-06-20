package dev.husein.servlet;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.husein.model.Message;
import dev.husein.model.User;
import dev.husein.persistence.MessagePersistenceService;
import dev.husein.persistence.UserPersistenceService;
import dev.husein.util.DateFormatter;

@WebServlet("/hello")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Inject
	UserPersistenceService ups;
	
	@Inject
	MessagePersistenceService mps;
	
	
    public Test() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user1 = new User("user1", "user1@company.com", "1234", "male", "01/09/1995", "add1", "pheniox", "arizona", "us", "daa", DateFormatter.getCurrentDate().toString(), "yes");
		User user2 = new User("user2", "user2@company.com", "1234", "male", "02/09/1995", "add2", "pheniox", "arizona", "us", "daa", DateFormatter.getCurrentDate().toString(), "yes");
		User user3 = new User("user3", "user3@company.com", "1234", "male", "03/09/1995", "add3", "pheniox", "arizona", "us", "daa", DateFormatter.getCurrentDate().toString(), "yes");
		
		ups.addUser(user1);
		ups.addUser(user2);
		ups.addUser(user3);
		
		Message message1 = new Message(user1.getEmail(), user2.getEmail(), "Message 1", "message body 1 \n hello user2 from user1", DateFormatter.getCurrentDate());
		Message message2 = new Message(user2.getEmail(), user1.getEmail(), "Message 2", "message body 2 \n hello user1 from user2", DateFormatter.getCurrentDate());
		Message message3 = new Message(user3.getEmail(), user2.getEmail(), "Message 3", "message body 3 \n hello user2 from user3", DateFormatter.getCurrentDate());
		
		mps.addMessage(message1);
		mps.addMessage(message2);
		mps.addMessage(message3);
		
		String users = ups.getUsers().toString();
		String messages = mps.getMessages().toString();
		
		List<Message> user1Inbox = mps.getInboxDescending(user1.getEmail());
		
		response.getWriter()
						.append("<br><br>Users: ").append(users)
						.append("<br><br>Messages: ").append(messages)
						.append("<br><br>User1 Inbox: ").append(user1Inbox.toString())
						.append("<br><br>").append("Served at: ").append(request.getContextPath());
	}

}
