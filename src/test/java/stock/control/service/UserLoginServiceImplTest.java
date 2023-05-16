package stock.control.service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import stock.control.entity.UserLoginEntity;
import stock.control.repository.UserLoginRepository;

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
class UserLoginServiceImplTest {

    @InjectMocks
    UserLoginServiceImpl userLoginService;
    @Mock
    UserLoginRepository userLoginRepository;

   @Test
    void should_save() {

        var  userLoginEntity = getUserLogin();

        when(userLoginRepository.save(any(UserLoginEntity.class))).thenReturn(userLoginEntity);

        final var actual = userLoginService.createUserLogin(userLoginEntity);

        assertThat(actual).usingRecursiveComparison().isEqualTo(userLoginEntity);
    }

    @Test
    void should_find() {

        var  userLoginEntity = getUserLogin();

        when(userLoginRepository.findById(any())).thenReturn(Optional.of(userLoginEntity));

        // Act
        final var actual = userLoginService.listUser(userLoginEntity.getId());

        assertThat(actual).usingRecursiveComparison().isEqualTo(Optional.of(userLoginEntity));
    }

    @Test
    void should_find_one_userlogin() {

        var  userLoginEntity = getUserLogin();

        List<UserLoginEntity> userLogin = new ArrayList<>();
        userLogin.add(userLoginEntity);

        when(userLoginRepository.findAll()).thenReturn(userLogin);

        final var actual = userLoginService.listAllUser();

        assertThat(actual).usingRecursiveComparison().isEqualTo(userLogin);
    }

   @Test
    void should_delete_one_userlogin() {

       UUID id = UUID.fromString("39a9e55c-6636-46e2-963f-ac0d1c7d7d05");

       doNothing().when(userLoginRepository).deleteById(any());

       userLoginService.deleteUserLogin(id);
        verify(userLoginRepository, times(1)).deleteById(any());
        verifyNoMoreInteractions(userLoginRepository);
    }

    private UserLoginEntity getUserLogin(){
        UUID id = UUID.fromString("39a9e55c-6636-46e2-963f-ac0d1c7d7d05");
        LocalDateTime Date = LocalDateTime.now();
        var userLoginEntity = new UserLoginEntity();
        userLoginEntity.setRegistrationDate(Date);
        userLoginEntity.setLoginName("teste");
        userLoginEntity.setPassword("aaaa");
        userLoginEntity.setId(id);
        return userLoginEntity;
    }
}
