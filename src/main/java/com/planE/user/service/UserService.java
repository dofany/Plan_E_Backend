package com.planE.user.service;

import com.planE.user.dto.UserDto;
import com.planE.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<UserDto> getUserList() {
        log.info("--- com.planE.user.service.UserService.getUserList() start ---");
        List<UserDto> userList = new ArrayList<>();
        userList = userRepository.getUserList();

        log.info("--- com.planE.user.service.UserService.getUserList() end ---");
        return userList;
    }
}