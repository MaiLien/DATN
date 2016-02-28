package datn.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="wave_of_making_project")
public class WaveOfMakingProject implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="end_day")
	private Date endDay;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_time_for_defending_project")
	private Date endTimeForDefendingProject;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_time_for_student_register_teacher")
	private Date endTimeForStudentRegisterTeacher;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_time_for_student_submit_project")
	private Date endTimeForStudentSubmitProject;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_time_for_teacher_propose_student")
	private Date endTimeForTeacherProposeStudent;

	@Column(name="how_many_time_progress_report")
	private int howManyTimeProgressReport;

	@Column(name="school_year")
	private String schoolYear;

	private int semester;

	@Temporal(TemporalType.DATE)
	@Column(name="start_day")
	private Date startDay;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_time_for_defending_project")
	private Date startTimeForDefendingProject;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_time_for_student_register_teacher")
	private Date startTimeForStudentRegisterTeacher;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_time_for_student_submit_project")
	private Date startTimeForStudentSubmitProject;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_time_for_teacher_propose_student")
	private Date startTimeForTeacherProposeStudent;

	//bi-directional many-to-one association to ListOfStudentsForEachWave
	@OneToMany(mappedBy="waveOfMakingProject")
	private List<ListOfStudentsForEachWave> listOfStudentsForEachWaves;

	public WaveOfMakingProject() {
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

	public Date getEndDay() {
		return this.endDay;
	}

	public void setEndDay(Date endDay) {
		this.endDay = endDay;
	}

	public Date getEndTimeForDefendingProject() {
		return this.endTimeForDefendingProject;
	}

	public void setEndTimeForDefendingProject(Date endTimeForDefendingProject) {
		this.endTimeForDefendingProject = endTimeForDefendingProject;
	}

	public Date getEndTimeForStudentRegisterTeacher() {
		return this.endTimeForStudentRegisterTeacher;
	}

	public void setEndTimeForStudentRegisterTeacher(Date endTimeForStudentRegisterTeacher) {
		this.endTimeForStudentRegisterTeacher = endTimeForStudentRegisterTeacher;
	}

	public Date getEndTimeForStudentSubmitProject() {
		return this.endTimeForStudentSubmitProject;
	}

	public void setEndTimeForStudentSubmitProject(Date endTimeForStudentSubmitProject) {
		this.endTimeForStudentSubmitProject = endTimeForStudentSubmitProject;
	}

	public Date getEndTimeForTeacherProposeStudent() {
		return this.endTimeForTeacherProposeStudent;
	}

	public void setEndTimeForTeacherProposeStudent(Date endTimeForTeacherProposeStudent) {
		this.endTimeForTeacherProposeStudent = endTimeForTeacherProposeStudent;
	}

	public int getHowManyTimeProgressReport() {
		return this.howManyTimeProgressReport;
	}

	public void setHowManyTimeProgressReport(int howManyTimeProgressReport) {
		this.howManyTimeProgressReport = howManyTimeProgressReport;
	}

	public String getSchoolYear() {
		return this.schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	public int getSemester() {
		return this.semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public Date getStartDay() {
		return this.startDay;
	}

	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}

	public Date getStartTimeForDefendingProject() {
		return this.startTimeForDefendingProject;
	}

	public void setStartTimeForDefendingProject(Date startTimeForDefendingProject) {
		this.startTimeForDefendingProject = startTimeForDefendingProject;
	}

	public Date getStartTimeForStudentRegisterTeacher() {
		return this.startTimeForStudentRegisterTeacher;
	}

	public void setStartTimeForStudentRegisterTeacher(Date startTimeForStudentRegisterTeacher) {
		this.startTimeForStudentRegisterTeacher = startTimeForStudentRegisterTeacher;
	}

	public Date getStartTimeForStudentSubmitProject() {
		return this.startTimeForStudentSubmitProject;
	}

	public void setStartTimeForStudentSubmitProject(Date startTimeForStudentSubmitProject) {
		this.startTimeForStudentSubmitProject = startTimeForStudentSubmitProject;
	}

	public Date getStartTimeForTeacherProposeStudent() {
		return this.startTimeForTeacherProposeStudent;
	}

	public void setStartTimeForTeacherProposeStudent(Date startTimeForTeacherProposeStudent) {
		this.startTimeForTeacherProposeStudent = startTimeForTeacherProposeStudent;
	}

	public List<ListOfStudentsForEachWave> getListOfStudentsForEachWaves() {
		return this.listOfStudentsForEachWaves;
	}

	public void setListOfStudentsForEachWaves(List<ListOfStudentsForEachWave> listOfStudentsForEachWaves) {
		this.listOfStudentsForEachWaves = listOfStudentsForEachWaves;
	}

	public ListOfStudentsForEachWave addListOfStudentsForEachWave(ListOfStudentsForEachWave listOfStudentsForEachWave) {
		getListOfStudentsForEachWaves().add(listOfStudentsForEachWave);
		listOfStudentsForEachWave.setWaveOfMakingProject(this);

		return listOfStudentsForEachWave;
	}

	public ListOfStudentsForEachWave removeListOfStudentsForEachWave(ListOfStudentsForEachWave listOfStudentsForEachWave) {
		getListOfStudentsForEachWaves().remove(listOfStudentsForEachWave);
		listOfStudentsForEachWave.setWaveOfMakingProject(null);

		return listOfStudentsForEachWave;
	}

}