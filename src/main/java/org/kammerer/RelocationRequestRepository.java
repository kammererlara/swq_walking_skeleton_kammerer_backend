package org.kammerer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelocationRequestRepository extends JpaRepository<RelocationRequest, Integer> {}
