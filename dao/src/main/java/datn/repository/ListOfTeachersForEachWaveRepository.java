package datn.repository;

import datn.entity.ListOfTeachersForEachWave;
import datn.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListOfTeachersForEachWaveRepository extends JpaRepository<ListOfTeachersForEachWave, String> {
}
