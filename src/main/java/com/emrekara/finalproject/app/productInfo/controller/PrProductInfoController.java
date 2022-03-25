package com.emrekara.finalproject.app.productInfo.controller;

import com.emrekara.finalproject.app.gen.dto.RestResponse;
import com.emrekara.finalproject.app.productInfo.dto.PrProductInfoDto;
import com.emrekara.finalproject.app.productInfo.dto.PrProductInfoSaveRequestDto;
import com.emrekara.finalproject.app.productInfo.dto.PrProductInfoUpdateRequestDto;
import com.emrekara.finalproject.app.productInfo.service.PrProductInfoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productInfo")
@RequiredArgsConstructor
public class PrProductInfoController {

    private final PrProductInfoService prProductInfoService;

    @Operation(tags = "ProductInfo Controller",
            description = "Save new product vatRate and product name",
            summary = "Save new product information")
    //@PostMapping
    public ResponseEntity save(@RequestBody PrProductInfoSaveRequestDto prProductInfoSaveRequestDto){

        PrProductInfoDto prProductInfoDto = prProductInfoService.save(prProductInfoSaveRequestDto);

        return ResponseEntity.ok(RestResponse.of(prProductInfoDto));
    }

    @Operation(tags = "ProductInfo Controller", description = "Find all ProductInfo", summary = "Find all ProductInfo")
    @GetMapping
    public ResponseEntity findAll(){
        List<PrProductInfoDto> prProductInfoDtoList =  prProductInfoService.findAll();

        return ResponseEntity.ok(RestResponse.of(prProductInfoDtoList));
    }

    @Operation(tags = "ProductInfo Controller",
            description = "Update vatRate : FOOD - STATIONARY - CLOTHES - TECHNOLOGY - CLEANING -OTHER - DUMMY",
            summary = "Update vatRate")
    @PostMapping("/update-vatRate")
    public ResponseEntity updateVatRate(@RequestBody PrProductInfoUpdateRequestDto prProductInfoUpdateRequestDto){
        PrProductInfoDto prProductInfoDto = prProductInfoService.updateVatRate(prProductInfoUpdateRequestDto);

        RestResponse<PrProductInfoDto> response = RestResponse.of(prProductInfoDto);
        response.setMessages("VatRate updated");

        return ResponseEntity.ok(response);
    }





}
