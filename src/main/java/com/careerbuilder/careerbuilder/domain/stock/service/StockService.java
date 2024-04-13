package com.careerbuilder.careerbuilder.domain.stock.service;

import com.careerbuilder.careerbuilder.domain.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;
}
