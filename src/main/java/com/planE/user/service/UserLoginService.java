package com.planE.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.planE.mail.dto.EmailAuthnDto;
import com.planE.mail.dto.MailInputDto;
import com.planE.mail.repository.EmailAuthnRepository;
import com.planE.mail.service.MailSendService;
import com.planE.user.dto.LoginHstDto;
import com.planE.user.dto.UserDto;
import com.planE.user.dto.UserLoginInputDto;
import com.planE.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserLoginService {

    @Autowired
    MailSendService mailSendService;
    @Autowired
    EmailAuthnRepository emailAuthnRepository;
    
    @Autowired
    UserService userService;
    
    @Autowired
    UserRepository userRepository;

    @Transactional
    public Boolean signUp(UserLoginInputDto userLoginInputDto) {
        log.info("--- com.planE.user.service.UserLoginService.signUp() start ---");

        int i = 0;
        String mailNumber = "";
        List<String> toUserLists = new ArrayList<>();
        String toUser = "";

        // 입력값 체크
        if(userLoginInputDto.getUserEmail() != null && !userLoginInputDto.getUserEmail().isEmpty()) {
            toUser = userLoginInputDto.getUserEmail();
        } else {
            return false;
        }

        // 6자리 인증번호 생성
        while(i < 6) {
            mailNumber += Integer.toString((int)(Math.random() * 9));
//            mailNumber += Integer.toString(number);
            i++;
        }

        // 이메일 인증번호 DB 저장
        EmailAuthnDto EmailAuthnDTO = new EmailAuthnDto();
		EmailAuthnDTO.setEmail(toUser);
		EmailAuthnDTO.setEmailAuthnNum(mailNumber);
		emailAuthnRepository.insertEmailAuthn(EmailAuthnDTO);

        // 메일 발송
        toUserLists.add(toUser);
        MailInputDto mailInputDto = new MailInputDto();
        mailInputDto.setEmailTitle("PlanE 회원가입 인증번호입니다.");
        mailInputDto.setEmailText("PlanE 가입 인증번호 : " + mailNumber + " 를 입력해주세요.");
        mailInputDto.setCcEmailAdr(null);
        mailInputDto.setToUserList(toUserLists);

        String result = mailSendService.sendMail(mailInputDto);

        log.info("--- com.planE.user.service.UserLoginService.signUp() end ---");

        if(result == "Y") {
            return true;
        } else {
          return false;
        }

    }


    @Transactional
    public Boolean login(UserLoginInputDto userLoginInputDto) {
        log.info("--- com.planE.user.service.UserLoginService.login() start ---");
           
        String userEmail = userLoginInputDto.getUserEmail();
        List<UserDto> userInfo = new ArrayList<>();
        userInfo = userService.userFind(userEmail);

        // 사용자 계정 존재
		if (!userInfo.isEmpty()) {
        	log.info("--- com.planE.user.service.UserLoginService.login()_UserExistent OK ---");
        } else {
        	log.info("--- com.planE.user.service.UserLoginService.login()_UserExistent NOT OK ---");
            return false;
        }
		
		// 로그인 이력 생성용
        LoginHstDto loginHstDto = new LoginHstDto();
        
		// 계정 상태 확인
		if(userInfo.get(0).getUserStatus().equals(1)) {
			log.info("--- com.planE.user.service.UserLoginService.login()_UserStatus OK ---");
		} else {
			log.info("--- com.planE.user.service.UserLoginService.login()_UserStatus NOT OK ---");
        	// 로그인 실패 이력 생성 INSERT
			loginHstDto.setUserId(userInfo.get(0).getUserId());
        	loginHstDto.setLoginAthnType("2");
        	loginHstDto.setSucesYn("N");
        	userRepository.lgnHstInsert(loginHstDto);
			
			return false;
        }
		
		// 로그인 실패 횟수 5회 미만
		if (userInfo.get(0).getLgnFailCnt() < 5) {
			log.info("--- com.planE.user.service.UserLoginService.login()_UserFailCnt OK ---");
		} else {
			log.info("--- com.planE.user.service.UserLoginService.login()_UserFailCnt NOT OK ---");
        	// 로그인 실패 이력 생성 INSERT
			loginHstDto.setUserId(userInfo.get(0).getUserId());
        	loginHstDto.setLoginAthnType("3");
        	loginHstDto.setSucesYn("N");
        	userRepository.lgnHstInsert(loginHstDto);
        	
			return false;
		}
        
        // 사용자 패스워드 일치 여부 확인 
        String userPwd = userLoginInputDto.getUserPw();
        if(userInfo.get(0).getUserPw().equals(userPwd)) {
        	log.info("--- com.planE.user.service.UserLoginService.login()_UserPwd OK ---");
        } else {

        	log.info("--- com.planE.user.service.UserLoginService.login()_UserPwd NOT OK ---");
        	// 로그인 실패 이력 생성 INSERT
			loginHstDto.setUserId(userInfo.get(0).getUserId());
        	loginHstDto.setLoginAthnType("4");
        	loginHstDto.setSucesYn("N");
        	userRepository.lgnHstInsert(loginHstDto);
        	
        	
        	//user_bas 로그인 횟수 +1
        	UserDto userDto = new UserDto();
        	userDto.setEmail(userEmail);
        	userDto.setLgnFailCnt(userInfo.get(0).getLgnFailCnt()+1);
        	userRepository.lgnFailUpdate(userDto);
        	
        	return false;
        	
        }


        // 토큰 부여 로직 호출 ?

        // 로그인 성공 이력 생성
		loginHstDto.setUserId(userInfo.get(0).getUserId());
    	loginHstDto.setLoginAthnType("5");
    	loginHstDto.setSucesYn("Y");
    	userRepository.lgnHstInsert(loginHstDto);
    	
    	//failCent 리셋
    	UserDto userDto = new UserDto();
    	userDto.setEmail(userEmail);
    	userDto.setLgnFailCnt(0);
    	userRepository.lgnFailUpdate(userDto);

        log.info("--- com.planE.user.service.UserLoginService.login() end ---");

        return true;

    }
}
