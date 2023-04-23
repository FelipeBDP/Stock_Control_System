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
import stock.control.entity.UserInformationEntity;
import stock.control.service.UserInformationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserInformatitonControllerTest {

    @InjectMocks
    UserInformationController userInformatitonController;

    @Mock
    UserInformationService userInformatitonService;

    @Test
    public void saveTest()
    {
        MockHttpServletRequest request = new MockHttpServletRequest(); // Crio uma requisição vazia
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request)); // Coloco os dados na requisição

        var userInformatitonEntity = new UserInformationEntity();
        userInformatitonEntity.setNickName("teste");
        userInformatitonEntity.setRegistrationDate(null);

        when(userInformatitonService.createUser(any(UserInformationEntity.class))).thenReturn(userInformatitonEntity);

        ResponseEntity<Object> responseEntity = userInformatitonController.addNewUser(userInformatitonEntity);
        assertThat(responseEntity.getBody()).isEqualTo(userInformatitonEntity);
    }

    @Test
    public void testFindAll() {
        var userInformatitonEntity = new UserInformationEntity();
        userInformatitonEntity.setNickName("teste");
        userInformatitonEntity.setRegistrationDate(null);

        List<UserInformationEntity> users = new ArrayList<>();
        users.add(userInformatitonEntity);

        when(userInformatitonService.listAllUser()).thenReturn(users);
        ResponseEntity<List<UserInformationEntity>> responseEntity = userInformatitonController.listUser();

        assertThat(Objects.requireNonNull(responseEntity.getBody()).get(0).getNickName()).isEqualTo(userInformatitonEntity.getNickName());

    }

    @Test
    public void testRemoveUserInformation() {
        UUID id = UUID.fromString("39a9e55c-6636-46e2-963f-ac0d1c7d7d05");
        var userInformatitonEntity = new UserInformationEntity();
        userInformatitonEntity.setNickName("teste");
        userInformatitonEntity.setRegistrationDate(null);
        userInformatitonEntity.setId(id);

        userInformatitonService.removeUser(id);

        verify(userInformatitonService, times(1)).removeUser(id);

    }
}
