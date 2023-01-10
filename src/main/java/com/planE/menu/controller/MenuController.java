package com.planE.menu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planE.menu.dto.MenuDto;
import com.planE.menu.service.MenuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api("메뉴")
@RestController
@RequestMapping("${property.api.end-point}")
@Slf4j
public class MenuController {

	@Autowired
	MenuService menuService;

	// 메뉴 전체 조회
	@ApiOperation("메뉴 전체 조회")
	@GetMapping("/menu/findAllMenu")
	public List<MenuDto> findAllMenu() {
		return menuService.findAllMenu();
	}

	// 메뉴 개별 조회
	@ApiOperation("메뉴 개별 조회")
	@GetMapping("/menu/findOneMenu")
	public MenuDto findOneMenu(@RequestBody MenuDto menuDto) {
		return menuService.findOneMenu(menuDto);
	}

	// 메뉴 생성
	@ApiOperation("메뉴 생성")
	@PostMapping("/menu/addMenu")
	public MenuDto addMenu(@RequestBody MenuDto menuDto) {
		log.info("--- com.planE.menu.controller.MenuController.addMenu() start ---");

		MenuDto result = menuService.addMenu(menuDto);

		log.info("--- com.planE.menu.controller.MenuController.addMenu() end ---");
		return result;
	}

	// 메뉴 수정
	@ApiOperation("메뉴 수정")
	@PostMapping("/menu/editMenu")
	public MenuDto editMenu(@RequestBody MenuDto menuDto) {
		log.info("--- com.planE.menu.controller.MenuController.editMenu() start ---");

		MenuDto result = menuService.editMenu(menuDto);

		log.info("--- com.planE.menu.controller.MenuController.editMenu() end ---");
		return result;
	}

	// 메뉴 삭제
	@ApiOperation("메뉴 삭제")
	@PostMapping("/menu/removeMenu")
	public MenuDto removeMenu(@RequestBody MenuDto menuDto) {
		log.info("--- com.planE.menu.controller.MenuController.removeMenu() start ---");
		
		MenuDto result = menuService.removeMenu(menuDto);
		
		log.info("--- com.planE.menu.controller.MenuController.removeMenu() end ---");
		return result;
	}
	
}
