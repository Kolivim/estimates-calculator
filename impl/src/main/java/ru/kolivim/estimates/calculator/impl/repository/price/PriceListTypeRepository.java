package ru.kolivim.estimates.calculator.impl.repository.price;

import org.springframework.data.jpa.repository.Query;
import ru.kolivim.estimates.calculator.domain.price.PriceList;
import ru.kolivim.estimates.calculator.domain.price.PriceListType;
import ru.kolivim.estimates.calculator.impl.repository.base.BaseRepository;

public interface PriceListTypeRepository extends BaseRepository<PriceListType> {

    Boolean existsByName(String name);

    @Query(value = "SELECT MAX(type) FROM type_price_list", nativeQuery = true)
    Integer lastTypePriceList();
}
