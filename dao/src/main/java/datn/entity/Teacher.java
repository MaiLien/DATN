package datn.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;


/**
 * The persistent class for the teacher database table.
 * 
 */
@Entity
@Table(name="teacher")
@DiscriminatorValue("0")
public class Teacher extends User{

	private String degree;

	private String major;

	@Column(name="research_direction")
	private String researchDirection;

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

}