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
	public List<MenuDto> findAllMenu() {
		return menuRepository.findAllMenu();
	}
	
	@Transactional
	public MenuDto findOneMenu(MenuDto menuDto) {
		
		MenuDto result = new MenuDto();
		if(!menuDto.getMenuId().isEmpty() && menuDto.getMenuId() != "") {
			result = menuRepository.findOneMenu(menuDto);
		}
		
		return result;
	}

	@Transactional
	public MenuDto addMenu(MenuDto menuDto) {
		log.info("--- com.planE.menu.service.MenuService.addMenu() start ---");
		
		int result = 0;
		MenuDto resultYn = new MenuDto();
		
		if( menuDto.getMenuNm() != null && menuDto.getMenuOdrg() != null) {
			
			result = menuRepository.addMenu(menuDto);
			
			if(result == 1) {
				resultYn.setResult(true);
				log.info("--- com.planE.menu.service.MenuService.addMenu() end ---");
			}else {
				resultYn.setResult(false);
				log.info("--- com.planE.menu.service.MenuService.addMenu() insert failed ---");
			}
		} else {
			resultYn.setResult(false);
			log.info("--- com.planE.menu.service.MenuService.addMenu() insert failed ---");
		}
		

		return resultYn;
	}

	@Transactional
	public MenuDto editMenu(MenuDto menuDto) {
		log.info("--- com.planE.menu.service.MenuService.editMenu() start ---");
		
		int result = 0;
		MenuDto resultYn = new MenuDto();
		
		resultYn = menuRepository.findOneMenu(menuDto);
		String menuId = resultYn.getMenuId();
		
		if(!menuId.isEmpty() && menuId != null) {
			
			result = menuRepository.editMenu(menuDto);
			
			if(result == 1) {
				resultYn.setResult(true);
				log.info("--- com.planE.menu.service.MenuService.editMenu() end ---");
			} else {
				resultYn.setResult(false);
				log.info("--- com.planE.menu.service.MenuService.editMenu() update failed ---");
			}
			
		} else {
			resultYn.setResult(false);
			log.info("--- com.planE.menu.service.MenuService.editMenu() MenuId not existed ---");
		}
		
		return resultYn;
	}

	@Transactional
	public MenuDto removeMenu(MenuDto menuDto) {
		log.info("--- com.planE.menu.service.MenuService.removeMenu() start ---");
		
		int result = 0;
		MenuDto resultYn = new MenuDto();
		
		resultYn = menuRepository.findOneMenu(menuDto);
		String menuId = resultYn.getMenuId();
		
		if(!menuId.isEmpty() && menuId != null) {
			
			result = menuRepository.removeMenu(menuDto);
			
			if(result == 1) {
				resultYn.setResult(true);
				log.info("--- com.planE.menu.service.MenuService.removeMenu() end ---");
			} else {
				resultYn.setResult(false);
				log.info("--- com.planE.menu.service.MenuService.removeMenu() delete failed ---");
			}
		} else {
			resultYn.setResult(false);
			log.info("--- com.planE.menu.service.MenuService.removeMenu() MenuId not existed ---");
		}

		return resultYn;
	}

}
