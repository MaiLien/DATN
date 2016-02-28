package datn.entity;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.*;

@Entity
@Table(name="list_of_topic_from_teacher")
public class ListOfTopicFromTeacher implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id= UUID.randomUUID().toString();

	private String description;

	private String title;

	@ManyToOne
	private Teacher teacher;

	public ListOfTopicFromTeacher() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

}