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
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Transactional
@ExtendWith(MockitoExtension.class)
class UserLoginServiceImplTest {

    @InjectMocks
    UserLoginServiceImpl userLoginService;
    @Mock
    UserLoginRepository userLoginRepository;

   @Test
    void should_save_one_student() {
        // Arrange

        var  userLoginEntity = getUserLogin();

        when(userLoginRepository.save(any(UserLoginEntity.class))).thenReturn(userLoginEntity);

        // Act
        final var actual = userLoginService.createUserLogin(userLoginEntity);

        // Assert
        assertThat(actual).usingRecursiveComparison().isEqualTo(userLoginEntity);
    }


   /*
    @Test
    public void testFindById() {
        Employee employee = getEmployee();
        employeeRepository.save(employee);
        Employee result = employeeRepository.findById(employee.getId()).get();
        assertEquals(employee.getId(), result.getId());
    }
    @Test
    public void testFindAll() {
        Employee employee = getEmployee();
        employeeRepository.save(employee);
        List<Employee> result = new ArrayList<>();
        employeeRepository.findAll().forEach(e -> result.add(e));
        assertEquals(result.size(), 1);
    }
    @Test
    public void testSave() {
        Employee employee = getEmployee();
        employeeRepository.save(employee);
        Employee found = employeeRepository.findById(employee.getId()).get();
        assertEquals(employee.getId(), found.getId());
    }
    @Test
    public void testDeleteById() {
        Employee employee = getEmployee();
        employeeRepository.save(employee);
        employeeRepository.deleteById(employee.getId());
        List<Employee> result = new ArrayList<>();
        employeeRepository.findAll().forEach(e -> result.add(e));
        assertEquals(result.size(), 0);
    }*/


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
