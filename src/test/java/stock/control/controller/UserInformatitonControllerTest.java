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
import stock.control.entity.UserInformationEntity;
import stock.control.service.UserInformationService;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserInformatitonControllerTest {

    @InjectMocks
    UserInformationController userInformatitonController;

    @Mock
    UserInformationService userInformatitonService;

    @Test
    public void saveTest()
    {
        MockHttpServletRequest request = new MockHttpServletRequest(); // Crio uma requisição vazia
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request)); // Coloco os dados na requisição

        var userInformation = getUserInformatiton();
        when(userInformatitonService.createUser(any(UserInformationEntity.class))).thenReturn(userInformation);

        ResponseEntity<Object> responseEntity = userInformatitonController.addNewUser(userInformation);
        assertThat(responseEntity.getBody()).isEqualTo(userInformation);
        assert(responseEntity.getStatusCode().isSameCodeAs(HttpStatus.CREATED));
    }

    @Test
    public void testFindAll() {

        var userInformation = getUserInformatiton();

        List<UserInformationEntity> users = new ArrayList<>();
        users.add(userInformation);

        when(userInformatitonService.listAllUser()).thenReturn(users);
        ResponseEntity<List<UserInformationEntity>> responseEntity = userInformatitonController.listUser();

        assertThat(Objects.requireNonNull(responseEntity.getBody()).get(0).getNickName()).isEqualTo(userInformation.getNickName());
        assertThat(Objects.requireNonNull(responseEntity.getBody()).get(0).getRegistrationDate()).isEqualTo(userInformation.getRegistrationDate());
        assertThat(Objects.requireNonNull(responseEntity.getBody()).get(0).getId()).isEqualTo(userInformation.getId());
        assertThat(Objects.requireNonNull(responseEntity.getBody()).get(0).getLastName()).isEqualTo(userInformation.getLastName());
        assertThat(Objects.requireNonNull(responseEntity.getBody()).get(0).getEmail()).isEqualTo(userInformation.getEmail());
        assertThat(Objects.requireNonNull(responseEntity.getBody()).get(0).getBirthday()).isEqualTo(userInformation.getBirthday());
        assertThat(Objects.requireNonNull(responseEntity.getBody()).get(0).getUserAddressHome()).isEqualTo(userInformation.getUserAddressHome());
        assertThat(Objects.requireNonNull(responseEntity.getBody()).get(0).getUserHomeNumber()).isEqualTo(userInformation.getUserHomeNumber());
        assertThat(Objects.requireNonNull(responseEntity.getBody()).get(0).getUserPhoneNumber()).isEqualTo(userInformation.getUserPhoneNumber());
        assertThat(Objects.requireNonNull(responseEntity.getBody()).get(0).getUserCity()).isEqualTo(userInformation.getUserCity());
        assertThat(Objects.requireNonNull(responseEntity.getBody()).get(0).getUserState()).isEqualTo(userInformation.getUserState());
        assertThat(Objects.requireNonNull(responseEntity.getBody()).get(0).getUserCountry()).isEqualTo(userInformation.getUserCountry());

    }

    @Test
    public void testFindById() {

        var userInformation = getUserInformatiton();

        Optional<UserInformationEntity> users = Optional.of(userInformation);

        when(userInformatitonService.listUser(userInformation.getId())).thenReturn(users);
        ResponseEntity<Object> responseEntity = userInformatitonController.getUser(userInformation.getId());

        assert(Objects.requireNonNull(responseEntity.getBody()).equals(userInformation));
        assert(responseEntity.getStatusCode().isSameCodeAs(HttpStatus.OK));
    }

    @Test
    public void testFindById_NotFound() {

        UUID id = UUID.fromString("39a9e55c-6636-46e2-963f-ac0d1c7d7d05");

        ResponseEntity<Object> responseEntity = userInformatitonController.getUser(id);

        assert(responseEntity.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND));
    }

    @Test
    public void testRemoveUserInformation() {

        var userInformation = getUserInformatiton();

        userInformatitonService.removeUser(userInformation.getId());

        verify(userInformatitonService, times(1)).removeUser(userInformation.getId());
        ResponseEntity<Object> responseEntity = userInformatitonController.deleteUser(userInformation.getId());

        assert(responseEntity.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND));

    }

    private UserInformationEntity getUserInformatiton(){

    UUID id = UUID.fromString("39a9e55c-6636-46e2-963f-ac0d1c7d7d05");
    LocalDateTime Date = LocalDateTime.now();
    Date DateBirthday =new Date(1992-12-5);
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
