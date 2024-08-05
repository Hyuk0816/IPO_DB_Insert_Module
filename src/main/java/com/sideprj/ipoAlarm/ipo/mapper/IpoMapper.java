package com.sideprj.ipoAlarm.ipo.mapper;

import com.sideprj.ipoAlarm.ipo.dto.IpoGetAllDto;
import com.sideprj.ipoAlarm.ipo.entity.Ipo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class IpoMapper {

    public static List<IpoGetAllDto> mapFromIpoListToIpoGetAllDtoList(Page<IpoGetAllDto> ipoList) {
        return ipoList.stream()
                .map(ipo -> new IpoGetAllDto(
                        ipo.getIpoName(),
                        ipo.getIpoPrice(),
                        ipo.getConfirmPrice(),
                        ipo.getCompetitionRate(),
                        ipo.getSecurities(),
                        ipo.getStartDate(),
                        ipo.getEndDate()
                )).toList();
    }

    public static Page<IpoGetAllDto> mapFromIpoToPage(List<Ipo> ipoList, Pageable pageable){
      List<IpoGetAllDto> ipoGetAllDto =  ipoList.stream().map(
                ipo -> new IpoGetAllDto(
                        ipo.getIpoName(),
                        ipo.getIpoPrice(),
                        ipo.getConfirmPrice(),
                        ipo.getCompetitionRate(),
                        ipo.getSecurities(),
                        ipo.getStartDate(),
                        ipo.getEndDate()
                )).toList();

      return new PageImpl<>(ipoGetAllDto, pageable, ipoGetAllDto.size());
    }


}
