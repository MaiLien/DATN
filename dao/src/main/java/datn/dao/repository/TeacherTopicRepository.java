package datn.dao.repository;

import datn.dao.entity.TeacherTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherTopicRepository extends JpaRepository<TeacherTopic, String> {
}
