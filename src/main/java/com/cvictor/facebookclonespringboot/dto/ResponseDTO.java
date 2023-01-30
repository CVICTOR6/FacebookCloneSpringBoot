package com.cvictor.facebookclonespringboot.dto;

import com.cvictor.facebookclonespringboot.model.User;
import lombok.Data;

import java.util.List;


@Data
public class ResponseDTO {
    private String message;
    private User data;
    private boolean status;

    private List<User> userList;


}
