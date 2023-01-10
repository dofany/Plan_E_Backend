package com.planE.menu.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.planE.menu.dto.MenuDto;

@Mapper
public interface MenuRepository {
	
	List<MenuDto> findAllMenu();
	
	MenuDto findOneMenu(MenuDto menuDto);

	int addMenu(MenuDto menuDto);

	int editMenu(MenuDto menuDto);

	int removeMenu(MenuDto menuDto);

}
