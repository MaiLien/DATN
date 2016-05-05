package datn.dao.repository;

import datn.dao.entity.Student;
import datn.dao.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, String> {

    @Query("select st from Teacher st")
    Page<Teacher> findAll(Pageable pageable);

    @Query("select st from Teacher st where ((LOWER(st.name) like :searchInput) or (LOWER(st.username) like :searchInput))")
    Page<Teacher> findBySearchInput(Pageable pageable, @Param("searchInput") String searchInput);

    @Query("select st from Teacher st where st.username =:username")
    Teacher findByUsername(@Param("username")String studentUsername);

}
