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
import stock.control.entity.UserLoginEntity;
import stock.control.service.UserLoginService;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserLoginControllerTest {

    @InjectMocks //Injetar os valores no controler
    UserLoginController userLoginController;

    @Mock //Injetar os valores na Entity
    UserLoginService userLoginService;

    @Test
    public void testAddUserLogin(){
       MockHttpServletRequest request = new MockHttpServletRequest(); // Crio uma requisição vazia
       RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request)); // Coloco os dados na requisição

       var userLogin = getUserLogin();

       when(userLoginService.createUserLogin(any(UserLoginEntity.class))).thenReturn(userLogin);

       ResponseEntity<Object> responseEntity = userLoginController.createNewLogin(userLogin);
       assertThat(responseEntity.getBody()).isEqualTo(userLogin);
       assert(responseEntity.getStatusCode().isSameCodeAs(HttpStatus.CREATED));

    }

    @Test
    public void testFindAll() {

       var userLogin = getUserLogin();

       List<UserLoginEntity> users = new ArrayList<>();
       users.add(userLogin);

       when(userLoginService.listAllUser()).thenReturn(users);
       ResponseEntity<List<UserLoginEntity>> responseEntity = userLoginController.listUser();

       assertThat(Objects.requireNonNull(responseEntity.getBody()).get(0).getLoginName()).isEqualTo(userLogin.getLoginName());
       assertThat(Objects.requireNonNull(responseEntity.getBody()).get(0).getPassword()).isEqualTo(userLogin.getPassword());
       assertThat(Objects.requireNonNull(responseEntity.getBody()).get(0).getId()).isEqualTo(userLogin.getId());
       assertThat(Objects.requireNonNull(responseEntity.getBody()).get(0).getRegistrationDate()).isEqualTo(userLogin.getRegistrationDate());

    }

    @Test
    public void testFindById() {

        var userLogin = getUserLogin();

        Optional<UserLoginEntity> users = Optional.of(userLogin);

        when(userLoginService.listUser(userLogin.getId())).thenReturn(users);
        ResponseEntity<Object> responseEntity = userLoginController.getUser(userLogin.getId());

        assert(Objects.requireNonNull(responseEntity.getBody()).equals(userLogin));
        assert(responseEntity.getStatusCode().isSameCodeAs(HttpStatus.OK));
    }

    @Test
    public void testFindById_NotFound() {

        UUID id = UUID.fromString("39a9e55c-6636-46e2-963f-ac0d1c7d7d05");

        ResponseEntity<Object> responseEntity = userLoginController.getUser(id);

        assert(responseEntity.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND));
    }

        @Test
    public void testRemoveUserLogin() {

        var userLogin = getUserLogin();

        userLoginService.deleteUserLogin(userLogin.getId());

        verify(userLoginService, times(1)).deleteUserLogin(userLogin.getId());
        ResponseEntity<Object> responseEntity = userLoginController.deleteUserLogin(userLogin.getId());

        assert(responseEntity.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND));

    }

    @Test
    public void testRemoveUserLoginfind() {

        var userLogin = getUserLogin();

        userLoginService.deleteUserLogin(userLogin.getId());

        verify(userLoginService, times(1)).deleteUserLogin(userLogin.getId());
        ResponseEntity<Object> responseEntity = userLoginController.deleteUserLogin(userLogin.getId());

        assert(responseEntity.getStatusCode().isSameCodeAs(HttpStatus.OK));

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
