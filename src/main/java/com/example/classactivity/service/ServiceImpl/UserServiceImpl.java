package com.example.classactivity.service.ServiceImpl;

import com.example.classactivity.ModelMapperConfig.ModelMapperConfigure;
import com.example.classactivity.Repository.UserRepository;
import com.example.classactivity.dto.UserDTO;
import com.example.classactivity.exception.UserAlreadyExistsException;
import com.example.classactivity.exception.UserNotFoundException;
import com.example.classactivity.model.User;
import com.example.classactivity.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final HttpServletRequest request;

    private final ModelMapper modelMapper;
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        Optional<User> find =  userRepository.findByEmail(userDTO.getEmail());
        if(find.isPresent()){
            throw  new UserAlreadyExistsException("User Already Exists");
        }
        User users = new User();
        users.setUserName(userDTO.getUserName());
        users.setEmail(userDTO.getEmail());
        users.setPassword(userDTO.getPassword());
        User newUser = userRepository.save(users);
        UserDTO mapUser = modelMapper.map(newUser, UserDTO.class);
        return mapUser;

    }

    @Override
    public UserDTO loginUser(UserDTO userDTO) {
        User user = userRepository.findByEmail(userDTO.getEmail()).orElseThrow(() -> new RuntimeException("User Dos Not exists"));

        HttpSession session = request.getSession();
        session.setAttribute("id", user.getId());
        UserDTO mapUser = modelMapper.map(user, UserDTO.class);
        return mapUser;
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("id");
        if(userId == null){
            throw  new RuntimeException("User session Not Found");
        }

        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()) {
            throw  new UserNotFoundException("User not found with id:" + userId);
        }
        User user = userOptional.get();
        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getEmail());
        User updatedUser = userRepository.save(user);
                return modelMapper.map(updatedUser, UserDTO.class);
    }

    @Override
    public void deleteUSer() {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("id");
        if(userId == null) {
            throw new RuntimeException("user session not found");
        }
       Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new UserNotFoundException("User not found with id:" + userId);

        }
        User user = userOptional.get();
        userRepository.delete(user);
    }

}
