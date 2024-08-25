package com.kalayciburak.inventoryservice.service;

import com.kalayciburak.commonpackage.model.response.BaseResponse;
import com.kalayciburak.inventoryservice.model.dto.request.ImageRequest;
import com.kalayciburak.inventoryservice.model.entitiy.Image;
import com.kalayciburak.inventoryservice.repository.ImageRepository;
import com.kalayciburak.inventoryservice.util.mapper.ImageMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kalayciburak.commonpackage.util.constant.Messages.Inventory.Image.*;
import static com.kalayciburak.commonpackage.util.response.ResponseBuilder.createNotFoundResponse;
import static com.kalayciburak.commonpackage.util.response.ResponseBuilder.createSuccessResponse;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageService {
    private final ImageMapper mapper;
    private final ImageRepository repository;

    @Transactional(readOnly = true)
    public BaseResponse getAll() {
        var images = repository.findAll();
        if (images.isEmpty()) return createNotFoundResponse(NOT_FOUND);
        var response = images.stream().map(mapper::toResponse).toList();

        return createSuccessResponse(response, LISTED);
    }

    @Transactional(readOnly = true)
    public BaseResponse getById(Long id) {
        var image = findImageByIdOrThrow(id);
        var response = mapper.toResponse(image);

        return createSuccessResponse(response, FOUND);
    }

    public BaseResponse save(ImageRequest request) {
        var image = mapper.toEntity(request);
        var savedImage = repository.save(image);
        var response = mapper.toResponse(savedImage);

        return createSuccessResponse(response, SAVED);
    }

    public BaseResponse update(Long id, ImageRequest request) {
        var image = findImageByIdOrThrow(id);
        mapper.updateEntity(request, image);
        var updatedImage = repository.save(image);
        var response = mapper.toResponse(updatedImage);

        return createSuccessResponse(response, UPDATED);
    }

    public void delete(Long id) {
        findImageByIdOrThrow(id);
        repository.softDeleteById(id);
    }

    private Image findImageByIdOrThrow(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
    }
}
