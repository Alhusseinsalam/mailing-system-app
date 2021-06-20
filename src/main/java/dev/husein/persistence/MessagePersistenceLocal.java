package dev.husein.persistence;

import java.util.List;

import javax.ejb.LocalBean;

import dev.husein.model.Message;

@LocalBean
public interface MessagePersistenceLocal {
	
	public List<Message> getMessages();
	
	public void addMessage(Message message);
	
	public Message getMessageById(Long messageId);
	
	public void deleteMessage(Message message);
	
	public List<Message> searchMessageBySubject(String subject);
	
	public void saveMessage(Message message);
	
	public void putMessageToTrashById(long id);
	
	public List<Message> getInboxDescending(String receiver);
	
	public List<Message> getSentMailDescending(String sender);
	
	public void deleteMessageFromTrash(long id);
	
	public List<Message> getTrash();
}
