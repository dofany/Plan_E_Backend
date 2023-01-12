package com.planE.user.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.planE.user.dto.UserAthnDto;
import com.planE.user.dto.EmailAuthnDto;
import com.planE.mail.dto.MailInputDto;
import com.planE.user.dto.UserDto;
import com.planE.mail.service.MailSendService;
import com.planE.user.repository.UserAthnRepository;
import com.planE.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserAthnService {

    @Autowired
    MailSendService mailSendService;
    @Autowired
	UserAthnRepository userAthnRepository;
    
    @Autowired
    UserService userService;
    
    @Autowired
    UserRepository userRepository;

    @Transactional
    public UserAthnDto signUp(UserAthnDto userAthnDto) {
        log.info("--- com.planE.user.service.UserAthnService.signUp() start ---");

		UserAthnDto result = new UserAthnDto();
        int i = 0;
        String mailNumber = "";
        List<String> toUserLists = new ArrayList<>();
        String toUser = "";

        // 입력값 체크
        if(userAthnDto.getEmail() != null && !userAthnDto.getEmail().isEmpty()) {
            toUser = userAthnDto.getEmail();
        } else {
			result.setResult(false);
			result.setResultMsg("이메일 주소를 입력해주세요.");
            return result;
        }

		List<UserDto> userInfo = new ArrayList<>();
		userInfo = userService.userFind(userAthnDto.getEmail());

		// 이메일 계정 존재시
		if(userInfo.size() > 0) {
			result.setResult(false);
			result.setResultMsg("이미 사용중인 이메일 주소입니다.");
			return result;
		}

        // 6자리 인증번호 생성
        while(i < 6) {
            mailNumber += Integer.toString((int)(Math.random() * 9));
            i++;
        }

        // 이메일 인증번호 DB 저장
        EmailAuthnDto EmailAuthnDTO = new EmailAuthnDto();
		EmailAuthnDTO.setEmail(toUser);
		EmailAuthnDTO.setEmailAuthnNum(mailNumber);
		userAthnRepository.insertEmailAuthn(EmailAuthnDTO);

        // 메일 발송
        toUserLists.add(toUser);
        MailInputDto mailInputDto = new MailInputDto();
        mailInputDto.setEmailTitle("PlanE 회원가입 인증번호입니다.");
        mailInputDto.setEmailText("PlanE 가입 인증번호 : " + mailNumber + " 를 입력해주세요.");
        mailInputDto.setCcEmailAdr(null);
        mailInputDto.setToUserList(toUserLists);

        String sendEmailYn = mailSendService.sendMail(mailInputDto);

        log.info("--- com.planE.user.service.UserAthnService.signUp() end ---");

        if("Y".equals(sendEmailYn)) {
			result.setResult(true);
            return result;
        } else {
			result.setResult(false);
			result.setResultMsg("인증번호 발송을 실패했습니다.");
          return result;
        }

    }


    @Transactional
    public UserAthnDto login(UserAthnDto userAthnDto) {
        log.info("--- com.planE.user.service.UserAthnService.login() start ---");
           
        String userEmail = userAthnDto.getEmail();

		List<UserDto> userInfo = new ArrayList<>();
        userInfo = userService.userFind(userEmail);

		UserAthnDto result = new UserAthnDto();
		String userId = "";
		String email = "";
		String userPw = "";


		// 사용자 계정 존재
		if (userInfo.size() > 0) {
        	log.info("--- com.planE.user.service.UserAthnService.login()_UserExistent OK ---");
			userId = userInfo.get(0).getUserId();
			email = userInfo.get(0).getEmail();
			userPw = userInfo.get(0).getUserPw();

			result.setUserId(userId);
			result.setEmail(email);
			result.setUserPw(userPw);
        } else {
        	log.info("--- com.planE.user.service.UserAthnService.login()_UserExistent NOT OK ---");

			result.setLoginAthnType("1");
			result.setSucesYn("N");
            return result;
        }
		
		// 계정 상태 확인
		if(userInfo.get(0).getUserStatus().equals(1)) {
			log.info("--- com.planE.user.service.UserAthnService.login()_UserStatus OK ---");
		} else {
			log.info("--- com.planE.user.service.UserAthnService.login()_UserStatus NOT OK ---");
	        
			// 로그인 이력 생성 && Response
			loginInsertHist(userId, "2", "N");
			result.setLoginAthnType("2");
			result.setSucesYn("N");

			return result;
        }
		
		// 로그인 실패 횟수 5회 미만
		if (userInfo.get(0).getLgnFailCnt() < 5) {
			log.info("--- com.planE.user.service.UserAthnService.login()_UserFailCnt OK ---");
		} else {
			log.info("--- com.planE.user.service.UserAthnService.login()_UserFailCnt NOT OK ---");

	        // 로그인 이력 생성 && Response
			loginInsertHist(userId, "3", "N");
			result.setLoginAthnType("3");
			result.setSucesYn("N");

        	
			return result;
		}
        
        // 사용자 패스워드 일치 여부 확인 
        String userPwd = userAthnDto.getUserPw();
        if(userInfo.get(0).getUserPw().equals(userPwd)) {
        	log.info("--- com.planE.user.service.UserAthnService.login()_UserPwd OK ---");
        } else {
        	log.info("--- com.planE.user.service.UserAthnService.login()_UserPwd NOT OK ---");

        	//user_bas 로그인 횟수 +1
        	UserDto userDto = new UserDto();
        	userDto.setSysAmdrId("SYSTEM");
        	userDto.setSysSvcId("UserAthnService");
        	userDto.setEmail(userEmail);
        	userDto.setLgnFailCnt(userInfo.get(0).getLgnFailCnt()+1);
        	userRepository.lgnFailUpdate(userDto);


			// 로그인 이력 생성 && Response
			loginInsertHist(userId, "4", "N");
			result.setLoginAthnType("4");
			result.setSucesYn("N");
        	return result;
        	
        }

        // 로그인 성공  이력 생성 && Response
		loginInsertHist(userId, "5", "Y");
		result.setLoginAthnType("5");
		result.setSucesYn("Y");

    	//failCent 리셋
    	UserDto userDto = new UserDto();
    	userDto.setEmail(userEmail);
    	userDto.setLgnFailCnt(0);
    	userRepository.lgnFailUpdate(userDto);

        log.info("--- com.planE.user.service.UserAthnService.login() end ---");

        return result;

    }
    
    @Transactional
    public Boolean pwChg(UserAthnDto userAthnDto) {
    	log.info("--- com.planE.user.service.UserAthnService.pwChg() start ---");
		
    	String userName = userAthnDto.getUserNm();
		String userEmail = userAthnDto.getEmail();
    	
		List<UserDto> userInfo = new ArrayList<>();
		userInfo = userService.userFind(userEmail);
		
		// userInfo 존재여부 확인
		if (!userEmail.isEmpty() && !userInfo.isEmpty()) { 
			
			int i = 0;
			String newPw = "";
			
			if(!userName.isEmpty() && userInfo.get(0).getUserNm().equals(userName)) {
				log.info("--- com.planE.user.service.UserAthnService.login()_UserExistent OK ---");
				
				// 임시 비밀번호 8자리 생성
				while(i < 8) {
					newPw += Integer.toString((int)(Math.random() * 9));
					i++;
				}
				
				if(!newPw.isEmpty()) {
					log.info("--- com.planE.user.service.UserAthnService.login()_UserPwChange OK ---");
					
					UserDto userDto = new UserDto();
					userDto.setEmail(userEmail);
					userDto.setUserPw(newPw);
					userDto.setSysAmdrId("SYSTEM");
					userDto.setSysSvcId("UserAthnService");

					userRepository.upNewPw(userDto);
					
				} else {
					log.info("--- com.planE.user.service.UserAthnService.login()_UserPwChange NOT OK ---");
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
				
				log.info("--- com.planE.user.service.UserAthnService.pwChg() end ---");
				if(result == "Y") {
					return true;        	
				} else {
					return false;
				}
			} else {
				log.info("--- com.planE.user.service.UserAthnService.login()_UserExistent NOT OK ---");
				return false;
			}
		} else {
			return false;
		}
    }


	@Transactional
	public EmailAuthnDto emailCheck(String userNm, String userPw, String email, String emailAuthnNum) {

		log.info("--- com.planE.user.service.UserAthnService.emailAuthn() start ---");

		EmailAuthnDto result = new EmailAuthnDto();

		// 최근 이메일 인증코드 체크
		EmailAuthnDto resentAuthnInfo = userAthnRepository.selectEmailAuthn(email);

		// 조회된 인증 데이터가 없을시
		if(resentAuthnInfo == null) {

			log.info("Fail Check EmailAuthn - No Result");
			result.setResCd("1");
			return result;
		} else {

			// 인증번호 유효시간 체크
			LocalDateTime authnDt = resentAuthnInfo.getSysRecdCretDt();
			LocalDateTime now = LocalDateTime.now();

			float diffMin = (float) (ChronoUnit.SECONDS.between(authnDt, now) / 60.0);

			if(diffMin >= 5) {
				//시간초과
				log.info("Fail Check EmailAuthn - Time Expiration");
				result.setResCd("2");
				return result;
			}

			//인증번호 비교
			String authnNum = resentAuthnInfo.getEmailAuthnNum();

			// 조회된 인증번호와 입력된 인증번호가 같을시 정상 처리
			if(authnNum.equals(emailAuthnNum)) {

				log.info("Success Check EmailAuthn");
				result.setResCd("4");
			} else {

				log.info("Fail Check EmailAuthn - AuthnNums Not Same");
				result.setResCd("3");
				return result;
			}

			// *인증성공*
			// 1. 인증 성공 여부 업데이트
			userAthnRepository.updateEmailAuthn(email, emailAuthnNum);
			log.info("Success Update EmailAuthn");
			// 2. 사용자 생성
			UserDto	userDto = new UserDto();
			userDto.setUserNm(userNm);
			userDto.setUserPw(userPw);
			userDto.setEmail(email);
			userService.addUser(userDto);
			log.info("Success Insert SignUp User");
		}


		log.info("--- com.planE.user.service.UserAthnService.emailAuthn() end ---");

		return result;
	}

	@Transactional
	public void loginInsertHist(String userId, String loginAthnType, String successYn) {

		UserAthnDto userAthnDto = new UserAthnDto();
		userAthnDto.setUserId(userId);
		userAthnDto.setLoginAthnType(loginAthnType);
		userAthnDto.setSucesYn(successYn);
		userRepository.lgnHstInsert(userAthnDto);
	}

}
