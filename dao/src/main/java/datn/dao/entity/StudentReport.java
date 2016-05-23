package datn.dao.entity;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="student_report")
public class StudentReport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id = UUID.randomUUID().toString();

	@Column(name="created_date")
	private Date createdDate;

	private int status;// 0: Nhap , 1: Da nop

	@Column(name="student_opinion")
	private String studentOpinion;

	@Column(name="teacher_opinion")
	private String teacherOpinion;

	@Column(name="percent_finish")
	private int percentFinish;

	@ManyToOne
	private Report report;

	@ManyToOne
	private Student student;

	@OneToMany(mappedBy="studentReport")
	private List<StudentReportDetail> studentReportDetails;

	public StudentReport() {
	}

	public int getPercentFinish() {
		return percentFinish;
	}

	public void setPercentFinish(int percentFinish) {
		this.percentFinish = percentFinish;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
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