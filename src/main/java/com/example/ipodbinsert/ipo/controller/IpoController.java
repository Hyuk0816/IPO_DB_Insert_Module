package com.example.ipodbinsert.ipo.controller;


import com.example.ipodbinsert.ipo.constant.IpoConstants;
import com.example.ipodbinsert.ipo.service.IpoService;
import com.example.ipodbinsert.ipo.vo.IpoDataSaveVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/ipo")
public class IpoController {

    private final IpoService ipoService;

    @PostMapping("/data")
    public ResponseEntity<IpoDataSaveVo> save() throws IOException {
        ipoService.save();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new IpoDataSaveVo(IpoConstants.STATUS_200, IpoConstants.MESSAGE_200));
    }



}
