package datn.dao.repository;

import datn.dao.entity.Report;
import datn.dao.entity.Student;
import datn.dao.entity.StudentReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface StudentReportRepository extends JpaRepository<StudentReport, String>{

    @Query("select st from StudentReport st where (st.student=:student and st.report=:report)")
    StudentReport findByStudentAndReport(@Param("student")Student student, @Param("report")Report report);

}
