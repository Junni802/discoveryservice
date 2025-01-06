package covy.stock_keeping_with_redis.repository;

import covy.stock_keeping_with_redis.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
