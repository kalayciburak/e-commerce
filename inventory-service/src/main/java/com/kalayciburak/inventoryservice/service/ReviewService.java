package com.kalayciburak.inventoryservice.service;

import com.kalayciburak.commonpackage.model.response.BaseResponse;
import com.kalayciburak.inventoryservice.model.dto.request.ReviewRequest;
import com.kalayciburak.inventoryservice.model.entitiy.Review;
import com.kalayciburak.inventoryservice.repository.ReviewRepository;
import com.kalayciburak.inventoryservice.util.mapper.ReviewMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kalayciburak.commonpackage.util.constant.Messages.Inventory.Review.*;
import static com.kalayciburak.commonpackage.util.response.ResponseBuilder.createNotFoundResponse;
import static com.kalayciburak.commonpackage.util.response.ResponseBuilder.createSuccessResponse;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewMapper mapper;
    private final ReviewRepository repository;

    @Transactional(readOnly = true)
    public BaseResponse getAll() {
        var reviews = repository.findAll();
        if (reviews.isEmpty()) return createNotFoundResponse(NOT_FOUND);
        var response = reviews.stream().map(mapper::toResponse).toList();

        return createSuccessResponse(response, LISTED);
    }

    @Transactional(readOnly = true)
    public BaseResponse getById(Long id) {
        var review = findReviewByIdOrThrow(id);
        var response = mapper.toResponse(review);

        return createSuccessResponse(response, FOUND);
    }

    public BaseResponse save(ReviewRequest request) {
        var review = mapper.toEntity(request);
        var savedReview = repository.save(review);
        var response = mapper.toResponse(savedReview);

        return createSuccessResponse(response, SAVED);
    }

    public BaseResponse update(Long id, ReviewRequest request) {
        var review = findReviewByIdOrThrow(id);
        mapper.updateEntity(request, review);
        var updatedReview = repository.save(review);
        var response = mapper.toResponse(updatedReview);

        return createSuccessResponse(response, UPDATED);
    }

    public void delete(Long id) {
        findReviewByIdOrThrow(id);
        repository.softDeleteById(id);
    }

    private Review findReviewByIdOrThrow(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
    }
}