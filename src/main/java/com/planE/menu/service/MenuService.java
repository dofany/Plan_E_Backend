package com.planE.menu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.planE.menu.dto.MenuDto;
import com.planE.menu.repository.MenuRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MenuService {
	
	@Autowired
	MenuRepository menuRepository;
	
	@Transactional
	public List<MenuDto> findMenu() {
		return menuRepository.findMenu();
	}

	@Transactional
	public MenuDto addMenu(MenuDto menuDto) {
		log.info("--- com.planE.menu.service.MenuService.addMenu() start ---");
		
		MenuDto menu = new MenuDto();
		menu.setMenuNm(menuDto.getMenuNm());
		menu.setMenuOdrg(menuDto.getMenuOdrg());
		
		log.info("======================" + menu);
		int result = menuRepository.addMenu(menu);
		log.info("ddddddddddddddd" + result);
		
		// MENU INSERT 결과값 RESULT 에 SET 해서 보내주기
		
		MenuDto resultYn = new MenuDto();
		
		if(result == 1) {
			resultYn.setResult(true);
		}else {
			resultYn.setResult(false);
		}

		return resultYn;
	}

}
