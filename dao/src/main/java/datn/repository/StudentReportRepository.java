package datn.repository;

import datn.entity.StudentReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentReportRepository extends JpaRepository<StudentReport, String>{
}
