package datn.dao.repository;

import datn.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

    @Query("select u from User u where u.username = :username and u.password = :password")
    User findUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    User findByUsername(String username);

}
