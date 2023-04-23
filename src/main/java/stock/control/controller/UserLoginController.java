package stock.control.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stock.control.entity.UserLoginEntity;
import stock.control.service.UserLoginService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@RestController
@RequestMapping("/api/login")
@AllArgsConstructor
public class UserLoginController {

    @Autowired
    private final UserLoginService userLoginService;

    @PostMapping
    public ResponseEntity<Object> createNewLogin(@RequestBody UserLoginEntity userLoginEntity){
        userLoginEntity.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(userLoginService.createUserLogin(userLoginEntity));
    }

    @GetMapping
    public ResponseEntity<List<UserLoginEntity>> listUser(){
        return ResponseEntity.status(HttpStatus.OK).body(userLoginService.listAllUser());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable UUID id){
        Optional<UserLoginEntity> userLoginEntity = userLoginService.listUser(id);
        return userLoginEntity.<ResponseEntity<Object>>map(loginEntity
                -> ResponseEntity.status(HttpStatus.OK).body(loginEntity))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUserLogin(@PathVariable UUID id){
        Optional<UserLoginEntity> userLoginEntity = userLoginService.listUser(id);
        if(userLoginEntity.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }else{
            userLoginService.deleteUserLogin(id);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully.");}
    }

}
