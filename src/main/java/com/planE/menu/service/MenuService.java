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
		
		int result = menuRepository.addMenu(menuDto);
		MenuDto resultYn = new MenuDto();
		
		if(result == 1) {
			resultYn.setResult(true);
			log.info("--- com.planE.menu.service.MenuService.addMenu() end ---");
		}else {
			resultYn.setResult(false);
			log.info("--- com.planE.menu.service.MenuService.addMenu() insert failed ---");
		}

		return resultYn;
	}

}
