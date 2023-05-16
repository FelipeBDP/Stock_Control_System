package stock.control.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table(name = "TB_USER_LOGIN")
@Entity
public class UserLoginEntity implements Serializable { // The data is serialize

   @Serial
   private static final long serialVersionUID = 1L; // I use to start ID

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Generated automatic the ID
    private UUID id;

    @Column
    private String loginName;

    @Column
    private String password;

    @Column
    private LocalDateTime registrationDate;
}