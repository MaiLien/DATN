package datn.entity;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.*;

@Entity
@Table(name="list_of_teachers_for_each_wave")
public class ListOfTeachersForEachWave implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id= UUID.randomUUID().toString();

	@Column(name="max_number_of_student")
	private int maxNumberOfStudent;

	@Column(name="min_number_of_student")
	private int minNumberOfStudent;

	@ManyToOne
	private Teacher teacher;

	@ManyToOne
	@JoinColumn(name="wave_id")
	private WaveOfMakingProject waveOfMakingProject;

	public ListOfTeachersForEachWave() {
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

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public WaveOfMakingProject getWaveOfMakingProject() {
		return this.waveOfMakingProject;
	}

	public void setWaveOfMakingProject(WaveOfMakingProject waveOfMakingProject) {
		this.waveOfMakingProject = waveOfMakingProject;
	}

}