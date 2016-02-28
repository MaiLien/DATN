package datn.entity;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="teacher")
@DiscriminatorValue("0")
public class Teacher extends User{

	private String degree;

	private String major;

	@Column(name="research_direction")
	private String researchDirection;

	@OneToMany(mappedBy="teacher")
	private List<TeacherWave> teacherWaves;

	@OneToMany(mappedBy="teacher")
	private List<TeacherTopic> teacherTopics;

	@OneToMany(mappedBy="teacher")
	private List<Assignment> assignments;

	public Teacher() {
	}

	public String getDegree() {
		return this.degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getMajor() {
		return this.major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getResearchDirection() {
		return this.researchDirection;
	}

	public void setResearchDirection(String researchDirection) {
		this.researchDirection = researchDirection;
	}

	public List<TeacherWave> getTeacherWaves() {
		return this.teacherWaves;
	}

	public void setTeacherWaves(List<TeacherWave> teacherWaves) {
		this.teacherWaves = teacherWaves;
	}

	public TeacherWave addTeacherWave(TeacherWave teacherWave) {
		getTeacherWaves().add(teacherWave);
		teacherWave.setTeacher(this);

		return teacherWave;
	}

	public TeacherWave removeTeacherWave(TeacherWave teacherWave) {
		getTeacherWaves().remove(teacherWave);
		teacherWave.setTeacher(null);

		return teacherWave;
	}

	public List<TeacherTopic> getTeacherTopics() {
		return this.teacherTopics;
	}

	public void setTeacherTopics(List<TeacherTopic> teacherTopics) {
		this.teacherTopics = teacherTopics;
	}

	public TeacherTopic addTeacherTopic(TeacherTopic teacherTopic) {
		getTeacherTopics().add(teacherTopic);
		teacherTopic.setTeacher(this);

		return teacherTopic;
	}

	public TeacherTopic removeTeacherTopic(TeacherTopic teacherTopic) {
		getTeacherTopics().remove(teacherTopic);
		teacherTopic.setTeacher(null);

		return teacherTopic;
	}

	public List<Assignment> getAssignments() {
		return this.assignments;
	}

	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}

	public Assignment addAssignment(Assignment assignment) {
		getAssignments().add(assignment);
		assignment.setTeacher(this);

		return assignment;
	}

	public Assignment removeAssignment(Assignment assignment) {
		getAssignments().remove(assignment);
		assignment.setTeacher(null);

		return assignment;
	}

}