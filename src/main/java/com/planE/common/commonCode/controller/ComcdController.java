package com.planE.common.commonCode.controller;

import com.planE.common.commonCode.dto.ComcdDto;
import com.planE.common.commonCode.service.ComcdService;
import com.planE.user.dto.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*
@Api("공통 서비스")
@RestController
@RequestMapping("${property.api.end-point}")
@Slf4j
public class ComcdController {

    @ApiOperation("메일 발송 API")
    @GetMapping("/comcd/getComcd")
    public String getComcd(String cdName) {
        log.info("--- com.planE.common.controller.ComcdController.getComcd() start ---");
        String str = "";
        log.info("--- com.planE.common.controller.ComcdController.getComcd() end ---");
        return str;
    }

}
*/

@Api("사용자")
@RestController
@RequestMapping("${property.api.end-point}")
@Slf4j
public class ComcdController {

    @Autowired
    ComcdService comcdService;

    @ApiOperation("공통코드 조회")
    @GetMapping("/common/findComcd")
    public List<ComcdDto> findComcd(String cdGroupId, String cdId) {
        return comcdService.findComcd(cdGroupId, cdId);
    }

}