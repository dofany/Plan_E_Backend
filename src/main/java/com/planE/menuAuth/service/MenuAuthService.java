package com.planE.menuAuth.service;

import com.planE.menuAuth.dto.MenuAuthDto;
import com.planE.menuAuth.repository.MenuAuthRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.List;

@Service
@Slf4j
public class MenuAuthService {

    @Autowired
    private MenuAuthRepository menuAuthRepository;

    public List<MenuAuthDto> findAllMenuAuth(MenuAuthDto menuAuthDto) {

        List<MenuAuthDto> result = menuAuthRepository.findAllMenuAuth(menuAuthDto);

        return result;
    }

    public List<MenuAuthDto> menuDuplicationCheck(MenuAuthDto menuAuthDto) {

        List<MenuAuthDto> result = menuAuthRepository.menuDuplicationCheck(menuAuthDto);

        return result;
    }

    public String addMenuAuth(MenuAuthDto menuAuthDto) {

        String str = "";

        if (menuAuthDto.getAuthId() == null || menuAuthDto.getAuthId().isBlank()) {
            str = "N";
            return str;
        }
        try {
            menuAuthRepository.addMenuAuth(menuAuthDto);
            str = "Y";
        } catch (Exception e) {
            str = "N";
            return str;
        }
        return str;
    }

    public String removeMenuAuth(MenuAuthDto menuAuthDto) {
        String str = "";

        if (menuAuthDto.getMenuId().equals(null) || menuAuthDto.getAuthId().equals(null)) {
            str = "N";
            return str;
        }
        try {
            menuAuthRepository.removeMenuAuth(menuAuthDto);
            str = "Y";
        } catch (Exception e) {
            str = "N";
            return str;
        }
        return str;
    }
}
