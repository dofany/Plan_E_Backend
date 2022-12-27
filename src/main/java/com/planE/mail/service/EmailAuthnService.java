package com.planE.mail.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.planE.mail.dto.EmailAuthnDto;
import com.planE.mail.repository.EmailAuthnRepository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class EmailAuthnService {

	@Autowired
	private EmailAuthnRepository emailAuthnRepository;

	@Transactional
	public String emailCheck(String inputEmailAuthnNum, EmailAuthnDto emailAuthnDto) {
		
		log.info("--- com.planE.mail.service.EmailAuthnService.emailAuthn() start ---");
	
		String result;
		//최근 이메일 코드 체크
		EmailAuthnDto resentAuthn = emailAuthnRepository.selectEmailAuthn(emailAuthnDto);
		if(resentAuthn == null) {
			
			log.info("Fail Check EmailAuthn - No Result");
			return "NoResult";
		} else {
			
			LocalDateTime authnDt = resentAuthn.getSysRecdCretDt();
			LocalDateTime now = LocalDateTime.now();
			
			float diffMin = (float) (ChronoUnit.SECONDS.between(authnDt, now) / 60.0);

			if(diffMin >= 5) {
				//시간초과
				log.info("Fail Check EmailAuthn - Time Expiration");
				return "TimeExpiration";
			}
			
			//인증번호 비교
			String authnNum = resentAuthn.getEmailAuthnNum();
			
			if(authnNum.equals(inputEmailAuthnNum)) {
				
				log.info("Success Check EmailAuthn");
				result = "Success";
			} else {

				log.info("Fail Check EmailAuthn - AuthnNums Not Same");
				return "Fail";
			}
			
			//인증성공
			emailAuthnRepository.updateEmailAuthn(emailAuthnDto);
			log.info("Success Update EmailAuthn");

		}
		

		log.info("--- com.planE.mail.service.EmailAuthnService.emailAuthn() end ---");
		
		return result;
	}
}
