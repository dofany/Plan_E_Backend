package com.planE.common.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Api("공통 서비스")
@RestController
@RequestMapping("${property.api.end-point}")
@Slf4j
public class ComcdController {

    @ApiOperation("메일 발송 API")
    @GetMapping("/comcd/getComcd")
    public String getComcd(String comcdGroupId) {
        log.info("--- com.planE.common.controller.ComcdController.getComcd() start ---");

        String str = "";
        log.info("--- com.planE.common.controller.ComcdController.getComcd() end ---");
        return str;
    }
}
