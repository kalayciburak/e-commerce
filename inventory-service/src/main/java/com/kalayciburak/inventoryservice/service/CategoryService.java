package com.kalayciburak.inventoryservice.service;

import com.kalayciburak.commonpackage.model.response.BaseResponse;
import com.kalayciburak.inventoryservice.model.dto.request.CategoryRequest;
import com.kalayciburak.inventoryservice.model.dto.response.category.AllParentCategoriesResponse;
import com.kalayciburak.inventoryservice.model.entitiy.Category;
import com.kalayciburak.inventoryservice.repository.CategoryRepository;
import com.kalayciburak.inventoryservice.util.mapper.CategoryMapper;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kalayciburak.commonpackage.util.constant.Messages.Inventory.Category.*;
import static com.kalayciburak.commonpackage.util.response.ResponseBuilder.createNotFoundResponse;
import static com.kalayciburak.commonpackage.util.response.ResponseBuilder.createSuccessResponse;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryMapper mapper;
    private final CategoryRepository repository;

    @Transactional(readOnly = true)
    public BaseResponse getAll() {
        var categories = repository.findAll();
        if (categories.isEmpty()) return createNotFoundResponse(NOT_FOUND);
        var response = categories.stream().map(mapper::toResponse).toList();

        return createSuccessResponse(response, LISTED);
    }

    @Transactional(readOnly = true)
    public BaseResponse getById(Long id) {
        var category = findCategoryByIdOrThrow(id);
        var response = mapper.toResponse(category);

        return createSuccessResponse(response, FOUND);
    }

    @Transactional(readOnly = true)
    public BaseResponse getByName(String name) {
        var category = repository.findByName(name);
        if (category.isEmpty()) return createNotFoundResponse(NOT_FOUND);
        var response = mapper.toResponse(category.get());

        return createSuccessResponse(response, FOUND);
    }

    @Transactional(readOnly = true)
    public BaseResponse getCategoryWithSubcategoryInfo(Long id) {
        var category = findCategoryByIdOrThrow(id);
        var response = mapper.toCategoryWithSubcategoryResponse(category);

        return createSuccessResponse(response, SUBCATEGORY_INFO);
    }

    @Transactional(readOnly = true)
    public BaseResponse getAllParentCategoriesWithSubcategoryInfo() {
        var parentCategories = repository.findAllByParentIsNull();
        var categoryResponses = mapper.toCategoryWithSubcategoryResponseList(parentCategories);
        var response = new AllParentCategoriesResponse(categoryResponses);

        return createSuccessResponse(response, PARENT_CATEGORIES_INFO);
    }

    public BaseResponse save(CategoryRequest request) {
        checkCategoryUniqueness(request.name());
        var category = mapper.toEntity(request);
        assignParentCategory(request, category);
        var savedCategory = repository.save(category);
        var response = mapper.toResponse(savedCategory);

        return createSuccessResponse(response, SAVED);
    }

    public BaseResponse update(Long id, CategoryRequest request) {
        var category = findCategoryByIdOrThrow(id);
        updateParentCategory(request, category);
        mapper.updateEntity(request, category);
        var updatedCategory = repository.save(category);
        var response = mapper.toResponse(updatedCategory);

        return createSuccessResponse(response, UPDATED);
    }

    public void delete(Long id) {
        findCategoryByIdOrThrow(id);
        repository.softDeleteById(id);
    }

    private void assignParentCategory(CategoryRequest request, Category category) {
        if (request.parentId() != null) category.setParent(findCategoryByIdOrThrow(request.parentId()));
    }

    /**
     * <b>Kategorinin üst kategorisini günceller.</b>
     * <p>
     * Eğer {@code request.parentId} null ise, kategorinin üst kategorisi null olarak ayarlanır.
     * Değilse, belirtilen üst kategori ID'sine sahip kategoriyi bulur ve onu üst kategori olarak ayarlar.
     *
     * @param request  Yeni kategori bilgileri.
     * @param category Güncellenecek kategori.
     */
    private void updateParentCategory(CategoryRequest request, Category category) {
        if (request.parentId() == null) category.setParent(null);
        else category.setParent(findCategoryByIdOrThrow(request.parentId()));
    }

    private void checkCategoryUniqueness(String name) {
        if (repository.existsByName(name)) throw new EntityExistsException(EXISTS);
    }

    protected Category findCategoryByIdOrThrow(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
    }
}
