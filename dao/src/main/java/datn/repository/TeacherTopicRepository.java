package datn.repository;

import datn.entity.TeacherTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherTopicRepository extends JpaRepository<TeacherTopic, String> {
}
