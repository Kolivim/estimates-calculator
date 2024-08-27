package ru.kolivim.estimates.calculator.impl.repository.price;

import ru.kolivim.estimates.calculator.domain.price.PriceList;
import ru.kolivim.estimates.calculator.impl.repository.base.BaseRepository;

import java.util.UUID;

public interface PriceListRepository extends BaseRepository<PriceList> {

    Boolean existsByName(String name);

    boolean existsById(UUID id);

}
