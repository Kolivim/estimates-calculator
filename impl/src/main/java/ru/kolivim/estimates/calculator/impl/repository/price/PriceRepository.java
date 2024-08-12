package ru.kolivim.estimates.calculator.impl.repository.price;

import ru.kolivim.estimates.calculator.domain.price.Price;
import ru.kolivim.estimates.calculator.domain.user.User;
import ru.kolivim.estimates.calculator.impl.repository.base.BaseRepository;

import java.util.Optional;
import java.util.UUID;

public interface PriceRepository extends BaseRepository<Price> {

    Optional<Price> findById(UUID id);

    Boolean existsByNameAndPriceListId(String name, UUID priceListId);

}
