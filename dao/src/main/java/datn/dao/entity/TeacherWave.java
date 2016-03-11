package datn.dao.entity;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.*;

@Entity
@Table(name="teacher_wave")
public class TeacherWave implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id = UUID.randomUUID().toString();

	@Column(name="max_number_of_student")
	private int maxNumberOfStudent;

	@Column(name="min_number_of_student")
	private int minNumberOfStudent;

	@ManyToOne
	@JoinColumn(name="wave_id")
	private ProjectWave projectWave;

	@ManyToOne
	private Teacher teacher;

	public TeacherWave() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getMaxNumberOfStudent() {
		return this.maxNumberOfStudent;
	}

	public void setMaxNumberOfStudent(int maxNumberOfStudent) {
		this.maxNumberOfStudent = maxNumberOfStudent;
	}

	public int getMinNumberOfStudent() {
		return this.minNumberOfStudent;
	}

	public void setMinNumberOfStudent(int minNumberOfStudent) {
		this.minNumberOfStudent = minNumberOfStudent;
	}

	public ProjectWave getProjectWave() {
		return this.projectWave;
	}

	public void setProjectWave(ProjectWave projectWave) {
		this.projectWave = projectWave;
	}

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

}