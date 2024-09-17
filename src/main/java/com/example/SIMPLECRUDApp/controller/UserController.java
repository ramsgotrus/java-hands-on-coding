package com.example.SIMPLECRUDApp.controller;

import com.example.SIMPLECRUDApp.model.Guest;
import com.example.SIMPLECRUDApp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    UserRepo userRepo;
    @GetMapping("/getUser")
    public ResponseEntity<List<Guest>> getAllUsers(){
        try{
            List<Guest> userList = userRepo.findAll();
            if(userList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(userList, HttpStatus.OK);
        }catch (Exception exp){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PostMapping("/addUser")
    public ResponseEntity<Guest> addUser(@RequestBody Guest user){
       try{
           Guest userObj = userRepo.save(user);
           return new ResponseEntity<>(userObj, HttpStatus.CREATED);
       }catch (Exception exp){
           return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
    @PostMapping("/updateUser")
    public ResponseEntity<Guest> updateUser(@PathVariable Long id, @RequestBody Guest user){
        try{
            Optional<Guest> userData = userRepo.findById(id);
            if (userData.isPresent()) {
                Guest updatedUserData = userData.get();
                updatedUserData.setFirstName(user.getFirstName());
                updatedUserData.setLastName(user.getLastName());
                updatedUserData.setDisplayName(user.getDisplayName());
                Guest useObj = userRepo.save(updatedUserData);
               return  new ResponseEntity<>(useObj,HttpStatus.CREATED);
            }
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }catch (Exception exp){
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/search")
    public ResponseEntity<List<Guest>> searchUsers(@RequestParam String query){
        try{
             List<Guest> users = userRepo.findByFirstNameORLastName(query);
             if(users.isEmpty()){
                 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
             }
             return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
           return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
