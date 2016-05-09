package datn.dao.repository;

import datn.dao.entity.ProjectWave;
import datn.dao.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface ProjectWaveRepository extends JpaRepository<ProjectWave, String> {

    @Query("select st from ProjectWave st where LOWER(st.schoolYear) like :searchInput order by st.startDay desc")
    Page<ProjectWave> findBySearchInput(Pageable pageable,@Param("searchInput") String searchInput);

    @Query("select st from ProjectWave st order by st.startDay desc")
    Page<ProjectWave> findAll(Pageable pageable);

}
