package com.kalayciburak.filterservice.api.controller;

import com.kalayciburak.commonpackage.model.response.BaseResponse;
import com.kalayciburak.filterservice.service.FilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/filters", produces = "application/json")
public class FiltersController {
    private final FilterService service;

    @GetMapping("/{id}")
    public BaseResponse getByFilterId(@PathVariable String id) {
        return service.getById(id);
    }

    @GetMapping("/product/{productId}")
    public BaseResponse getByProductId(@PathVariable Long productId) {
        return service.getByProductId(productId);
    }

    @GetMapping
    public BaseResponse getAll() {
        return service.getAll();
    }
}
