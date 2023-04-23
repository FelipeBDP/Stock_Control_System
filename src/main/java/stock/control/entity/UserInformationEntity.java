package stock.control.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@Table(name = "TB_USER_INFORMATION") //Always put name of table that I will use
@Entity
public class UserInformationEntity implements Serializable { // The data is serialize

    @Serial
    private static final long serialVersionUID = 1L; // I use to start ID

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Generated automatic the ID
    private UUID id; //UUID is type to use in generated ID

    @Column
    private String nickName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private Date birthday;

    @Column
    private String userAddressHome;

    @Column
    private String userHomeNumber;

    @Column
    private String userPhoneNumber;

    @Column
    private String userCity;

    @Column
    private String userState;

    @Column
    private String userCountry;

    @Column
    private LocalDateTime registrationDate;

}
