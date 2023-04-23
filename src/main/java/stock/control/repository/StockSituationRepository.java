package stock.control.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stock.control.entity.StockSituationEntity;

import java.util.UUID;

@Repository
public interface StockSituationRepository extends JpaRepository<StockSituationEntity, UUID> {
}
