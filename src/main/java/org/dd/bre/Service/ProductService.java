package org.dd.bre.Service;

import lombok.RequiredArgsConstructor;
import org.dd.bre.Dto.ProductDTO;
import org.dd.bre.Dto.ProductDetailsDto;
import org.dd.bre.Dto.ProductPageResponse;
import org.dd.bre.Exception.ProductNotFoundException;
import org.dd.bre.Exception.WishListNotFoundException;
import org.dd.bre.Mapper.ProductDetailsMapper;
import org.dd.bre.Mapper.ProductMapper;
import org.dd.bre.Repo.CategoryRepo;
import org.dd.bre.Repo.ProductRepo;
import org.dd.bre.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final ProductMapper productMapper;
    private final ProductDetailsMapper productDetailsMapper;

    public ProductDetailsDto getProductByName(String productName) {
        Product product = productRepo.findByProductName(productName);
        if (product == null) {
            throw new ProductNotFoundException("Product not found: " + productName);
        }
        return productDetailsMapper.mapToProductDTO(product);
    }


    protected ProductPageResponse getAllProductsByWishList(List<WishList> wishLists,Pageable pageable) {
        List<Long> in = wishLists.stream().map(WishList::getId).toList();
        Page<Product> pageResult = productRepo.findAllByWishLists_IdIn(in,pageable);
        return buildProductPageResponse(pageResult);
    }

    public ProductPageResponse getAllProductsByCategory(String categoryName,Pageable pageable, String sortBy, String direction) {

        Category category = categoryRepo.findByCategoryName(categoryName);
        Page<Product> pageResult = productRepo.findAllByCategory(category, pageable);

        if (category == null) {
            throw new ProductNotFoundException("Category not found: " + categoryName);
        }

        if("rate".equalsIgnoreCase(sortBy)) {
            pageResult = productRepo.findAllByCategoryHighRate(category,pageable);
        }

        else if("price".equalsIgnoreCase(sortBy)) {
            Pageable page = sortProducts(pageable, sortBy, direction);
            pageResult = productRepo.findAllByCategory(category, page);
        }

        return buildProductPageResponse(pageResult);

    }
    public ProductPageResponse getAllProducts(Pageable pageable, String sortBy, String direction) {
        Page<Product> pageResult = productRepo.findAll(pageable);

        if("rate".equalsIgnoreCase(sortBy)) {
            pageResult = productRepo.findAllByHighRate(pageable);
        }

        else if("price".equalsIgnoreCase(sortBy)) {
            Pageable page = sortProducts(pageable, sortBy, direction);
            pageResult = productRepo.findAll(page);
        }

        return buildProductPageResponse(pageResult);
    }

    public ProductPageResponse buildProductPageResponse(Page<Product> pageResult) {
        List<ProductDTO> dtos =  new ArrayList<>();
        if (!pageResult.isEmpty()) {
            dtos = pageResult.getContent().stream()
                    .map(productMapper::mapToProductDTO)
                    .toList();
        }

        return new ProductPageResponse(
                dtos,
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.getTotalPages(),
                pageResult.hasNext()
        );
    }

    public Pageable sortProducts(Pageable pageable,String sortBy,String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
    }


}
