package com.careerbuilder.careerbuilder.domain.stock.repository;

import com.careerbuilder.careerbuilder.domain.stock.dto.StockQuantityIfs;
import com.careerbuilder.careerbuilder.domain.stock.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    // select * from stock where locationId = ? and productId;
    Stock findFirstByLocationIdAndProductIdOrderById(Long locationId, Long productId);

    // select * from stock where locationId = ?;
    List<Stock> findAllByLocationIdOrderById(Long locationId);

    @Query(
            value = "select " +
                    "s.locationId as locationId" +
                    ", sum(s.stockQuantity) as totalQuantity " +
                    "from Stock s " +
                    "group by s.locationId"
    )
    List<StockQuantityIfs> findStockTotalQuantityGroupByLocation();


    @Modifying
    @Query("update Stock s set s.stockQuantity = :quantity where s.locationId = :locationId and s.productId = :productId")
    int updateStockQuantityByLocationIdAnAndProductId(@Param("locationId") Long locationId, @Param("productId") Long productId, @Param("quantity") int quantity);

    @Modifying
    @Query("update Stock s set s.stockQuantity = s.stockQuantity + :quantity where s.locationId = :locationId and s.productId = :productId")
    int updateStockQuantityIncreaseByLocationIdAnAndProductId(@Param("locationId") Long locationId, @Param("productId") Long productId, @Param("quantity") int quantity);

    @Modifying
    @Query("update Stock s set s.stockQuantity = s.stockQuantity - :quantity where s.locationId = :locationId and s.productId = :productId")
    int updateStockQuantityDecreaseByLocationIdAnAndProductId(@Param("locationId") Long locationId, @Param("productId") Long productId, @Param("quantity") int quantity);
}
