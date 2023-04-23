package stock.control.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table(name = "TB_STOCK_SITUATION") //Always put name of table that I will use
@Entity
public class StockSituationEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L; // I use to start ID

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Generated automatic the ID
    private UUID id; //UUID is type to use in generated ID

    @Column
    private Integer quantity;

    @Column
    private BigDecimal price;

    @Column
    private String operation;

    @Column
    private String stock;

    @Column
    private LocalDateTime registrationDate;
}