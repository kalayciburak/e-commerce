package com.kalayciburak.inventoryservice.service;

import com.kalayciburak.commonjpapackage.model.response.BaseResponse;
import com.kalayciburak.inventoryservice.model.dto.request.ProductRequest;
import com.kalayciburak.inventoryservice.model.entitiy.Product;
import com.kalayciburak.inventoryservice.repository.ProductRepository;
import com.kalayciburak.inventoryservice.util.mapper.ProductMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kalayciburak.commonjpapackage.util.constant.Messages.Inventory.Product.*;
import static com.kalayciburak.commonjpapackage.util.response.ResponseBuilder.createNotFoundResponse;
import static com.kalayciburak.commonjpapackage.util.response.ResponseBuilder.createSuccessResponse;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper mapper;
    private final ProductRepository repository;
    private final CategoryService categoryService;

    @Transactional(readOnly = true)
    public BaseResponse getAll() {
        var products = repository.findAll();
        if (products.isEmpty()) return createNotFoundResponse(NOT_FOUND);
        var response = products.stream().map(mapper::toResponse).toList();

        return createSuccessResponse(response, LISTED);
    }

    @Transactional(readOnly = true)
    public BaseResponse getById(Long id) {
        var product = findProductByIdOrThrow(id);
        var response = mapper.toResponse(product);

        return createSuccessResponse(response, FOUND);
    }

    @Transactional(readOnly = true)
    public BaseResponse getByName(String name) {
        var product = repository.findByName(name);
        if (product.isEmpty()) return createNotFoundResponse(NOT_FOUND);
        var response = mapper.toResponse(product.get());

        return createSuccessResponse(response, FOUND);
    }

    public BaseResponse save(ProductRequest request) {
        var product = mapper.toEntity(request);
        var category = categoryService.findCategoryByIdOrThrow(request.categoryId());
        product.setCategory(category);
        var savedProduct = repository.save(product);
        var response = mapper.toResponse(savedProduct);

        return createSuccessResponse(response, SAVED);
    }

    public BaseResponse update(Long id, ProductRequest request) {
        var product = findProductByIdOrThrow(id);
        mapper.updateEntity(request, product);
        var updatedProduct = repository.save(product);
        var response = mapper.toResponse(updatedProduct);

        return createSuccessResponse(response, UPDATED);
    }

    public void delete(Long id) {
        findProductByIdOrThrow(id);
        repository.softDeleteById(id);
    }

    private Product findProductByIdOrThrow(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
    }
}
