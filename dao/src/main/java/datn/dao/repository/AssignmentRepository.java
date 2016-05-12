package datn.dao.repository;

import datn.dao.entity.Assignment;
import datn.dao.entity.ProjectWave;
import datn.dao.entity.Student;
import datn.dao.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, String> {

    @Query("select st from Assignment st where (st.student=:student and st.projectWave=:projectWave)")
    ArrayList<Assignment> findByStudentAndWave(@Param("student")Student student, @Param("projectWave")ProjectWave projectWave);

    @Query("select st from Assignment st where (st.teacher=:teacher and st.projectWave=:projectWave)")
    ArrayList<Assignment> findByTeacherAndWave(@Param("teacher")Teacher teacher, @Param("projectWave")ProjectWave wave);

    @Query("select st from Assignment st where (st.student=:student and st.teacher=:teacher and st.projectWave=:projectWave)")
    Assignment findByStudentAndTeacherAndWave(@Param("student")Student student, @Param("teacher") Teacher teacher, @Param("projectWave")ProjectWave projectWave);
}
