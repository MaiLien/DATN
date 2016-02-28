package datn.entity;

import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name="student_report")
public class StudentReport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id = UUID.randomUUID().toString();

	@CreatedDate
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

}