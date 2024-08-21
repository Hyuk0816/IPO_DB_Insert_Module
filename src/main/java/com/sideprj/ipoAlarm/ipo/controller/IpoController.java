package com.sideprj.ipoAlarm.ipo.controller;

import com.sideprj.ipoAlarm.ipo.constant.IpoConstants;
import com.sideprj.ipoAlarm.ipo.service.IpoService;
import com.sideprj.ipoAlarm.ipo.vo.IpoDataSaveVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/ipo")
public class IpoController {

    private final IpoService ipoService;

    @PostMapping("/data")
    public ResponseEntity<IpoDataSaveVo> save() throws IOException {
        ipoService.saveIpoData();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new IpoDataSaveVo(IpoConstants.STATUS_200, IpoConstants.MESSAGE_200));
    }

}
