package com.kalayciburak.inventoryservice.service;

import com.kalayciburak.commonjpa.audit.AuditorAwareImpl;
import com.kalayciburak.commonpackage.core.response.common.Response;
import com.kalayciburak.commonpackage.messaging.event.inventory.product.ProductCreatedEvent;
import com.kalayciburak.inventoryservice.mapper.ProductMapper;
import com.kalayciburak.inventoryservice.messaging.producer.ProductProducer;
import com.kalayciburak.inventoryservice.model.dto.request.ProductRequest;
import com.kalayciburak.inventoryservice.model.dto.response.product.ProductSimpleResponse;
import com.kalayciburak.inventoryservice.model.entitiy.Product;
import com.kalayciburak.inventoryservice.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kalayciburak.commonjpa.constant.Auditor.ANONYMOUS;
import static com.kalayciburak.commonpackage.core.constant.Messages.Inventory.Product.*;
import static com.kalayciburak.commonpackage.core.response.builder.ResponseBuilder.createNotFoundResponse;
import static com.kalayciburak.commonpackage.core.response.builder.ResponseBuilder.createSuccessResponse;

@Service
@RequiredArgsConstructor
public class ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    private final ProductMapper mapper;
    private final ProductProducer producer;
    private final ProductRepository repository;
    private final AuditorAwareImpl auditorAware;
    private final CategoryService categoryService;

    @Transactional(readOnly = true)
    public Response getAll() {
        var products = repository.findAll();
        if (products.isEmpty()) return createNotFoundResponse(NOT_FOUND);
        var response = products.stream().map(mapper::toResponse).toList();
        log.info("Retrieved {} products from the database.", response.size());

        return createSuccessResponse(response, LISTED);
    }

    @Transactional(readOnly = true)
    public Response getById(Long id) {
        var product = findProductByIdOrThrow(id);
        var response = mapper.toResponse(product);

        return createSuccessResponse(response, FOUND);
    }

    @Transactional(readOnly = true)
    public Response getByName(String name) {
        var product = repository.findByName(name);
        if (product.isEmpty()) return createNotFoundResponse(NOT_FOUND);
        var response = mapper.toResponse(product.get());

        return createSuccessResponse(response, FOUND);
    }

    @Transactional
    public Response save(ProductRequest request) {
        var product = mapper.toEntity(request);
        assignCategoryToProduct(request, product);
        var savedProduct = repository.save(product);
        var response = mapper.toSimpleResponse(savedProduct);
        produceProductCreatedEvent(response);

        return createSuccessResponse(response, SAVED);
    }

    @Transactional
    public Response update(Long id, ProductRequest request) {
        var product = findProductByIdOrThrow(id);
        assignCategoryToProduct(request, product);
        mapper.updateEntity(request, product);
        var updatedProduct = repository.save(product);
        var response = mapper.toSimpleResponse(updatedProduct);

        return createSuccessResponse(response, UPDATED);
    }

    @Transactional
    public void delete(Long id) {
        repository.softDeleteByIdOrThrow(auditorAware.getCurrentAuditor().orElse(ANONYMOUS), id);
    }

    protected Product findProductByIdOrThrow(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
    }

    private void assignCategoryToProduct(ProductRequest request, Product product) {
        var category = categoryService.findCategoryByIdOrThrow(request.categoryId());
        product.setCategory(category);
    }

    /**
     * Ürün oluşturulduğunda bir {@link ProductCreatedEvent} üretir ve Kafka'ya gönderir.
     *
     * @param response Ürün bilgileri
     */
    private void produceProductCreatedEvent(ProductSimpleResponse response) {
        var event = mapper.toProductCreatedEvent(response);
        producer.sendProductCreatedEvent(event);
    }
}
