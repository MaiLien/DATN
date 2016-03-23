package datn.dao.repository;

import datn.dao.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    @Override
    @Query("select st from Student st where st.deleted = 0")
    List<Student> findAll();
}
