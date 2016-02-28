package datn.repository;

import datn.entity.ListOfTopicFromTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListOfTopicFromTeacherRepository extends JpaRepository<ListOfTopicFromTeacher, String> {
}
