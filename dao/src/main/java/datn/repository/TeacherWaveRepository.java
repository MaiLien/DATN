package datn.repository;

import datn.entity.TeacherWave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherWaveRepository extends JpaRepository<TeacherWave, String> {
}
