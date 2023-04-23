package stock.control.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stock.control.entity.StockSituationEntity;
import stock.control.service.StockSituationService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@RestController
@RequestMapping("/api/stock")
@AllArgsConstructor
public class StockSituationController {

    @Autowired
    private final StockSituationService stockSituationService;

    @PostMapping
    public ResponseEntity<Object> addSituation(@RequestBody StockSituationEntity stockSituationEntity){
        stockSituationEntity.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(stockSituationService.addStock(stockSituationEntity));
    }

    @GetMapping
    public ResponseEntity<List<StockSituationEntity>> listStock(){
        return ResponseEntity.status(HttpStatus.OK).body(stockSituationService.listAllStock());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSitaution(@PathVariable UUID id){
        Optional<StockSituationEntity> stockSituationEntity = stockSituationService.listStock(id);
        return stockSituationEntity.<ResponseEntity<Object>>map(stockSituation -> ResponseEntity.status(HttpStatus.OK).body(stockSituation))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable UUID id){
        Optional<StockSituationEntity> stockSituationEntity = stockSituationService.listStock(id);
        if(stockSituationEntity.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }else{
            stockSituationService.removeStock(id);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully.");}
    }

}
