package com.planE.common.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("공통")
@RestController
@RequestMapping("${property.api.end-point}")
@Slf4j
public class restTestController {
    @ApiOperation("Rest 호출 테스트")
    @GetMapping("/common/restTest")
    public String getString() {
        return "호출 성공";
    }

}