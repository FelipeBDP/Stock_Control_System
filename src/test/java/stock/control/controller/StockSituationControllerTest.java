package stock.control.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import stock.control.entity.StockSituationEntity;
import stock.control.service.StockSituationService;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StockSituationControllerTest{
   @InjectMocks
    StockSituationController stockSituationController;

    @Mock
    StockSituationService stockSituationService;

    @Test
    public void saveTest()
    {
        MockHttpServletRequest request = new MockHttpServletRequest(); // Crio uma requisição vazia
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request)); // Coloco os dados na requisição

        var stockSituation = new StockSituationEntity();
        stockSituation.setStock("BBAS3");
        stockSituation.setRegistrationDate(null);

        when(stockSituationService.addStock(any(StockSituationEntity.class))).thenReturn(stockSituation);

        ResponseEntity<Object> responseEntity = stockSituationController.addSituation(stockSituation);
        assertThat(responseEntity.getBody()).isEqualTo(stockSituation);
    }

    @Test
    public void testRemoveStockSituation() {
        UUID id = UUID.fromString("39a9e55c-6636-46e2-963f-ac0d1c7d7d05");
        var stockSituation = new StockSituationEntity();
        stockSituation.setStock("BBAS3");
        stockSituation.setRegistrationDate(null);
        stockSituation.setId(id);

        stockSituationService.removeStock(id);

        verify(stockSituationService, times(1)).removeStock(id);

    }

}