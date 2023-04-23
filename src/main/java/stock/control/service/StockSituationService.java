package stock.control.service;


import stock.control.entity.StockSituationEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StockSituationService {
    StockSituationEntity addStock(StockSituationEntity stockSituationEntity);

    List<StockSituationEntity> listAllStock();

    Optional<StockSituationEntity> listStock(UUID id);

    void removeStock(UUID id);
}