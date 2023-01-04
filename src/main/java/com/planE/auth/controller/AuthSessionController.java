package com.planE.auth.controller;

//import com.planE.auth.dto.User;
//import com.planE.auth.dto.UserAuthDto;
//import com.planE.auth.service.AuthJwtSevice;
import com.planE.user.dto.UserDto;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Api("세션로그인")
@RestController
@RequestMapping("${property.api.end-point}")
@Slf4j
@CrossOrigin("*")
public class AuthSessionController {
    @Autowired
   // AuthJwtSevice jwtService;
    @PostMapping("/sessionLogin") // 로그인, 세션로그인
    public String login(@RequestBody UserDto userDto, HttpServletRequest request, RedirectAttributes redirect)  throws  Exception{

        log.info("sessionLogin!!!!");

        HttpSession session = request.getSession();

        if(userDto == null)
        {
            log.info("!@#!@#@!#!@#!@#");
            session.setAttribute("user", null);
            redirect.addFlashAttribute("msg", false);
            log.info("!@#!@#@!#!@#!@#" + session);
        }else{
            log.info("!@@@@@");
            //로그인 성공처리
            //세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
            session.setAttribute("user", userDto);
            log.info("!@@@@@" + session);
            log.info("로그인 성공!!!");
        }
        return "redirect:/";
    }
    @GetMapping("/sessionLogout") // 로그아웃, 세션 로그아웃
    public String logout(HttpSession session) throws Exception{
        log.info("session!!!"  + session);
        session.invalidate();
        log.info("로그아웃 성공!!!");
        return "redirect:/";
    }
}
