package com.kalayciburak.filterservice.service;

import com.kalayciburak.commonpackage.event.inventory.ProductCreatedEvent;
import com.kalayciburak.filterservice.repository.FilterRepository;
import com.kalayciburak.filterservice.util.mapper.FilterMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FilterService {
    private static final Logger log = LoggerFactory.getLogger(FilterService.class);
    private final FilterMapper mapper;
    private final FilterRepository repository;

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
        log.info("Product saved to database successfully");
    }
}
