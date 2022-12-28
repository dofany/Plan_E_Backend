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
import com.planE.user.dto.LoginResltDto;
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
    public LoginResltDto login(UserLoginInputDto userLoginInputDto) {
        log.info("--- com.planE.user.service.UserLoginService.login() start ---");
           
        String userEmail = userLoginInputDto.getInputEmail();
        List<UserDto> userInfo = new ArrayList<>();
        userInfo = userService.userFind(userEmail);

        // 사용자 계정 존재
		if (!userInfo.isEmpty()) {
        	log.info("--- com.planE.user.service.UserLoginService.login()_UserExistent OK ---");
        } else {
        	log.info("--- com.planE.user.service.UserLoginService.login()_UserExistent NOT OK ---");
        	
            // 로그인 이력 생성 && Response
            LoginResltDto loginResltDto = new LoginResltDto();
        	loginResltDto.setSucesYn("N");
        	loginResltDto.setLoginAthnType("1"); // 없는 email
            return loginResltDto;
        }
		
		// 계정 상태 확인
		if(userInfo.get(0).getUserStatus().equals(1)) {
			log.info("--- com.planE.user.service.UserLoginService.login()_UserStatus OK ---");
		} else {
			log.info("--- com.planE.user.service.UserLoginService.login()_UserStatus NOT OK ---");
	        
			// 로그인 이력 생성 && Response
	        LoginResltDto loginResltDto = new LoginResltDto();
			loginResltDto.setUserId(userInfo.get(0).getUserId());
			loginResltDto.setLoginAthnType("2");
			loginResltDto.setSucesYn("N");
        	userRepository.lgnHstInsert(loginResltDto);
			
			return loginResltDto;
        }
		
		// 로그인 실패 횟수 5회 미만
		if (userInfo.get(0).getLgnFailCnt() < 5) {
			log.info("--- com.planE.user.service.UserLoginService.login()_UserFailCnt OK ---");
		} else {
			log.info("--- com.planE.user.service.UserLoginService.login()_UserFailCnt NOT OK ---");

	        // 로그인 이력 생성 && Response
	        LoginResltDto loginResltDto = new LoginResltDto();
			loginResltDto.setUserId(userInfo.get(0).getUserId());
        	loginResltDto.setLoginAthnType("3");
        	loginResltDto.setSucesYn("N");
        	userRepository.lgnHstInsert(loginResltDto);
        	
			return loginResltDto;
		}
        
        // 사용자 패스워드 일치 여부 확인 
        String userPwd = userLoginInputDto.getInputPw();
        if(userInfo.get(0).getUserPw().equals(userPwd)) {
        	log.info("--- com.planE.user.service.UserLoginService.login()_UserPwd OK ---");
        } else {
        	log.info("--- com.planE.user.service.UserLoginService.login()_UserPwd NOT OK ---");
            
        	// 로그인 이력 생성 && Response
            LoginResltDto loginResltDto = new LoginResltDto();
			loginResltDto.setUserId(userInfo.get(0).getUserId());
        	loginResltDto.setLoginAthnType("4");
        	loginResltDto.setSucesYn("N");
        	userRepository.lgnHstInsert(loginResltDto);
        	
        	
        	//user_bas 로그인 횟수 +1
        	UserDto userDto = new UserDto();
        	userDto.setSysAmdrId("SYSTEM");
        	userDto.setSysSvcId("UserLoginService");
        	userDto.setEmail(userEmail);
        	userDto.setLgnFailCnt(userInfo.get(0).getLgnFailCnt()+1);
        	userRepository.lgnFailUpdate(userDto);
        	
        	return loginResltDto;
        	
        }

        // 로그인 성공  이력 생성 && Response
        LoginResltDto loginResltDto = new LoginResltDto();
		loginResltDto.setUserId(userInfo.get(0).getUserId());
    	loginResltDto.setLoginAthnType("5");
    	loginResltDto.setSucesYn("Y");
    	userRepository.lgnHstInsert(loginResltDto);
    	
    	//failCent 리셋
    	UserDto userDto = new UserDto();
    	userDto.setEmail(userEmail);
    	userDto.setLgnFailCnt(0);
    	userRepository.lgnFailUpdate(userDto);

        log.info("--- com.planE.user.service.UserLoginService.login() end ---");

        return loginResltDto;

    }
    
    @Transactional
    public Boolean pwChg(UserLoginInputDto userLoginInputDto) {
    	log.info("--- com.planE.user.service.UserLoginService.pwChg() start ---");
		
    	String userName = userLoginInputDto.getUserName();
		String userEmail = userLoginInputDto.getInputEmail();
    	
		List<UserDto> userInfo = new ArrayList<>();
		userInfo = userService.userFind(userEmail);
		
		// userInfo 존재여부 확인
		if (!userEmail.isEmpty() && !userInfo.isEmpty()) { 
			
			int i = 0;
			String newPw = "";
			
			if(!userName.isEmpty() && userInfo.get(0).getUserNm().equals(userName)) {
				log.info("--- com.planE.user.service.UserLoginService.login()_UserExistent OK ---");
				
				// 임시 비밀번호 8자리 생성
				while(i < 8) {
					newPw += Integer.toString((int)(Math.random() * 9));
					i++;
				}
				
				if(!newPw.isEmpty()) {
					log.info("--- com.planE.user.service.UserLoginService.login()_UserPwChange OK ---");
					
					UserDto userDto = new UserDto();
					userDto.setEmail(userEmail);
					userDto.setUserPw(newPw);
					userDto.setSysAmdrId("SYSTEM");
					userDto.setSysSvcId("UserLoginService");
					
				} else {
					log.info("--- com.planE.user.service.UserLoginService.login()_UserPwChange NOT OK ---");
					return false;
				}
				// 이메일 발송
				List<String> toUserLists = new ArrayList<>();
				String toUser = "";
				toUser = userInfo.get(0).getEmail();
				
				toUserLists.add(toUser);
				MailInputDto mailInputDto = new MailInputDto();
				mailInputDto.setEmailTitle("PlanE 임시비밀번호 입니다.");
				mailInputDto.setEmailText("PlanE 임시 비밀번호 : " + newPw + " 입니다.");
				mailInputDto.setCcEmailAdr(null);
				mailInputDto.setToUserList(toUserLists);
				
				String result = mailSendService.sendMail(mailInputDto);
				
				log.info("--- com.planE.user.service.UserLoginService.pwChg() end ---");
				if(result == "Y") {
					return true;        	
				} else {
					return false;
				}
			} else {
				log.info("--- com.planE.user.service.UserLoginService.login()_UserExistent NOT OK ---");
				return false;
			}
		} else {
			return false;
		}
    }
}
