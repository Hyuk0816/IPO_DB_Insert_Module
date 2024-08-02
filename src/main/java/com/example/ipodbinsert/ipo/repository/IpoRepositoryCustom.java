package com.example.ipodbinsert.ipo.repository;


import com.example.ipodbinsert.ipo.dto.IpoGetAllDto;
import com.example.ipodbinsert.ipo.dto.IpoSearchRequestVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IpoRepositoryCustom {

    Page<IpoGetAllDto> fetchIpoData(IpoSearchRequestVo searchRequestVo, Pageable pageable);

}
