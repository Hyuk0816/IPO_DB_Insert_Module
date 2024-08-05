package com.sideprj.ipoAlarm.ipo.repository;


import com.sideprj.ipoAlarm.ipo.dto.IpoGetAllDto;
import com.sideprj.ipoAlarm.ipo.dto.IpoSearchRequestVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IpoRepositoryCustom {

    Page<IpoGetAllDto> fetchIpoData(IpoSearchRequestVo searchRequestVo, Pageable pageable);

}
