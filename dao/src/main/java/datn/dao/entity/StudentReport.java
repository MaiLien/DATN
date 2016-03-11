package datn.dao.entity;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="student_report")
public class StudentReport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id = UUID.randomUUID().toString();

	@CreatedDate
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Column(name="created_date")
	private Timestamp createdDate;

	private String status;

	@Column(name="student_opinion")
	private String studentOpinion;

	@Column(name="teacher_opinion")
	private String teacherOpinion;

	@ManyToOne
	private Report report;

	@ManyToOne
	private Student student;

	@OneToMany(mappedBy="studentReport")
	private List<StudentReportDetail> studentReportDetails;

	public StudentReport() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStudentOpinion() {
		return this.studentOpinion;
	}

	public void setStudentOpinion(String studentOpinion) {
		this.studentOpinion = studentOpinion;
	}

	public String getTeacherOpinion() {
		return this.teacherOpinion;
	}

	public void setTeacherOpinion(String teacherOpinion) {
		this.teacherOpinion = teacherOpinion;
	}

	public Report getReport() {
		return this.report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<StudentReportDetail> getStudentReportDetails() {
		return this.studentReportDetails;
	}

	public void setStudentReportDetails(List<StudentReportDetail> studentReportDetails) {
		this.studentReportDetails = studentReportDetails;
	}

	public StudentReportDetail addStudentReportDetail(StudentReportDetail studentReportDetail) {
		getStudentReportDetails().add(studentReportDetail);
		studentReportDetail.setStudentReport(this);

		return studentReportDetail;
	}

	public StudentReportDetail removeStudentReportDetail(StudentReportDetail studentReportDetail) {
		getStudentReportDetails().remove(studentReportDetail);
		studentReportDetail.setStudentReport(null);

		return studentReportDetail;
	}

}