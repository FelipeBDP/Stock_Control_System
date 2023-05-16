package stock.control.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import stock.control.entity.StockSituationEntity;
import stock.control.service.StockSituationService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StockSituationControllerTest{

   @InjectMocks
    StockSituationController stockSituationController;

    @Mock
    StockSituationService stockSituationService;

    @Test
    public void saveTest()
    {
        MockHttpServletRequest request = new MockHttpServletRequest(); // Crio uma requisição vazia
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request)); // Coloco os dados na requisição

        var stockSituation = getStockSituation();

        when(stockSituationService.addStock(any(StockSituationEntity.class))).thenReturn(stockSituation);

        ResponseEntity<Object> responseEntity = stockSituationController.addSituation(stockSituation);
        assertThat(responseEntity.getBody()).isEqualTo(stockSituation);
        assert(responseEntity.getStatusCode().isSameCodeAs(HttpStatus.CREATED));

    }

    @Test
    public void testFindAll() {

        var stockSituation = getStockSituation();

        List<StockSituationEntity> stocks = new ArrayList<>();
        stocks.add(stockSituation);

        when(stockSituationService.listAllStock()).thenReturn(stocks);
        ResponseEntity<List<StockSituationEntity>> responseEntity = stockSituationController.listStock();

        assertThat(Objects.requireNonNull(responseEntity.getBody()).get(0).getStock()).isEqualTo(stockSituation.getStock());
        assertThat(Objects.requireNonNull(responseEntity.getBody()).get(0).getId()).isEqualTo(stockSituation.getId());
        assertThat(Objects.requireNonNull(responseEntity.getBody()).get(0).getOperation()).isEqualTo(stockSituation.getOperation());
        assertThat(Objects.requireNonNull(responseEntity.getBody()).get(0).getPrice()).isEqualTo(stockSituation.getPrice());
        assertThat(Objects.requireNonNull(responseEntity.getBody()).get(0).getRegistrationDate()).isEqualTo(stockSituation.getRegistrationDate());
        assertThat(Objects.requireNonNull(responseEntity.getBody()).get(0).getQuantity()).isEqualTo(stockSituation.getQuantity());
    }

    @Test
    public void testFindById() {

        var stockSituation = getStockSituation();

        Optional<StockSituationEntity> stocks = Optional.of(stockSituation);

        when(stockSituationService.listStock(stockSituation.getId())).thenReturn(stocks);
        ResponseEntity<Object> responseEntity = stockSituationController.getSitaution(stockSituation.getId());

        assert(Objects.requireNonNull(responseEntity.getBody()).equals(stockSituation));
        assert(responseEntity.getStatusCode().isSameCodeAs(HttpStatus.OK));
    }

    @Test
    public void testFindById_NotFound() {

        UUID id = UUID.fromString("39a9e55c-6636-46e2-963f-ac0d1c7d7d05");

        ResponseEntity<Object> responseEntity = stockSituationController.getSitaution(id);

        assert(responseEntity.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND));
    }


    @Test
    public void WhenRemoveSotck_ButNotFind() {

        var stockSituation = getStockSituation();

        List<StockSituationEntity> stocks = new ArrayList<>();
        stocks.add(stockSituation);

        stockSituationService.removeStock(stockSituation.getId());
        verify(stockSituationService, times(1)).removeStock(stockSituation.getId());

        ResponseEntity<Object> responseEntity = stockSituationController.deleteUser(stockSituation.getId());

        assert(responseEntity.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND));

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