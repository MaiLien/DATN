package datn.dao.entity;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.*;

@Entity
@Table(name="assignment")
public class Assignment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id = UUID.randomUUID().toString();

	@ManyToOne
	@JoinColumn(name="wave_id")
	private ProjectWave projectWave;

	@ManyToOne
	private Student student;

	@ManyToOne
	private Teacher teacher;

	public Assignment() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ProjectWave getProjectWave() {
		return this.projectWave;
	}

	public void setProjectWave(ProjectWave projectWave) {
		this.projectWave = projectWave;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

}