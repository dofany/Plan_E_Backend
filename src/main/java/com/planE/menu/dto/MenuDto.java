package com.planE.menu.dto;

import com.planE.common.base.dto.BaseDto;

import lombok.*;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuDto extends BaseDto{

	private String menuId;      //메뉴id	
	private String menuNm;		//메뉴이름
	private String menuOdrg;	//메뉴순서
	private String menuUpId;	//상위메뉴id
	private String useYn;		//사용여부
	private Boolean result;		//output 결과
	
}
