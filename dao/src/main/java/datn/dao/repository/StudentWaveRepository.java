package datn.dao.repository;

import datn.dao.entity.ProjectWave;
import datn.dao.entity.Student;
import datn.dao.entity.StudentWave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface StudentWaveRepository extends JpaRepository<StudentWave, String> {

    @Query("select st from StudentWave st where st.projectWave=:wave")
    ArrayList<StudentWave> findByProjectWave(@Param("wave") ProjectWave wave);

    @Query("select st from StudentWave st where st.projectWave=:wave and st.student=:student")
    ArrayList<StudentWave> findByStudentAndProjectWave(@Param("student")Student student, @Param("wave") ProjectWave wave);

    @Query("select st from StudentWave st where st.id=:id")
    StudentWave findOne(@Param("id") String id);

    @Query("select st from StudentWave st where st.student = :student")
    ArrayList<StudentWave> findByStudent(@Param("student")Student student);
}
