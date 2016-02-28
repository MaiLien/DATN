package datn.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the list_of_teachers_for_each_wave database table.
 * 
 */
@Entity
@Table(name="list_of_teachers_for_each_wave")
@NamedQuery(name="ListOfTeachersForEachWave.findAll", query="SELECT l FROM ListOfTeachersForEachWave l")
public class ListOfTeachersForEachWave implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="max_number_of_student")
	private int maxNumberOfStudent;

	@Column(name="min_number_of_student")
	private int minNumberOfStudent;

	//bi-directional many-to-one association to Teacher
	@ManyToOne
	private Teacher teacher;

	//bi-directional many-to-one association to WaveOfMakingProject
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