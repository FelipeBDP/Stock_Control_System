package stock.control.service;

import stock.control.entity.UserLoginEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserLoginService {

    UserLoginEntity createUserLogin(UserLoginEntity userLoginEntity);

    List<UserLoginEntity> listAllUser();

    Optional<UserLoginEntity> listUser(UUID id);

    void deleteUserLogin(UUID id);
}
