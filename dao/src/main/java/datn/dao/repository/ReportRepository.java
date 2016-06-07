package datn.dao.repository;

import datn.dao.entity.ProjectWave;
import datn.dao.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

public interface ReportRepository extends JpaRepository<Report, String>{

    @Query("select st from Report st where st.projectWave=:projectWave order by st.ordinal")
    ArrayList<Report> findByProjectWave(@Param("projectWave") ProjectWave projectWave);

}
