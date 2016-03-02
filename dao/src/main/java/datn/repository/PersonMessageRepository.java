package datn.repository;

import datn.entity.PersonMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonMessageRepository extends JpaRepository<PersonMessage, String> {
}
