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
	private List<ListOfStudentsForEachWave> listOfStudentsForEachWaves;

	public Student() {
	}

	public String getClass_() {
		return this.class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}

}