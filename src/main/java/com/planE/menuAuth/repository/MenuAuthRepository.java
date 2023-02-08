package com.planE.menuAuth.repository;

import com.planE.menuAuth.dto.MenuAuthDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuAuthRepository {

    List<MenuAuthDto> findAllMenuAuth(MenuAuthDto menuAuthDto);

    List<MenuAuthDto> menuDuplicationCheck(MenuAuthDto menuAuthDto);

    void addMenuAuth(MenuAuthDto menuAuthDto);

    void removeMenuAuth(MenuAuthDto menuAuthDto);
}
