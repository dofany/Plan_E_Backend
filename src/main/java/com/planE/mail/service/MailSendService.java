package com.planE.mail.service;


import com.planE.mail.dto.MailInputDto;
import com.planE.user.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MailSendService {

    @Autowired
    private JavaMailSender javaMailSender;

    public String sendMail(MailInputDto mailInputDto) {
        log.info("--- com.planE.mail.service.MailSendService.sendMail() start ---");
        ArrayList<String> toUserList = new ArrayList<>();
        ArrayList<String> toCcList = new ArrayList<>();

        if(mailInputDto.getToUserList() == null) {
            return "N";
        }

        mailInputDto.getToUserList().forEach(user -> {
            toUserList.add(user);
        });

        if(mailInputDto.getCcEmailAdr() != null) {
            mailInputDto.getCcEmailAdr().forEach(cc -> {
                toCcList.add(cc);
            });
        }
//        toUserList.add("sybaek7980@gmail.com");

        int toUserListSize = toUserList.size();
        int ccListSize = toCcList.size();

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo((String[]) toUserList.toArray(new String[toUserListSize]));

        simpleMailMessage.setSubject(mailInputDto.getEmailTitle());
        simpleMailMessage.setText(mailInputDto.getEmailText());
        simpleMailMessage.setCc((String[]) toCcList.toArray(new String[ccListSize]));

        String str = "";
        try {
            log.info("-- simpleMailMessage :: {}", simpleMailMessage);
            javaMailSender.send(simpleMailMessage);
            // 성공시 메세지
            str = "Y";

            return str;
        } catch (Exception e) {
            log.info(e.getMessage());
            str = "N";
            // 실패시 메세지
            return str;
        }
        finally {
            log.info("--- com.planE.mail.service.MailSendService.sendMail() end ---");
        }
    }
}
