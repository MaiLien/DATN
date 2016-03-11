package datn.dao.repository;

import datn.dao.entity.PersonMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonMessageRepository extends JpaRepository<PersonMessage, String> {
}
