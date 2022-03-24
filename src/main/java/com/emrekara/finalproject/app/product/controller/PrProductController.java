package com.emrekara.finalproject.app.product.controller;

import com.emrekara.finalproject.app.gen.dto.RestResponse;
import com.emrekara.finalproject.app.gen.enums.ProductType;
import com.emrekara.finalproject.app.product.dto.*;
import com.emrekara.finalproject.app.product.entity.PrProduct;
import com.emrekara.finalproject.app.product.service.PrProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class PrProductController {

    private final PrProductService prProductService;

    @Operation(tags = "Product Controller",
            description = "Save new product : FOOD - STATIONARY - CLOTHES - TECHNOLOGY - CLEANING -OTHER - DUMMY",
            summary = "Save new product")
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody PrProductSaveRequestDto prProductSaveRequestDto){

        PrProductDto prProductDto = prProductService.save(prProductSaveRequestDto);

        WebMvcLinkBuilder linkGet = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(
                        this.getClass()).findAll(prProductSaveRequestDto.getProductType()));

        EntityModel entityModel = EntityModel.of(prProductDto);

        entityModel.add(linkGet.withRel("Find-All-Product-By-Product-Type"));

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(entityModel);

        return ResponseEntity.ok(RestResponse.of(mappingJacksonValue));
    }


    @Operation(tags = "Product Controller", description = "Update product attributes", summary = "Update product")
    @PutMapping
    public ResponseEntity update(@RequestBody PrProductUpdateRequestDto prProductUpdateRequestDto){

        PrProductDto prProductDto = prProductService.update(prProductUpdateRequestDto);

        return ResponseEntity.ok(RestResponse.of(prProductDto));
    }

    @Operation(tags = "Product Controller", description = "Delete product", summary = "Delete product")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){

        prProductService.delete(id);

        RestResponse<Object> empty = RestResponse.empty();
        empty.setMessages(id + " : entity deleted");

        return ResponseEntity.ok(empty);
    }


    @Operation(tags = "Product Controller", description = "Update product price", summary = "Update product price")
    @PutMapping("/update-price")
    public ResponseEntity updatePrice(@RequestBody PrProductUpdatePriceDto prProductUpdatePriceDto){

        PrProductDto prProductDto = prProductService.updatePrice(prProductUpdatePriceDto);

        return ResponseEntity.ok(RestResponse.of(prProductDto));
    }

    @Operation(tags = "Product Controller", description = "Show all product", summary = "Show all product")
    @GetMapping
    public ResponseEntity findAll(){
        List<PrProductDto> prProductDtoList = prProductService.findAll();

        return ResponseEntity.ok(RestResponse.of(prProductDtoList));
    }

    @Operation(tags = "Product Controller", description = "Show all by productType", summary = "Show all by productType")
    @GetMapping("/{productType}")
    public ResponseEntity findAll(@PathVariable ProductType productType){
        List<PrProductDto> prProductDtoList = prProductService.findAllByProduct(productType);

        return ResponseEntity.ok(RestResponse.of(prProductDtoList));
    }


    @Operation(tags = "Product Controller", description = "Show all product by price", summary = "Show all product by price")
    @GetMapping("/List-product-by-price")
    public ResponseEntity findProductByPriceBetween(@RequestParam BigDecimal minimum,
                                                    @RequestParam BigDecimal maximum){
        List<PrProductDto> productDtoList = prProductService.findAllByFinalPriceBetween(minimum, maximum);

        return ResponseEntity.ok(RestResponse.of(productDtoList));
    }


    @Operation(tags = "Product Controller", description = "Show details", summary = "Show details")
    @GetMapping("/{productType}/details")
    public ResponseEntity productDetails(@PathVariable ProductType productType){

        PrProductDetailsDto prProductDetailsDto = prProductService.findProductDetails(productType);

        return ResponseEntity.ok(RestResponse.of(prProductDetailsDto));
    }

}
