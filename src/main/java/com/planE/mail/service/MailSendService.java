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

@Slf4j
@Service
public class MailSendService {

    @Autowired
    private JavaMailSender javaMailSender;

    public String sendMail(MailInputDto mailInputDto) {
        log.info("--- com.planE.mail.service.MailSendService.sendMail() start ---");
        ArrayList<String> toUserList = new ArrayList<>();
        ArrayList<String> toCcList = new ArrayList<>();

        mailInputDto.getToUserList().forEach(user -> {
            toUserList.add(user);
        });
        mailInputDto.getCcEmailAdr().forEach(cc -> {
            toCcList.add(cc);
        });
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
            str = "success";

            return str;
        } catch (Exception e) {
            str = e.getMessage();
            return str;
        }
        finally {
            log.info("--- com.planE.mail.service.MailSendService.sendMail() end ---");
        }
    }
}
