package stock.control.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import stock.control.entity.StockSituationEntity;
import stock.control.repository.StockSituationRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public  class StockSituationServiceImpl implements StockSituationService{

    private final StockSituationRepository stockSituationRepository;
    @Transactional
    public StockSituationEntity addStock(StockSituationEntity stockSituationEntity){
       return stockSituationRepository.save(stockSituationEntity);
    }

    public List<StockSituationEntity> listAllStock(){
        return stockSituationRepository.findAll();
    }

    public Optional<StockSituationEntity> listStock(UUID id){
        return stockSituationRepository.findById(id);
    }

    @Transactional
    public void removeStock(UUID id){
       stockSituationRepository.deleteById(id);
    }

}