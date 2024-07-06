package ru.kolivim.myproject.task.management.system.impl.repository.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import ru.kolivim.myproject.task.management.system.domain.base.BaseEntity;

import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface BaseRepository<Entity extends BaseEntity> extends JpaRepository<Entity, UUID>, JpaSpecificationExecutor<Entity> {


    void deleteById(UUID uuid);

    void deleteAllById(Iterable<? extends UUID> uuids);

    Optional<Entity> findById(UUID uuid);

    void hardDeletedById(UUID uuid);

}
