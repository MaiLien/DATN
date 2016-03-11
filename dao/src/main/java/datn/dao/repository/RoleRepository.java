package datn.dao.repository;

import datn.dao.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface RoleRepository extends JpaRepository<Role, String>{

    @Query("select r from Role r where r.id= :id")
    public ArrayList<Role> getRolesByUserId(String id);

}
