package datn.repository;

import datn.entity.StudentWave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentWaveRepository extends JpaRepository<StudentWave, String> {
}
