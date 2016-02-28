package datn.entity;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.*;

@Entity
@Table(name="list_of_students_for_each_wave")
public class ListOfStudentsForEachWave implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id = UUID.randomUUID().toString();

	private String description;

	private int status;

	private String topic;

	@ManyToOne
	private Student student;

	@ManyToOne
	@JoinColumn(name="wave_id")
	private WaveOfMakingProject waveOfMakingProject;

	public ListOfStudentsForEachWave() {
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

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTopic() {
		return this.topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public WaveOfMakingProject getWaveOfMakingProject() {
		return this.waveOfMakingProject;
	}

	public void setWaveOfMakingProject(WaveOfMakingProject waveOfMakingProject) {
		this.waveOfMakingProject = waveOfMakingProject;
	}

}