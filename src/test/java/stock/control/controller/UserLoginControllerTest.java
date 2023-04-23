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
import stock.control.entity.UserLoginEntity;
import stock.control.service.UserLoginService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserLoginControllerTest {

    @InjectMocks //Injetar os valores no controler
    UserLoginController userLoginController;

    @Mock //Injetar os valores na Entity
    UserLoginService userLoginService;

    @Test
    public void testAddUserLogin(){
        MockHttpServletRequest request = new MockHttpServletRequest(); // Crio uma requisição vazia
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request)); // Coloco os dados na requisição

        var userLoginEntity = new UserLoginEntity();
        userLoginEntity.setRegistrationDate(null);
        userLoginEntity.setLoginName("teste");
        userLoginEntity.setPassword("aaaa");

        when(userLoginService.createUserLogin(any(UserLoginEntity.class))).thenReturn(userLoginEntity);

        ResponseEntity<Object> responseEntity = userLoginController.createNewLogin(userLoginEntity);
        assertThat(responseEntity.getBody()).isEqualTo(userLoginEntity);

    }

    @Test
    public void testFindAll() {
        var userLoginEntity = new UserLoginEntity();
        userLoginEntity.setRegistrationDate(null);
        userLoginEntity.setLoginName("teste");
        userLoginEntity.setPassword("aaaa");

       List<UserLoginEntity> users = new ArrayList<>();
       users.add(userLoginEntity);

       when(userLoginService.listAllUser()).thenReturn(users);
       ResponseEntity<List<UserLoginEntity>> responseEntity = userLoginController.listUser();

       assertThat(Objects.requireNonNull(responseEntity.getBody()).get(0).getLoginName()).isEqualTo(userLoginEntity.getLoginName());
       assertThat(Objects.requireNonNull(responseEntity.getBody()).get(0).getPassword()).isEqualTo(userLoginEntity.getPassword());

    }

    @Test
    public void testRemoveUserLogin() {
        UUID id = UUID.fromString("39a9e55c-6636-46e2-963f-ac0d1c7d7d05");
        var userLoginEntity = new UserLoginEntity();
        userLoginEntity.setRegistrationDate(null);
        userLoginEntity.setLoginName("teste");
        userLoginEntity.setPassword("aaaa");
        userLoginEntity.setId(id);

        userLoginService.deleteUserLogin(id);

        verify(userLoginService, times(1)).deleteUserLogin(id);

    }
}
