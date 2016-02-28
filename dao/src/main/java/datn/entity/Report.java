package datn.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="report")
public class Report implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id = UUID.randomUUID().toString();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_time")
	private Date endTime;

	private int ordinal;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_time")
	private Date startTime;

	@ManyToOne
	@JoinColumn(name="wave_id")
	private ProjectWave projectWave;

	@OneToMany(mappedBy="report")
	private List<StudentReport> studentReports;

	public Report() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getOrdinal() {
		return this.ordinal;
	}

	public void setOrdinal(int ordinal) {
		this.ordinal = ordinal;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public ProjectWave getProjectWave() {
		return this.projectWave;
	}

	public void setProjectWave(ProjectWave projectWave) {
		this.projectWave = projectWave;
	}

	public List<StudentReport> getStudentReports() {
		return this.studentReports;
	}

	public void setStudentReports(List<StudentReport> studentReports) {
		this.studentReports = studentReports;
	}

	public StudentReport addStudentReport(StudentReport studentReport) {
		getStudentReports().add(studentReport);
		studentReport.setReport(this);

		return studentReport;
	}

	public StudentReport removeStudentReport(StudentReport studentReport) {
		getStudentReports().remove(studentReport);
		studentReport.setReport(null);

		return studentReport;
	}

}