package org.dd.bre.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.dd.bre.Dto.ErrorDetails;
import org.dd.bre.Dto.ProductDetailsDto;
import org.dd.bre.Dto.ProductPageResponse;
import org.dd.bre.Exception.ProductNotFoundException;
import org.dd.bre.Service.ProductService;
import org.dd.bre.model.Product;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/category")
    public ResponseEntity<ProductPageResponse> getAllProductByCategoryHandler(@RequestParam String categoryName,
                                                                           @RequestParam(defaultValue = "0") int page,
                                                                           @RequestParam(defaultValue = "12") int size,
                                                                           @RequestParam(required = false) String sortBy,
                                                                           @RequestParam(required = false) String direction){
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(productService.getAllProductsByCategory(categoryName,pageable,sortBy,direction), HttpStatus.OK);
    }

    @Operation(
            summary = "Get product by productName",
            description = "Returns a single product based on its name",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful Operation",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProductDetailsDto.class))),
                    @ApiResponse(responseCode = "404", description = "Product Not Found",content =  @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDetails.class)))
            }
    )
    @GetMapping("/{productName}")
    public ResponseEntity<ProductDetailsDto> getProductByNameHandler(@Parameter(description = "name of the product to retrieve")
            @PathVariable String productName){
        return new ResponseEntity<>(productService.getProductByName(productName), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ProductPageResponse> getAllProductsHandler(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String direction) {

        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(productService.getAllProducts(pageable, sortBy, direction));
    }


}
