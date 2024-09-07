package ru.kolivim.estimates.calculator.impl.repository.element;

import jakarta.persistence.Column;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.kolivim.estimates.calculator.domain.estimate.Element;
import ru.kolivim.estimates.calculator.impl.repository.base.BaseRepository;

import java.util.UUID;

public interface ElementRepository extends BaseRepository<Element> {

    Boolean existsByWorksIdAndRevision(UUID worksId, String revision);
    Boolean existsByWorksId(UUID worksId);
    Boolean existsByGroup(Integer group);

    @Query(value = "SELECT MAX(version) FROM elements where works_id = :find_works_id and revision = :find_revision", nativeQuery = true)
    Integer lastWorkVersion(@Param("find_works_id") UUID worksId, @Param("find_revision") String revision);
    // @Query(value = "SELECT MAX(version) FROM elements where works_id = :find_works_id and revision = : find_revision"
}
/*
  "worksId": "93a747a4-a7df-4056-8354-5e4327460007",
  "revision": "2",
* */