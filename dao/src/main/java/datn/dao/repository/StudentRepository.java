package datn.dao.repository;

import datn.dao.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {

    @Query("select st from Student st")
    Page<Student> findAll(Pageable pageable);

    @Query("select st from Student st where ((LOWER(st.name) like :searchInput) or (LOWER(st.username) like :searchInput))")
    Page<Student> findBySearchInput(Pageable pageable, @Param("searchInput") String searchInput);

    @Query("select st from Student st where st.username =:username")
    Student findByUsername(@Param("username")String studentUsername);
}
