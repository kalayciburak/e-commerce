package com.kalayciburak.inventoryservice.service;

import com.kalayciburak.commonjpa.audit.AuditorAwareImpl;
import com.kalayciburak.commonpackage.core.response.common.Response;
import com.kalayciburak.inventoryservice.mapper.ImageMapper;
import com.kalayciburak.inventoryservice.model.dto.request.ImageRequest;
import com.kalayciburak.inventoryservice.model.entitiy.Image;
import com.kalayciburak.inventoryservice.repository.ImageRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kalayciburak.commonjpa.constant.Auditor.ANONYMOUS;
import static com.kalayciburak.commonpackage.core.constant.Messages.Inventory.Image.*;
import static com.kalayciburak.commonpackage.core.response.builder.ResponseBuilder.createNotFoundResponse;
import static com.kalayciburak.commonpackage.core.response.builder.ResponseBuilder.createSuccessResponse;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageMapper mapper;
    private final ImageRepository repository;
    private final AuditorAwareImpl auditorAware;
    private final ProductService productService;

    @Transactional(readOnly = true)
    public Response getAll() {
        var images = repository.findAll();
        if (images.isEmpty()) return createNotFoundResponse(NOT_FOUND);
        var response = images.stream().map(mapper::toResponse).toList();

        return createSuccessResponse(response, LISTED);
    }

    @Transactional(readOnly = true)
    public Response getById(Long id) {
        var image = findImageByIdOrThrow(id);
        var response = mapper.toResponse(image);

        return createSuccessResponse(response, FOUND);
    }

    @Transactional
    public Response save(ImageRequest request) {
        var image = mapper.toEntity(request);
        assignProductToImage(request, image);
        var savedImage = repository.save(image);
        var response = mapper.toResponse(savedImage);

        return createSuccessResponse(response, SAVED);
    }

    @Transactional
    public Response update(Long id, ImageRequest request) {
        var image = findImageByIdOrThrow(id);
        assignProductToImage(request, image);
        mapper.updateEntity(request, image);
        var updatedImage = repository.save(image);
        var response = mapper.toResponse(updatedImage);

        return createSuccessResponse(response, UPDATED);
    }

    @Transactional
    public void delete(Long id) {
        repository.softDeleteByIdOrThrow(auditorAware.getCurrentAuditor().orElse(ANONYMOUS), id);
    }

    private void assignProductToImage(ImageRequest request, Image image) {
        var product = productService.findProductByIdOrThrow(request.productId());
        image.setProduct(product);
    }

    private Image findImageByIdOrThrow(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
    }
}
