package datn.dao.repository;

import datn.dao.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ReportRepository extends JpaRepository<Report, String>{
}
