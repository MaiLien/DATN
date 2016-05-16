package datn.dao.repository;

import datn.dao.entity.StudentReport;
import datn.dao.entity.StudentReportDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentReportDetailRepository extends JpaRepository <StudentReportDetail, String>{

    @Query("select st from StudentReportDetail st where (st.studentReport=:studentReport)")
    StudentReportDetail findByStudentReport(@Param("studentReport")StudentReport studentReport);

}
