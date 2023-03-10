package com.cvictor.facebookclonespringboot.serviceImplementation;

import com.cvictor.facebookclonespringboot.dto.LogInDTO;
import com.cvictor.facebookclonespringboot.dto.ResponseDTO;
import com.cvictor.facebookclonespringboot.model.Friend;
import com.cvictor.facebookclonespringboot.model.User;
import com.cvictor.facebookclonespringboot.repository.FriendRepository;
import com.cvictor.facebookclonespringboot.repository.UserRepository;
import com.cvictor.facebookclonespringboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final FriendRepository friendRepository;

    /**
     * Method to register a user and save the details to the database
     * @param user the user to be registered
     * @return the response
     */
    public ResponseDTO addUser(User user) {
        Optional<User> userDb = userRepository.getUserByEmailAddress(user.getEmailAddress());
        ResponseDTO response = new ResponseDTO();

        try{
            if (userDb.isPresent()) {
                throw new Exception("This email is already registered");
            }
            User savedUser = userRepository.save(user);
            response.setData(savedUser);
            response.setMessage("Registration successful");
            response.setStatus(true);
            return response;

        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setStatus(false);
            return response;

        }
    }



    public ResponseDTO logInUser(LogInDTO logInDTO) {
        Optional<User> userDb = userRepository.getUserByEmailAddressAndPassword
                (logInDTO.getEmailAddress(), logInDTO.getPassword());

        ResponseDTO response = new ResponseDTO();

        if (userDb.isPresent()) {
            response.setStatus(true);
            response.setData(userDb.get());
            response.setMessage("LogIn successful");
            return response;
        }
        response.setMessage("Invalid email or password");
        return response;

    }
    @Override
    public ResponseDTO follow(Long userId) {
        User user = userRepository.findUserByUserId(userId);
        Friend friend = new Friend();
        friend.setUser(user);
        friendRepository.save(friend);
        return null;
    }

    @Override
    public ResponseDTO unfollow(Long userId) {
        User user = userRepository.findUserByUserId(userId);
        Friend friend = new Friend();
        friend.setUser(user);
        friendRepository.delete(friend);

        return null;
    }

    @Override
    public List<User> getAllUser() {
        List<User> users = userRepository.findAll();
        if(users.isEmpty()){
            return null;
        }else {
            return users;
        }
    }
}
