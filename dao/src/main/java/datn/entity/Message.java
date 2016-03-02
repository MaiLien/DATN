package datn.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="message")
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id = UUID.randomUUID().toString();

	private String content;

	@Column(name="created_date")
	private Timestamp createdDate;

	private boolean deleted;

	private String subject;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="creator")
	private User creator;

	//bi-directional many-to-one association to PersonMessage
	@OneToMany(mappedBy="message")
	private List<PersonMessage> personMessages;

	public Message() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public boolean getDeleted() {
		return this.deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public User getCreator() {
		return this.creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public List<PersonMessage> getPersonMessages() {
		return this.personMessages;
	}

	public void setPersonMessages(List<PersonMessage> personMessages) {
		this.personMessages = personMessages;
	}

	public PersonMessage addPersonMessage(PersonMessage personMessage) {
		getPersonMessages().add(personMessage);
		personMessage.setMessage(this);

		return personMessage;
	}

	public PersonMessage removePersonMessage(PersonMessage personMessage) {
		getPersonMessages().remove(personMessage);
		personMessage.setMessage(null);

		return personMessage;
	}

}