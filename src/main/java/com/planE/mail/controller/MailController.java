package com.planE.mail.controller;

import com.planE.mail.dto.MailInputDto;
import com.planE.mail.service.MailSendService;
import com.planE.user.dto.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("메일발송")
@RestController
@RequestMapping("${property.api.end-point}")
@Slf4j
public class MailController {

    @Autowired
    MailSendService mailSendService;

    @ApiOperation("메일 발송 API")
    @PostMapping("/mail/sendMail")
    public String sendMail(@RequestBody MailInputDto mailInputDto) {
        log.info("--- com.planE.mail.controller.MailController.sendMail() start ---");

        String str = mailSendService.sendMail(mailInputDto);
        log.info("--- com.planE.mail.controller.MailController.sendMail() end ---");
        return str;
    }
}
