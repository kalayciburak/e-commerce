package com.kalayciburak.inventoryservice.api.controller;

import com.kalayciburak.commonpackage.core.response.common.Response;
import com.kalayciburak.inventoryservice.model.dto.request.AttributeRequest;
import com.kalayciburak.inventoryservice.service.AttributeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/attributes", produces = "application/json")
public class AttributesController {
    private final AttributeService service;

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response save(@RequestBody @Valid AttributeRequest request) {
        return service.save(request);
    }

    @PutMapping("/{id}")
    public Response update(@PathVariable Long id, @RequestBody @Valid AttributeRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}