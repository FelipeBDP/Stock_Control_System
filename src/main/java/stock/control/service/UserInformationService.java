package stock.control.service;

import stock.control.entity.UserInformationEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserInformationService {

    UserInformationEntity createUser(UserInformationEntity userInformation);

   List<UserInformationEntity> listAllUser();

    Optional<UserInformationEntity> listUser(UUID id);

    void removeUser(UUID id);

}
