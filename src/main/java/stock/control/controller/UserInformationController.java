package stock.control.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stock.control.entity.UserInformationEntity;
import stock.control.service.UserInformationService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/information")
@AllArgsConstructor
public class UserInformationController {

    @Autowired
    private final UserInformationService userInformationService;

    @PostMapping
    public ResponseEntity<Object> addNewUser(@RequestBody UserInformationEntity userInformationEntity){
        userInformationEntity.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(userInformationService.createUser(userInformationEntity));
    }

    @GetMapping
    public ResponseEntity<List<UserInformationEntity>> listUser(){
        return ResponseEntity.status(HttpStatus.OK).body(userInformationService.listAllUser());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable UUID id){
        Optional<UserInformationEntity> userInformationEntity = userInformationService.listUser(id);
        return userInformationEntity.<ResponseEntity<Object>>map(userInformation
                -> ResponseEntity.status(HttpStatus.OK).body(userInformation))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable UUID id){
        Optional<UserInformationEntity> userInformationEntity = userInformationService.listUser(id);
        if(userInformationEntity.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }else{
            userInformationService.removeUser(id);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully.");}
    }

}