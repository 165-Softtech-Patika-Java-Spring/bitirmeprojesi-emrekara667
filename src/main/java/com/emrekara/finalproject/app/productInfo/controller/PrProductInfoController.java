package com.emrekara.finalproject.app.productInfo.controller;

import com.emrekara.finalproject.app.gen.dto.RestResponse;
import com.emrekara.finalproject.app.productInfo.dto.PrProductInfoDto;
import com.emrekara.finalproject.app.productInfo.dto.PrProductInfoSaveRequestDto;
import com.emrekara.finalproject.app.productInfo.service.PrProductInfoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/productInfo")
@RequiredArgsConstructor
public class PrProductInfoController {

    private final PrProductInfoService prProductInfoService;

    @Operation(tags = "ProductInfo Controller",
            description = "Save new product vatRate and product name",
            summary = "Save new product information")
    @PostMapping
    public ResponseEntity save(@RequestBody PrProductInfoSaveRequestDto prProductInfoSaveRequestDto){

        PrProductInfoDto prProductInfoDto = prProductInfoService.save(prProductInfoSaveRequestDto);

        return ResponseEntity.ok(RestResponse.of(prProductInfoDto));
    }

}
