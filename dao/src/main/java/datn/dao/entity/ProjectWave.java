package datn.dao.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="project_wave")
public class ProjectWave implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id= UUID.randomUUID().toString();

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

	@OneToMany(mappedBy="projectWave")
	private List<TeacherWave> teacherWaves;

	@OneToMany(mappedBy="projectWave")
	private List<StudentWave> studentWaves;

	@OneToMany(mappedBy="projectWave")
	private List<Report> reports;

	@OneToMany(mappedBy="projectWave")
	private List<Assignment> assignments;

	public ProjectWave() {
	}

	public ProjectWave(String id) {
		this.setId(id);
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

	public List<TeacherWave> getTeacherWaves() {
		return this.teacherWaves;
	}

	public void setTeacherWaves(List<TeacherWave> teacherWaves) {
		this.teacherWaves = teacherWaves;
	}

	public TeacherWave addTeacherWave(TeacherWave teacherWave) {
		getTeacherWaves().add(teacherWave);
		teacherWave.setProjectWave(this);

		return teacherWave;
	}

	public TeacherWave removeTeacherWave(TeacherWave teacherWave) {
		getTeacherWaves().remove(teacherWave);
		teacherWave.setProjectWave(null);

		return teacherWave;
	}

	public List<StudentWave> getStudentWaves() {
		return this.studentWaves;
	}

	public void setStudentWaves(List<StudentWave> studentWaves) {
		this.studentWaves = studentWaves;
	}

	public StudentWave addStudentWave(StudentWave studentWave) {
		getStudentWaves().add(studentWave);
		studentWave.setProjectWave(this);

		return studentWave;
	}

	public StudentWave removeStudentWave(StudentWave studentWave) {
		getStudentWaves().remove(studentWave);
		studentWave.setProjectWave(null);

		return studentWave;
	}

	public List<Report> getReports() {
		return this.reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	public Report addReport(Report report) {
		getReports().add(report);
		report.setProjectWave(this);

		return report;
	}

	public Report removeReport(Report report) {
		getReports().remove(report);
		report.setProjectWave(null);

		return report;
	}

	public List<Assignment> getAssignments() {
		return this.assignments;
	}

	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}

	public Assignment addAssignment(Assignment assignment) {
		getAssignments().add(assignment);
		assignment.setProjectWave(this);

		return assignment;
	}

	public Assignment removeAssignment(Assignment assignment) {
		getAssignments().remove(assignment);
		assignment.setProjectWave(null);

		return assignment;
	}

}