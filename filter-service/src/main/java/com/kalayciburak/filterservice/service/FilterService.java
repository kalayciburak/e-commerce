package com.kalayciburak.filterservice.service;

import com.kalayciburak.commonpackage.core.advice.exception.EntityNotFoundException;
import com.kalayciburak.commonpackage.core.response.common.Response;
import com.kalayciburak.commonpackage.messaging.event.inventory.ProductCreatedEvent;
import com.kalayciburak.filterservice.mapper.FilterMapper;
import com.kalayciburak.filterservice.repository.FilterRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.kalayciburak.commonpackage.core.constant.Messages.Inventory.Product.*;
import static com.kalayciburak.commonpackage.core.response.builder.ResponseBuilder.createSuccessResponse;

@Service
@RequiredArgsConstructor
public class FilterService {
    private static final Logger log = LoggerFactory.getLogger(FilterService.class);
    private final FilterMapper mapper;
    private final FilterRepository repository;

    public Response getById(String id) {
        var filter = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        var data = mapper.toResponse(filter);

        return createSuccessResponse(data, FOUND);
    }

    public Response getByProductId(Long productId) {
        var filter = repository.findByProductId(productId).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        var data = mapper.toResponse(filter);

        return createSuccessResponse(data, FOUND);
    }

    public Response getAll() {
        var filters = repository.findAll();
        var data = filters.stream().map(mapper::toResponse).toList();

        return createSuccessResponse(data, LISTED);
    }

    /**
     * <b>Veritabanına yeni bir ürün kaydeder.</b>
     * <p>
     * Bu metot, {@link ProductCreatedEvent} türündeki bir event'i alır ve bu event'i veritabanına kaydeder. Event,
     * {@link FilterMapper} sınıfı aracılığıyla entity'e dönüştürülür ve ardından veritabanına kaydedilir. Event'in
     * veritabanına kaydedilmesi başarılı olursa loglama yapılır.
     *
     * @param event Kaydedilecek {@link ProductCreatedEvent} türünde event
     */
    public void onProductCreatedEvent(ProductCreatedEvent event) {
        var filter = mapper.toEntity(event);
        repository.save(filter);
        log.info("{} saved to database successfully", event.name());
    }
}
