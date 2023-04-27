package com.example.classactivity.controller;

import com.example.classactivity.dto.UserDTO;
import com.example.classactivity.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final HttpServletRequest request;

    public UserController(UserService userService, HttpServletRequest request) {
        this.userService = userService;
        this.request = request;
    }
    @PostMapping("/register")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO, HttpServletRequest httpServletRequest){
        return  new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login (@RequestBody UserDTO userDTO){
        UserDTO userDTO1 = userService.loginUser(userDTO);
        return  new ResponseEntity<>(userDTO1.getUserName(), HttpStatus.OK);
    }
    @PutMapping ("/update")
    public  ResponseEntity <String> updateUser(@RequestBody UserDTO userDTO){
        UserDTO updateUser = userService.updateUser(userDTO);
        return  new ResponseEntity<>(updateUser.getUserName(), HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(){
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("id") == null) {
            return  new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        userService.deleteUSer();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
