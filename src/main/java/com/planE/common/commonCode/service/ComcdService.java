package com.planE.common.commonCode.service;

import com.planE.common.commonCode.dto.ComcdDto;
import com.planE.common.commonCode.repository.ComcdRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ComcdService {

    @Autowired
    ComcdRepository comcdRepository;
    public List<ComcdDto> findComcd(String cdGroupId, String cdId) {

        return comcdRepository.findComcd(cdGroupId, cdId);
    }
}
