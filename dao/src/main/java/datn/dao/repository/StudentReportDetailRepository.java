package datn.dao.repository;

import datn.dao.entity.StudentReport;
import datn.dao.entity.StudentReportDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface StudentReportDetailRepository extends JpaRepository <StudentReportDetail, String>{

    @Query("select st from StudentReportDetail st where (st.studentReport=:studentReport)")
    ArrayList<StudentReportDetail> findByStudentReport(@Param("studentReport")StudentReport studentReport);

    @Modifying(clearAutomatically = true)
    @Query("delete from StudentReportDetail st where (st.studentReport=:studentReport)")
    void deleteByStudentReport(@Param("studentReport")StudentReport studentReport);
}
