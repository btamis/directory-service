package com.btamis.directoryservice.unit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface UnitRepository extends JpaRepository<Unit, UUID> {
    // Example custom query
    // List<Unit> findByParentId(UUID parentId);
}