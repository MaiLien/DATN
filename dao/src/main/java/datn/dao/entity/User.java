package datn.dao.entity;

import datn.dao.constant.Gender;
import datn.dao.constant.TypeOfUser;
import datn.dao.constant.UserStatusConstant;
import org.springframework.data.annotation.*;

import java.io.Serializable;
import javax.persistence.*;
import javax.persistence.Id;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="user")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="type_of_user", discriminatorType=DiscriminatorType.INTEGER)
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id = UUID.randomUUID().toString();

	private  String username;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	@Column(name="created_by")
	@CreatedBy
	private String createdBy;

	@Column(name="created_date")
	@CreatedDate
	private Timestamp createdDate;

	private boolean deleted = false;

	private String description;

	private String email;

	private Gender gender;

	@LastModifiedBy
	@Column(name="last_modified_by")
	private String lastModifiedBy;


	@Column(name="last_modified_date")
	@LastModifiedDate
	private Timestamp lastModifiedDate;

	private String name;

	private String password;

	@Column(name="phone_number")
	private String phoneNumber;

	private int status = UserStatusConstant.ACTIVE;

	@Column(name="type_of_user", insertable = false, updatable = false)
	private TypeOfUser typeOfUser;

	@Column(name="version_no")
	@org.springframework.data.annotation.Version
	private String versionNo;

	//bi-directional many-to-one association to Group
	@OneToMany(mappedBy="creator")
	private List<Group> createdGroups;

	//bi-directional many-to-one association to MemberGroup
	@OneToMany(mappedBy="user")
	private List<MemberGroup> memberGroups;

	//bi-directional many-to-one association to Message
	@OneToMany(mappedBy="creator")
	private List<Message> createdMessages;

	//bi-directional many-to-one association to PersonRecieveMessage
	@OneToMany(mappedBy="user")
	private List<PersonMessage> personMessages;

	//bi-directional many-to-one association to UserRole
	@OneToMany(mappedBy="user")
	private List<UserRole> userRoles;

	public User() {
		deleted = false;
		status = UserStatusConstant.ACTIVE;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Gender getGender() {
		return this.gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getLastModifiedBy() {
		return this.lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Timestamp getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Timestamp lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public TypeOfUser getTypeOfUser() {
		return this.typeOfUser;
	}

	public void setTypeOfUser(TypeOfUser typeOfUser) {
		this.typeOfUser = typeOfUser;
	}

	public String getVersionNo() {
		return this.versionNo;
	}

	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}

	public List<Group> getCreatedGroups() {
		return this.createdGroups;
	}

	public void setGroups(List<Group> createdGroups) {
		this.createdGroups = createdGroups;
	}

	public Group addCreatedGroup(Group CreatedGroup) {
		getCreatedGroups().add(CreatedGroup);
		CreatedGroup.setCreator(this);

		return CreatedGroup;
	}

	public Group removeGroup(Group createdGroup) {
		getCreatedGroups().remove(createdGroup);
		createdGroup.setCreator(null);

		return createdGroup;
	}

	public List<MemberGroup> getMemberGroups() {
		return this.memberGroups;
	}

	public void setMemberGroups(List<MemberGroup> memberGroups) {
		this.memberGroups = memberGroups;
	}

	public MemberGroup addMemberGroup(MemberGroup memberGroup) {
		getMemberGroups().add(memberGroup);
		memberGroup.setUser(this);

		return memberGroup;
	}

	public MemberGroup removeMemberGroup(MemberGroup memberGroup) {
		getMemberGroups().remove(memberGroup);
		memberGroup.setUser(null);

		return memberGroup;
	}

	public List<Message> getCreatedMessages() {
		return this.createdMessages;
	}

	public void setCreatedMessages(List<Message> createdMessages) {
		this.createdMessages = createdMessages;
	}

	public Message addCreatedMessage(Message createdMessage) {
		getCreatedMessages().add(createdMessage);
		createdMessage.setCreator(this);

		return createdMessage;
	}

	public Message removeCreatedMessage(Message createdMessage) {
		getCreatedMessages().remove(createdMessage);
		createdMessage.setCreator(null);

		return createdMessage;
	}

	public List<PersonMessage> getPersonMessages() {
		return this.personMessages;
	}

	public void setPersonMessages(List<PersonMessage> personMessages) {
		this.personMessages = personMessages;
	}

	public PersonMessage addPersonMessage(PersonMessage personMessage) {
		getPersonMessages().add(personMessage);
		personMessage.setUser(this);

		return personMessage;
	}

	public PersonMessage removePersonMessage(PersonMessage personMessage) {
		getPersonMessages().remove(personMessage);
		personMessage.setUser(null);

		return personMessage;
	}

	public List<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public UserRole addUserRole(UserRole userRole) {
		getUserRoles().add(userRole);
		userRole.setUser(this);

		return userRole;
	}

	public UserRole removeUserRole(UserRole userRole) {
		getUserRoles().remove(userRole);
		userRole.setUser(null);

		return userRole;
	}

}