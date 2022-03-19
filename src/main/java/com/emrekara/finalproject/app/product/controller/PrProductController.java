package com.emrekara.finalproject.app.product.controller;

import com.emrekara.finalproject.app.gen.dto.RestResponse;
import com.emrekara.finalproject.app.product.dto.PrProductDto;
import com.emrekara.finalproject.app.product.dto.PrProductSaveRequestDto;
import com.emrekara.finalproject.app.product.service.PrProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class PrProductController {

    private final PrProductService prProductService;

    @Operation(tags = "Product Controller", description = "Save new product", summary = "Save new product")
    @PostMapping
    public ResponseEntity save(@RequestBody PrProductSaveRequestDto prProductSaveRequestDto){

        PrProductDto prProductDto = prProductService.save(prProductSaveRequestDto);

        return ResponseEntity.ok(RestResponse.of(prProductDto));
    }







}
