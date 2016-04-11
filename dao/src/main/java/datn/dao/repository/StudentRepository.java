package datn.dao.repository;

import datn.dao.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    @Override
    @Query("select st from Student st where st.deleted = 0")
    List<Student> findAll();

    @Override
    @Query("select st from Student st where st.deleted = 0")
    Page<Student> findAll(Pageable pageable);

    @Query("select st from Student st where st.deleted = 0 and ((LOWER(st.name) like :searchInput) or (LOWER(st.username) like :searchInput))")
    Page<Student> findBySearchInput(Pageable pageable, @Param("searchInput") String searchInput);

}
