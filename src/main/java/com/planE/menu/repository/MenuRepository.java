package com.planE.menu.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.planE.menu.dto.MenuDto;

@Mapper
public interface MenuRepository {
	
	List<MenuDto> findMenu();

	int addMenu(MenuDto menuDto);

}
