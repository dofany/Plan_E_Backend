package com.planE.user.service;

import com.planE.mail.dto.MailInputDto;
import com.planE.mail.service.MailSendService;
import com.planE.user.dto.UserDto;
import com.planE.user.dto.UserLoginInputDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserLoginService {

    @Autowired
    MailSendService mailSendService;

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

        // 유저명, 이메일 주소에 생성된 인증번호 DB 저장 로직 필요




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
}
