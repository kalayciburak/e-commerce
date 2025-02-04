package com.kalayciburak.inventoryservice;

import com.kalayciburak.inventoryservice.mapper.ProductMapper;
import com.kalayciburak.inventoryservice.model.dto.response.product.ProductResponse;
import com.kalayciburak.inventoryservice.model.entitiy.Product;
import com.kalayciburak.inventoryservice.repository.ProductRepository;
import com.kalayciburak.inventoryservice.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static com.kalayciburak.commonpackage.core.constant.Messages.Inventory.Product.LISTED;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductServiceTest {
    @MockBean
    private ProductMapper mapper;

    @MockBean
    private ProductRepository repository;

    @Autowired
    private ProductService productService;

    @Test
    public void GetAll_WithProducts_ReturnsSuccessResponse() {
        // Arrange
        var product1 = new Product();
        var product2 = new Product();
        var products = List.of(product1, product2);
        when(repository.findAll()).thenReturn(products);
        when(mapper.toResponse(product1)).thenReturn(createMockProductResponse());
        when(mapper.toResponse(product2)).thenReturn(createMockProductResponse());

        // Act
        var response = productService.getAll();

        // Assert
        Assertions.assertEquals(LISTED, response.getMessage());
    }

    @Test
    public void GetById_WithNonExistentId_ThrowsEntityNotFoundException() {
        // Arrange
        Long nonExistentId = 999L;
        when(repository.findById(nonExistentId)).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(EntityNotFoundException.class, () -> productService.getById(nonExistentId));
    }

    private ProductResponse createMockProductResponse() {
        return new ProductResponse(null, null, null, null, null, null, null, null, null);
    }
}