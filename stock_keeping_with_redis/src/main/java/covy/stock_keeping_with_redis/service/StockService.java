package covy.stock_keeping_with_redis.service;

import covy.stock_keeping_with_redis.entity.Stock;
import covy.stock_keeping_with_redis.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    /**
     * 재고 감소 로직
     *
     * @param id
     * @param quantity
     */
    @Transactional
    public void decrease(Long id, Long quantity) {
        // stock 조회
        Optional<Stock> stock = stockRepository.findById(id);

        // 재고 감소
        stock.orElseThrow(() -> new RuntimeException("해당하는 재고가 없습니다."))
                .decrease(quantity);

        // 갱신된 값을 저장
        stockRepository.save(stock.get());
    }

}
