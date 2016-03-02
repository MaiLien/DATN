package datn.entity;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.*;

@Entity
@Table(name="group_recieve_message")
public class GroupMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id = UUID.randomUUID().toString();

	//bi-directional many-to-one association to Group
	@ManyToOne
	private Group group;

	//bi-directional many-to-one association to Message
	@ManyToOne
	private Message message;

	public GroupMessage() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Group getGroup() {
		return this.group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Message getMessage() {
		return this.message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

}