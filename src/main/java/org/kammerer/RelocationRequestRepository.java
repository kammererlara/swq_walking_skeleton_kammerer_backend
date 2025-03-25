package org.kammerer;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RelocationRequestRepository {
    public Optional<RelocationRequest> findById(int id) {
        return Optional.empty();
    }
}
