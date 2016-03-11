package datn.dao.repository;

import datn.dao.entity.Role;
import datn.dao.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String> {

    @Query("select r.role from UserRole r where r.user.id= :id")
    public ArrayList<Role> getRolesByUserId(@Param("id") String id);

}
