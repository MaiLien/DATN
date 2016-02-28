package datn.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="officer")
@DiscriminatorValue("2")
public class Officer extends User {

}