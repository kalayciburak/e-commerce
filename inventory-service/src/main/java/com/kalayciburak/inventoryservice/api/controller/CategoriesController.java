package com.kalayciburak.inventoryservice.api.controller;

import com.kalayciburak.commonpackage.model.response.BaseResponse;
import com.kalayciburak.inventoryservice.model.dto.request.CategoryRequest;
import com.kalayciburak.inventoryservice.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/categories", produces = "application/json")
public class CategoriesController {
    private final CategoryService service;

    @GetMapping
    public BaseResponse getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public BaseResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/q")
    public BaseResponse getByName(@RequestParam String name) {
        return service.getByName(name);
    }

    @GetMapping("/{id}/products")
    public BaseResponse getByIdWithProducts(@PathVariable Long id) {
        return service.getByIdWithProducts(id);
    }

    @PostMapping
    public BaseResponse save(@RequestBody @Valid CategoryRequest request) {
        return service.save(request);
    }

    @PutMapping("/{id}")
    public BaseResponse update(@PathVariable Long id, @RequestBody @Valid CategoryRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
