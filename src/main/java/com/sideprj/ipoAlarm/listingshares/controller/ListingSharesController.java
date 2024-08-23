package com.sideprj.ipoAlarm.listingshares.controller;

import com.sideprj.ipoAlarm.ipo.constant.IpoConstants;
import com.sideprj.ipoAlarm.ipo.vo.IpoDataSaveVo;
import com.sideprj.ipoAlarm.listingshares.service.ListingSharesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/listing_shares")
public class ListingSharesController {

    private final ListingSharesService listingSharesService;

    @Operation(
            summary = "IPO Data db insert test Rest API",
            description = "Create IPO data Detail get Request"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP STATUS OK"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "HTTP STATUS UNAUTHORIZED"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP STATUS NOT FOUND"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )

    }
    )
    @PostMapping("/data")
    public ResponseEntity<IpoDataSaveVo> saveListingShares() throws IOException {
        listingSharesService.saveListingShares();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new IpoDataSaveVo(IpoConstants.STATUS_200, IpoConstants.MESSAGE_200));
    }
}
