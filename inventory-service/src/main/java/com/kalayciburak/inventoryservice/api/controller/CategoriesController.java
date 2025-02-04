package com.kalayciburak.inventoryservice.api.controller;

import com.kalayciburak.commonpackage.core.response.common.Response;
import com.kalayciburak.inventoryservice.model.dto.request.CategoryRequest;
import com.kalayciburak.inventoryservice.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/categories", produces = "application/json")
public class CategoriesController {
    private final CategoryService service;

    @GetMapping
    public Response getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Response getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/q")
    public Response getByName(@RequestParam String name) {
        return service.getByName(name);
    }

    @GetMapping("/{id}/details")
    public Response getCategoryDetails(@PathVariable Long id) {
        return service.getCategoryWithSubcategoryInfo(id);
    }

    @GetMapping("/all-parents/details")
    public Response getAllParentCategories() {
        return service.getAllParentCategoriesWithSubcategoryInfo();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response save(@RequestBody @Valid CategoryRequest request) {
        return service.save(request);
    }

    @PutMapping("/{id}")
    public Response update(@PathVariable Long id, @RequestBody @Valid CategoryRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
