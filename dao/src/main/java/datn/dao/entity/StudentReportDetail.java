package datn.dao.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="student_report_detail")
public class StudentReportDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id = UUID.randomUUID().toString();

	@Temporal(TemporalType.DATE)
	@Column(name="end_time")
	private Date endTime;

	private String note;

	@Temporal(TemporalType.DATE)
	@Column(name="start_time")
	private Date startTime;

	@Column(name="work_content")
	private String workContent;

	@ManyToOne
	@JoinColumn(name="student_report_id")
	private StudentReport studentReport;

	public StudentReportDetail() {
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

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getWorkContent() {
		return this.workContent;
	}

	public void setWorkContent(String workContent) {
		this.workContent = workContent;
	}

	public StudentReport getStudentReport() {
		return this.studentReport;
	}

	public void setStudentReport(StudentReport studentReport) {
		this.studentReport = studentReport;
	}

}