package datn.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="student")
@DiscriminatorValue("1")
public class Student extends User {

	@Column(name="class")
	private String class_;

	@OneToMany(mappedBy="student")
	private List<StudentWave> studentWaves;

	@OneToMany(mappedBy="student")
	private List<StudentReport> studentReports;

	public Student() {
	}

	public String getClass_() {
		return this.class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}

	public List<StudentWave> getStudentWaves() {
		return studentWaves;
	}

	public void setStudentWaves(List<StudentWave> studentWaves) {
		this.studentWaves = studentWaves;
	}

	public StudentWave addStudentWave(StudentWave studentWave) {
		getStudentWaves().add(studentWave);
		studentWave.setStudent(this);

		return studentWave;
	}

	public StudentWave removeStudentWave(StudentWave studentWave) {
		getStudentWaves().remove(studentWave);
		studentWave.setStudent(null);

		return studentWave;
	}

	public List<StudentReport> getStudentReports() {
		return this.studentReports;
	}

	public void setStudentReports(List<StudentReport> studentReports) {
		this.studentReports = studentReports;
	}

	public StudentReport addStudentReport(StudentReport studentReport) {
		getStudentReports().add(studentReport);
		studentReport.setStudent(this);

		return studentReport;
	}

	public StudentReport removeStudentReport(StudentReport studentReport) {
		getStudentReports().remove(studentReport);
		studentReport.setStudent(null);

		return studentReport;
	}
}