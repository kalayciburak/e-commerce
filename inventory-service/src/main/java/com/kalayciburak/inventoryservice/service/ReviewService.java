package com.kalayciburak.inventoryservice.service;

import com.kalayciburak.commonjpa.audit.AuditorAwareImpl;
import com.kalayciburak.commonpackage.core.response.common.Response;
import com.kalayciburak.inventoryservice.mapper.ReviewMapper;
import com.kalayciburak.inventoryservice.model.dto.request.ReviewRequest;
import com.kalayciburak.inventoryservice.model.entitiy.Review;
import com.kalayciburak.inventoryservice.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kalayciburak.commonjpa.constant.Auditor.ANONYMOUS;
import static com.kalayciburak.commonpackage.core.constant.Messages.Inventory.Review.*;
import static com.kalayciburak.commonpackage.core.response.builder.ResponseBuilder.createNotFoundResponse;
import static com.kalayciburak.commonpackage.core.response.builder.ResponseBuilder.createSuccessResponse;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewMapper mapper;
    private final ReviewRepository repository;
    private final AuditorAwareImpl auditorAware;
    private final ProductService productService;

    @Transactional(readOnly = true)
    public Response getAll() {
        var reviews = repository.findAll();
        if (reviews.isEmpty()) return createNotFoundResponse(NOT_FOUND);
        var response = reviews.stream().map(mapper::toResponse).toList();

        return createSuccessResponse(response, LISTED);
    }

    @Transactional(readOnly = true)
    public Response getById(Long id) {
        var review = findReviewByIdOrThrow(id);
        var response = mapper.toResponse(review);

        return createSuccessResponse(response, FOUND);
    }

    @Transactional
    public Response save(ReviewRequest request) {
        var review = mapper.toEntity(request);
        assignProductToReview(request, review);
        var savedReview = repository.save(review);
        var response = mapper.toResponse(savedReview);

        return createSuccessResponse(response, SAVED);
    }

    @Transactional
    public Response update(Long id, ReviewRequest request) {
        var review = findReviewByIdOrThrow(id);
        assignProductToReview(request, review);
        mapper.updateEntity(request, review);
        var updatedReview = repository.save(review);
        var response = mapper.toResponse(updatedReview);

        return createSuccessResponse(response, UPDATED);
    }

    @Transactional
    public void delete(Long id) {
        repository.softDeleteByIdOrThrow(auditorAware.getCurrentAuditor().orElse(ANONYMOUS), id);
    }

    private void assignProductToReview(ReviewRequest request, Review review) {
        var product = productService.findProductByIdOrThrow(request.productId());
        review.setProduct(product);
    }

    private Review findReviewByIdOrThrow(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
    }
}