package stock.control.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stock.control.entity.UserInformationEntity;

import java.util.UUID;

@Repository
public interface UserInformationRepository extends JpaRepository<UserInformationEntity, UUID> {
}
