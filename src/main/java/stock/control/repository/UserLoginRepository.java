package stock.control.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stock.control.entity.UserLoginEntity;

import java.util.UUID;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLoginEntity, UUID> {}
