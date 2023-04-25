package stock.control.service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import stock.control.entity.StockSituationEntity;
import stock.control.repository.StockSituationRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Transactional
@ExtendWith(MockitoExtension.class)
public class StockSituationServiceImplTest {

    @InjectMocks
    StockSituationServiceImpl stockSituationService;

    @Mock
    StockSituationRepository stockSituationRepository;

    @Test
    void should_save() {

        var stock = getStockSituation();

        when(stockSituationRepository.save(any(StockSituationEntity.class))).thenReturn(stock);

        final var actual =  stockSituationService.addStock(stock );

        assertThat(actual).usingRecursiveComparison().isEqualTo(stock );
    }

    @Test
    void should_find() {

        var stock = getStockSituation();

        when(stockSituationRepository.findById(any())).thenReturn(Optional.of(stock));

        final var actual = stockSituationService.listStock(stock.getId());

        assertThat(actual).usingRecursiveComparison().isEqualTo(Optional.of(stock));
    }

    @Test
    void should_find_one_stock() {

        var stock = getStockSituation();

        List<StockSituationEntity> stockSituation = new ArrayList<>();
        stockSituation.add(stock);

        when(stockSituationRepository.findAll()).thenReturn(stockSituation);

        final var actual = stockSituationService.listAllStock();

        assertThat(actual).usingRecursiveComparison().isEqualTo(stockSituation);
    }

    @Test
    void should_delete_one_userlogin() {

        UUID id = UUID.fromString("39a9e55c-6636-46e2-963f-ac0d1c7d7d05");

        doNothing().when(stockSituationRepository).deleteById(any());

        stockSituationService.removeStock(id);

        verify(stockSituationRepository, times(1)).deleteById(any());
        verifyNoMoreInteractions(stockSituationRepository);
    }

    private StockSituationEntity getStockSituation(){
        UUID id = UUID.fromString("39a9e55c-6636-46e2-963f-ac0d1c7d7d05");
        LocalDateTime Date = LocalDateTime.now();
        var stockSituationEntity = new StockSituationEntity();
        stockSituationEntity.setRegistrationDate(Date);
        stockSituationEntity.setStock("BBAS3");
        stockSituationEntity.setOperation("C");
        stockSituationEntity.setPrice(BigDecimal.valueOf(33.00));
        stockSituationEntity.setQuantity(10);
        stockSituationEntity.setId(id);
        return stockSituationEntity;
    }
}
