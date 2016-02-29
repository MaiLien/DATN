package datn.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.*;

@Entity
@Table(name="member_group")
public class MemberGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id = UUID.randomUUID().toString();

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="member_id")
	private User user;

	//bi-directional many-to-one association to Group
	@ManyToOne
	private Group group;

	public MemberGroup() {
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

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}