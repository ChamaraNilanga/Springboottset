package com.test2.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiControllers {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String getPage(){
        return "Welcome";
    }

    @GetMapping( path = "/users")
    public List<User> getUser () {
        return userRepo.findAll();
    }

    @PostMapping(path = "/save")
    public String saveUser(@RequestBody User user){
        userRepo.save(user);
        return "saved";
    }

    @PutMapping(value = "update/{personID}")
    public String updateUser (@PathVariable Integer personID,@RequestBody User user){
        User updatedUser = userRepo.findById(personID).get();
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setAddress(user.getAddress());
        updatedUser.setCity(user.getCity());
        userRepo.save(updatedUser);
        return "updated successfully";
    }

    @DeleteMapping(path = "/deleteUser/{id}")
    public String deleteUser(@PathVariable Integer id) {
        User deleteUser = userRepo.findById(id).get();
        userRepo.delete(deleteUser);
        return "deleted successfully";
    }
}
