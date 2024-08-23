package com.sideprj.ipoAlarm.ipo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IpoSearchRequestVo {

    private String ipoName;

    @Schema(description = "조회 기준 시작", example = "2024-07-01")
    private LocalDateTime searchStartDate;

    @Schema(description = "조회 기준 끝", example = "2024-07-30")
    private LocalDateTime searchEndDate;
}
