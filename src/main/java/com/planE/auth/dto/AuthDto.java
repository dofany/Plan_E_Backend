package com.planE.auth.dto;

import java.io.Serializable;

import com.planE.common.base.dto.BaseDto;

import io.swagger.annotations.ApiParam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthDto extends BaseDto implements Serializable {

	private static final long serialVersionUID = 7542547719780442399L;
	
	@ApiParam("권한아이디")
	private String authId;
	@ApiParam("권한이름")
	private String authNm;
	@ApiParam("사용여부")
	private String useYn;
	
	@ApiParam("성공여부")
    private String sucesYn;
}
