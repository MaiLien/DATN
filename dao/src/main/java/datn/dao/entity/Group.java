package datn.dao.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;

@Entity
@Table(name="group_")
public class Group implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id = UUID.randomUUID().toString();

	@Column(name="created_date")
	private Timestamp createdDate;

	private byte deleted;

	@Column(name="group_name")
	private String groupName;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="creator")
	private User creator;

	//bi-directional many-to-one association to MemberGroup
	@OneToMany(mappedBy="group")
	private List<MemberGroup> memberGroups;

	//bi-directional many-to-one association to GroupRecieveMessage
	@OneToMany(mappedBy="group")
	private List<GroupMessage> groupMessages;

	public Group() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public byte getDeleted() {
		return this.deleted;
	}

	public void setDeleted(byte deleted) {
		this.deleted = deleted;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public User getCreator() {
		return this.creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public List<MemberGroup> getMemberGroups() {
		return this.memberGroups;
	}

	public void setMemberGroups(List<MemberGroup> memberGroups) {
		this.memberGroups = memberGroups;
	}

	public MemberGroup addMemberGroup(MemberGroup memberGroup) {
		getMemberGroups().add(memberGroup);
		memberGroup.setGroup(this);

		return memberGroup;
	}

	public MemberGroup removeMemberGroup(MemberGroup memberGroup) {
		getMemberGroups().remove(memberGroup);
		memberGroup.setGroup(null);

		return memberGroup;
	}

	public List<GroupMessage> getGroupRecieveMessages() {
		return this.groupMessages;
	}

	public void setGroupRecieveMessages(List<GroupMessage> groupMessages) {
		this.groupMessages = groupMessages;
	}

	public GroupMessage addGroupRecieveMessage(GroupMessage groupMessage) {
		getGroupRecieveMessages().add(groupMessage);
		groupMessage.setGroup(this);

		return groupMessage;
	}

	public GroupMessage removeGroupRecieveMessage(GroupMessage groupMessage) {
		getGroupRecieveMessages().remove(groupMessage);
		groupMessage.setGroup(null);

		return groupMessage;
	}

}