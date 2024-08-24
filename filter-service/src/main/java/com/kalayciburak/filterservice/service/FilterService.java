package com.kalayciburak.filterservice.service;

import com.kalayciburak.filterservice.repository.FilterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FilterService {
    private final FilterRepository repository;
}
