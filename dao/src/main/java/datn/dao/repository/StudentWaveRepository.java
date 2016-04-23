package datn.dao.repository;

import datn.dao.entity.ProjectWave;
import datn.dao.entity.StudentWave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentWaveRepository extends JpaRepository<StudentWave, String> {

    @Query("select st from StudentWave st where st.projectWave=:wave")
    List<StudentWave> findByProjectWave(@Param("wave") ProjectWave wave);

    @Query("select st from StudentWave st where st.id=:id")
    StudentWave findOne(@Param("id") String id);

}
