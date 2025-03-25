package org.kammerer;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RelocationRequestRepository {
    Optional<RelocationRequest> findById(int id);

    RelocationRequest save(RelocationRequest request);
}
