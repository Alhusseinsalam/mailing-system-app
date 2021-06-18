package dev.husein.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "MESSAGE")
public class Message {
	@Id
	@Column(name = "MESSAGE_ID")
	private long id;
	
	@Column(name = "SENDER")
	@NotBlank
	private String sender;
	
	@Column(name = "RECEIVER")
	@NotBlank
	private String receiver;
	
	@Column(name = "SUBJECT")
	@NotBlank
	private String subject;
	
	@Column(name = "MESSAGE_BODY")
	@NotBlank
	private String messageBody;
	
	@Column(name = "TRASH")
	private String trash;
	
	@Column(name = "MESSAGE_DATE")
	private Date messageDate;
		
	public Message(String sender, String receiver, String subject, String messageBody, Date messageDate) {
		super();
		this.sender = sender;
		this.receiver = receiver;
		this.subject = subject;
		this.messageBody = messageBody;
		this.messageDate = messageDate;
	}

	public Message() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	public String getTrash() {
		return trash;
	}

	public void setTrash(String trash) {
		this.trash = trash;
	}

	public Date getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}
	
}
