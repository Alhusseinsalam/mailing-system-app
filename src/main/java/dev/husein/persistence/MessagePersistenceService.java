package dev.husein.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dev.husein.model.Message;

public class MessagePersistenceService implements MessagePersistenceLocal {

	@PersistenceContext
	private EntityManager entityManager;
	
	public MessagePersistenceService() {
	}
	
	@Override
	public List<Message> getMessages() {
		return this.entityManager.createQuery("SELECT message from Message message", Message.class).getResultList();
	}

	@Override
	public void addMessage(Message message) {
		this.entityManager.persist(message);
	}

	@Override
	public Message findMessage(Long messageId) {
		return this.entityManager.find(Message.class, messageId);
	}

	@Override
	public void deleteMessage(Message message) {
		this.entityManager.remove(this.entityManager.contains(message) ? message : this.entityManager.merge(message)); 
		
	}

	@Override
	public List<Message> searchMessageBySubject(String subject) {
		return this.entityManager.createQuery("SELECT message FROM Message message WHERE message.subject LIKE :subject", Message.class).setParameter("subject", "%" + subject + "%").getResultList();
	}

	@Override
	public void saveMessage(Message message) {
		this.entityManager.merge(message);
	}

	@Override
	public void putMessageToTrashById(long id) {
		this.entityManager.createQuery("UPDATE Message SET trash='yes' WHERE id=:id", Message.class).setParameter("id", id).getResultList();
	}

	@Override
	public List<Message> getInboxDescending(String receiver) {
		return this.entityManager.createQuery("SELECT message FROM Message message WHERE message.receiver=:receiver AND message.trash='no' ORDER BY message.message_id DESC", Message.class).setParameter("receiver", receiver).getResultList();
	}

//	"select * from company_mailer_message where receiver=? and trash='no' order by id desc"
	
	
}
