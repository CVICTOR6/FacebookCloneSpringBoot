package com.cvictor.facebookclonespringboot.service;

import com.cvictor.facebookclonespringboot.dto.ResponseDTO;
import com.cvictor.facebookclonespringboot.model.User;
import com.cvictor.facebookclonespringboot.dto.LogInDTO;

import java.util.List;

public interface UserService {
    ResponseDTO addUser(User user);

    ResponseDTO logInUser(LogInDTO logInDTO);

    ResponseDTO follow(Long userId);
    ResponseDTO unfollow(Long userId);

    List<User> getAllUser();

}
