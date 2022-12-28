package com.planE.common.commonCode.repository;

import com.planE.common.commonCode.dto.ComcdDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ComcdRepository {

    List<ComcdDto> findComcd(String cdGroupId, String cdId);

}
