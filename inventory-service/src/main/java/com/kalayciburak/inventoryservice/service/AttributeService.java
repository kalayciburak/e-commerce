package com.kalayciburak.inventoryservice.service;

import com.kalayciburak.commonjpapackage.audit.AuditorAwareImpl;
import com.kalayciburak.commonpackage.model.response.BaseResponse;
import com.kalayciburak.inventoryservice.model.dto.request.AttributeRequest;
import com.kalayciburak.inventoryservice.model.entitiy.Attribute;
import com.kalayciburak.inventoryservice.repository.AttributeRepository;
import com.kalayciburak.inventoryservice.util.mapper.AttributeMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kalayciburak.commonpackage.util.constant.Auditor.ANONYMOUS;
import static com.kalayciburak.commonpackage.util.constant.Messages.Inventory.Attribute.*;
import static com.kalayciburak.commonpackage.util.response.ResponseBuilder.createNotFoundResponse;
import static com.kalayciburak.commonpackage.util.response.ResponseBuilder.createSuccessResponse;

@Service
@Transactional
@RequiredArgsConstructor
public class AttributeService {
    private final AttributeMapper mapper;
    private final AuditorAwareImpl auditorAware;
    private final ProductService productService;
    private final AttributeRepository repository;

    @Transactional(readOnly = true)
    public BaseResponse getAll() {
        var attributes = repository.findAll();
        if (attributes.isEmpty()) return createNotFoundResponse(NOT_FOUND);
        var response = attributes.stream().map(mapper::toResponse).toList();

        return createSuccessResponse(response, LISTED);
    }

    @Transactional(readOnly = true)
    public BaseResponse getById(Long id) {
        var attribute = findAttributeByIdOrThrow(id);
        var response = mapper.toResponse(attribute);

        return createSuccessResponse(response, FOUND);
    }

    @Transactional(readOnly = true)
    public BaseResponse getByName(String name) {
        var attribute = repository.findByName(name);
        if (attribute.isEmpty()) return createNotFoundResponse(NOT_FOUND);
        var response = mapper.toResponse(attribute.get());

        return createSuccessResponse(response, FOUND);
    }

    public BaseResponse save(AttributeRequest request) {
        var attribute = mapper.toEntity(request);
        assignProductToAttribute(request, attribute);
        var savedAttribute = repository.save(attribute);
        var response = mapper.toResponse(savedAttribute);

        return createSuccessResponse(response, SAVED);
    }

    public BaseResponse update(Long id, AttributeRequest request) {
        var attribute = findAttributeByIdOrThrow(id);
        assignProductToAttribute(request, attribute);
        mapper.updateEntity(request, attribute);
        var updatedAttribute = repository.save(attribute);
        var response = mapper.toResponse(updatedAttribute);

        return createSuccessResponse(response, UPDATED);
    }

    public void delete(Long id) {
        findAttributeByIdOrThrow(id);
        repository.softDeleteById(auditorAware.getCurrentAuditor().orElse(ANONYMOUS), id);
    }

    private void assignProductToAttribute(AttributeRequest request, Attribute attribute) {
        var product = productService.findProductByIdOrThrow(request.productId());
        attribute.setProduct(product);
    }

    private Attribute findAttributeByIdOrThrow(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
    }
}
