package com.planE.user.service;

import com.planE.user.dto.UserDto;
import com.planE.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;

    public List<UserDto> userFind(String email) {
    	
        return userRepository.userFind(email);
    }

}