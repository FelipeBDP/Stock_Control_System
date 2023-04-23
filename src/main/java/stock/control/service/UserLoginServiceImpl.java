package stock.control.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import stock.control.entity.UserLoginEntity;
import stock.control.repository.UserLoginRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserLoginServiceImpl implements UserLoginService{

    private final UserLoginRepository userLoginRepository;

    @Transactional
    public UserLoginEntity createUserLogin(UserLoginEntity userLoginEntity){
        return userLoginRepository.save(userLoginEntity);
    }

    public List<UserLoginEntity> listAllUser(){
        return userLoginRepository.findAll();
    }

    public Optional<UserLoginEntity> listUser(UUID id){
        return userLoginRepository.findById(id);
    }

    @Transactional
    public void deleteUserLogin(UUID id){
        userLoginRepository.deleteById(id);
    }
}
