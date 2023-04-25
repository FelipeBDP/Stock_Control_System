package stock.control.service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import stock.control.entity.UserInformationEntity;
import stock.control.repository.UserInformationRepository;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Transactional
@ExtendWith(MockitoExtension.class)
public class UserInformationServiceImplTest {
    
    @InjectMocks
    UserInformationServiceImpl userInformationService;
    @Mock
    UserInformationRepository userInformationRepository;
    @Test
    void should_save() {

        var  userLoginEntity = getUserInformatiton();

        when(userInformationRepository.save(any(UserInformationEntity.class))).thenReturn(userLoginEntity);

        final var actual = userInformationService.createUser(userLoginEntity);

        assertThat(actual).usingRecursiveComparison().isEqualTo(userLoginEntity);
    }

    @Test
    void should_find() {

        var  userLoginEntity = getUserInformatiton();

        when(userInformationRepository.findById(any())).thenReturn(Optional.of(userLoginEntity));

        final var actual = userInformationService.listUser(userLoginEntity.getId());

        assertThat(actual).usingRecursiveComparison().isEqualTo(Optional.of(userLoginEntity));
    }

    @Test
    void should_find_one_userinformation() {

        var  userLoginEntity = getUserInformatiton();

        List<UserInformationEntity> userLogin = new ArrayList<>();
        userLogin.add(userLoginEntity);

        when(userInformationRepository.findAll()).thenReturn(userLogin);

        final var actual = userInformationService.listAllUser();

        assertThat(actual).usingRecursiveComparison().isEqualTo(userLogin);
    }

    @Test
    void should_delete_one_userlogin() {

        UUID id = UUID.fromString("39a9e55c-6636-46e2-963f-ac0d1c7d7d05");

        doNothing().when(userInformationRepository).deleteById(any());

        userInformationService.removeUser(id);

        verify(userInformationRepository, times(1)).deleteById(any());
        verifyNoMoreInteractions(userInformationRepository);
    }

    private UserInformationEntity getUserInformatiton(){

        UUID id = UUID.fromString("39a9e55c-6636-46e2-963f-ac0d1c7d7d05");
        LocalDateTime Date = LocalDateTime.now();
        java.util.Date DateBirthday =new Date(1992-12-5);
        var userInformatitonEntity = new UserInformationEntity();
        userInformatitonEntity.setRegistrationDate(Date);
        userInformatitonEntity.setNickName("teste");
        userInformatitonEntity.setId(id);
        userInformatitonEntity.setLastName("teste");
        userInformatitonEntity.setEmail("teste");
        userInformatitonEntity.setBirthday(DateBirthday);
        userInformatitonEntity.setUserAddressHome("teste");
        userInformatitonEntity.setUserHomeNumber("teste");
        userInformatitonEntity.setUserPhoneNumber("teste");
        userInformatitonEntity.setUserCity("teste");
        userInformatitonEntity.setUserState("teste");
        userInformatitonEntity.setUserCountry("teste");
        return userInformatitonEntity;
    }
}
