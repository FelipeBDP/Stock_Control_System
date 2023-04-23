package stock.control.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import stock.control.entity.UserInformationEntity;
import stock.control.repository.UserInformationRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserInformationServiceImpl implements UserInformationService {

    private final UserInformationRepository userInformationRepository;
    @Transactional
    public UserInformationEntity createUser(UserInformationEntity userInformationEntity){
        return userInformationRepository.save(userInformationEntity);
    }

    public List<UserInformationEntity> listAllUser(){
        return userInformationRepository.findAll();
    }

    public Optional<UserInformationEntity> listUser(UUID id){
        return userInformationRepository.findById(id);
    }

    @Transactional
    public void removeUser(UUID id){
        userInformationRepository.deleteById(id);
    }

}