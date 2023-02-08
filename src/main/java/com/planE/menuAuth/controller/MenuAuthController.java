package com.planE.menuAuth.controller;

import com.planE.auth.dto.AuthDto;
import com.planE.menuAuth.dto.MenuAuthDto;
import com.planE.menuAuth.service.MenuAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("메뉴권한")
@RestController
@RequestMapping("${property.api.end-point}")
@Slf4j
public class MenuAuthController {

    @Autowired
    private MenuAuthService menuAuthService;

    @ApiOperation("메뉴권한조회")
    @PostMapping("/menuAuth/findAllMenuAuth")
    public List<MenuAuthDto> findAllMenuAuth(@RequestBody MenuAuthDto menuAuthDto) {

        List<MenuAuthDto> result = menuAuthService.findAllMenuAuth(menuAuthDto);

        return result;
    }

    @ApiOperation("메뉴권한중복제거조회")
    @PostMapping("/menuAuth/menuDuplicationCheck")
    public List<MenuAuthDto> menuDuplicationCheck(@RequestBody MenuAuthDto menuAuthDto) {

        List<MenuAuthDto> result = menuAuthService.menuDuplicationCheck(menuAuthDto);

        return result;
    }

    @ApiOperation("메뉴권한추가")
    @PostMapping("/menuAuth/addMenuAuth")
    public String addMenuAuth(@RequestBody MenuAuthDto menuAuthDto) {

        String str = menuAuthService.addMenuAuth(menuAuthDto);

        return str;
    }

    @ApiOperation("메뉴권한삭제")
    @PostMapping("/menuAuth/removeMenuAuth")
    public String removeMenuAuth(@RequestBody MenuAuthDto menuAuthDto) {

        String str = menuAuthService.removeMenuAuth(menuAuthDto);

        return str;
    }
}

