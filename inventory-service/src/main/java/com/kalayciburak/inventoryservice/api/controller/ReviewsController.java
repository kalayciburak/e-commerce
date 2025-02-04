package com.kalayciburak.inventoryservice.api.controller;

import com.kalayciburak.commonpackage.core.response.common.Response;
import com.kalayciburak.inventoryservice.model.dto.request.ReviewRequest;
import com.kalayciburak.inventoryservice.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/reviews", produces = "application/json")
public class ReviewsController {
    private final ReviewService service;

    @GetMapping
    public Response getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Response getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response save(@RequestBody @Valid ReviewRequest request) {
        return service.save(request);
    }

    @PutMapping("/{id}")
    public Response update(@PathVariable Long id, @RequestBody @Valid ReviewRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}